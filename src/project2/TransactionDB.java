package project2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/dbgoldchick";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void saveTransaction(String itemName, int purchaseId, String adminName, double total) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO transactiondetails (time, itemName, purchaseId, adminName, total) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setTimestamp(1, new java.sql.Timestamp(System.currentTimeMillis()));
            statement.setString(2, itemName);
            statement.setInt(3, purchaseId);
            statement.setString(4, adminName);
            statement.setDouble(5, total);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
