class OneSecondSleeper{
	private int sleepNumber;

	OneSecondSleeper(int sleepNumber_){
		sleepNumber = sleepNumber_;
	}

	public void sleep(){
		try{
			System.out.println(sleepNumber + " - Going to sleep");
			Thread.sleep(1000); 
			System.out.println("... " + sleepNumber + " We are done sleeping");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

public class Sleep{
	public static void main(String[] args) {
		System.out.println("\nSleep");
		long start = System.currentTimeMillis();


		OneSecondSleeper sleeper0 = new OneSecondSleeper(0);

		sleeper0.sleep();
	}
}