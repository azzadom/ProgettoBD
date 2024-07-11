package dao;

import exception.DAOException;
import model.PersonalTrainer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonalTrainerDAO {

    public void addPersonalTrainer(PersonalTrainer pt) throws DAOException {

        try{
            Connection connection = ConnectionSingleton.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call nuovo_personal(?, ?, ?, ?)}");
            callableStatement.setString(1, pt.getCF());
            callableStatement.setString(2, pt.getNome());
            callableStatement.setString(3, pt.getCognome());
            callableStatement.setString(4, pt.getUsername());
            callableStatement.execute();
        }catch (SQLException e){
            if (e.getErrorCode() == 1062) {
                throw new DAOException("Il personal trainer è già presente nel database.");
            }
            else if (e.getErrorCode() == 1406) {
                throw new DAOException("Uno dei campi inseriti è troppo lungo.");
            }
            else if (e.getErrorCode() == 1048) {
                throw new DAOException("Uno dei campi obbligatori è vuoto.");
            }
            else if (e.getErrorCode() == 1644) {
                throw new DAOException(e.getMessage());
            } else {
                throw new DAOException("Errore durante l'inserimento del personal trainer: " + e.getMessage());
            }
        }
    }

    public List<PersonalTrainer> getListaPersonalTrainer() throws DAOException {

        List<PersonalTrainer> listaPT = new ArrayList<>();
        try {
            Connection connection = ConnectionSingleton.getConnection();
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
