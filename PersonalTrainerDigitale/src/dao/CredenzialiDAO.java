package dao;

import exception.DAOException;
import model.Credenziali;
import model.Ruolo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class CredenzialiDAO {

    public Credenziali getCredentials(String username, String password) throws DAOException {

        int ruolo;
        String CF;

        try {
            Connection conn = ConnectionSingleton.getConnection();
            CallableStatement cs = conn.prepareCall("{call login(?,?,?,?)}");
            cs.setString(1, username);
            cs.setString(2, password);
            cs.registerOutParameter(3, Types.NUMERIC);
            cs.registerOutParameter(4, Types.VARCHAR);
            cs.executeQuery();
            ruolo = cs.getInt(3);
            CF = cs.getString(4);
        } catch(SQLException e) {
            throw new DAOException("Login error: " + e.getMessage());
        }

        return new Credenziali(username, password, Ruolo.fromInt(ruolo), CF);
    }

    public void updatePassword(String username, String password) throws DAOException {
        try {
            Connection conn = ConnectionSingleton.getConnection();
            CallableStatement cs = conn.prepareCall("{call update_password(?,?)}");
            cs.setString(1, username);
            cs.setString(2, password);
            cs.executeQuery();
        } catch(SQLException e) {
            if(e.getErrorCode() == 1644) {
                throw new DAOException(e.getMessage());
            } else {
                throw new DAOException("Errore nell'aggiornare la password: " + e.getMessage());
            }
        }
    }

}
