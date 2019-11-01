import java.net.URL;
import java.net.URI;
import java.net.URLConnection;
import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.*;


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
		
		
		URL myURL = new URL(requestURL);
		URLConnection myConnection = myURL.openConnection();
		URI myURI = new URI(requestURL);


		

		try (Stream<Path> walk = Files.walk(Paths.get(myURI))){
			List<String> result = walk.filter(Files::isRegularFile)
				.map(x -> x.toString()).collect(Collectors.toList());
			result.forEach(System.out::println);
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		HttpRequestIndex request = new HttpRequestIndex();
		if (request.readUrl(args[0])){
			request.List();
		}
	}
}