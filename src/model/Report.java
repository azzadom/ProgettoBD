package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Report {

    private final String cliente;

    private String nome;

    private String cognome;

    private Integer numeroSessioni;

    private final LocalDate dataInizio;
    private final LocalDate dataFine;
    private final List<Sessione> sessioni = new ArrayList<>();

    public Report(String cliente, LocalDate dataInizio, LocalDate dataFine) {
        this.cliente = cliente;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        numeroSessioni = 0;
    }


    public void addSessione(Sessione sessione) {
        this.sessioni.add(sessione);
        numeroSessioni++;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public Integer getNumeroSessioni() {
        return numeroSessioni;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("********** Report di " + getNome() + " " + getCognome() + " - " + getCliente() + " **********\n");
        sb.append("Nel periodo tra " + getDataInizio() + " e " + getDataFine() + " ha svolto "+ sessioni.size() + " sessioni:\n");
        for(Sessione sessione : sessioni) {
            sb.append(sessione);
        }
        return sb.toString();
    }

}
