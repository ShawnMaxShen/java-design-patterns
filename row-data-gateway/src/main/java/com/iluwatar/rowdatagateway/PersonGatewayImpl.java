package com.iluwatar.rowdatagateway;

import javax.sql.DataSource;
import java.sql.SQLException;

public class PersonGatewayImpl implements PersonGateway{
    /**
     * The SQL statement used to create a table in the database*
     */
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS PERSONS (ID NUMBER UNIQUE, FIRSTNAME VARCHAR(30), LASTNAME VARCHAR(30))";

    /**
     * The SQL statement used to drop(delete) the PERSONS table in the database*
     */
    public static final String DELETE_TABLE = "DROP TABLE PERSONS IF EXISTS";

    /**
     * The data source
     */
    private final DataSource db;

    /**
     * The person info which needs to be inserted/ updated/ deleted
     */
    private final Person person;

    /**
     * The constructor for the PersonGatewayImpl
     * @param person person info which needs to be inserted/ updated/ deleted
     * @param db the data source (database)
     */
    public PersonGatewayImpl(final Person person, final DataSource db) {
        this.person = person;
        this.db = db;
    }


    /**
     * The method used to find the Person according to the ID
     * @return The person according to the ID
     * @throws SQLException if any
     */
    @Override
    public Person find(int id) throws SQLException {
        var findSql = "SELECT ID, LASTNAME, FIRSTNAME FROM PERSONS WHERE ID = ?";
        try (var connection = db.getConnection();
             var preparedStatement = connection.prepareStatement(findSql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
        return null;
    }

    /**
     * The insert method which is used to insert a row (an instance) into the table (schema)
     *
     * @return true if the insert operation succeed, false else
     * @throws SQLException if any
     */
    @Override
    public boolean insert() throws SQLException {
        var insertSql = "INSERT INTO PERSONS (ID, FIRSTNAME, LASTNAME) values (?, ?, ?)";
        try (var connection = db.getConnection();
             var preparedStatement = connection.prepareStatement(insertSql)
        ) {
           preparedStatement.setInt(1, person.getId());
           preparedStatement.setString(2, person.getFirstName());
           preparedStatement.setString(3, person.getLastName());
           preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    /**
     * The update method which is used to update a existed row (an instance) in the table (schema)
     *
     * @return true if the update operation succeed, false else
     * @throws java.sql.SQLException if any
     */
    @Override
    public boolean update() throws SQLException {
        var updateSql = "UPDATE PERSONS SET FIRSTNAME = ?, LASTNAME = ? WHERE ID = ?";
        try (var connection = db.getConnection();
             var preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setInt(3, person.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    /**
     * The delete method which is used to delete an existed row (an instance) in the table (schema)
     *
     * @return true if the delete operation succeed, false else
     * @throws SQLException if any
     */
    @Override
    public boolean delete() throws SQLException {
        var deleteSQL = "DELETE FROM PERSONS WHERE ID = ?";
        try (var connection = db.getConnection();
             var preparedStatement = connection.prepareStatement(deleteSQL)
        ) {
            preparedStatement.setInt(1, person.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
