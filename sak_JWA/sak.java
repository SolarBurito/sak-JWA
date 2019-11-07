

import java.util.Scanner;
import java.lang.String;



public class sak{
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		String userIn = "";
		String cmd = "";
		String[] cmdArg = new String[1];

		System.out.println("Welcome to SwissArmyKnife\nType 'help' to see a list of commands\nType 'exit' to quit");

		

		while (userIn.compareTo("exit") != 0){
			userIn = scanner.nextLine();
			int cmdEnd = userIn.indexOf(" ");

			if (cmdEnd != -1){
				cmd = userIn.substring(0, cmdEnd);
				cmdArg[0] = userIn.substring(cmdEnd+1,userIn.length());
			}

			if (userIn.compareTo("help") == 0){
				System.out.println("help\nHttpRequest\nHttpRequestIndex\n");
				System.out.print("Examples:\nHttpReques [URL]\nHttpRequestIndex [URL]\n");
			}else if(cmd.compareTo("HttpRequest") == 0){
				System.out.print("HttpRequest" + cmdArg);
				HttpRequest.main(cmdArg);
			}else if (cmd.compareTo("HttpRequestIndex") == 0){
				System.out.print("HttpRequestIndex" + cmdArg);
				HttpRequestIndex.main(cmdArg);
			}else {
				System.out.println("help\nHttpRequest\nHttpRequestIndex\n");
				System.out.print("Examples:\nHttpReques [URL]\nHttpRequestIndex [URL]\n");
			}
		}
		scanner.close();
	}
}