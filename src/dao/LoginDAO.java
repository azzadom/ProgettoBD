package dao;

import dao.procedure.GenericProcedureDAO;
import dao.procedure.login.*;
import exception.DAOException;
import model.Credenziali;

public class LoginDAO {

    public Credenziali login(String username, String password) throws DAOException {
        GenericProcedureDAO<Credenziali> loginProcedureDAO = new LoginProcedureDAO();
        return loginProcedureDAO.execute(username, password);
    }

    public void updatePassword(String username, String password) throws DAOException {
        GenericProcedureDAO<Void> updatePasswordProcedureDAO = new UpdatePasswordProcedureDAO();
        updatePasswordProcedureDAO.execute(username, password);
    }

}
