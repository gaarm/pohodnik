package excursion;

import excursion.model.Excursion;
import excursion.model.Person;
import excursion.model.PersonExcursion;

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
            //String url = "jdbc:mysql://localhost:3306/excursion?useSSL=false";
            String url = "jdbc:sqlite:C:\\Users\\gaspe\\IdeaProjects\\db-sqlite\\database.db";
            String user = "root";
            String password = "example";

            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBConnection.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public List<Person> getPersons(String searchString) throws SQLException {
        System.out.println(searchString);
        String query = "SELECT * FROM oseba WHERE ime LIKE ? OR priimek LIKE ?";
        List<Person> personList = new ArrayList<>();

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + searchString + "%");
            ps.setString(2, "%" + searchString + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                personList.add(new Person(rs.getInt("id"),
                        rs.getString("ime"),
                        rs.getString("priimek"))
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

        return personList;
    }

    public boolean addPerson(Person person) {
        String query = "INSERT INTO oseba (ime, priimek) VALUES (?, ?)";

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, person.getName());
            ps.setString(2, person.getSurname());
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

    public boolean updatePerson(Person person) {
        System.out.println(person.getName());

        String query = "UPDATE oseba SET ime = ?, priimek = ? WHERE id = ?";

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, person.getName());
            ps.setString(2, person.getSurname());
            ps.setInt(3, person.getId());
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

    public boolean deletePerson(Person person) {
        String query = "DELETE FROM oseba WHERE id = ?";

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, person.getId());
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

    public List<Excursion> getExcursions(String searchString) throws SQLException {
        String query = "SELECT * FROM pohod WHERE naziv LIKE ?";
        List<Excursion> excursionList = new ArrayList<>();

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + searchString + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                excursionList.add(new Excursion(rs.getInt("id"),
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

        return excursionList;
    }

    public boolean addExcursion(Excursion excursion) {
        String query = "INSERT INTO pohod (naziv) VALUES (?)";

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, excursion.getName());
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

    public boolean updateExcursion(Excursion excursion) {

        String query = "UPDATE pohod SET naziv = ? WHERE id = ?";

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, excursion.getName());
            ps.setInt(2, excursion.getId());
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


    public boolean deleteExcursion(Excursion excursion) {
        String query = "DELETE FROM pohod WHERE id = ?";

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, excursion.getId());
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

    public List<PersonExcursion> getPersonExcursions(Person person) throws SQLException {
        String query = "SELECT * FROM oseba_pohod WHERE oseba_id = ?";
        List<PersonExcursion> personExcursionList = new ArrayList<>();

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, person.getId());
            rs = ps.executeQuery();

            while (rs.next()) {
                personExcursionList.add(new PersonExcursion(rs.getInt("oseba_id"),
                        rs.getInt("pohod_id"))
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

        return personExcursionList;
    }

    public boolean addPersonExcursion(Person person, int excursionId) {
        String query = "INSERT INTO oseba_pohod (oseba_id, pohod_id) VALUES (?, ?)";

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, person.getId());
            ps.setInt(2, excursionId);
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

    public boolean deletePersonExcursion(Person person, int excursionId) {
        String query = "DELETE FROM oseba_pohod WHERE oseba_id = ? and pohod_id = ?";

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, person.getId());
            ps.setInt(2, excursionId);
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