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
        String query = "SELECT * FROM person WHERE first_name LIKE ? OR last_name LIKE ?";
        List<Person> personList = new ArrayList<>();

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + searchString + "%");
            ps.setString(2, "%" + searchString + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                personList.add(new Person(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"))
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
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";

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

        String query = "UPDATE person SET first_name = ?, last_name = ? WHERE id = ?";

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
        String query = "DELETE FROM person WHERE id = ?";

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
        String query = "SELECT * FROM excursion WHERE name LIKE ?";
        List<Excursion> excursionList = new ArrayList<>();

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + searchString + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                excursionList.add(new Excursion(rs.getInt("id"),
                        rs.getString("name"))
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
        String query = "INSERT INTO excursion (name) VALUES (?)";

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

        String query = "UPDATE excursion SET name = ? WHERE id = ?";

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
        String query = "DELETE FROM excursion WHERE id = ?";

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
        String query = "SELECT * FROM person_excursion WHERE person_id = ?";
        List<PersonExcursion> personExcursionList = new ArrayList<>();

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, person.getId());
            rs = ps.executeQuery();

            while (rs.next()) {
                personExcursionList.add(new PersonExcursion(rs.getInt("person_id"),
                        rs.getInt("excursion_id"))
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
        String query = "INSERT INTO person_excursion (person_id, excursion_id) VALUES (?, ?)";

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
        String query = "DELETE FROM person_excursion WHERE person_id = ? and excursion_id = ?";

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