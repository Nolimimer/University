/**
 * Die Klasse Game fuehrt die Benutzerinteraktion durch.
 *
 */

public class Game {

	private Position pos;
	public String fromGame, toGame;
	public boolean passenGame;
	
	/**
	 * Startet ein neues Spiel. Der Benutzer wird ueber das Spielgeschehen
	 * informiert.
	 *
	 * Dazu gehoert auch die Information, wie lange die einzelnen Raubtiere noch
	 * ohne Essen auskommen koennen. Diese Information soll auf Anfrage oder
	 * immer angezeigt werden.
	 *
	 * Es soll ausserdem eine Moeglichkeit geben, sich alle Zuege anzeigen zu
	 * lassen, die in der Spielsituation moeglich sind.
	 *
	 * Bei fehlerhaften Eingaben wird die Eingabe natuerlich wiederholt.
	 *
	 * Der Parameter spezifiziert, wer das Spiel beginnen darf.
	 */
	public void startGame(boolean ladiesFirst) {
		String input;
		//String erstellen und schonmal füllen, damit keine leeren vorkommen
		String[] sameAnimal = new String[4];
		sameAnimal[0] = "z5";
		sameAnimal[1] = "z5";
		sameAnimal[2] = "z5";
		sameAnimal[3] = "z5";
		//Zum späteren Vergleich zum passen
		String noMove = "zzzz";
		pos = new Position();
		pos.reset(ladiesFirst ? 'W' : 'M');
		while (pos.theWinner() == 'X') {
			// Jeden Zug Infos über das Spiel ausgeben
			InfoAboutGame();
			// For-Schleife wegen vier Zügen, 
			for (int i = 0; i < 4; i++) {
				do {
					input = IO.readString((i + 1)
							+ ". Zug: Welche Figur willst du ziehen und wohin? Du kannst bis zu drei Vegetariar und bis zu einen Fleischfresser ziehen. (Gebe zzzz zum passen ein)");
				} while (input.length() == 0 || input.length() == 1 || input.length() == 2 || input.length() == 3);
				Move m = new Move(input);
				// Move wird zerlegt
				String from = m.getMoveFrom();
				String to = m.getMoveTo();
				//Diese Methode wird aufgerufen, wenn der Spieler nicht passen will
				//Hier wird noch der Fall behandelt, dass man ein Tier fälschlicherweise doppelt ziehen will
				if (!input.equals(noMove)) {
					int k = 0;
					sameAnimal[i] = to;
					for (int j = 0; j < sameAnimal.length; j++) {
						//Abfangen, wenn der Zug nicht möglich ist, bzw. DoppeltZug
						if (sameAnimal[j].equals(from)) {
							System.out.println("-Der Zug ist nicht möglich!-");
							//Ausgabe 1. Zug, 2. Zug muss minus ein gerechnet werden
							i--;
							//Aus der Forschleife gehen
							j = 10;
							//applyMove nicht ausführen, da k == 1
							k++;
						}
					}
					if (k == 0) {
						if (pos.applyOneMove(from, to, false) == -1) {
							i--;
						}
					}
				}
				//Equals noMove bedeutet er will passen
				if (input.equals(noMove)) {
					pos.applyOneMove(from, to, true);
				}
			}
		}
		// Ausgabe des Gewinners
		if (pos.theWinner() == 'N') {
			System.out.println("Unentschieden");
		}

		if (pos.theWinner() == 'M') {
			System.out.println("m hat gewonnen!");
		}

		if (pos.theWinner() == 'W') {
			System.out.println("w hat gewonnen!");
		}

	}

	// Ausgabe der Infos
	public void InfoAboutGame() {
		System.out.println("INFOS");
		System.out.println("-----------------------------------------------");
		System.out.println("Es leben noch so viele Tiere: " + pos.getNrAnimals());
		System.out.println("-----------------------------------------------");
		System.out.print("So lange können die Fleischfresser noch überleben: ");
		pos.printsunset();
		System.out.println("-----------------------------------------------");
		possibleMoves();
	}

	// Alle möglichen Züge werden getestet
	public void possibleMoves() {
	
		pos.TestMovesForRabbits();
		pos.TestMovesForPenguins();
		pos.TestMovesForElephant();
		pos.TestMovesForHorse();
		pos.TestMovesForSnake();
		pos.TestMovesForLeopard();

	}

}
