package stepImplementation;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;

import com.dbcompare.common.connection.MySQLConnector;

public class CompareSteps {
    MySQLConnector connect = new MySQLConnector();

    @Given("Connection to Database")
    public void Connection_to_Database(String dbName, String username, String password, String tableName) {
        Connection conn = null;
        connect.Connector_One(dbName, username, password, conn);
        connect.SelectStatement(conn, tableName);
        // Reporter.logInfo("Connection to " + firstDb + " successful");
    }

    @Given("I print to resultFile{int}")
    public void i_print_to_resultFile(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();

    }

}