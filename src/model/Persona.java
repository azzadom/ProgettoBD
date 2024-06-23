package model;

public class Persona {
    String CF;
    String nome;
    String cognome;
    String username;

    public Persona(String CF, String nome, String cognome){
        this.CF = CF;
        this.nome = nome;
        this.cognome = cognome;
    }

    public Persona(String CF, String nome, String cognome, String username){
        this.CF = CF;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
    }

    public String getCF(){
        return CF;
    }

    public String getNome(){
        return nome;
    }

    public String getCognome(){
        return cognome;
    }

    public String getUsername(){
        return username;
    }

    @Override
    public String toString(){
        return "CF: " + CF + " | Nome: " + nome + " | Cognome: " + cognome;
    }
}
