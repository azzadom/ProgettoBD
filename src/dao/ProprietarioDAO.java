package dao;

import exception.DAOException;
import dao.procedure.GenericProcedureDAO;
import dao.procedure.proprietario.*;
import model.Cliente;
import model.Esercizio;
import model.Macchinario;
import model.PersonalTrainer;

import java.util.List;

public class ProprietarioDAO {

    public void inserisciCliente(Cliente cliente) throws DAOException {
        GenericProcedureDAO<Void> procedure = new InserisciClienteProcedureDAO();
        procedure.execute(cliente);
    }

    public void inserisciPersonalTrainer(PersonalTrainer personalTrainer) throws DAOException {
        GenericProcedureDAO<Void> procedure = new InserisciPersonalProcedureDAO();
        procedure.execute(personalTrainer);
    }

    public void inserisciMacchinario(Macchinario macchinario) throws DAOException {
        GenericProcedureDAO<Void> procedure = new InserisciMacchinarioProcedureDAO();
        procedure.execute(macchinario);
    }

    public void inserisciEsercizio(Esercizio esercizio) throws DAOException {
        GenericProcedureDAO<Void> procedure = new InserisciEsercizioProcedureDAO();
        procedure.execute(esercizio);
    }

    public List<Macchinario> listaMacchinari() throws DAOException {
        GenericProcedureDAO<List<Macchinario>> procedure = new ListaMacchinariProcedureDAO();
        return procedure.execute();
    }

    public List<PersonalTrainer> listaPersonalTrainer() throws DAOException {
        GenericProcedureDAO<List<PersonalTrainer>> procedure = new ListaPersonalTrainerDAO();
        return procedure.execute();
    }

}
