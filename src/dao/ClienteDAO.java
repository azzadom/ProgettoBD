package dao;

import dao.procedure.GenericProcedureDAO;
import dao.procedure.cliente.*;
import exception.DAOException;
import model.SchedaAllenamento;
import model.SchedaArchiviata;
import model.Sessione;

import java.time.LocalDate;
import java.util.List;

public class ClienteDAO {

    public List<SchedaArchiviata> listaArchiviate(String CF, LocalDate dataInizio, LocalDate dataFine) throws DAOException {
        GenericProcedureDAO<List<SchedaArchiviata>> procedure = new ListaArchiviateProcedureDAO();
        GenericProcedureDAO<SchedaArchiviata> schedaProcedure = new SchedaArchiviataProcedureDAO();
        List<SchedaArchiviata> schede = procedure.execute(CF, dataInizio, dataFine);
        for(int i = 0; i < schede.size(); i++) {
            schede.set(i, schedaProcedure.execute(CF, schede.get(i).getId(), schede.get(i).getDataInizio(), schede.get(i).getDataFine()));
        }
        return schede;
    }

    public void registraSessione(Sessione sessione) throws DAOException {
        GenericProcedureDAO<Void> procedure = new RegistraSessioneProcedureDAO();
        procedure.execute(sessione);
    }

    public SchedaAllenamento schedaCorrente(String CF) throws DAOException {
        GenericProcedureDAO<SchedaAllenamento> procedure = new SchedaAllenamentoProcedureDAO();
        return procedure.execute(CF);
    }



}
