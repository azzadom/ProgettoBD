package model;

import java.sql.Date;

public class SchedaArchiviata extends SchedaAllenamento{

    private Date dataInizio;

    private Date dataFine;

    public SchedaArchiviata(Integer id, Date dataInizio, Date dataFine) {
        super(id);
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public String toStringInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Scheda " + this.getId()).append(" - ").append("Data Inizio " + this.dataInizio).append(" - ").append("Data Fine " + this.dataFine).append("\n");
        return sb.toString();
    }


}
