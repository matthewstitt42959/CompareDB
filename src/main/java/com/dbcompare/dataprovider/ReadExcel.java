package com.dbcompare.dataprovider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	 
	public static Iterator<Object[]> getExceldata(String filePath, String Sheetname) throws Exception
	{
		List<Object[]> excelData = new ArrayList<Object[]>();
		String[][] arraydata = null;
		try {
			FileInputStream fs = new FileInputStream(filePath);
			wb= new XSSFWorkbook(fs);
			sheet = wb.getSheet(Sheetname);
			int totalrows = sheet.getLastRowNum();
			int totalCols = sheet.getRow(0).getPhysicalNumberOfCells();
			 arraydata = new String[totalrows][totalCols-1];
			 int k = 0;
			for(int i=1;i<totalrows+1;i++){
				Object[] tokensWithoutFlag = new String[totalCols-1];
				List<Object> list = new ArrayList<Object>();
				if (getcelldata(i,0).equals("Y")) {	
					for(int j=1;j<totalCols;j++)	{ 
						arraydata[i-1][j-1] = getcelldata(i,j);
						list.add(arraydata[i-1][j-1]);
						Object a = arraydata[i-1][j-1];
						tokensWithoutFlag[j-1] = a;		
						
						System.out.println(excelData);			
					}
					excelData.add(k,tokensWithoutFlag);
					k++;
				}
			}	
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return excelData.iterator();
	}
	private static String getcelldata(int i, int j) throws Exception{
		// TODO Auto-generated method stub
		try{			 
			cell = sheet.getRow(i).getCell(j);
			String CellData = cell.getStringCellValue();
			return CellData;
		}
			catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw (e);
		}
	}
}

	
	


