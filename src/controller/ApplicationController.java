package controller;
import model.Credenziali;

public class ApplicationController implements Controller {

    @Override
    public void start() {

        LoginController loginController = new LoginController();

        loginController.start();
        Credenziali cred = loginController.getCred();

        switch(cred.getRuolo()) {
            case CLIENTE:
                ClienteController cliente = new ClienteController(cred.getMatricola());
                cliente.start();
            case PERSONALTRAINER:
                PersonalTrainerController personalTrainer = new PersonalTrainerController(cred.getMatricola());
                personalTrainer.start();
            case PROPRIETARIO:
                new ProprietarioController().start();
            default:
                throw new RuntimeException("Errore nelle credenziali.");
        }
    }
}
