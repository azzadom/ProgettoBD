package dao;

import dao.procedure.GenericProcedureDAO;
import dao.procedure.personal.*;
import exception.DAOException;
import model.Cliente;
import model.Esercizio;
import model.Report;
import model.SchedaEsercizi;

import java.time.LocalDate;
import java.util.List;

public class PersonalTrainerDAO {

    public void aggiungiScheda(String CF, SchedaEsercizi scheda) throws DAOException {
        GenericProcedureDAO<Void> procedure = new AggiungiSchedaProcedureDAO();
        procedure.execute(CF, scheda);
    }

    public List<Esercizio> listaEsercizi() throws DAOException {
        GenericProcedureDAO<List<Esercizio>> procedure = new ListaEserciziProcedureDAO();
        return procedure.execute();
    }

    public List<Cliente> listaClienti(String CF) throws DAOException {
        GenericProcedureDAO<List<Cliente>> procedure = new ListaClientiProcedureDAO();
        return procedure.execute(CF);
    }

    public Report visualizzaReport(String CF, String CFCliente, LocalDate dataInizio, LocalDate dataFine) throws DAOException {
        GenericProcedureDAO<Report> procedure = new ReportProcedureDAO();
        return procedure.execute(CF, CFCliente, dataInizio, dataFine);
    }
}
