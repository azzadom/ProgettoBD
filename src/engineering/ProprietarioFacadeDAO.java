package engineering;

import dao.ClienteDAO;
import dao.EsercizioDAO;
import dao.MacchinarioDAO;
import dao.PersonalTrainerDAO;
import exception.DAOException;

import model.Cliente;
import model.Esercizio;
import model.Macchinario;
import model.PersonalTrainer;

import java.util.List;

public class ProprietarioFacadeDAO {

    private final MacchinarioDAO macchinarioDAO;
    private final PersonalTrainerDAO personalTrainerDAO;
    private final ClienteDAO clienteDAO;
    private final EsercizioDAO esercizioDAO;

    public ProprietarioFacadeDAO() {
        macchinarioDAO = new MacchinarioDAO();
        personalTrainerDAO = new PersonalTrainerDAO();
        clienteDAO = new ClienteDAO();
        esercizioDAO = new EsercizioDAO();
    }

    public void inserisciCliente(Cliente cliente) throws DAOException {
        clienteDAO.addCliente(cliente);
    }

    public void inserisciPersonalTrainer(PersonalTrainer personalTrainer) throws DAOException {
        personalTrainerDAO.addPersonalTrainer(personalTrainer);
    }

    public void inserisciMacchinario(Macchinario macchinario) throws DAOException {
        macchinarioDAO.addMacchinario(macchinario);
    }

    public void inserisciEsercizio(Esercizio esercizio) throws DAOException {
        esercizioDAO.addEsercizio(esercizio);
    }

    public List<Macchinario> listaMacchinari() throws DAOException {
        return macchinarioDAO.getListaMacchinari();
    }

    public List<PersonalTrainer> listaPersonalTrainer() throws DAOException {
        return personalTrainerDAO.getListaPersonalTrainer();
    }

}
