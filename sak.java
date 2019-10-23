import java.util.Scanner;
import java.lang.String;

public class sak{
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		String userIn;
		String cmd = "";
		String cmdArg = "";

		System.out.println("Welcome to SwissArmyKnife\nType 'help' to see a list of commands");

		userIn = scanner.nextLine();

		int cmdEnd = userIn.indexOf(" ");

		if (cmdEnd != -1){
			cmd = userIn.substring(0, cmdEnd);
			cmdArg = userIn.substring(cmdEnd+1,userIn.length());
		}

		if (userIn.compareTo("help") == 0){
			System.out.println("help\nhttprequest\nhttprequestindex");
		}else if(cmd.compareTo("httprequest") == 0){
			HttpRequest.main(cmdArg);
		}
	}
}