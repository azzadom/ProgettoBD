package controller;

import dao.login.UpdatePasswordProcedureDAO;
import exception.DAOException;
import dao.login.LoginProcedureDAO;
import model.Credenziali;
import view.LoginView;

public class LoginController implements Controller{

    private Credenziali cred;
    private final LoginView view;

    public LoginController() {
        view = new LoginView();
    }

    public void start() {
        int choice;
        choice = view.showMenu();

        switch(choice) {
            case 1 -> login();
            case 2 -> System.exit(0);
            default -> throw new RuntimeException("Scelta non valida");
        }
    }

    public void login() {
        String[] credenziali;

        while (true) {
            credenziali = view.authenticate();
            try {
                cred = new LoginProcedureDAO().execute(credenziali[0], credenziali[1]);
            } catch (DAOException e) {
                throw new RuntimeException(e);
            }

            if (cred.getRuolo() != null) {
                break;
            } else if (cred.getRuolo() == null) {
                System.out.println("Credenziali errate, riprovare.");
            }
        }

        if(cred.getPassword().equals("Nuova")){
            updatePassword();
        }
    }

    private void updatePassword(){
        cred = new Credenziali(cred.getUsername(), view.updatePassword(), cred.getRuolo(), cred.getMatricola());

        try {
            new UpdatePasswordProcedureDAO().execute(cred.getUsername(), cred.getPassword());
        } catch(DAOException e) {
            throw new RuntimeException(e);
        }
    }

    public Credenziali getCred() {
        return cred;
    }
}

