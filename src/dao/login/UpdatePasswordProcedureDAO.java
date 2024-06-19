package dao.login;

import dao.ConnectionFactory;
import dao.GenericProcedureDAO;
import exception.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class UpdatePasswordProcedureDAO implements GenericProcedureDAO<Void> {

    @Override
    public Void execute(Object... params) throws DAOException{

        String username = (String) params[0];
        String password = (String) params[1];

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call update_password(?,?)}");
            cs.setString(1, username);
            cs.setString(2, password);
            cs.executeQuery();
        } catch(SQLException e) {
            throw new DAOException("Errore nell'aggiornare la password: " + e.getMessage());
        }

        return null;
    }
}
