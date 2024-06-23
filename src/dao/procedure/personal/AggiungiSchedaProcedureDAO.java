package dao.procedure.personal;

import dao.ConnectionFactory;
import dao.procedure.GenericProcedureDAO;
import exception.DAOException;
import model.Esercizio;
import model.SchedaEsercizi;
import org.json.simple.JSONObject;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AggiungiSchedaProcedureDAO implements GenericProcedureDAO<Void> {

    @Override
    public Void execute(Object... params) throws DAOException{

        String CF = (String) params[0];
        SchedaEsercizi scheda = (SchedaEsercizi) params[1];

        try {
            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call inserisci_scheda(?,?,?,?)}");
            callableStatement.setString(1, CF);
            callableStatement.setString(2, scheda.getMatricola());
            callableStatement.setInt(3, scheda.getNumeroEsercizi());
            callableStatement.setString(4, estraiEsercizi(scheda.getEsercizi()).toString());
            callableStatement.executeQuery();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1644) {
                throw new DAOException(e.getMessage());
            }if (e.getErrorCode() == 1452) {
                throw new DAOException("Un esercizio inserito risulta non presente");
            } else if (e.getErrorCode() == 1062) {
                throw new DAOException("Hai inserito uno stesso esercizio più volte o hai già inserito una scheda per questo cliente oggi");
            } else {
                throw new DAOException("Errore durante l'inserimento della scheda: " + e.getMessage());
            }
        }

        return null;
    }

    private List<JSONObject> estraiEsercizi(List<Esercizio> esercizi) {

        List<JSONObject> eserciziJSON = new ArrayList<>();

        for (int i = 0; i < esercizi.size(); i++) {
            JSONObject esercizioJSON = new JSONObject();
            esercizioJSON.put("numero", i+1);
            esercizioJSON.put("nome", esercizi.get(i).getNome());
            esercizioJSON.put("serie", esercizi.get(i).getSerie());
            esercizioJSON.put("ripetizioni", esercizi.get(i).getRipetizioni());
            eserciziJSON.add(esercizioJSON);
        }
        return eserciziJSON;
    }
}
