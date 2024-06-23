package dao.procedure.proprietario;

import dao.ConnectionFactory;
import dao.procedure.GenericProcedureDAO;
import exception.DAOException;
import model.Macchinario;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListaMacchinariProcedureDAO implements GenericProcedureDAO<List<Macchinario>> {

    @Override
    public List<Macchinario> execute(Object... params) throws DAOException {
        List<Macchinario> listaMacchinari = new ArrayList<>();
        try {
            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call lista_macchinari()}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                listaMacchinari.add(new Macchinario(resultSet.getString("Nome"), resultSet.getInt("Quantità")));
            }
        } catch (SQLException e) {
            throw new DAOException("Errore durante il caricamento della lista dei macchinari: " + e.getMessage());
        }
        return listaMacchinari;
    }

}
