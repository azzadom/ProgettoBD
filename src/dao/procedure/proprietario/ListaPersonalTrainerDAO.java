package dao.procedure.proprietario;

import dao.ConnectionFactory;
import dao.procedure.GenericProcedureDAO;
import exception.DAOException;
import model.PersonalTrainer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListaPersonalTrainerDAO implements GenericProcedureDAO<List<PersonalTrainer>> {

    @Override
    public List<PersonalTrainer> execute(Object... params) throws DAOException {

        List<PersonalTrainer> listaPT = new ArrayList<>();
        try {
            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call lista_personal()}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                listaPT.add(new PersonalTrainer(resultSet.getString("CF"), resultSet.getString("Nome"), resultSet.getString("Cognome"), resultSet.getInt("NumClienti")));
            }
        } catch (SQLException e) {
            throw new DAOException("Errore durante il caricamento della lista dei personal trainer: " + e.getMessage());
        }
        return listaPT;

    }

}
