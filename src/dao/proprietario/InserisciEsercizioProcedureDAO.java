package dao.proprietario;

import dao.ConnectionFactory;
import dao.GenericProcedureDAO;
import exception.DAOException;
import model.Esercizio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class InserisciEsercizioProcedureDAO implements GenericProcedureDAO {

    @Override
    public Void execute(Object... params) throws DAOException {

        Esercizio esercizio = (Esercizio) params[0];

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("call inserisci_esercizio(?,?)");
            cs.setString(1, esercizio.getNome());
            cs.setString(2, esercizio.getMacchinario().getNome());
            cs.executeQuery();
        } catch(SQLException e) {
            if (e.getErrorCode() == 1062)
                throw new DAOException("Esercizio già presente");
            if (e.getErrorCode() == 1452)
                throw new DAOException("Macchinario non presente");
            if(e.getErrorCode() == 1406)
                throw new DAOException("Nome troppo lungo");
            if(e.getErrorCode() == 1644)
                throw new DAOException(e.getMessage());
            else
                throw new DAOException("Errore nell'inserimento dell'esercizio: " + e.getMessage());
        }

        return null;
    }
}
