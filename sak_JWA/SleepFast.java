import java.util.ArrayList;

class FastSleeper extends Thread{
	private int sleepNumber;

	FastSleeper  (int sleepNumber_){
		sleepNumber = sleepNumber_;
	}

	public void sleep(){
		try{
			System.out.println(sleepNumber + " - Going to sleep");
			Thread.sleep(1000); 
			System.out.println(sleepNumber + " - done sleeping");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run() {
		sleep();
	}
}

public class SleepFast{
	public static void main() {
		System.out.println("\nNon-Threaded Sleep");
		long start = System.currentTimeMillis();


		FastSleeper sleeper0 = new FastSleeper(0);
		FastSleeper sleeper1 = new FastSleeper(1);


		sleeper0.sleep();
		sleeper1.sleep();
		System.out.println("Elapsed time = " + (System.currentTimeMillis()-start) + "\n");
		
		System.out.println("Threaded Sleep");
		start = System.currentTimeMillis();
		
		sleeper0.start();
		sleeper1.start();
		
		try {
			sleeper0.join();
			sleeper1.join();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Elapsed time = " + (System.currentTimeMillis()-start) + "\n");
		
		System.out.println("Non-Threaded ArrayList Sleep");
		ArrayList<FastSleeper> sleeperList = new ArrayList<FastSleeper>();
		for (int i = 2; i < 12; i++) {
			sleeperList.add(new FastSleeper(i));
		}
		
		start = System.currentTimeMillis();
		for (FastSleeper s: sleeperList) {
			s.sleep();
		}
		
		System.out.println("Elapsed time = " + (System.currentTimeMillis()-start) + "\n");

		
		System.out.println("Threaded ArrayList Sleep");
		
		start = System.currentTimeMillis();
		for (FastSleeper s: sleeperList) {
			s.start();
		}
		
		try {
			for (FastSleeper s:sleeperList) {
				s.join();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Elapsed time = " + (System.currentTimeMillis()-start) + "\n");



	}
}