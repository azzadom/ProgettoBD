package dao;

import exception.DAOException;
import model.Macchinario;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MacchinarioDAO {

    public void addMacchinario (Macchinario macchinario) throws DAOException{

        try {
            Connection conn = ConnectionSingleton.getConnection();
            CallableStatement cs = conn.prepareCall("call inserisci_macchinario(?,?)");
            cs.setString(1, macchinario.getNome());
            cs.setInt(2, macchinario.getQuantità());
            cs.executeQuery();
        } catch (SQLException e) {
            if(e.getErrorCode() == 1406)
                throw new DAOException("Nome troppo lungo");
            if (e.getErrorCode() == 1264)
                throw new DAOException("Quantità non valida");
            if(e.getErrorCode() == 1644)
                throw new DAOException(e.getMessage());
            else
                throw new DAOException("Errore nell'inserimento del macchinario: " + e.getMessage());
        }
    }

    public List<Macchinario> getListaMacchinari() throws DAOException {
        List<Macchinario> listaMacchinari = new ArrayList<>();
        try {
            Connection connection = ConnectionSingleton.getConnection();
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
