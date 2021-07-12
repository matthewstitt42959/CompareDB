package com.dbcompare.dataprovider;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import cucumber.api.java.hr.I;

public class WriteCsv {

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

 	public static String WriteToCSV(String resultString, String fileName, String count) throws IOException {
        
        try {
            // create a list of objects
            List<List<String>> records = Arrays.asList(Arrays.asList("1", resultString)

            );
            //Attempt to create unique filename. Produce random number
			int min = 1;
			int max = 10;
			int ranStr = 0; 
			for(int i = min; i <=max; i++) {
			ranStr = (int) (Math.random()*(max-min)) + min;
			System.out.println(ranStr);
			}
	

            // create a writer
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("users-with-header" + ranStr + ".csv"));

            // write header record
            writer.write("MySQl count number");
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

}
