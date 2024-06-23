package dao;

import exception.DAOException;
import model.Sessione;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class SessioneDAO {

    public void addSessione(Sessione sessione) throws DAOException {

        try {
            Connection connection = ConnectionSingleton.getConnection();
            CallableStatement callableStatement = connection.prepareCall("call registra_sessione(?,?,?,?,?,?)");
            callableStatement.setString(1, sessione.getCliente());
            callableStatement.setDate(2, sessione.getData());
            callableStatement.setTime(3, sessione.getOraInizio());
            callableStatement.setTime(4, sessione.getOraFine());
            callableStatement.setInt(5, sessione.getScheda());
            callableStatement.setInt(6, sessione.getPercentuale());
            callableStatement.executeQuery();
        } catch (SQLException e) {
            throw new DAOException("Errore nell'inserimento della sessione: " + e.getMessage());
        }

    }
}
