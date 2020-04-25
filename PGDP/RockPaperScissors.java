public class RockPaperScissors implements Runnable {

	private Player pl1 = new Player();
	private Player pl2 = new Player();
	private int newValuepl1 = -1, newValuepl2 = -1;
	private int player1won = 0, player2won = 0;
	private boolean next = false;

	private final Object lockGame = new Object();

	public RockPaperScissors(Player pl1, Player pl2) {
		this.pl1 = pl1;
		this.pl2 = pl2;
	}

	public void run() {
		Thread t1 = new Thread(pl1);
		t1.start();
		Thread t2 = new Thread(pl2);
		t2.start();
		try {
			for (int i = 0; i < 1000; i++) {
				spiel2();
				spiel();
			}
		} catch (Exception e) {
			if (!(e instanceof InterruptedException)) {
				System.out.println("Unexpected Exception in RockPaperScissors.run()");
			}
		}
		System.out.println(
				"Spieler 1 hat " + player1won + " mal gewonnen!\nSpieler 2 hat " + player2won + " mal gewonnen!");
		try {
			t1.join();
		} catch (InterruptedException e) {
		}
		try {
			t2.join();
		} catch (InterruptedException e) {
		}
		t1.interrupt();
		t2.interrupt();
	}

	public void spiel2() {
		synchronized (lockGame) {
			while (next == true) {
				try {
					lockGame.wait();
				} catch (InterruptedException e) {
				}
			}
			// Nach dem ersten mal durchlaufen werden hier die Gewinner
			// ermittelt
			if (newValuepl1 != -1 && newValuepl2 != -1) {
				if (newValuepl1 == newValuepl2) {
					if (newValuepl1 == 0) {
						System.out.println("Unentschieden! (Pl1,Pl2: Schere)");
					}
					if (newValuepl1 == 1) {
						System.out.println("Unentschieden! (Pl1,Pl2: Stein)");
					}
					if (newValuepl1 == 2) {
						System.out.println("Unentschieden! (Pl1,Pl2: Papier)");
					}
				}
				if (newValuepl1 == 0 && newValuepl2 == 2) {
					System.out.println("Spieler 1 hat gewonnen! (Pl1 : Schere, Pl2: Papier)");
					player1won++;
				}
				if (newValuepl1 == 0 && newValuepl2 == 1) {
					System.out.println("Spieler 2 hat gewonnen! (Pl1: Schere, Pl2: Stein)");
					player2won++;
				}
				if (newValuepl1 == 1 && newValuepl2 == 0) {
					System.out.println("Spieler 1 hat gewonnen! (Pl1: Stein, Pl2: Schere)");
					player1won++;
				}
				if (newValuepl1 == 1 && newValuepl2 == 2) {
					System.out.println("Spieler 2 hat gewonnen! (Pl1: Stein, Pl2: Papier)");
					player2won++;
				}
				if (newValuepl1 == 2 && newValuepl2 == 0) {
					System.out.println("Spieler 2 hat gewonnen! (Pl1: Papier, Pl2: Schere)");
					player2won++;
				}
				if (newValuepl1 == 2 && newValuepl2 == 1) {
					System.out.println("Spieler 1 hat gewonnen! (Pl1: Papier, Pl2: Stein)");
					player1won++;
				}

			}
			try {
				newValuepl1 = pl1.getChoice();
			} catch (InterruptedException e) {
			}
			next = true;
			lockGame.notify();
		}
	}

	public void spiel() {
		synchronized (lockGame) {
			while (next == false) {
				try {
					lockGame.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				newValuepl2 = pl2.getChoice();
			} catch (InterruptedException e) {
			}
			next = false;
			lockGame.notify();
		}
	}
}