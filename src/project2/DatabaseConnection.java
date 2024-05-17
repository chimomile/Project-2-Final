package project2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {

    private final String url = "jdbc:mysql://localhost:3306/dbgoldchick";
    private final String user = "root";
    private final String password = "";
    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database connection failed", e);
            return null;
        }
    }

    public boolean updateUser(String id, String name, String username, String password) {
        Connection con = null;
        try {
            con = getConnection();
            String query = "UPDATE employee SET nama = ?, username = ?, password = ? WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setString(4, id);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating user", e);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "Error closing connection", e);
                }
            }
        }
    }

    public boolean deleteUser(String id) {
        Connection con = null;
        try {
            con = getConnection();
            String query = "DELETE FROM employee WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, id);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting user", e);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "Error closing connection", e);
                }
            }
        }
    }

    public boolean usernameExists(String username) {
        String query = "SELECT COUNT(*) FROM employee WHERE username = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error checking username", e);
        }
        return false;
    }

    public boolean saveUser(String nama, String username, String password) {
        String query = "INSERT INTO employee (nama, username, password) VALUES (?, ?, ?)";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, nama);
            ps.setString(2, username);
            ps.setString(3, password);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saving user", e);
        }
        return false;
    }

    public boolean updateMenu1(String id, String imgPath, String menuName, String menuPrice) {
        String query = "UPDATE menu SET img_path = ?, menu_name = ?, menu_price = ? WHERE menu_id = ?";
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, imgPath);
            pst.setString(2, menuName);
            pst.setString(3, menuPrice);
            pst.setString(4, id);
            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error updating menu", ex);
            return false;
        }
    }

    public boolean deleteMenu1(String id) {
        String query = "DELETE FROM menu WHERE menu_id = ?";
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, id);
            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error deleting menu", ex);
            return false;
        }
    }
    
    public List<String> getMenuNames() {
        List<String> menuNames = new ArrayList<>();
        String query = "SELECT menu_name FROM menu LIMIT 9"; // Adjust the limit as needed
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                menuNames.add(rs.getString("menu_name"));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving menu names", e);
        }
        return menuNames;
    }
    
    public List<Menu> getMenuItems() {
        List<Menu> menuItems = new ArrayList<>();
        Connection conn = getConnection();
        if (conn != null) {
            try {
                String query = "SELECT menu_name, menu_price FROM menu";
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("menu_name");
                    String price = rs.getString("menu_price").replace("Rp", "").replace(",", "").trim();
                    Menu menu = new Menu(name, Double.parseDouble(price));
                    menuItems.add(menu);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return menuItems;
    }

}
