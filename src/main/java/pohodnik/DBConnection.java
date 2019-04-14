package pohodnik;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.w3c.dom.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public DBConnection() {

        try {
            String url = "jdbc:mysql://localhost:3306/pohodnik?useSSL=false";
            String user = "root";
            String password = "example";

            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBConnection.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public boolean canLogin(String username, String password) {
        String query = "SELECT * FROM members WHERE username = ? AND password = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(rs.getString("email"));
                return true;
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(DBConnection.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return false;
    }

    public List<Member> getMembers(String searchString) throws SQLException {
        String query = "SELECT * FROM members WHERE username LIKE ? OR email LIKE ?";
        List<Member> memberList = new ArrayList<>();

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + searchString + "%");
            ps.setString(2, "%" + searchString + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                memberList.add(new Member(rs.getString("username"), rs.getString("email")));
            }
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(DBConnection.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }



        return memberList;
    }
}