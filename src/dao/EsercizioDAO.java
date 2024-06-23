package dao;

import exception.DAOException;
import model.Esercizio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EsercizioDAO {

    public List<Esercizio> getListaEsercizi() throws DAOException {
        List<Esercizio> listaEsercizi = new ArrayList<>();
        try {
            Connection connection = ConnectionSingleton.getConnection();
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

    public void addEsercizio(Esercizio esercizio) throws DAOException {
        try {
            Connection conn = ConnectionSingleton.getConnection();
            CallableStatement cs = conn.prepareCall("call inserisci_esercizio(?,?)");
            cs.setString(1, esercizio.getNome());
            if (esercizio.getMacchinario().getNome().equals("Nessuno"))
                cs.setString(2, null);
            else
                cs.setString(2, esercizio.getMacchinario().getNome());
            cs.executeQuery();
        } catch(SQLException e) {
            if (e.getErrorCode() == 1062)
                throw new DAOException("Esercizio già presente");
            if (e.getErrorCode() == 1452)
                throw new DAOException("Macchinario non presente");
            if(e.getErrorCode() == 1406)
                throw new DAOException("Nome troppo lungo");
            if(e.getErrorCode() == 1644)
                throw new DAOException(e.getMessage());
            else
                throw new DAOException("Errore nell'inserimento dell'esercizio: " + e.getMessage());
        }
    }
}
