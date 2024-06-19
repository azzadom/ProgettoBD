package dao.personal;

import dao.ConnectionFactory;
import dao.GenericProcedureDAO;
import exception.DAOException;
import model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListaClientiProcedureDAO implements GenericProcedureDAO<List<Cliente>> {

    @Override
    public List<Cliente> execute(Object... params) throws DAOException {

        String CF = (String) params[0];

        List<Cliente> lista = new ArrayList<>();

        try {
            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call lista_clienti(?)}");
            callableStatement.setString(1, CF);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                lista.add(
                        new Cliente(resultSet.getString("CF"), resultSet.getString("Nome"), resultSet.getString("Cognome"), CF));
            }
        } catch (SQLException e) {
            throw new DAOException("Errore durante la visualizzazione del report: " + e.getMessage());
        }

        return lista;
    }
}
