package com.dbcompare.dataprovider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;
import org.w3c.dom.Node;

import com.dbcompare.dataprovider.ReadCsv;
import com.dbcompare.dataprovider.ReadXml;
import com.dbcompare.utility.Constants;
import com.dbcompare.utility.TestData;

public class DataProviderUtils {

	//Data provider for various types of files.. So far for EXCEL, CSV and XML
	@DataProvider(name = "dataprovider")
	public static Iterator<Object[]> genericDataProvider(Method method, ITestContext textContext) throws Exception {
		
		String dataProviderFile = TestData.DEFAULT_TEST_DATA_FILE_TYPE;	
		
		//Switches based on the file type
		switch (dataProviderFile) {

		case "CSV":
			
			String csvFilePath = Constants.TESTDATA_FILE_PATH + method.getName() + ".csv";
			//Reads the CSV file and return the data
			return ReadCsv.getCsvData(csvFilePath);
			
		case "XML":

			String xmlFilePath = Constants.TESTDATA_FILE_PATH + method.getName() + ".xml";
			//Reads the XML file and return the data
			return ReadXml.getXmlData(xmlFilePath);
			
		case "EXCEL":				
				
			 // String filepath =System.getProperty("user.dir")+"\\TestDataFiles\\";
			  String filepath = Constants.TESTDATA_FILE_PATH + method.getName() + ".xlsx";
			  Iterator<Object[]> ExcelreturnArray = ReadExcel.getExceldata(filepath, "Sheet1");       
			  return (ExcelreturnArray);
			  }

		
		return null;

	}
}