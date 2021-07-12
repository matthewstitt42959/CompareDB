package com.dbcompare.common.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static Connection conn;

    public static Connection Connector(String host, String dbName, String userName, String password, String tableName) {

        try (Connection conn = DriverManager.getConnection(host, userName, password)) {
            System.out.println(dbName + "" + " connected!");
           
            //CountResultset(conn, dbName, tableName, count);
            CloseConnection(conn);

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);

        }
        return conn;

    }

    private static void CloseConnection(Connection conn) throws SQLException {
        conn.close();

        try {
            conn.getMetaData();
        } catch (Exception e) {
            System.out.println("Connection is closed");
        }
    }
}
