package com.dbcompare.cucumber.testrunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

//import com.dbcompare.common.Assert;
//import com.dbcompare.common.LoginInformation;
//import com.dbcompareproject.common.Reporter;
//import com.dbcomparet.pageObjects.LoginPage;
//import com.dbcompare.testCases.TestBase;
import com.dbcompare.utility.Driver;
import com.dbcompare.utility.TestData;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.junit.Cucumber;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import com.dbcompare.common.connection.MySQLConnector; 


@SuppressWarnings("deprecation")
@RunWith(Cucumber.class)	
@CucumberOptions(
		features={"features/compareMySql.feature"},
		plugin = {
		        "html:target/cucumber-html-report",     //to generate different types of reporting
		        "json:target/cucumber.json",
		        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		},
		glue="/stepImplementation")
		//src\test\java\stepImplementation\CompareSteps.java
public class TestRunner {
	
	/*
	 * public static TestBase testBaseobj =null;
	 * 
	 * @BeforeClass //@Test public static void beforeStory() {
	 * System.out.println("Before Feature");
	 * 
	 * // Reporter starts the test here String environment =
	 * TestData.DEFAULT_ENVIRONMENT_NAME; String browser =
	 * TestData.DEFAULT_BROWSER_NAME;
	 * 
	 * testBaseobj = new TestBase();
	 * Reporter.startTest("Test Class Initialization"); try {
	 * Reporter.logInfo("Begin Test Class Initialization",
	 * "The following steps are executed before all tests defined in the same test class to prepare them for execution."
	 * );
	 * 
	 * // Configure test data based on environment
	 * TestData.assignEnvironmentDefaults(environment);
	 * 
	 * // Configure driver Driver.initialize(browser);
	 * Driver.instance.manage().window().maximize(); //
	 * Driver.setBaseAddress(TestData.getBaseAddress());
	 * 
	 * // Login (if information available) LoginInformation loginInformation =
	 * testBaseobj.getLoginInformation(); if (loginInformation != null) {
	 * 
	 * // Launch the application LoginPage.goTo();
	 * 
	 * // Authenticate Assert.isTrue(LoginPage.isAt(),
	 * "The application should display the Login page.");
	 * LoginPage.authenticateAs(loginInformation).login();
	 * 
	 * }
	 * 
	 * Reporter.logInfo("End Test Class Initialization", null);
	 * 
	 * } finally { Reporter.endTest(); }
	 * 
	 * }
	 * 
	 * @AfterClass public static void afterAllTestMethods() { File
	 * reportOutputDirectory = new File("target"); List<String> jsonFiles = new
	 * ArrayList<>(); jsonFiles.add("target/cucumber.json"); String buildNumber =
	 * "1"; String projectName = "NexPort-Cucumber"; try { Configuration
	 * configuration = new Configuration(reportOutputDirectory, projectName);
	 * configuration.setBuildNumber(buildNumber);
	 * 
	 * ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
	 * reportBuilder.generateReports(); // NOTE: While debugging tests, it can be
	 * beneficial to leave session open to assess any error state at the end of the
	 * run // NOTE: If the driver is not closed, processes will be left running on
	 * the machine Driver.close(); }catch (Exception ex){ System.out.println();
	 * 
	 * } finally { Reporter.endTestGroup(); } }
	 */

}
