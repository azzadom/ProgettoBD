package dao.cliente;

import dao.ConnectionFactory;
import dao.GenericProcedureDAO;
import exception.DAOException;
import model.Esercizio;
import model.SchedaAllenamento;

import java.sql.*;

public class SchedaAllenamentoProcedureDAO implements GenericProcedureDAO<SchedaAllenamento> {
    @Override
    public SchedaAllenamento execute(Object... params) throws DAOException {
        String CF = (String) params[0];

        SchedaAllenamento scheda;
        int codice;

        try {
            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call scheda_corrente(?,?)}");
            callableStatement.setString(1, CF);
            callableStatement.registerOutParameter(2, Types.SMALLINT);
            ResultSet resultSet = callableStatement.executeQuery();
            codice = callableStatement.getInt(2);
            scheda = new SchedaAllenamento(codice);
            while (resultSet.next()) {
                scheda.addEsercizio(new Esercizio(resultSet.getInt("Numero"), resultSet.getString("Esercizio"), resultSet.getInt("Serie"), resultSet.getInt("Ripetizioni"), resultSet.getString("Macchinario")));
            }
        } catch (SQLException e) {
            throw new DAOException("Errore durante il caricamento della scheda: " + e.getMessage());
        }

        return scheda;

    }
}
