package model;

import java.util.ArrayList;
import java.util.List;

public class SchedaAllenamento {

    private Integer id;
    private List<Esercizio> esercizi = new ArrayList<>();

    public SchedaAllenamento(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public List<Esercizio> getEsercizi() {
        return esercizi;
    }

    public Esercizio getEsercizio(int index) {
        return esercizi.get(index);
    }

    public void addEsercizio(Esercizio esercizio) {
        esercizi.add(esercizio);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("****************************** Scheda "+ this.id +" ******************************\n");
        for(Esercizio esercizio : esercizi) {
            sb.append(esercizio).append('\n');
        }
        return sb.toString();
    }

}
