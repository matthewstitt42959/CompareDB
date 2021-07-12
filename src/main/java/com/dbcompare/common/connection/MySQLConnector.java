package com.dbcompare.common.connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.sql.PreparedStatement;

import com.dbcompare.dataprovider.ReadCsv;
import com.mysql.cj.mysqla.result.ResultsetRowsStreaming;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.seleniumhq.jetty9.server.Connector;

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
    private static String row;
    private static String count;


    public static void main(String[] args) throws IOException {
        Connector(host, dbName, userName, password, tableName);
        dbName = dbName2;  
        Connector(host, dbName2, userName, password, tableName);
        resultString = count; 
        Compare_Results(resultString);
    }

    private static void Compare_Results(String resultString) throws IOException {
        // To capture the result and save it for comparison.
        // Get first result
        // Save to csv?
        
        WriteToCSV(resultString, fileName, count);
        ReadfromCSV(fileName);

    }

    private static void ReadfromCSV(String fileName) throws IOException {
        String pathToCsv = "src//test//java//com//dbcompare//TestDataFiles//users-with-header.csv";
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            String hi = data.toString(); 
            System.out.println(hi);
        }
        csvReader.close();
        // create BufferedReader and read data from csv
    }

    public static String getCount() {
        return count;
    }

    public static void setCount(String count) {
        MySQLConnector.count = count;
    }

    private static String WriteToCSV(String resultString, String fileName, String count) throws IOException {
        
        try {
            // create a list of objects
            List<List<String>> records = Arrays.asList(Arrays.asList("1", resultString)

            );
            //Increment csv name - temp setup
            int i = 1;
            while (i <= 5) {
                System.out.println(i);
                i++; // add 1 to i
            }

            // create a writer
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("users-with-header" + i + ".csv"));

            // write header record
            writer.write("ID,Name,Country");
            writer.newLine();

            // write all records
            for (List<String> record : records) {
                writer.write(String.join(",", record));
                writer.newLine();
            }

            // close the writer
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileName;
    }

    public static Connection Connector(String host, String dbName, String userName, String password, String tableName) {

        try (Connection conn = DriverManager.getConnection(host, userName, password)) {
            System.out.println(dbName + "" + " connected!");
           
            CountResultset(conn, dbName, tableName, count);
            CloseConnection(conn);

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore
                rs = null;
            }
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
