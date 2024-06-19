package dao.cliente;

import dao.ConnectionFactory;
import dao.GenericProcedureDAO;
import exception.DAOException;
import model.SchedaArchiviata;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ListaArchiviateProcedureDAO implements GenericProcedureDAO<List<SchedaArchiviata>> {
    @Override
    public List<SchedaArchiviata> execute(Object... params) throws DAOException {
        String CF = (String) params[0];
        LocalDate dataInizio = (LocalDate) params[1];
        LocalDate dataFine = (LocalDate) params[2];

        List<SchedaArchiviata> lista = new ArrayList<>();

        try {
            Connection connection = ConnectionFactory.getConnection();
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
}
