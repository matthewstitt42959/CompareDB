package com.dbcompare.utility;

import org.apache.commons.lang3.NotImplementedException;

//import com.dbcompare.common.LoginInformation;
import com.dbcompare.common.Reporter;

/**
 * Manages data used when executing tests.
 *
 * @author Amrutharaja
 *
 */
public class TestData {

	/**
	 * Defines the available environments.
	 * @author Amrutharaja
	 *
	 */
	public enum Environment {
		UNSPECIFIED,
		STAGING,
		QA,
		PREPROD,
	}
	
	/**
	 * Defines the available frameworks.
	 * @author Amrutharaja
	 *
	 */
	public enum Framework {
		UNSPECIFIED,
		BDD,
		TESTNG,
	}
	
	/**
	 * Defines the available application types.
	 * @author Amrutharaja
	 *
	 */
	public enum ApplicationType {
		UNSPECIFIED,
		WEB,
		API,
		WEB_API,
		MOBILE_WEB,
		MOBILE_NATIVE,
	}
	
	/**
	 * Defines the available tools.
	 * @author Amrutharaja
	 *
	 */
	public enum Tool {
		UNSPECIFIED,
		SELENIUM,
		WATIR,
	}
	
	/**
	 * Defines the available tools.
	 * @author Amrutharaja
	 *
	 */
	public enum TestDataFileType {
		UNSPECIFIED,
		XML,
		CSV,
		EXCEL,
	}
	
	

	// The default environment to use when one is not specified
	// NOTE: Must exactly match name and casing of TestData.Environment enum
	public static final String DEFAULT_ENVIRONMENT_NAME = "QA";

	// The default browser to use when one is not specified
	// NOTE: Must exactly match name and casing of the DriverBase.BrowserType enum
	public static final String DEFAULT_BROWSER_NAME = "CHROME";
	// "IE","FIREFOX","ANDROID","ANDROIDWEB","IOS","IOSWEB"
	
	//The default framework to use when one is not specified
	// NOTE: Must exactly match name and casing of the TestData.Framework enum
	public static final String DEFAULT_FRAMEWORK = "TESTNG";
	
	//The default application type to use when one is not specified
	// NOTE: Must exactly match name and casing of the TestData.ApplicationType enum
	public static final String DEFAULT_APPLICATION_TYPE = "WEB";
	
	//The default tool to use when one is not specified
	// NOTE: Must exactly match name and casing of the TestData.Tool enum
	public static final String DEFAULT_TOOL = "SELENIUM";
	
	//The default tool to use when one is not specified
	// NOTE: Must exactly match name and casing of the TestData.Tool enums ["CSV, "XML", "EXCEL"]
	public static final String DEFAULT_TEST_DATA_FILE_TYPE = "CSV";
	
	// Staging Environment
	private static final String BASE_ADDRESS_STAGING = "http://www.gmail.com";
	private static final String DEFAULT_USER_NAME_STAGING = "hcapoc4@gmail.com";
	private static final String DEFAULT_PASSWORD_STAGING = "Nexient123";

	// QA Environment
	//private static final String BASE_ADDRESS_QA = "https://portal.nexient.com/gateway/api/";
	//mstitt temp changes made 6/23
	//Prod Environment 
	private static final String BASE_ADDRESS_QA = "http://portal.nexient.com/";
	//private static final String BASE_ADDRESS_QA = "http://nxpdev.corp.nexient.com/";
	private static final String DEFAULT_USER_NAME_QA = "mstitt";
	private static final String DEFAULT_PASSWORD_QA = "@w3s0m3P0ssum2400";
	// Temp password for mstitt dev only
	//private static final String DEFAULT_PASSWORD_QA = "Welcome75%$32";
	
	
	// QA Environment
//	private static final String BASE_ADDRESS_QA = "https://devqa.corp.nexient.com/#!/home";
//	private static final String DEFAULT_USER_NAME_QA = "qausername";
//	private static final String DEFAULT_PASSWORD_QA = "qapassword";
	
	
	 // Preprod Environment
    private static final String BASE_ADDRESS_PREPROD = "http://localhost:8080/v2/flow";
    private static final String DEFAULT_USER_NAME_PREPROD = "preprodusername";
    private static final String DEFAULT_PASSWORD_PREPROD = "preprodusername";

  
    private static final String BASE_URI_PROD = "";
    private static final String BASE_URI_PREPROD = "";

	
	// Fields
	private static String baseAddress;
	//private static LoginInformation userAccount;

	//Mobile Configurations Settings
	public static final String Type = "MobileApp";
	public static final String androidApkFile ="com.whatsapp_Android-2.1.apk";
	public static final String androidDeviceName ="motorola-xt1080-TA6270HW6Z";
	public static final String androidPlatformVersion ="4.4.4";
	public static final String androidAppPackage ="com.android.chrome";
	public static final String androidAppActivity ="org.chromium.Chrome.browser.ChromeTabbedActivity";
	public static final String androidBrowser = "Chrome";

	public static final String iosIpaFile ="EriBank.ipa";
	public static final String iosDeviceName ="motorola-xt1080-TA6270HW6Z";
	public static final String iosPlatformVersion ="4.4.4";
	public static final String iosappPackage ="com.android.chrome";
	public static final String iosAppActivity ="org.chromium.Chrome.browser.ChromeTabbedActivity";
	public static final String iosBrowser = "Chrome";
	
	/**
	 * The base address of the application.
	 */
	public static String getBaseAddress() {
		return baseAddress;
	}

	/**
	 * Gets the login information for the default account.
	 */
	//public static LoginInformation getDefaultAccount() {
	//	return userAccount;
	//}
	
	/**
	 * Gets the currently configured environment.
	 * @return	Returns the currently configured environment if recognized.
	 */
	public static Environment getConfiguredEnvironment() {
		if (baseAddress.equalsIgnoreCase(BASE_ADDRESS_QA))
			return Environment.QA;
		if (baseAddress.equalsIgnoreCase(BASE_ADDRESS_STAGING))
			return Environment.STAGING;
		if (baseAddress.equalsIgnoreCase(BASE_ADDRESS_PREPROD))
			return Environment.PREPROD;
		return Environment.UNSPECIFIED;
	}
	
	/**
	 * Gets the currently configured environment.
	 * @return	Returns the currently configured environment if recognized.
	 *//*
	public static Environment getConfiguredFramework () {
		if (baseAddress.equalsIgnoreCase(BASE_ADDRESS_QA))
			return Environment.QA;
		if (baseAddress.equalsIgnoreCase(BASE_ADDRESS_STAGING))
			return Environment.STAGING;
		return Environment.UNSPECIFIED;
	}*/
	
	
	/**
	 * Assigns default values for the specified environment
	 * @param environmentName	The name of the environment (e.g. 'QA' or 'Staging')
	 */
	public static void assignEnvironmentDefaults(String environmentName) {
		Environment environment = Environment.valueOf(environmentName);
		assignEnvironmentDefaults(environment);
	}

	/**
	 * Assigns default values for the specified environment
	 * @param environment		The environment
	 */
	public static void assignEnvironmentDefaults(Environment environment) {
		Reporter.logInfo("Environment Defaults '" + environment + "'", "Assigning default values to be used for the '" + environment + "' environment.");
		switch (environment) {

		case QA:
			assignQAEnvironmentDefaults();
			break;
			
		case STAGING:
			assignStagingEnvironmentDefaults();
			break;
			
		case PREPROD:
			assignPreprodEnvironmentDefaults();
			break;
			
		default:
			throw new NotImplementedException("The environment '" + environment + "' has not been implemented.");
		
		}
	}

	/**
	 * Configures test data with defaults for the staging environment.
	 */
	private static void assignStagingEnvironmentDefaults() {
		
		baseAddress = BASE_ADDRESS_STAGING;
		
	//	userAccount = new LoginInformation(DEFAULT_USER_NAME_STAGING,
	//			DEFAULT_PASSWORD_STAGING);
	}

	/**
	 * Configures test data with defaults for the QA environment.
	 */
	private static void assignQAEnvironmentDefaults() {
		
		baseAddress = BASE_ADDRESS_QA;
		
	//	userAccount = new LoginInformation(DEFAULT_USER_NAME_QA,
	//			DEFAULT_PASSWORD_QA);
	
	}
	
	/**
	 * Configures test data with defaults for the QA environment.
	 */
	private static void assignPreprodEnvironmentDefaults() {
		
		baseAddress = BASE_ADDRESS_PREPROD;
		
	//	userAccount = new LoginInformation(DEFAULT_USER_NAME_PREPROD,
	//			DEFAULT_PASSWORD_PREPROD);
	
	}

}
