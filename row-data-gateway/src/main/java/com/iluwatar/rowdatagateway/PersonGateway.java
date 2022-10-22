package com.iluwatar.rowdatagateway;

import java.sql.SQLException;

public interface PersonGateway {
    public Person find(int id) throws SQLException;
    public boolean insert() throws SQLException;
    public boolean update() throws SQLException;
    public boolean delete() throws SQLException;
}
