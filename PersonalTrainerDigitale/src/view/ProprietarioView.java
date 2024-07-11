package view;

import model.Cliente;
import model.Esercizio;
import model.Macchinario;
import model.PersonalTrainer;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProprietarioView extends AbstractView{

    public int showMenu() {
        printMenu("MENU PROPRIETARIO", "Inserisci esercizio", "Inserisci macchinario",
                "Lista macchinari", "Inserisci cliente", "Inserisci personal trainer", "Lista personal trainer", "Esci");
        return getInputMenu(7);
    }

    public String[] inserisciMacchinario() {
        String[] macchinario = new String[2];
        Scanner input = new Scanner(System.in);
        getInput(input, macchinario,0,"Inserire nome macchinario: ");
        getInput(input, macchinario,1,"Inserire quantità da aggiungere: ");
        return macchinario;
    }

    public String[] inserisciEsercizio() {

        String[] esercizio = new String[2];

        while(true){
            Scanner input = new Scanner(System.in);
            getInput(input, esercizio,0,"Inserire nome esercizio: ");
            showMessage("Utilizza un macchinario? (si/no)");
            String risposta = input.nextLine();
            if(risposta.equals("no")) {
                esercizio[1] = null;
                return esercizio;
            } else if(risposta.equals("si")) {
                getInput(input, esercizio,1,"Inserire nome macchinario: ");
                return esercizio;
            } else {
                showMessage("Risposta non valida");
            }
        }
    }

    public String[] inserisciCliente() {
        Scanner input = new Scanner(System.in);
        String[] cliente = new String[5];

        getInput(input, cliente,0,"Inserire codice fiscale: ");
        getInput(input, cliente,1,"Inserire nome: ");
        getInput(input, cliente,2,"Inserire cognome: ");
        getInput(input, cliente,3,"Inserire codice fiscale del personal trainer che lo gestirà: ");
        getInput(input, cliente,4,"Inserire username: ");

        return cliente;
    }

    public String[] inserisciPersonal() {
        Scanner input = new Scanner(System.in);
        String[] personal = new String[4];

        getInput(input, personal,0,"Inserire codice fiscale: ");
        getInput(input, personal,1,"Inserire nome: ");
        getInput(input, personal,2,"Inserire cognome: ");
        getInput(input, personal,3,"Inserire username: ");

        return personal;
    }

}

