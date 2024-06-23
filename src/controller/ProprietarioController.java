package controller;

import dao.ConnectionFactory;
import dao.ProprietarioDAO;
import exception.DAOException;
import model.*;
import view.ProprietarioView;

import java.util.List;

public class ProprietarioController implements Controller{

    private final ProprietarioView view;
    private final ProprietarioDAO dao;

    public ProprietarioController() {
        view = new ProprietarioView();
        dao = new ProprietarioDAO();
    }

    @Override
    public void start() {
        try {
            ConnectionFactory.changeRole(Ruolo.PROPRIETARIO);
        } catch(DAOException e) {
            throw new RuntimeException(e);
        }
        while(true) {
            int choice;
            choice = view.showMenu();

            switch(choice) {
                case 1 -> inserisciEsercizio();
                case 2 -> inserisciMacchinario();
                case 3 -> listaMacchinari();
                case 4 -> inserisciCliente();
                case 5 -> inserisciPersonalTrainer();
                case 6 -> listaPersonalTrainer();
                case 7 -> System.exit(0);
                default -> throw new RuntimeException("Scelta non valida");
            }
        }
    }

    public void inserisciEsercizio() {

        Esercizio esercizio;
        String[] data;

        data = view.inserisciEsercizio();
        esercizio = new Esercizio(data[0], data[1]);


        try {
           dao.inserisciEsercizio(esercizio);
            view.showMessage("Inserimento eseguito correttamente!");
        } catch(DAOException e) {
            view.showError(e.getMessage());
        }
    }

    public void inserisciMacchinario() {
        Macchinario macchinario;
        String[] data;

        data = view.inserisciMacchinario();
        try{
            macchinario = new Macchinario(data[0], Integer.parseInt(data[1]));
        } catch(NumberFormatException e) {
            view.showError("Quantità non valida");
            return;
        }

        try {
            dao.inserisciMacchinario(macchinario);
            view.showMessage("Inserimento eseguito correttamente!");
        } catch(DAOException e) {
            view.showError(e.getMessage());
        }
    }

    public void listaMacchinari() {
        List<Macchinario> listaMacchinari;

        try {
            listaMacchinari = dao.listaMacchinari();

            if (listaMacchinari.isEmpty()){
                view.showMessage("Nessun macchinario presente nel database.");
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("******************* Lista Macchinari *******************\n");
            for(Macchinario m : listaMacchinari) {
                sb.append(m).append("\n");
            }

            view.showMessage(sb.toString());
        } catch(DAOException e) {
            view.showError(e.getMessage());
        }
    }

    public void listaPersonalTrainer() {
        List<PersonalTrainer> listaPT;

        try {
            listaPT = dao.listaPersonalTrainer();

            if (listaPT.isEmpty()){
                view.showMessage("Nessun personal trainer presente nel database.");
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("******************* Lista Personal Trainer *******************\n");
            for(PersonalTrainer pt : listaPT) {
                sb.append(pt.toString()).append("\n");
            }

            view.showMessage(sb.toString());
        } catch(DAOException e) {
            view.showError(e.getMessage());
        }
    }

    public void inserisciCliente() {
        Cliente cliente;
        String[] data;

        data = view.inserisciCliente();
        cliente = new Cliente(data[0], data[1], data[2], data[3], data[4]);

        try {
            dao.inserisciCliente(cliente);
            view.showMessage("Inserimento eseguito correttamente!");
        } catch(DAOException e) {
            view.showError(e.getMessage());
        }
    }

    public void inserisciPersonalTrainer() {
        PersonalTrainer personalTrainer;
        String[] data;

        data = view.inserisciPersonal();
        personalTrainer = new PersonalTrainer(data[0], data[1], data[2], data[3]);

        try {
            dao.inserisciPersonalTrainer(personalTrainer);
            view.showMessage("Inserimento eseguito correttamente!");
        } catch(DAOException e) {
            view.showError(e.getMessage());
        }
    }



}

