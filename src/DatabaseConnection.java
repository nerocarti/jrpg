import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:C:\\Users\\realj\\IdeaProjects\\JavaRPG\\db\\letsgo.sqlite";
        return DriverManager.getConnection(url);
    }

    public static void initializeDatabase() {
        String sqlUsersTable = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username TEXT NOT NULL, "
                + "password TEXT NOT NULL);";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            // Create users table if it doesn't exist
            stmt.execute(sqlUsersTable);
            System.out.println("Database initialized successfully");
        } catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }
}

