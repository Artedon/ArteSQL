package nl.artesql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Helper {

    private Connection connection;
    private Options options;
    private boolean connected;

    public Helper(Options options) {

        this.options = options;

        String url = "jdbc:mysql://" + options.host + ":" + options.port;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, options.user, options.pass);


            connected = true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            connected = false;
        }

    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean isConnected() {
        return connected;
    }
}
