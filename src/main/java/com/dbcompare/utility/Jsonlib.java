package com.dbcompare.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;

public class Jsonlib {
	
	public static JSONArray parseJsontoArray(String jsonmessage) {
	 	JSONParser parser = new JSONParser();
	 	JSONArray array = null;
	 	 try{
	         Object obj = parser.parse(jsonmessage);
	         array = (JSONArray)obj;    
	 	 }catch(ParseException pe){
	         System.out.println("position: " + pe.getPosition());
	         System.out.println(pe);
	      }
		return array;
	}
	
	
	public static Object getJsonObject(String jsonFilePath) throws IOException {
		Object json = null;
		try {
	        File jsonFile = getFileFromURL(jsonFilePath);
	        FileReader reader = new FileReader(jsonFile);
	        String jsonString = "";
	        int temp = 0;
	        while ((temp = reader.read()) != -1) {
	            if(temp != 10 && temp != 13 && temp != 9){
	                jsonString += (char) temp;
	            }
	        }
	        reader.close();
	        JSONParser parser = new JSONParser();
			json = parser.parse(jsonString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return json;
    }
	
	public static String getJsonFiledValue(String jsonfield, String jsonMsg) throws IOException {
		Object obj = null;
		String value= null;
		try {
	        JSONParser parser = new JSONParser();
	        obj = parser.parse(jsonMsg);
			JSONObject jsonObject = (JSONObject)obj;
			value = jsonObject.get(jsonfield).toString();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return value;
    }
    
    public static File getFileFromURL(String relativefilepath) throws FileNotFoundException {
        File file = null;
        try {

            System.out.println("filename in util : " + relativefilepath);
            //Get file from resources folder
           // ClassLoader classLoader = getClass().getClassLoader();
            //file = new File(classLoader.getResource(resourceName).getFile());
            file = new File(relativefilepath);
            System.out.println("filepath in util : " + file.getPath());
         
            if (file == null || !file.exists()) {
                throw new FileNotFoundException(relativefilepath);
            }
        //} catch (URISyntaxException e) {
        } catch (Exception e) {
            throw new FileNotFoundException(e.getLocalizedMessage());
        }
        return file;
    }
	
	

}
