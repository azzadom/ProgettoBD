package model;

public class Cliente extends Persona {

    String PersonalTrainer;

    public Cliente(String CF, String nome, String cognome, String PersonalTrainer) {
        super(CF, nome, cognome);
        this.PersonalTrainer = PersonalTrainer;
    }

    public Cliente(String CF, String nome, String cognome, String PersonalTrainer, String username) {
        super(CF, nome, cognome, username);
        this.PersonalTrainer = PersonalTrainer;
    }

    public String getPersonalTrainer() {
        return PersonalTrainer;
    }

}
