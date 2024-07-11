package view;

import model.Esercizio;
import model.Report;
import model.SchedaEsercizi;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PersonalTrainerView extends AbstractView{
    public int showMenu() {
        printMenu("MENU PERSONAL TRAINER", "Inserisci una nuova scheda per un cliente", "Visualizza il report di un tuo cliente", "Lista clienti", "Lista esercizi", "Esci");
        return getInputMenu(5);
    }

    public String[] nuovaSchedaDati(){
        Scanner input = new Scanner(System.in);
        String[] data = new String[2];
        getInput(input, data, 0, "Inserire il CF del cliente: ");
        getInput(input, data, 1, "Inserire la quantità di esercizi: ");
        return data;
    }

    public List<String[]> nuovaSchedaEsercizi(Integer quantità){
        Scanner input = new Scanner(System.in);

        List<String[]> esercizi = new ArrayList<>();

        for(int i = 0; i < quantità; i++) {
            String[] data = new String[3];
            getInput(input, data, 0, "Inserire il nome dell'esercizio: ");
            getInput(input, data, 1, "Inserire il numero di serie: ");
            getInput(input, data, 2, "Inserire il numero di ripetizioni: ");
            esercizi.add(data);
        }

        return esercizi;
    }

    public String[] reportSessioni() throws DateTimeException {

        String[] data = new String[3];
        Scanner input = new Scanner(System.in);

        getInput(input, data, 0, "Inserire il CF del cliente: ");
        getInput(input, data, 1, "Inserire la data di inizio (dd-mm-yyyy): ");
        getInput(input, data, 2, "Inserire la data di fine (dd-mm-yyyy): ");

        return data;
    }

}

