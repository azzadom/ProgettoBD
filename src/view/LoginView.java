package view;

import java.util.Scanner;

public class LoginView extends AbstractView{

    public int showMenu(){
        printMenu("MENU GENERALE", "Login", "Cambia password", "Esci");
        return getInputMenu(3);
    }

    public String[] authenticate() {
        Scanner input = new Scanner(System.in);
        String[] credenziali = new String[2];
        getInput(input, credenziali,0,"username: ");
        getInput(input, credenziali,1, "password: ");
        return credenziali;
    }

    public String updatePassword() {
        String[] password = new String[1];
        getInput(new Scanner(System.in), password, 0, "Inserire la nuova password: ");
        return password[0];
    }

}
