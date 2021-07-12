package com.dbcompare.dataprovider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReadCsv {

	private static String row;

	public static Iterator<Object[]> getCsvData(String fileName) {

		List<Object[]> csvData = new ArrayList<Object[]>();
		File file = new File(fileName);
		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader(file));
			line = br.readLine();
			while (line != null) {

				// use comma as separator
				Object[] token = line.split(",");
				if (token[0].equals("Y")) {

					// for ignoring 1st column(flag0)
					Object[] tokensWithoutFlag = new String[token.length - 1];

					for (int i = 1; i < token.length; i++) {
						tokensWithoutFlag[i - 1] = token[i];
					}
					csvData.add(tokensWithoutFlag);
					line = br.readLine();
				} else {
					line = br.readLine();
				}
			
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return csvData.iterator();
	}

	public static void ReadfromCSV(String fileName) throws IOException {
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

}
