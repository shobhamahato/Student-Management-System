package studentPack;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static final String CONFIG_FILE = "db.properties";

    public static Connection getConnection() throws SQLException {

        Properties properties = new Properties();

        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
        } catch (IOException e) {
            throw new SQLException("Could not find db.properties.", e);
        }

        String url = properties.getProperty(
                "db.url",
                "jdbc:mysql://localhost:3306/Std_Details"
        );

        String username = properties.getProperty(
                "db.username",
                "root"
        );

        String password = properties.getProperty("db.password");

        return DriverManager.getConnection(url, username, password);
    }
}