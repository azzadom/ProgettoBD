package dao;

import exception.DAOException;
import model.Report;
import model.Sessione;

import java.sql.*;
import java.time.LocalDate;

public class ReportDAO {

    public Report getReport(String CF, String cliente, LocalDate dataInizio, LocalDate dataFine) throws DAOException {

        Report report = new Report(cliente, dataInizio, dataFine);

        try {
            Connection connection = ConnectionSingleton.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call report_sessioni(?,?,?,?)}");
            callableStatement.setString(1, CF);
            callableStatement.setString(2, cliente);
            callableStatement.setDate(3, Date.valueOf(dataInizio));
            callableStatement.setDate(4, Date.valueOf(dataFine));
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                report.setNome(resultSet.getString("Nome"));
                report.setCognome(resultSet.getString("Cognome"));
                report.addSessione(new Sessione(
                        resultSet.getDate("Data"),
                        resultSet.getTime("OraInizio"),
                        resultSet.getString("Durata"),
                        resultSet.getInt("Scheda"),
                        resultSet.getInt("Percentuale")
                ));
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 1644) {
                throw new DAOException(e.getMessage());
            } else {
                throw new DAOException("Errore durante la visualizzazione del report: " + e.getMessage());
            }
        }

        return report;
    }
}
