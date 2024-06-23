package dao.procedure.proprietario;

import dao.ConnectionFactory;
import dao.procedure.GenericProcedureDAO;
import exception.DAOException;
import model.Cliente;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class InserisciClienteProcedureDAO implements GenericProcedureDAO<Void> {

@Override
    public Void execute(Object... params) throws DAOException{
        Cliente cliente = (Cliente) params[0];

        try{
            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call nuovo_cliente(?, ?, ?, ?, ?)}");
            callableStatement.setString(1, cliente.getCF());
            callableStatement.setString(2, cliente.getNome());
            callableStatement.setString(3, cliente.getCognome());
            callableStatement.setString(4, cliente.getPersonalTrainer());
            callableStatement.setString(5, cliente.getUsername());
            callableStatement.execute();
        }catch (SQLException e){
            if (e.getErrorCode() == 1062) {
                throw new DAOException("Il cliente è già presente nel database.");
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
                throw new DAOException("Errore durante l'inserimento del cliente: " + e.getMessage());
            }
        }

        return null;
    }

}
