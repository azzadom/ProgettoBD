package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class AbstractView {

    private static final String SEPARATOR = "****************************************";

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";


    protected void printSeparator() {
        System.out.println(SEPARATOR);
    }

    public abstract int showMenu();

    public void printTitle(String title) {

        if (title == null) {
            throw new IllegalArgumentException("Title cannot be null");
        }

        int titleLength = title.length();
        int separatorLength = SEPARATOR.length();

        if(titleLength > separatorLength) {
            printSeparator();
            System.out.println(title);
            printSeparator();
            return;
        }

        int spaces = ((separatorLength - titleLength) / 2) - 1;
        int odd = (separatorLength - titleLength) % 2;

        String spacesString = " ".repeat(Math.max(0, spaces));

        if (odd == 0) {
            printSeparator();
            System.out.println("*" + spacesString + title + spacesString + "*");
            printSeparator();
        } else {
            printSeparator();
            System.out.println("*" + spacesString + title + spacesString + " *");
            printSeparator();
        }
    }

    public void showError(String message) {
        System.out.println(ANSI_RED + message + ANSI_RESET);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    protected void getInput(Scanner scanner, String[] data, Integer index, String message) {
        showMessage(message);
        data[index] = scanner.nextLine();
    }

    protected void printMenu(String title, String ... options) {
        printTitle(title);
        for(int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    protected Integer getInputMenu(Integer options){
        Scanner input = new Scanner(System.in);
        int choice;

        while (true) {
            try {
                showMessage("Inserire il numero della azione da svolgere: ");
                choice = input.nextInt();
                if (choice >= Math.min(1,options) && choice <= Math.max(1,options)) {
                    return choice;
                } else {
                    showMessage("Input non valido!");
                }
            } catch (InputMismatchException e) {
                showMessage("Input non valido!");
                input.next();
            }
        }
    }


}
