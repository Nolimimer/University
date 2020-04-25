import java.util.Random;

public class Player implements Runnable {

	public int newRandom = 0;
	public int valueOfRandom;

	private final Object lockChoice = new Object();

	public void run() {
		try {
			while (true) {
				newRandom();
			}
		} catch (Exception e) {
			if (!(e instanceof InterruptedException)) {
				System.out.println("Unexpected Exception in Player.run()");
			}
		}
	}

	public int getChoice() throws InterruptedException {
		synchronized (lockChoice) {
			while (newRandom <= 0) {
				lockChoice.wait();
			}
			newRandom--;
			lockChoice.notify();
			return valueOfRandom;
		}
	}

	public void newRandom() {
		synchronized (lockChoice) {
			// Warten, wenn schon erstellt
			while (newRandom > 0) {
				try {
					lockChoice.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// Erstellen einer neuen zufälligen Zahl
			Random r = new Random();
			valueOfRandom = r.nextInt(3);
			newRandom++;
			lockChoice.notify();
		}
	}

	public static void main(String[] args) {
		Player p1 = new Player();
		Player p2 = new Player();
		RockPaperScissors rps = new RockPaperScissors(p1, p2);
		Thread t1 = new Thread(rps);
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
		}
		t1.interrupt();

	}
}
