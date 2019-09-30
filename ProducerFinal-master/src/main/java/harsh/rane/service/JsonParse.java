package harsh.rane.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import harsh.rane.controller.ProducerController;
import harsh.rane.model.Patient;

@Service
public class JsonParse {

	private static final Logger LOGGER = LogManager.getLogger(ProducerController.class);
	
	public Patient getLocalJson()
	{
		JSONParser jsonParser = new JSONParser();	
		Object obj;
	
		try 
		{
			obj = jsonParser.parse(new FileReader("D:\\patient.json")); //Reading Json File
			JSONObject patientInfo = (JSONObject) obj;					// JSON Object
			JSONArray address = (JSONArray) patientInfo.get("address"); //JSON Array
			Object object = address.get(0);
			Object object1 = address.get(1);
			JSONObject employeeList1 = (JSONObject) object; 
			JSONObject employeeList2 = (JSONObject) object1;
		
			Patient patient=new Patient((String) patientInfo.get("id"),(String) patientInfo.get("name"),
  		      (String) patientInfo.get("surname"),(String) patientInfo.get("email"),
	 	      (String) employeeList1.get("city"),(String) employeeList2.get("city"));   //setting values in Patient Object
		
			LOGGER.info("JSON Parsed returned object");
			return patient;						//Returning Patient Object
		}
		catch (FileNotFoundException e) 
		{
			LOGGER.error("File is not present in E:\\");
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			LOGGER.error("File is not present in E:\\");
			e.printStackTrace();
		} 
		catch (ParseException e) 
		{
			LOGGER.error("JSON Parsing problem");
			e.printStackTrace();
		}
			return null;
}
}