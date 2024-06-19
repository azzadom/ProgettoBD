package view;

import java.util.InputMismatchException;
import java.util.Scanner;

    public class ClienteView extends AbstractView {

        public int showMenu(){
            printMenu("MENU CLIENTE", "Visualizza scheda corrente", "Effettua allenamento", "Visualizza schede archiviate", "Esci");
            return getInputMenu(4);
        }

        public String[] listaArchiviate() {

            String[] data = new String[2];
            Scanner input = new Scanner(System.in);
            getInput(input, data,0, "Inserire la data di inizio (dd-mm-yyyy): ");
            getInput(input, data,1, "Inserire la data di fine (dd-mm-yyyy): ");

            return data;
        }

        public Integer schedaArchiviata() {
            Scanner input = new Scanner(System.in);
            String[] scelta = new String[1];
            while (true) {
                getInput(input, scelta,0, "Inserire l'id della scheda da visualizzare: ");
                try {
                    return Integer.parseInt(scelta[0]);
                } catch (NumberFormatException e) {
                    showMessage("Input non valido!");
                }
            }
        }

        public Integer effettuaEsercizio(Integer serie) {
            Scanner input = new Scanner(System.in);
            int serieEffettuate = 0;
            String[] scelta = new String[1];

            for (int i = 0; i < serie; i++) {
                showMessage("Serie in corso: " + (i + 1));
                while(true) {
                    try{
                        getInput(input, scelta,0,"Digitare 'c' per completare la serie, 's' per saltarla: ");
                        if (scelta[0].equals("c")) {
                            serieEffettuate += 1;
                            break;
                        } else if (scelta[0].equals("s")) {
                            break;
                        } else {
                            throw new InputMismatchException();
                        }
                    } catch (InputMismatchException e) {
                        showMessage("Input non valido!");
                    }
                }
            }
            return serieEffettuate;
        }
    }

