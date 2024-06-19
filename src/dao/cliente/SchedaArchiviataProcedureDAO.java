package dao.cliente;

import dao.ConnectionFactory;
import dao.GenericProcedureDAO;
import exception.DAOException;
import model.Esercizio;
import model.SchedaAllenamento;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchedaArchiviataProcedureDAO implements GenericProcedureDAO<SchedaAllenamento> {
    @Override
    public SchedaAllenamento execute(Object... params) throws DAOException {
        String CF = (String) params[0];
        Integer id = (Integer) params[1];

        SchedaAllenamento scheda = new SchedaAllenamento(id);

        try {
            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call scheda_archiviata(?,?)}");
            callableStatement.setString(1, CF);
            callableStatement.setInt(2, id);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                scheda.addEsercizio(new Esercizio(resultSet.getInt("Numero"), resultSet.getString("Esercizio"), resultSet.getInt("Serie"), resultSet.getInt("Ripetizioni"), resultSet.getString("Macchinario")));
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 1644) {
                throw new DAOException(e.getMessage());
            }else {
                throw new DAOException("Errore durante il caricamento della scheda: " + e.getMessage());
            }
        }

        return scheda;
    }
}
