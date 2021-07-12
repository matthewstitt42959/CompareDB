package com.dbcompare.common.connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.sql.Connection;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import java.sql.PreparedStatement;

import com.dbcompare.dataprovider.ReadCsv;
import com.dbcompare.dataprovider.WriteCsv;




public class MySQLConnector {

    static String host = "jdbc:mysql://localhost:3306/";
    static String dbName = "orgchart_api";
    static String dbName2 = "orgchart_api_copy";
    private static String resultString;
    private static Connection conn;
    static String userName = "root";
    static String password = "";
    static Statement stmt = null;
    static PreparedStatement pstmt = null;
    static ResultSet rs = null;
    static String tableName = "department";
    // Following is Printing details

    static String fileName = "";
    private static String count;


    public static void main(String[] args) throws IOException {
        DBConnector.Connector(host, dbName, userName, password, tableName);
        dbName = dbName2;  
        DBConnector.Connector(host, dbName2, userName, password, tableName);
        resultString = count; 
        Compare_Results(resultString);
    }

    private static void Compare_Results(String resultString) throws IOException {
        // To capture the result and save it for comparison.
        // Get first result
        // Save to csv?
        
        WriteCsv.WriteToCSV(resultString, fileName, count);
        ReadCsv.ReadfromCSV(fileName);

    }

  

    public static String getCount() {
        return count;
    }

    public static void setCount(String count) {
        MySQLConnector.count = count;
    }

    public static void CountResultset(Connection conn, String dbName, String tableName, String count) {

        try {
            String pstatement = "SELECT count(*) FROM " + dbName + "." + tableName;
            stmt = conn.createStatement();
            // PreparedStatement statement = conn.prepareStatement(pstatement);
            // statement.setString(1, host);
            // ResultSet rs = pstmt.executeQuery();

            rs = stmt.executeQuery(pstatement);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                String coffeeName = rs.getString(columnsNumber);
                for (int i = 1; i < columnsNumber; i++)
                    System.out.print(rs.getInt(i) + " ");
                System.out.println(coffeeName);
                // Send to print out or store as String
                count = coffeeName;
                
            }
            System.out.println(count);
            setCount(count); 

            // Close the database
        } catch (SQLException e) {
            throw new IllegalStateException("Issue with the query. Rework", e);

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore
                rs = null;
            }

        }
      

    }

}
