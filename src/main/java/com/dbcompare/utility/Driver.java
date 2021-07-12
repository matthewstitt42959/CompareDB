package com.dbcompare.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.dbcompare.common.DriverBase;
import com.dbcompare.common.Timer;

public class Driver extends DriverBase {
	
	/**
	 * Waits for the modal 'spinner' to go way since the spinner indicates content is loading and blocks access to the page.
	 */
	public static void waitOnModalSpinner() {
		
		System.out.println("Waiting for modal spinner to not be displayed...");
		
		Timer timer = new Timer();		
		
		By spinnerLocator = By.id("spinner-modal");

		// Wait briefly to see if the spinner is going to be displayed
		try {
		//	storeCurrentWait();
		//	turnOnShortWait();
			
		//	waitForElement(spinnerLocator);
			
		} catch (NoSuchElementException e) {
			// Nothing to do if Spinner is not available
			System.out.println(String.format("\tSpinner not displayed after %d seconds, resuming execution", timer.getElapsedSeconds()));
			return;
		} finally {
		//	restorePreviousWait();
		}

		// Now that we know the spinner is visible, wait for the spinner to go away before continuing (potentially long-runner operation)
		try {
		//	storeCurrentWait();
		//	turnOnLongWait();
			
			System.out.println("\tSpinner found, waiting for spinner to hide...");
		//	waitWhileElementVisible(spinnerLocator);
			
			System.out.println(String.format("\tSpinner no longer displayed after %d seconds", timer.getElapsedSeconds()));
		} finally {
		//	restorePreviousWait();
		}
		
	}
	
	

}