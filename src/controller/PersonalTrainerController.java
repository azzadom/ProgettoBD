package controller;

import dao.ConnectionSingleton;
import engineering.PersonalTrainerFacadeDAO;
import model.*;
import view.PersonalTrainerView;
import exception.DAOException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PersonalTrainerController implements Controller{

    private final String CF;
    private final PersonalTrainerView view;
    private final PersonalTrainerFacadeDAO dao;

    public PersonalTrainerController(String CF) {
        this.view = new PersonalTrainerView();
        this.CF = CF;
        this.dao = new PersonalTrainerFacadeDAO();
    }

    public void start() {
        try {
            ConnectionSingleton.changeRole(Ruolo.PERSONALTRAINER);
        } catch(DAOException e) {
            throw new RuntimeException(e);
        }
        while(true) {
            int choice;
            choice = view.showMenu();

            switch(choice) {
                case 1 -> inserisciScheda();
                case 2 -> reportSessioni();
                case 3 -> listaClienti();
                case 4 -> listaEsercizi();
                case 5 -> System.exit(0);
                default -> throw new RuntimeException("Scelta non valida");
            }
        }
    }

    public void inserisciScheda() {
        SchedaEsercizi scheda;

        String[] dati = view.nuovaSchedaDati();
        try{
            scheda = new SchedaEsercizi(dati[0], Integer.parseInt(dati[1]));
        } catch(NumberFormatException e) {
            view.showError("Errore nella conversione del numero di esercizi.");
            return;
        }

        List<String[]> esercizi = view.nuovaSchedaEsercizi(scheda.getNumeroEsercizi());
        for(String[] esercizio : esercizi) {
            try {
                scheda.addEsercizio(new Esercizio(esercizio[0], Integer.parseInt(esercizio[1]), Integer.parseInt(esercizio[2])));
            } catch(NumberFormatException e) {
                view.showError("Errore nella conversione del numero di serie o ripetizioni.");
                return;
            }
        }

        try {
            dao.aggiungiScheda(this.CF, scheda);
            view.showMessage("Inserimento eseguito correttamente!");
        } catch(DAOException e) {
            view.showError(e.getMessage());
        }
    }

    public void reportSessioni() {

        Report report;

        String[] dati;
        String cliente;
        LocalDate dataInizio;
        LocalDate dataFine;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            dati = view.reportSessioni();
            cliente = dati[0];
            dataInizio = LocalDate.parse(dati[1], formatter);
            dataFine = LocalDate.parse(dati[2], formatter);
        } catch (DateTimeException e) {
            view.showError("Errore nella conversione delle date.");
            return;
        }


        try {
            report = dao.visualizzaReport(this.CF, cliente, dataInizio, dataFine);
            if (report.getNumeroSessioni() == 0) {
                view.showMessage("Non ci sono sessioni di allenamento registrate per il cliente nel periodo specificato.");
                return;
            }
            view.showMessage(report.toString());
        } catch (DateTimeException | DAOException e) {
            view.showError(e.getMessage());
        }

    }

    public void listaClienti() {

        List<Cliente> listaClienti;

        try {
            listaClienti = dao.listaClienti(this.CF);

            if (listaClienti.isEmpty()){
                view.showMessage("Non hai clienti registrati. Contattare il proprietario");
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("******************* Lista Clienti *******************\n");
            for(Cliente cliente : listaClienti) {
                sb.append(cliente.toString()).append("\n");
            }

            view.showMessage(sb.toString());
        } catch(DAOException e) {
            view.showError(e.getMessage());
        }
    }

    public void listaEsercizi() {

        List<Esercizio> listaEsercizi;

        try {
            listaEsercizi = dao.listaEsercizi();

            if (listaEsercizi.isEmpty()){
                view.showMessage("Non ci sono esercizi registrati. Contattare il proprietario");
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("******************* Lista Esercizi *******************\n");
            for(Esercizio e : listaEsercizi) {
                sb.append(e.toString_short()).append("\n");
            }

            view.showMessage(sb.toString());
        } catch(DAOException e) {
            view.showError(e.getMessage());
        }
    }

}