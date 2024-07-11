package controller;

import engineering.LoginFacadeDAO;
import exception.DAOException;
import model.Credenziali;
import view.LoginView;

public class LoginController implements Controller{

    private Credenziali cred;
    private final LoginView view;
    private final LoginFacadeDAO dao;

    private boolean newPassword;

    public LoginController() {
        view = new LoginView();
        newPassword = false;
        dao = new LoginFacadeDAO();
    }

    public void start() {
        while(true) {
            int choice;
            choice = view.showMenu();

            switch (choice) {
                case 1 -> {
                    login();
                    return;
                }
                case 2 -> {
                    newPassword = true;
                    login();
                }
                case 3 -> System.exit(0);
                default -> throw new RuntimeException("Scelta non valida");
            }
        }
    }

    private void login() {
        String[] credenziali;

        while (true) {
            credenziali = view.authenticate();
            try {
                cred = dao.login(credenziali[0], credenziali[1]);
            } catch (DAOException e) {
                view.showError(e.getMessage());
            }

            if (cred.getRuolo() != null) {
                break;
            } else if (cred.getRuolo() == null) {
                view.showError("Credenziali errate, riprovare.");
            }
        }

        if(cred.getPassword().equals("Nuova") || newPassword){
            updatePassword();
        }
    }

    private void updatePassword(){
        String password;
        while(true) {
            try {
                password = view.updatePassword();
                dao.updatePassword(cred.getUsername(), password);
                cred = new Credenziali(cred.getUsername(), password, cred.getRuolo(), cred.getMatricola());
                break;
            } catch (DAOException e) {
                view.showError(e.getMessage());
            } finally {
                newPassword = false;
            }
        }
    }

    public Credenziali getCred() {
        return cred;
    }
}

