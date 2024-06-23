package dao.procedure.proprietario;

import dao.ConnectionFactory;
import dao.procedure.GenericProcedureDAO;
import exception.DAOException;
import model.Macchinario;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class InserisciMacchinarioProcedureDAO implements GenericProcedureDAO<Void> {
    @Override
    public Void execute(Object... params) throws DAOException{

        Macchinario macchinario = (Macchinario) params[0];

        try {
            Connection conn = ConnectionFactory.getConnection();
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
        return null;
    }
}
