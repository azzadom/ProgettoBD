package dao.proprietario;

import dao.ConnectionFactory;
import dao.GenericProcedureDAO;
import exception.DAOException;
import model.PersonalTrainer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class InserisciPersonalProcedureDAO implements GenericProcedureDAO<Void> {

    public Void execute(Object... params) throws DAOException {
        PersonalTrainer pt = (PersonalTrainer) params[0];

        try{
            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call nuovo_personal(?, ?, ?, ?)}");
            callableStatement.setString(1, pt.getCF());
            callableStatement.setString(2, pt.getNome());
            callableStatement.setString(3, pt.getCognome());
            callableStatement.setString(4, pt.getUsername());
            callableStatement.execute();
        }catch (SQLException e){
            if (e.getErrorCode() == 1062) {
                throw new DAOException("Il personal trainer è già presente nel database.");
            }
            else if (e.getErrorCode() == 1406) {
                throw new DAOException("Uno dei campi inseriti è troppo lungo.");
            }
            else if (e.getErrorCode() == 1048) {
                throw new DAOException("Uno dei campi obbligatori è vuoto.");
            }
            else if (e.getErrorCode() == 1644) {
                throw new DAOException(e.getMessage());
            } else {
                throw new DAOException("Errore durante l'inserimento del personal trainer: " + e.getMessage());
            }
        }

        return null;
    }
}
