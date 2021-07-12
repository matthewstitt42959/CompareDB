package com.dbcompare.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.dbcompare.utility.Driver;


/*
 * This verifies the object model of dresses page
 */
public class SQLObjects {
	

	// ********************************************************************************
    // Object Model
    // ********************************************************************************
	
	/*
	 * This tests if the Page is displayed
	 * @param : 
	 * @return : returns true if this displayed. Otherwise false
	 */
//	public static boolean isAt() {

//		boolean isDressDisplayed = ElementActions.isDisplayed(SQLObjects.getDressCategory());
//		return isDressDisplayed;
//	}
	
	/*
	 * Navigates to this page 
	 */
	public static void gotoDresses() {
//		ElementActions.clickElement("Click on Dresses Tab", SQLObjects.getDresses());
//		ElementActions.clickElement("Click on Dresses Tab", Dresses.getDresses("Dresses"));
	}
	

	
	// ********************************************************************************
    // Object Recognition
    // ********************************************************************************
//	private static WebElement getDresses(String linkText) {
//		return Driver.instance.findElement(By.linkText(linkText));
//	}
	
}