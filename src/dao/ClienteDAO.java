package dao;

import exception.DAOException;
import model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public List<Cliente> getListaClienti(String cfPersonal) throws DAOException {

        List<Cliente> lista = new ArrayList<>();

        try {
            Connection connection = ConnectionSingleton.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call lista_clienti(?)}");
            callableStatement.setString(1, cfPersonal);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                lista.add(
                        new Cliente(resultSet.getString("CF"), resultSet.getString("Nome"), resultSet.getString("Cognome"), cfPersonal));
            }
        } catch (SQLException e) {
            throw new DAOException("Errore durante la visualizzazione del report: " + e.getMessage());
        }

        return lista;
    }

    public void addCliente(Cliente cliente) throws DAOException{

        try{
            Connection connection = ConnectionSingleton.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call nuovo_cliente(?, ?, ?, ?, ?)}");
            callableStatement.setString(1, cliente.getCF());
            callableStatement.setString(2, cliente.getNome());
            callableStatement.setString(3, cliente.getCognome());
            callableStatement.setString(4, cliente.getPersonalTrainer());
            callableStatement.setString(5, cliente.getUsername());
            callableStatement.execute();
        }catch (SQLException e){
            if (e.getErrorCode() == 1062) {
                throw new DAOException("Il cliente è già presente nel database.");
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
                throw new DAOException("Errore durante l'inserimento del cliente: " + e.getMessage());
            }
        }
    }

}
