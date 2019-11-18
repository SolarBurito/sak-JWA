

import java.net.*;
import java.io.*;
import java.util.*;
import org.json.*;
import java.time.*;


/**
 * 
 * @author James Archey
 * @version 1.0
 * 
 * This Class uses the JSON-Java libraries JSONObject and JSONArray classes
 */



public class JSONValidateIndex{
	private String requestURL;
	protected ArrayList<String> urlContent;
	
	JSONValidateIndex(){
		requestURL = "";
		urlContent = new ArrayList<String>();
	}

	JSONValidateIndex(String urlIn){
		//Initializes URL and Content variables
		requestURL = urlIn;
		urlContent = new ArrayList<String>();
		
		//Tries to connect to web page and copy content into an ArrayList
		try {
			URL myURL = new URL(requestURL);	//Creating URL object
			URLConnection myConnection = myURL.openConnection();	//Opening web connection
			BufferedReader stream = new BufferedReader(new InputStreamReader(myConnection.getInputStream()));	//Creating Reader to read input stream
			String inputLine;
			
			//Writes each line to an element in an ArrayList
			while ((inputLine = stream.readLine()) != null) {
				urlContent.add(inputLine);
			}
			stream.close();
			
		}catch(Exception e) {
			System.out.print("Failed to Connect to URL: " + requestURL + "\n");
		}
	}

	//Creates String with the WebPage URL and content
	public String toString(){
		String returnValue = "";
		
		for (String s:urlContent){
			returnValue = returnValue + s + "\n";
		}
		return returnValue;
	}

	public void List(){
		//Creates HttpRequest and prints content before parsing JSON
		JSONValidateIndex listRequest = new JSONValidateIndex(requestURL);

		
		//Begin Parsing
		try {
			JSONArray myJSON = new JSONArray(listRequest.urlContent);	//Recording URL content into JSONArray
			
			//Iterates through JSONArray, creating a JSONObject for each element
			for (int i = 1; i < myJSON.length()-1;i++){
				JSONObject tempJSON = new JSONObject(myJSON.getString(i));
				Iterator keys = tempJSON.keys();
				
				//Iterates through the keys of each JSONObject  
				while (keys.hasNext()) {
					String key = (String) keys.next();
					String content = tempJSON.getString(key);
					
					//If the content of any key contains "http" then create a new HttpRequest of the found url, and validates it.
					if (content.contains("http")) {
						JSONValidateIndex tempReq = new JSONValidateIndex(content);
						try {
							JSONObject checkJSON = new JSONObject(tempReq.toString());
							Validate(checkJSON, tempReq);
						}catch(Exception e) {
							System.out.println("Error occured while attempting to parse JSON data from " + content + " from " + tempJSON.getString("Name") + "---" + tempJSON.getString("Email"));
						}

					}
				}
			}
		}catch(JSONException e) {
			e.printStackTrace();
		}
	}
	
	public void Validate(JSONObject myJSON, JSONValidateIndex myReq) {
		//First and Last Name
		try {
			if (myJSON.getString("firstName").length() < 2 || myJSON.getString("lastName").length() < 2 || myJSON.getString("firstName").length() > 16 || myJSON.getString("lastName").length() > 16) {
				System.out.print("Invalid JSON --- " +"First Name: " + myJSON.getString("firstName") + " Last Name: " + myJSON.getString("lastName") +"\n");
			}
		}catch (Exception e) {
			System.out.println("First or Last name not found in " + myReq.requestURL);
		}
		
		//EMAIL
		try {
			String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
			if (!myJSON.getString("email").matches(regex)) {
				System.out.println("Invalid JSON --- '" + "Email: " +myJSON.getString("Email") + " -From " + myReq.requestURL + "\n");
			}
		}catch(Exception e) {
			System.out.println("Email not found in " + myReq.requestURL);
		}
		
		//Preferred Name 
		try {
			if (myJSON.getString("preferredName").length() < 2 || myJSON.getString("preferredName").length() > 16) {
				System.out.print("Invalid JSON --- " + "Preferred Name: " + myJSON.getString("preferredName") + " -From " + myReq.requestURL + "\n");
			}
		}catch(Exception e) {
			
		}

		
	}

	public static void main(String[] args){
		long start = System.currentTimeMillis();
		
		JSONValidateIndex request = new JSONValidateIndex(args[0]);
		request.List();
		
		long timeTaken = System.currentTimeMillis() - start;
		System.out.println("\n\nExecution Time : " + timeTaken);

	}
}