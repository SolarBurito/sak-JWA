

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
				System.out.print("\nHttpReques [URL]\nHttpRequestIndex [URL]\nSleep\nSleepFast\nSleepFastImplementsRunnable\nJSONValidateIndex [URL]\nJSONValidateIndexThreaded [URL]\n");
			}else if(cmd.compareTo("HttpRequest") == 0){
				HttpRequest.main(cmdArg);
			}else if (cmd.compareTo("HttpRequestIndex") == 0){
				HttpRequestIndex.main(cmdArg);
			}else if(userIn.compareTo("Sleep") == 0) {
				Sleep.main();
			}else if(userIn.compareTo("SleepFast") == 0) {
				SleepFast.main();
			}else if(userIn.compareTo("SleepFastImplementsRunnable") == 0) {
				SleepFastImplementsRunnable.main();
			}else if (cmd.compareTo("JSONValidateIndex") == 0){
				JSONValidateIndex.main(cmdArg);
			}else if (cmd.compareTo("JSONValidateIndexThreaded") == 0){
				JSONValidateIndexThreaded.main(cmdArg);
			}else {
				System.out.print("\nHttpReques [URL]\nHttpRequestIndex [URL]\nSleep\nSleepFast\nSleepFastImplementsRunnable\nJSONValidateIndex [URL]\nJSONValidateIndexThreaded [URL]\n");
			}
		}
		scanner.close();
	}
}