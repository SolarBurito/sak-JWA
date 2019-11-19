# sak-JWA
SwissArmyKnife is a command line tool, with multiple features implementied in seperate java files. It is designed to be easy to create a new java class and add the functionality to this tool.

List of Implemented classes, how to use them, and their purpose.
-HttpRequest [URL] - Connects to a website and displays the index file associated with the passed URL
-HttpRequestIndex [URL] - Connects to a website, scans a JSON file for additional URLs and makes an HttpRequest for each of the found URL
-Sleep- Simply does nothing for one second.
-SleepFast - Runs 20 instances of sleep, the first 10 normally, and the second 10 threaded to compare the time taken.
-SleepFastImplementsRunnable- Similar to SleepFast, but uses the Runnable interface instead of the Thread class.
-JSONValidateIndex [URL] - Connects to URL, scans a JSON file for more URLs and validates the JSON Syntax. Reports location of Errors 
-JSONValidateIndexThreaded [URL] - A threaded version of JSONValidateIndex.
