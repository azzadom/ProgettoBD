package model;

public class PersonalTrainer extends Persona{

    Integer numClienti;

    public PersonalTrainer(String CF, String nome, String cognome, Integer numClienti){
        super(CF, nome, cognome);
        this.numClienti = numClienti;
    }

    public PersonalTrainer(String CF, String nome, String cognome, String username){
        super(CF, nome, cognome, username);
    }

    @Override
    public String toString(){
        return super.toString() + " | Clienti: " + this.numClienti + "\n";
    }
}
