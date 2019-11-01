import java.net.*;
import java.io.*;
import java.util.*;


public class HttpRequestIndex{
	private String requestURL;
	protected ArrayList<String> urlContent;

	HttpRequestIndex(){
		requestURL = "";
		urlContent = new ArrayList<String>();
	}

	public Boolean readUrl(String urlIn){
		requestURL = urlIn;
		Boolean returnValue = false;
		try{
			URL myURL = new URL(requestURL);
			URLConnection myConnection = myURL.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(myConnection.getInputStream()));
			String inputLine;
			while((inputLine = in.readLine()) != null){
				urlContent.add(inputLine);
				returnValue = true;
			}
			in.close();
		} catch(Exception e){
			returnValue = false;
			System.out.println("An exception occured in HttpRequestIndex");
			e.printStackTrace();
			System.out.println("URL: " +requestURL);
		}

		return returnValue;
	}

	public String toString(){
		String returnValue = "URL: " + requestURL;
		for (String s:urlContent){
			returnValue = returnValue + s + "\n";
		}
		return returnValue;
	}

	public void List(){
		
		try{
			URL myURL = new URL(requestURL);
			URLConnection myConnection = myURL.openConnection();
			URI myURI = new URI("file:/"+requestURL);
			System.out.println(myURI);

			File folder = new File(myURI);
			String[] fileList = folder.list();

			for (String file:fileList){
				System.out.println(file);

			}
		}catch(Exception e){
			System.out.println("An exception occured in HttpRequestIndex\n");
			e.printStackTrace();
			System.out.print(e);
			System.out.println("\nURL: " +requestURL);

		}



	}

	public static void main(String[] args){
		HttpRequestIndex request = new HttpRequestIndex();
		if (request.readUrl(args[0])){
			request.List();
		}
	}
}