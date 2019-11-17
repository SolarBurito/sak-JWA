import java.util.ArrayList;

class FastSleeperIR implements Runnable{
	private int sleepNumber;

	FastSleeperIR  (int sleepNumber_){
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

public class SleepFastImplementsRunnable{
	public static void main(String[] args) {
		System.out.println("\nNon-Threaded Sleep");
		long start = System.currentTimeMillis();


		FastSleeperIR sleeper0 = new FastSleeperIR(0);
		FastSleeperIR sleeper1 = new FastSleeperIR(1);


		sleeper0.sleep();
		sleeper1.sleep();
		System.out.println("Elapsed time = " + (System.currentTimeMillis()-start) + "\n");
		
		System.out.println("Threaded Sleep");
		start = System.currentTimeMillis();
		
		Thread t0 = new Thread(sleeper0);
		Thread t1 = new Thread(sleeper1);
		t0.start();
		t1.start();
		
		try {
			t0.join();
			t1.join();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Elapsed time = " + (System.currentTimeMillis()-start) + "\n");
		
		System.out.println("Non-Threaded ArrayList Sleep");
		ArrayList<FastSleeperIR> sleeperList = new ArrayList<FastSleeperIR>();
		for (int i = 2; i < 12; i++) {
			sleeperList.add(new FastSleeperIR(i));
		}
		
		start = System.currentTimeMillis();
		for (FastSleeperIR s: sleeperList) {
			s.sleep();
		}
		
		System.out.println("Elapsed time = " + (System.currentTimeMillis()-start) + "\n");

		
		System.out.println("Threaded ArrayList Sleep");
		ArrayList<Thread> threadList = new ArrayList<Thread>();
		start = System.currentTimeMillis();
		
		for (FastSleeperIR s: sleeperList) {
			threadList.add(new Thread(s));
		}
		
		for (Thread t: threadList) {
			t.start();
		}
		
		try {
			for (Thread t:threadList) {
				t.join();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Elapsed time = " + (System.currentTimeMillis()-start) + "\n");



	}
}