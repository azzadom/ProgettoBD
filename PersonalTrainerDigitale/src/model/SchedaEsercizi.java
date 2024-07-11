package model;

import java.util.ArrayList;
import java.util.List;

public class SchedaEsercizi {

    private String CF;

    private Integer numeroEsercizi;

    private List<Esercizio> esercizi = new ArrayList<>();

    public SchedaEsercizi(String idCliente, Integer numeroEsercizi) {
        this.CF = idCliente;
        this.numeroEsercizi = numeroEsercizi;
    }

    public String getMatricola() {
        return CF;
    }

    public Integer getNumeroEsercizi() {
        return numeroEsercizi;
    }

    public List<Esercizio> getEsercizi() {
        return esercizi;
    }

    public void addEsercizio(Esercizio esercizio) {
        this.esercizi.add(esercizio);
    }

}
