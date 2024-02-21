import java.sql.*;

public class UserDao {
    // Method to insert a new user into the database
    public void insertUser(String username, String password) {
        // Hash the password
        String hashedPassword = org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());

        String sql = "INSERT INTO users(username, password) VALUES(?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to validate a user's login credentials
    public boolean validateUser(String username, String rawPassword) {
        String sql = "SELECT password FROM users WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password");
                    return org.mindrot.jbcrypt.BCrypt.checkpw(rawPassword, storedHash);
                }
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Method to update a user's password
    public void updateUserPassword(String username, String newPassword) {
        String hashedPassword = org.mindrot.jbcrypt.BCrypt.hashpw(newPassword, org.mindrot.jbcrypt.BCrypt.gensalt());
        String sql = "UPDATE users SET password = ? WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, hashedPassword);
            pstmt.setString(2, username);
            int updatedRows = pstmt.executeUpdate();

            if (updatedRows > 0) {
                System.out.println("Password updated successfully for user: " + username);
            } else {
                System.out.println("User not found: " + username);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Optional: Method to delete a user
    public void deleteUser(String username) {
        String sql = "DELETE FROM users WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            int deletedRows = pstmt.executeUpdate();

            if (deletedRows > 0) {
                System.out.println("User deleted successfully: " + username);
            } else {
                System.out.println("User not found: " + username);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public User getUserDetails(String username) {
        String sql = "SELECT id, username FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                // Set other user properties
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
