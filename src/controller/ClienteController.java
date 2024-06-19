package controller;

import dao.ConnectionFactory;
import dao.cliente.*;
import model.*;
import view.ClienteView;
import exception.DAOException;

import java.sql.Date;
import java.sql.Time;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ClienteController implements Controller{

    private final ClienteView view;
    private final String CF;

    public ClienteController(String CF) {
        this.view = new ClienteView();
        this.CF = CF;
    }

    public void start() {
        try {
            ConnectionFactory.changeRole(Ruolo.CLIENTE);
        } catch(DAOException e) {
            throw new RuntimeException(e);
        }
        while(true) {
            int choice;
            choice = view.showMenu();

            switch(choice) {
                case 1 -> schedaCorrente();
                case 2 -> effettuaAllenamento();
                case 3 -> listaSchedeArchiviate();
                case 4 -> System.exit(0);
                default -> throw new RuntimeException("Scelta non valida");
            }
        }
    }

    private void schedaCorrente() {

        SchedaAllenamento scheda;

        try {
            scheda = new SchedaAllenamentoProcedureDAO().execute(CF);

            if (scheda.getEsercizi().isEmpty()) {
                view.showMessage("Non ci sono esercizi nella scheda!");
                return;
            }

            view.showMessage(scheda.toString());
        } catch(DAOException e) {
            view.showError(e.getMessage());
        }
    }

    private void effettuaAllenamento() {
        Sessione sessione;
        SchedaAllenamento scheda;
        Date data;
        Time oraInizio;
        Time oraFine;
        int numEsercizi;

        Integer numTotSerie = 0;
        Integer numSerieSvolte = 0;
        int percentuale;

        try {
            scheda = new SchedaAllenamentoProcedureDAO().execute(CF);
        } catch (DAOException e) {
            view.showError(e.getMessage());
            return;
        }

        if (scheda.getEsercizi().isEmpty()) {
            view.showMessage("Non ci sono esercizi nella scheda!");
            return;
        }

        numEsercizi = scheda.getEsercizi().size();

        view.showMessage("***************** Sessione di allenamento - Scheda "+ scheda.getId() +" *****************");
        data = new Date(System.currentTimeMillis());
        oraInizio = new Time(System.currentTimeMillis());
        view.showMessage("Data: "+ data + " | Ora inizio: "+ oraInizio);
        for (int i = 0; i< numEsercizi; i++) {
            Esercizio esercizio = scheda.getEsercizio(i);
            Integer serie = esercizio.getSerie();
            numTotSerie += serie;
            view.showMessage(esercizio.toString());
            numSerieSvolte += view.effettuaEsercizio(serie);

        }
        oraFine = new Time(System.currentTimeMillis());
        view.showMessage("Ora fine: "+ oraFine);
        percentuale = (numSerieSvolte * 100) / numTotSerie;
        sessione = new Sessione(CF, data, oraInizio, oraFine, scheda.getId(), percentuale);
        try {
            new RegistraSessioneProcedureDAO().execute(sessione);
            view.showMessage("Sessione registrata correttamente!");
        } catch (DAOException e) {
            view.showError(e.getMessage());
        }
    }

    private void listaSchedeArchiviate() {

        SchedaAllenamento scheda;
        List<SchedaArchiviata> lista;
        LocalDate dataInizio;
        LocalDate dataFine;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String[] data = view.listaArchiviate();

        try {
            dataInizio = LocalDate.parse(data[0], formatter);
        }catch (DateTimeException e) {
            view.showError("Errore nella conversione della data di inizio");
            return;
        }
        try {
            dataFine = LocalDate.parse(data[1], formatter);
        }catch (DateTimeException e) {
            view.showError("Errore nella conversione della data di fine");
            return;
        }

        try {
            lista  = new ListaArchiviateProcedureDAO().execute(CF, dataInizio, dataFine);
            if (lista.isEmpty()) {
                view.showMessage("Non ci sono schede archiviate!");
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("******************* Schede archiviate dal " + dataInizio + " al " + dataFine + " *******************\n");
            for(SchedaArchiviata schedaArchiviata : lista) {
                sb.append(schedaArchiviata);
            }

            view.showMessage(sb.toString());

            Integer idScheda = view.schedaArchiviata();
            scheda = new SchedaArchiviataProcedureDAO().execute(CF, idScheda);
            view.showMessage(scheda.toString());

        } catch(DAOException | DateTimeException e) {
            view.showError(e.getMessage());
        }
    }

}
