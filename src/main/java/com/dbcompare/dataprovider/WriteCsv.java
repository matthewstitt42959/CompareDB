package com.dbcompare.dataprovider;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

	private List<String>[] rows;

	public void writeToCSV() throws IOException{
		FileWriter csvWriter = new FileWriter("new.csv");
	csvWriter.append("Name");
	csvWriter.append(",");
	csvWriter.append("Role");
	csvWriter.append(",");
	csvWriter.append("Topic");
	csvWriter.append("\n");

for (List<String> rowData : rows) {
    csvWriter.append(String.join(",", rowData));
    csvWriter.append("\n");
}

	csvWriter.flush();
	csvWriter.close();
	}
	

}
