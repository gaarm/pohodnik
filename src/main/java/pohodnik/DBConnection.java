package pohodnik;

import pohodnik.model.Hike;

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
            //String url = "jdbc:mysql://localhost:3306/pohodnik?useSSL=false";
            String url = "jdbc:sqlite:C:\\Users\\gaspe\\IdeaProjects\\db-sqlite\\database.db";
            String user = "root";
            String password = "example";

            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBConnection.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public boolean canLogin(String username, String password) {
        String query = "SELECT * FROM oseba WHERE ime = ? AND priimek = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(rs.getString("ime"));
                return false;
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(DBConnection.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {

            }
        }
        return false;
    }

    public List<Oseba> getMembers(String searchString) throws SQLException {
        String query = "SELECT * FROM oseba WHERE ime LIKE ? OR priimek LIKE ?";
        List<Oseba> osebaList = new ArrayList<>();

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + searchString + "%");
            ps.setString(2, "%" + searchString + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                osebaList.add(new Oseba(rs.getString("ime"),
                        rs.getString("priimek"),
                        rs.getInt("id"))
                );
            }
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(DBConnection.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {

            }
        }

        return osebaList;
    }

    public boolean addMember(Oseba oseba) {
        String query = "INSERT INTO oseba (ime, priimek) VALUES (?, ?)";

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, oseba.getIme());
            ps.setString(2, oseba.getPriimek());
            ps.execute();
            return true;
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(DBConnection.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {

            }
        }
        return false;
    }

    public boolean updateMember(Oseba oseba) {
        System.out.println(oseba.getIme());

        String query = "UPDATE oseba SET ime = ?, priimek = ? WHERE id = ?";

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, oseba.getIme());
            ps.setString(2, oseba.getPriimek());
            ps.setInt(3, oseba.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(DBConnection.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {

            }
        }
        return false;
    }


    public boolean deleteMember(Oseba oseba) {
        String query = "DELETE FROM oseba WHERE ime = ? and priimek = ?";

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, oseba.getIme());
            ps.setString(2, oseba.getPriimek());
            ps.execute();
            return true;
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(DBConnection.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {

            }
        }
        return false;
    }

    public List<Hike> getHikes(String searchString) throws SQLException {
        String query = "SELECT * FROM pohod WHERE naziv LIKE ?";
        List<Hike> hikeList = new ArrayList<>();

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + searchString + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                hikeList.add(new Hike(rs.getInt("id"),
                        rs.getString("naziv"))
                );
            }
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(DBConnection.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {

            }
        }

        return hikeList;
    }

    public boolean addHike(Hike hike) {
        String query = "INSERT INTO pohod (naziv) VALUES (?)";

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, hike.getName());
            ps.execute();
            return true;
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(DBConnection.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {

            }
        }
        return false;
    }

    public boolean updateHike(Hike hike) {

        String query = "UPDATE pohod SET naziv = ? WHERE id = ?";

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, hike.getName());
            ps.setInt(2, hike.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(DBConnection.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {

            }
        }
        return false;
    }


    public boolean deleteHike(Hike hike) {
        String query = "DELETE FROM pohod WHERE id = ?";

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, hike.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(DBConnection.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {

            }
        }
        return false;
    }
}