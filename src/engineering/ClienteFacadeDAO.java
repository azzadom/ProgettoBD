package engineering;


import dao.SchedaAllenamentoDAO;
import dao.SchedaArchiviataDAO;
import dao.SessioneDAO;
import exception.DAOException;
import model.SchedaAllenamento;
import model.SchedaArchiviata;
import model.Sessione;

import java.time.LocalDate;
import java.util.List;

public class ClienteFacadeDAO {

    private final SchedaArchiviataDAO schedaArchiviataDAO;
    private final SessioneDAO sessioneDAO;
    private final SchedaAllenamentoDAO schedaAllenamentoDAO;

    public ClienteFacadeDAO() {
        schedaArchiviataDAO = new SchedaArchiviataDAO();
        sessioneDAO = new SessioneDAO();
        schedaAllenamentoDAO = new SchedaAllenamentoDAO();
    }

    public List<SchedaArchiviata> listaArchiviate(String CF, LocalDate dataInizio, LocalDate dataFine) throws DAOException {
        return schedaArchiviataDAO.getListaArchiavate(CF, dataInizio, dataFine);
    }

    public void registraSessione(Sessione sessione) throws DAOException {
        sessioneDAO.addSessione(sessione);
    }

    public SchedaAllenamento schedaCorrente(String CF) throws DAOException {
        return schedaAllenamentoDAO.getSchedaAllenamento(CF);
    }

    public void schedaArchiviataEsercizi(String CF, SchedaArchiviata scheda) throws DAOException {
        schedaArchiviataDAO.loadSchedaArchiviata(CF, scheda);
    }



}
