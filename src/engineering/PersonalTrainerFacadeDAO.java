package engineering;

import dao.*;
import exception.DAOException;
import model.Cliente;
import model.Esercizio;
import model.Report;
import model.SchedaEsercizi;

import java.time.LocalDate;
import java.util.List;

public class PersonalTrainerFacadeDAO {

    private final SchedaEserciziDAO schedaEserciziDAO;
    private final EsercizioDAO esercizioDAO;
    private final ClienteDAO clienteDAO;
    private final ReportDAO reportDAO;

    public PersonalTrainerFacadeDAO() {
        schedaEserciziDAO = new SchedaEserciziDAO();
        esercizioDAO = new EsercizioDAO();
        clienteDAO = new ClienteDAO();
        reportDAO = new ReportDAO();
    }

    public void aggiungiScheda(String CF, SchedaEsercizi scheda) throws DAOException {
        schedaEserciziDAO.addSchedaEsercizi(CF, scheda);
    }

    public List<Esercizio> listaEsercizi() throws DAOException {
        return esercizioDAO.getListaEsercizi();
    }

    public List<Cliente> listaClienti(String CF) throws DAOException {
        return clienteDAO.getListaClienti(CF);
    }

    public Report visualizzaReport(String CF, String CFCliente, LocalDate dataInizio, LocalDate dataFine) throws DAOException {
        return reportDAO.getReport(CF, CFCliente, dataInizio, dataFine);
    }
}
