package dao.procedure.personal;

import dao.ConnectionFactory;
import dao.procedure.GenericProcedureDAO;
import exception.DAOException;
import model.Report;
import model.Sessione;

import java.sql.*;
import java.time.LocalDate;

public class ReportProcedureDAO implements GenericProcedureDAO<Report> {

    @Override
    public Report execute(Object... params) throws DAOException {
        String CF = (String) params[0];
        String cliente = (String) params[1];
        LocalDate dataInizio = (LocalDate) params[2];
        LocalDate dataFine = (LocalDate) params[3];


        Report report = new Report(cliente, dataInizio, dataFine);

        try {
            Connection connection = ConnectionFactory.getConnection();
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
