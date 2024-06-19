package dao.login;

import dao.ConnectionFactory;
import dao.GenericProcedureDAO;
import exception.DAOException;
import model.Credenziali;
import model.Ruolo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class LoginProcedureDAO implements GenericProcedureDAO<Credenziali> {

    @Override
    public Credenziali execute(Object... params) throws DAOException {
        String username = (String) params[0];
        String password = (String) params[1];
        int ruolo;
        String CF;

        try {
            Connection conn = ConnectionFactory.getConnection();
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
}
