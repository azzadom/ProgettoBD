package model;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Report {

    private String cliente;

    private String nome;

    private String cognome;

    private LocalDate dataInizio;
    private LocalDate dataFine;
    private List<Sessione> sessioni = new ArrayList<>();

    public Report(String cliente, LocalDate dataInizio, LocalDate dataFine) {
        this.cliente = cliente;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }


    public void addSessione(Sessione sessione) {
        this.sessioni.add(sessione);
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public String getCliente() {
        return cliente;
    }

    public String setNome(String nome) {
        return this.nome = nome;
    }

    public String setCognome(String cognome) {
        return this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("********** Report di " + getNome() + " " + getCognome()  + " - "+ getCliente() + " **********\n");
        for(Sessione sessione : sessioni) {
            sb.append(sessione);
        }
        return sb.toString();
    }

}
