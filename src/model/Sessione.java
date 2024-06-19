package model;

import java.sql.Date;
import java.sql.Time;

public class Sessione {

    private String Cliente;

    private Date Data;

    private Time OraInizio;

    private Time OraFine;

    private String Durata;

    private Integer Scheda;

    private Integer Percentuale;

    public Sessione(Date Data, Time Ora, String Durata, Integer Scheda, Integer Percentuale) {
        this.Data = Data;
        this.OraInizio = Ora;
        this.Durata = Durata;
        this.Scheda = Scheda;
        this.Percentuale = Percentuale;
    }

    public Sessione(String Cliente, Date Data, Time OraInizio, Time OraFine, Integer Scheda, Integer Percentuale) {
        this.Cliente = Cliente;
        this.Data = Data;
        this.OraInizio = OraInizio;
        this.OraFine = OraFine;
        this.Scheda = Scheda;
        this.Percentuale = Percentuale;
    }

    public String getCliente() {
        return Cliente;
    }

    public Date getData() {
        return Data;
    }

    public Time getOraInizio() {
        return OraInizio;
    }

    public Time getOraFine() {
        return OraFine;
    }

    public String getDurata() {
        return Durata;
    }

    public Integer getScheda() {
        return Scheda;
    }

    public Integer getPercentuale() {
        return Percentuale;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("- ").append("Data " + this.Data).append(" - ").append("Ora Inizio " + this.OraInizio).append(" - ").append("Durata " + this.Durata).append("\n")
                .append("  ").append("Scheda " + this.Scheda).append(" - ").append("Percentuale completamento " + this.Percentuale + "%").append("\n");
        return sb.toString();
    }

}
