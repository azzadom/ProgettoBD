package dao.personal;

import dao.ConnectionFactory;
import dao.GenericProcedureDAO;
import exception.DAOException;
import model.Esercizio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListaEserciziProcedureDAO implements GenericProcedureDAO<List<Esercizio>> {

    @Override
    public List<Esercizio> execute(Object... params) throws DAOException {
        List<Esercizio> listaEsercizi = new ArrayList<>();
        try {
            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call lista_esercizi()}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                listaEsercizi.add(new Esercizio(resultSet.getString("Nome"), resultSet.getString("Macchinario")));
            }
        } catch (SQLException e) {
            throw new DAOException("Errore durante il caricamento della lista degli esercizi: " + e.getMessage());
        }
        return listaEsercizi;
    }
}
