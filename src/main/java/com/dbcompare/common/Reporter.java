package com.dbcompare.common;


import java.io.File;
import java.io.IOException;

import org.openqa.selenium.SessionNotCreatedException;
//import org.openqa.selenium.remote.SessionNotFoundException;

import com.dbcompare.utility.Constants;
import com.dbcompare.utility.Driver;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class Reporter {
		
	private static ExtentReports report;
	private static ExtentTest parentLogger;
	private static ExtentTest childLogger;
	private static ExtentTest logger;
	private static String runReportDirectoryName;
	
	static {
		
		// Creating the unique run directory for each execution.
		File runReportDirectory = new File(Constants.REPORTS_PATH + "Run_" + Driver.getCurrentTimeStamp());
		
		// Capture the path of run directory to add screenshots
		runReportDirectoryName = runReportDirectory.getAbsolutePath();
		report = new ExtentReports(runReportDirectory + "\\report.html", true, DisplayOrder.NEWEST_FIRST);
		File configFile = new File(Constants.PROJECT_PATH + "\\extent-config.xml");
		report.loadConfig(configFile);
	}

	/**
	 * Gets the full path of the directory where report information is stored
	 */	
	public static String getReportDirectory() {
		return runReportDirectoryName;
	}		
	
	/**
	 * Checks if the file exists relative to the report directory.
	 * @param	relativeFilePath	The path to the file, relative to the current run directory.
	 * @return 	Returns true if the file exists in the relative file path; otherwise false. 
	 */
	public static boolean doesFileExistInReportDirectory(String relativeFilePath) {
		File file = new File(getReportDirectory() + "//" + relativeFilePath);
		boolean fileExist = file.exists();
		return fileExist;
	}	

	/**
	 * Marks the beginning of a group of tests that will be collected under a single group.
	 * @param groupName	Name of the test group.
	 */
	public static void startTestGroup(String groupName) {
		// Create a new logger for the parent
		parentLogger = report.startTest(groupName);
		// Set this logger as the active one
		logger = parentLogger;		
	}
			
	/**
	 * Marks the end of a group of tests.
	 */
	public static void endTestGroup() {
		
		if (parentLogger != null) {
			
			// Finalize the test group and flush to file
			report.endTest(parentLogger);
			report.flush();
			
			// Clear all remaining logger references.  A new test or test group will have to be created before logging again.
			parentLogger = null;
			logger = null;
		}
		
	}
	
	/**
	 * Marks the beginning of a test case.
	 * @param  testName		Name of the test
	 */
	public static void startTest(String testName) {
		// Start the test without categories
		startTest(testName, new String[0]);
	}
	
	/**
	 * Marks the beginning of a test case.
	 * @param  testName		Name of the test
	 * @param  category		Optional categories to associate with the test
	 */
	public static void startTest(String testName, String...category ) {
		
		// Start a new logger and cache as a child even if the parent logger is not defined
		childLogger = report.startTest(testName);
		
		// Switch to this logger being the active one
		logger = childLogger;
		
		// User can assign a single test to multiple categories for reporting purpose
		// Gets the list of categories and assigns them to test
		
		for(int i = 0; i < category.length; i++){
			String categoryToAssign = category[i];
			if (categoryToAssign != null && !categoryToAssign.isEmpty())
				logger.assignCategory(categoryToAssign);
			
		}

		logger.log(LogStatus.INFO, "Test Started", "Starting test '" + testName + "'");
	}
	
	/**
	 * Marks the end of a test.
	 */
	public static void endTest() {
		
		if (parentLogger != null) {
			
			// This test was grouped under a parent, so finalize the test by appending to the parent
			parentLogger.appendChild(childLogger);
			childLogger = null;
			
			// Switch logging back to the parent logger
			logger = parentLogger;
			
		} else {
			
			// This test was not grouped under a parent, so end the test
			report.endTest(logger);
			
		}
		
		// Flush the report to make sure the current test is written to file
		report.flush();
	}
		
	/**
	 * Initializes a complete test suite. 
	 */
	public static void startSuite() {
		// Nothing at this time
	}
	
	/**
	 * Finalizes a complete test suite.
	 */
	public static void endSuite() {
		// Make sure the report is closed
		if (report != null)
			report.close();
	}

	/**
	 * Logs an informational message to the report.
	 * 
	 * @param	message		The message to log.
	 */
	public static void logInfo(String message) {
		String stepName = null;
		logInfo(stepName, message);
	}	
	
	/**
	 * Logs an informational message to the report.
	 * 
	 * @param	stepName	The brief name of the step; if null or empty, the name 'Information' will be used.
	 * @param	message		The message to log.
	 */
	public static void logInfo(String stepName, String message) {
		boolean takeScreenshot = false;
		logInfo(stepName, message, takeScreenshot);
	}
	
	/**
	 * Logs an informational message to the report.
	 * 
	 * @param	stepName		The brief name of the step; if null or empty, the name 'Information' will be used.
	 * @param	message			The message to log.
	 * @param 	takeScreenshot 	Pass true to take screenshot to include in the report; otherwise false if a screenshot is not needed.
	 */
	public static void logInfo(String stepName, String message, boolean takeScreenshot) {
		if (stepName == null || stepName.isEmpty())
			stepName = "Information";
		System.out.println(String.format("[INFO] %s - %s", stepName, message));
		logger.log(LogStatus.INFO, stepName, message);

		// Include an additional step with the screenshot (if requested)
		if (takeScreenshot) {
		//	logScreenshot(LogStatus.PASS, stepName, message);
		}
		
	}
	
	/**
	 * Logs Pass test message to the report.
	 * 
	 * @param	stepName	The brief name of the step; if null or empty, the name 'Pass' will be used.
	 * @param	message		The message to log.
	 */	
	public static void logPass(String stepName, String message) {
		boolean takeScreenshot = false;
		logPass(stepName, message, takeScreenshot);
	}
	
	/**
	 * Logs Pass test message to the report.
	 * 
	 * @param	stepName		The brief name of the step; if null or empty, the name 'Pass' will be used.
	 * @param	message			The message to log.
	 * @param 	takeScreenshot 	Pass true to take screenshot to include in the report; otherwise false if a screenshot is not needed.
	 */	
	public static void logPass(String stepName, String message, boolean takeScreenshot) {
		if (stepName == null || stepName.isEmpty())
			stepName = "Pass";
		System.out.println(String.format("[PASS] %s - %s", stepName, message));
		logger.log(LogStatus.PASS, stepName, message);
		
		// Include an additional step with the screenshot (if requested)
		if (takeScreenshot) {
	//		logScreenshot(LogStatus.PASS, stepName, message);
		}
		
	}
	
	/**
	 * Logs Skip test message to the report.
	 * 
	 * @param	stepName	The brief name of the step; if null or empty, the name 'Skip Information' will be used.
	 * @param	message		The message to log.
	 */	
	public static void logSkip(String stepName,String message) {
		if (stepName == null || stepName.isEmpty())
			stepName = "Skip Information";
		System.out.println(String.format("[SKIP] %s - %s", stepName, message));
		logger.log(LogStatus.SKIP, stepName, message);
	}

	/**
	 * Logs Pass test message to the report.
	 * 
	 * @param	stepName	The brief name of the step; if null or empty, the name 'Pass' will be used.
	 * @param	message		The message to log.
	 */	
	public static void logWarning(String stepName, String message) {
		boolean takeScreenshot = false;
		logPass(stepName, message, takeScreenshot);
	}
	
	/**
	 * Logs Warning test message to the report.
	 * 
	 * @param	stepName		The brief name of the step; if null or empty, the name 'Warning' will be used.
	 * @param	message			The message to log.
	 * @param 	takeScreenshot 	Pass true to take screenshot to include in the report; otherwise false if a screenshot is not needed.
	 */	
	public static void logWarning(String stepName, String message, boolean takeScreenshot) {
		if (stepName == null || stepName.isEmpty())
			stepName = "Warning";
		System.out.println(String.format("[WARNING] %s - %s", stepName, message));
		logger.log(LogStatus.WARNING, stepName, message);
		
		// Include an additional step with the screenshot (if requested)
		if (takeScreenshot) {
	//		logScreenshot(LogStatus.WARNING, stepName, message);
		}
		
	}	
	
	/**
	 * Logs test Error message to the report.
	 * 
	 * @param	stepName	The brief name of the step; if null or empty, the name 'Error Information' will be used.
	 * @param	message		The message to log.
	 */	
	public static void logError(String stepName, String message) {
		logError(stepName, message, null);
	}

	/**
	 * Logs test Error message to the report.
	 * 
	 * @param	stepName	The brief name of the step; if null or empty, the name 'Error Information' will be used.
	 * @param	message		The message to log.
	 * @param 	t			Exception message to the log
	 */	
	public static void logError(String stepName, String message, Throwable t) {
		if (stepName == null || stepName.isEmpty())
			stepName = "Error Information";
		System.err.println(String.format("[ERROR] %s - %s", stepName, message));
		logger.log(LogStatus.ERROR, stepName, message);
		
		// Include an additional step with the throwable (if provided)
		if(t != null) {
			logger.log(LogStatus.FAIL, stepName,  t);
		}
		
	}

	/**
	 * Logs test Fail message to the report with a screenshot.
	 * 
	 * @param	stepName		The brief name of the step; if null or empty, the name 'Failure Information' will be used.
	 * @param	message			Brief description about the failure
	 */
	public static void logFail(String stepName, String message) {
		boolean takeScreenshot = true;
		logFail(stepName, message, takeScreenshot);
	}
	
	/**
	 * Logs test Fail message to the report.
	 * When a custom check fails, user can call this method to log the failure with or without screenshot including failure description. 
	 * 
	 * @param	stepName		The brief name of the step; if null or empty, the name 'Failure Information' will be used.
	 * @param	message			Brief description about the failure
	 * @param 	takeScreenshot 	Pass true to take screenshot to include in the report; otherwise false if a screenshot is not needed.
	 */
	public static void logFail(String stepName, String message, boolean takeScreenshot) {
		Throwable t = null;
		logFail(stepName, message, takeScreenshot, t);
	}

	/**
	 * Logs test Fail message to the report.
	 * When an assertion fails, it triggers the Failure of the test and this method will be called after. 
	 * 
	 * @param	stepName		The brief name of the step; if null or empty, the name 'Failure Information' will be used.
	 * @param	message			Brief description about the failure
	 * @param 	t				Exception message to the log
	 */
	public static void logFail(String stepName, String message, Throwable t) {
		boolean takeScreenshot = true;
		logFail(stepName, message, takeScreenshot, t);		
	}
	
	/**
	 * Logs test Fail message to the report.
	 * When an assertion fails, it triggers the Failure of the test and this method will be called after. 
	 * 
	 * @param	stepName		The brief name of the step; if null or empty, the name 'Failure Information' will be used.
	 * @param	message			Brief description about the failure
	 * @param 	takeScreenshot 	Pass true to take screenshot to include in the report; otherwise false if a screenshot is not needed.
	 * @param 	t				Exception message to the log
	 */
	public static void logFail(String stepName, String message, boolean takeScreenshot, Throwable t) {
		if (stepName == null || stepName.isEmpty())
			stepName = "Failure Information";

		System.err.println(String.format("[FAIL] %s - %s", stepName, message));

		logger.log(LogStatus.FAIL, stepName, message);
		
		// Include an additional step with the screenshot (if requested)
		if (takeScreenshot) {
			message = "errorScreenshot";
	//		logScreenshot(LogStatus.FAIL, stepName, message);
		}

		// Include an additional step with the throwable (if provided)
		if(t != null) {
			logger.log(LogStatus.FAIL, stepName,  t);
		}
	}
	
	/**
	 * Logs a screenshot to the report.
	 * @param status			The status of the message being logged.
	 * @param stepName			The name of the step.
	 * @param screenshotName	The name to use when describing the screenshot.
	 */
	/*
	 * private static void logScreenshot(LogStatus status, String stepName, String
	 * screenshotName) { try { String screenshotPath =
	 * saveScreenshotToResultsFolder(screenshotName); if (screenshotPath != null) {
	 * String screenShot = logger.addScreenCapture(screenshotPath);
	 * logger.log(status, stepName, screenShot); }else { logger.log(status,
	 * stepName); } } catch (IOException e) { logError("Capture Screenshot [" +
	 * stepName + "]", "Error capturing screenshot.  Error message = " +
	 * e.getMessage()); } catch (SessionNotCreatedException e) { // WebDriver is no
	 * longer available logError("Capture Screenshot [" + stepName + "]",
	 * "Error capturing screenshot.  WebDriver session is not available."); } }
	 */
	
	/**
	 * Captures the Screenshot of the current browser screen and saves the image in the current run results folder.
	 * @param	screenshotName	Name of the screenshot to be taken
	 * @return 					Returns the path of the captured screenshot if taken; otherwise null if not taken
	 */		
//	private static String saveScreenshotToResultsFolder(String screenshotName) throws IOException {

		// Combine the requested name with the current timestamp to make the file name unique
//		String uniqueFileName = screenshotName + "_" + Driver.getCurrentTimeStamp() + ".png";

		// TODO Need to make sure that the generated file name only includes valid file system characters

		// Store the screenshot in current execution run folder
//		File capturedFile = Driver.captureScreenshot(runReportDirectoryName + "\\" + uniqueFileName);
//		if (capturedFile == null) {
			// The current browser does not support screen capture.  No need to log anything.
//			return null;
//		}

		// Return the full path of the captured file
//		return capturedFile.getAbsolutePath();
//	}

}
