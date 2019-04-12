package pohodnik;

import java.io.Console;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {

    public static void main() {

        String url = "jdbc:mysql://localhost:3306/pohodnik?useSSL=false";
        String user = "root";
        String password = "example";

        String query = "SELECT * FROM members";
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                System.out.println(rs.getString("email"));

            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(DBConnection.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}