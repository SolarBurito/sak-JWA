

import java.net.*;
import java.io.*;
import java.util.*;
import org.json.*;


/**
 * 
 * @author James Archey
 * @version 1.0
 * 
 * This Class uses the JSON-Java libraries JSONObject and JSONArray classes
 */



public class HttpRequestIndex{
	private String requestURL;
	protected ArrayList<String> urlContent;
	
	HttpRequestIndex(){
		requestURL = "";
		urlContent = new ArrayList<String>();
	}

	HttpRequestIndex(String urlIn){
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
			System.out.print("Failed to Connect to URL");
			e.printStackTrace();
		}
	}

	//Creates String with the WebPage URL and content
	public String toString(){
		String returnValue = "URL: " + requestURL + "\n";
		
		for (String s:urlContent){
			returnValue = returnValue + s + "\n";
		}
		return returnValue;
	}

	public void List(){
		//Creates HttpRequest and prints content before parsing JSON
		HttpRequestIndex listRequest = new HttpRequestIndex(requestURL);
		System.out.println(listRequest);
		
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
					
					//If the content of any key contains "http" then create a new HttpRequest of the found url, and print it.
					if (content.contains("http")) {
						HttpRequestIndex tempReq = new HttpRequestIndex(content);
						System.out.println(tempReq);
						
					}
				}
			}
		}catch(JSONException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		HttpRequestIndex request = new HttpRequestIndex(args[0]);
		request.List();

	}
}