package dao;

import exception.DAOException;
import model.Esercizio;
import model.SchedaArchiviata;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SchedaArchiviataDAO {

    public List<SchedaArchiviata> getListaArchiavate(String CF, LocalDate dataInizio, LocalDate dataFine) throws DAOException {
        List<SchedaArchiviata> lista = new ArrayList<>();

        try {
            Connection connection = ConnectionSingleton.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call lista_archiviate(?,?,?)}");
            callableStatement.setString(1, CF);
            callableStatement.setDate(2, Date.valueOf(dataInizio));
            callableStatement.setDate(3, Date.valueOf(dataFine));
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                lista.add(
                        new SchedaArchiviata(resultSet.getInt("Codice"), resultSet.getDate("DataInizio"), resultSet.getDate("DataFine")));
            }
        } catch (SQLException e) {
            throw new DAOException("Errore durante la visualizzazione del report: " + e.getMessage());
        }

        return lista;
    }

    public void loadSchedaArchiviata(String CF, SchedaArchiviata scheda) throws DAOException {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call scheda_archiviata(?,?)}");
            callableStatement.setString(1, CF);
            callableStatement.setInt(2, scheda.getId());
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
    }

}
