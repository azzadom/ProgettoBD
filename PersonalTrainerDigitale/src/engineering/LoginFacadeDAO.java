package engineering;

import dao.CredenzialiDAO;

import exception.DAOException;
import model.Credenziali;

public class LoginFacadeDAO {

    private final CredenzialiDAO credentialsDAO;

    public LoginFacadeDAO(){
        credentialsDAO = new CredenzialiDAO();
    }

    public Credenziali login(String username, String password) throws DAOException {
        return credentialsDAO.getCredentials(username, password);
    }

    public void updatePassword(String username, String password) throws DAOException {
        credentialsDAO.updatePassword(username, password);
    }

}
