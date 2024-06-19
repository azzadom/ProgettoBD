package dao;

import exception.DAOException;
import model.Ruolo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static Connection connection = null;
    private static final String CONNECTION_SETTINGS = "resources/db.properties";

    private ConnectionFactory() {}

    public static synchronized Connection getConnection() throws DAOException {
        if (connection == null) {
            try (InputStream input = new FileInputStream(CONNECTION_SETTINGS)) {
                Properties properties = new Properties();
                properties.load(input);

                String connectionUrl = properties.getProperty("CONNECTION_URL");
                String user = properties.getProperty("LOGIN_USER");
                String pass = properties.getProperty("LOGIN_PASS");

                connection = DriverManager.getConnection(connectionUrl, user, pass);
            } catch (IOException | SQLException e) {
                throw new DAOException("Error in getConnection: " + e.getMessage(), e);
            }
        }
        return connection;
    }

    public static void changeRole(Ruolo ruolo) throws DAOException {
        try (InputStream input = new FileInputStream("resources/db.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            String connection_url = properties.getProperty("CONNECTION_URL");
            String user = properties.getProperty(ruolo.name() + "_USER");
            String pass = properties.getProperty(ruolo.name() + "_PASS");

            connection.close();
            connection = DriverManager.getConnection(connection_url, user, pass);
        } catch (IOException | SQLException e) {
            throw new DAOException("Error in changeRole: " + e.getMessage(), e);
        }
    }
}
