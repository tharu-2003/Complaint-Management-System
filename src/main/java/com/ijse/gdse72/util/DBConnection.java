package com.ijse.gdse72.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {

    private static final BasicDataSource ds = new BasicDataSource();

    static {
        try{
            ds.setDriverClassName("com.mysql.jdbc.Driver");
            ds.setUrl("jdbc:mysql://localhost:3306/complaint_system");
            ds.setUsername("root");
            ds.setPassword("Ijse@1234");

            ds.setInitialSize(5);
            ds.setMaxTotal(5);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
