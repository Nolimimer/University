/**
 * Die Klasse Position repraesentiert eine Spielsituation.
 *
 */
public class Position {

	/**
	 * Die Tiere werden intern in einem Array gespeichert. nrAnimals gibt an,
	 * wie viele Tiere auf dem Brett sind. Diese sind in myAnimals an den
	 * Positionen 0 bis nrAnimals-1 enthalten.
	 *
	 * Es ist empfohlen, aber nicht vorgeschrieben, diese Attribute zu
	 * verwenden.
	 *
	 * Falls die beiden Attribute NICHT verwendet werden, muss die Ausgabe der
	 * Spielposition unten entsprechend auf die verwendete Datenstruktur
	 * angepasst werden. Die toString-Methode darf dabei nicht veraendert
	 * werden, muss jedoch die selbe Rueckgabe liefern. D.h. es ist dann
	 * notwendig, die Hilfsmethode boardRepresentation auf die verwendete
	 * Datenstruktur anzupassen.
	 */
	private Animal[] myAnimals = new Animal[32];
	private int nrAnimals;
	private int posAnimalfrom, posAnimalto;
	private int counter1 = 0;
	private int counter = 0;
	private int counterForPredators = 0;
	private int counterForVegetarian = 0;

	public int getNrAnimals() {
		return nrAnimals;
	}

	public Animal[] getAnimals() {
		return myAnimals;
	}

	// Hier werden alle Tiere initalisiert
	// Kurze Erklärung: mit der Forschleife wird ein
	// Schachfeld simuliert mit 1-8 und a bis h
	// Erst wird die eine Seite initalisiert, daraufhin nur
	// das Geschlecht geändert und dann auf der anderen Seite
	public void initAnimals() {

		int r = 0;
		boolean s = true;

		for (int i = 1; i <= 8; i++) {

			// Siebte Reihe wird männlich
			if (i == 7) {
				s = false;
			}

			for (char c = 'a'; c <= 'h'; c++) {

				if (i == 1 || i == 8) {
					if (c == 'a' || c == 'h') {
						myAnimals[r++] = new Snake(s, true, c + "" + i, null, 5);
					} else if (c == 'b' || c == 'g') {
						myAnimals[r++] = new Elephant(s, true, c + "" + i, null, -1);
					} else if (c == 'c' || c == 'f') {
						myAnimals[r++] = new Horse(s, true, c + "" + i, null, -1);
					} else {
						myAnimals[r++] = new Leopard(s, true, c + "" + i, null, 9);
					}
				}

				if (i == 2 || i == 7) {
					if (c > 'a' && c < 'h') {
						myAnimals[r++] = new Rabbit(s, true, c + "" + i, null, -1);
					} else {
						myAnimals[r++] = new Penguin(s, true, c + "" + i, null, 12);
					}
				}

			}

		}
		//Immer 32 :)
		nrAnimals = r;
	}

	/**
	 * Spieler, der als naechstes ziehen darf ('M' oder 'W'). Wird jedes Mal
	 * aktualisiert, wenn eine Seite ihre Zuege ausfuehrt.
	 */
	private char next = 'W';

	//Gibt den Spieler, der dran ist zurück
	public boolean getPlayer() {
		if (next == 'W') {
			return true;
		}
		return false;
	}

	// Setzt den nächsten Spieler
	public void setnext() {
		if (next == 'W') {
			next = 'M';
		} else {
			next = 'W';
		}
	}

	/**
	 * Stellt die Anfangsposition des Spiels her. Der Parameter gibt an, welche
	 * Seite beginnt ('M' oder 'W').
	 */

	// Spielfeld "reseted"
	public void reset(char movesNext) {

		initAnimals();
		if (movesNext == 'M') {
			setnext();
		}
		System.out.println(toString());

	}

	/**
	 * Fuehrt die uebergebenen Zuege fuer einen der Spieler aus. Die Reihenfolge
	 * soll keinen Unterschied machen. Diese Methode geht davon aus, dass dies
	 * bereits ueberprueft wurde.
	 *
	 * Der Zustand des Spiels wird entsprechend angepasst, d. h. ein Spiel kann
	 * von der Anfangsposition aus allein mittels Aufrufen dieser Methode
	 * gespielt werden. Insbesondere wechselt durch den Aufruf das Zugrecht, da
	 * M und W abwechselnd ziehen.
	 *
	 * @param move
	 *            Array mit den Zuegen, die ausgefuehrt werden sollen.
	 *
	 */

	// Ausgabe der Lebensdauer der Tiere
	public void printsunset() {
		int counter = 0;
		for (int i = 0; i < getAnimals().length; i++) {
			if (myAnimals[i].isPredator()) {
				if (counter % 3 == 0) {
					System.out.print("\n");
				}
				if (myAnimals[i].isSnake() && myAnimals[i].alive) {
					System.out.print("Schlange(" + myAnimals[i].square + "): " + myAnimals[i].withoutFood + " ");
					counter++;
				} else if (myAnimals[i].isPenguin() && myAnimals[i].alive) {
					System.out.print("Pinguin(" + myAnimals[i].square + "): " + myAnimals[i].withoutFood + " ");
					counter++;
				} else if (myAnimals[i].isLeopard() && myAnimals[i].alive) {
					System.out.print("Leopard(" + myAnimals[i].square + "): " + myAnimals[i].withoutFood + " ");
					counter++;
				}

			}
		}
		System.out.print("\n");
	}

	// Hier wird der "Hunger" der Tiere behandelt, bei -1 sterben die
	// Fleischfresser, sonst -1 Abzug;
	public void applysunset() {
		for (int i = 0; i < getAnimals().length; i++) {
			if (myAnimals[i].isPredator()) {
				System.out.print("\n");
			}
			if (myAnimals[i].isSnake()) {
				myAnimals[i].withoutFood--;
				if (myAnimals[i].withoutFood == -1) {
					myAnimals[i].alive = false;
					myAnimals[i].square = "zz";
					nrAnimals--;
				}
			} else if (myAnimals[i].isPenguin()) {
				myAnimals[i].withoutFood--;
				if (myAnimals[i].withoutFood == -1) {
					myAnimals[i].alive = false;
					myAnimals[i].square = "zz";
					nrAnimals--;
				}
			} else {
				myAnimals[i].withoutFood--;
				if (myAnimals[i].withoutFood == -1) {
					myAnimals[i].alive = false;
					myAnimals[i].square = "zz";
					nrAnimals--;
				}
			}

		}
	}

	// Zug immer nur in einem Array wegen Forschleife in Klasse Game
	public void applyMoves(Move[] move) {
		// from, to aus Move Methode
		String from = move[0].getMoveFrom();
		String to = move[0].getMoveTo();
		boolean passen = move[0].getPassen();
		// Methode drunter wird aufgerufen
		applyOneMove(from, to, passen);
	}

	// ein Zug möglich: 1 || nicht möglich, -1
	public int applyOneMove(String from, String to, boolean passen) {

		// für falschen Zug
		if (passen == false) {
			if (isPossibleMove(from, to) == -1) {
				System.out.println("-Der Zug ist nicht möglich-");
				return -1;
			}
		}

		// Zählt Anzahl an Fleischfressern und Vegetariern
		if (passen == false) {
			if (myAnimals[posAnimalfrom].isPredator()) {
				counterForPredators++;
			}
			if (myAnimals[posAnimalfrom].isVegetarian()) {
				counterForVegetarian++;
			}
		}

		// für falsche Anzahl an Vegetarian oder Fleischfressern
		if (counterForPredators > 1) {
			System.out.println("-Du kannt nicht mehr als einen Fleischfresser ziehen-");
			counterForPredators--;
			return -1;
		}
		if (counterForVegetarian > 3) {
			System.out.println("-Du kannt nicht mehr als einen Vegetarier ziehen-");
			counterForVegetarian--;
			return -1;
		}

		System.out.println("Fleischfresser: " + counterForPredators + " " + "Vegetarier: " + counterForVegetarian);
		counter1++;

		// für freies Feld
		if (isPossibleMove(from, to) == 1) {
			myAnimals[posAnimalfrom].square = to;
		}

		// für Schlagen
		if (isPossibleMove(from, to) == 2) {
			myAnimals[posAnimalto].square = "zz";
			myAnimals[posAnimalto].alive = false;
			myAnimals[posAnimalfrom].square = to;
			nrAnimals--;
			if (myAnimals[posAnimalfrom].isLeopard()) {
				myAnimals[posAnimalfrom].withoutFood = 9;
			}
			if (myAnimals[posAnimalfrom].isSnake()) {
				myAnimals[posAnimalfrom].withoutFood = 5;
			}
			if (myAnimals[posAnimalfrom].isPenguin()) {
				myAnimals[posAnimalfrom].withoutFood = 12;
			}

		}

		// Soll nur alle vier Male das Spielfeld ausprinten
		if (counter1 == 4) {
			setnext();
			counter++;
			counter1 = 0;
			counterForPredators = 0;
			counterForVegetarian = 0;
			//counter dafür da, damit nachdem zwei Spieler gespielt haben, der nächste dran kommt
			if (counter % 2 == 0) {
				applysunset();
			}
			System.out.println(toString());

		}
		return 1;
	}

	//Hier wird geschaut, ob ein Zug möglich ist
	public int isPossibleMove(String from, String to) {
		int f = 0;
		int t = 0;
		boolean Pred = false;

		for (int i = 0; i < getAnimals().length; i++) {
			// Ausgangsfeld überprüfen
			if (myAnimals[i].square.equals(from) && myAnimals[i].female != getPlayer()) {
				Pred = myAnimals[i].isPredator();
				posAnimalfrom = i;
				f++;
			}
			// Endfeld überprüfen
			if (myAnimals[i].square.equals(to)) {
				posAnimalto = i;
				t++;
			}
		}

		// Überprüfen für Rabbit
		if (myAnimals[posAnimalfrom].isRabbit()) {
			if (isRightMoveForRabbit(from, to) == -1)
				return -1;
		}

		// Überprüfen für Pinguin
		if (myAnimals[posAnimalfrom].isPenguin()) {
			if (isRightMoveForPenguin(from, to) == -1)
				return -1;
		}

		// Überprüfen für Elefant
		if (myAnimals[posAnimalfrom].isElephant()) {
			if (isRightMoveForElephant(from, to) == -1)
				return -1;
		}

		// Überprüfen für Pferd
		if (myAnimals[posAnimalfrom].isHorse()) {
			if (isRightMoveForHorse(from, to) == -1)
				return -1;
		}

		// Überprüfen für Snake
		if (myAnimals[posAnimalfrom].isSnake()) {
			if (isRightMoveForSnake(from, to) == -1)
				return -1;
		}

		// Überprüfen für Leopard
		if (myAnimals[posAnimalfrom].isLeopard()) {
			if (isRightMoveForLeopard(from, to) == -1)
				return -1;
		}

		// Zug auf freies Feld
		if (f == 1 && t == 0) {
			return 1;
		}

		// Schlagen
		if (f == 1 && t == 1 && Pred == true) {
			for (int i = 0; i < getAnimals().length; i++) {
				// Eigenes Tier schlagen
				if (myAnimals[posAnimalto].female != getPlayer()) {
					return -1;
				}
				// Fremdes Tier schlagen
				if (myAnimals[posAnimalto].female == getPlayer()) {
					return 2;
				}
			}
		}
		return -1;
	}

	//Hier wird getestet, ob ein spezieller Zug aus Moves möglich ist
	public int isRightMoveForRabbit(String from, String to) {
		String speicher1 = from;
		String speicher2 = to;

		char currentchar1 = speicher1.charAt(0); // Buchstabe
		char currentchar2 = speicher1.charAt(1); // Zahl
		char currentchar3 = speicher2.charAt(0); // Buchstabe
		char currentchar4 = speicher2.charAt(1); // Zahl

		if (currentchar2 != currentchar4 - 1 && currentchar2 != currentchar4 && currentchar2 != currentchar4 + 1) {
			return -1;
		}

		if (currentchar1 != currentchar3 - 1 && currentchar1 != currentchar3 && currentchar1 != currentchar3 + 1) {
			return -1;
		}

		return 1;
	}

	//Hier werden alle möglichen Züge eines Kanninchen ausgeprinted
	public void TestMovesForRabbits() {
		String s = "";
		int counter = 0;
		for (int i = 0; i < getAnimals().length; i++) {
			if (myAnimals[i].isRabbit()) {
				for (int j = 1; j <= 8; j++) {
					for (char c = 'a'; c <= 'h'; c++) {
						if (isRightMoveForRabbit(myAnimals[i].square, c + "" + j) == -1) {
						} else if (isPossibleMove(myAnimals[i].square, c + "" + j) == -1) {
						} else {
							counter++;
							s += myAnimals[i].square + "" + c + "" + j + " ";
							//
							if (counter % 4 == 0) {
								s += "\n  ";
							}
						}
					}
				}
			}
		}
		System.out.println("Mögliche Züge für die Kanninchen: \n" + "{ " + s + "}");

	}

	//Hier wird getestet, ob ein spezieller Zug aus Moves möglich ist
	public int isRightMoveForPenguin(String from, String to) {
		String speicher1 = from;
		String speicher2 = to;

		char currentchar1 = speicher1.charAt(0); // Buchstabe
		char currentchar2 = speicher1.charAt(1); // Zahl
		char currentchar3 = speicher2.charAt(0); // Buchstabe
		char currentchar4 = speicher2.charAt(1); // Zahl

		if (currentchar2 != currentchar4 - 1 && currentchar2 != currentchar4 && currentchar2 != currentchar4 + 1) {
			return -1;
		}

		if (currentchar1 != currentchar3 - 1 && currentchar1 != currentchar3 && currentchar1 != currentchar3 + 1) {
			return -1;
		}

		return 1;
	}

	//Hier werden alle möglichen Züge eines Penguins ausgeprinted
	public void TestMovesForPenguins() {
		String s = "";
		int counter = 0;
		for (int i = 0; i < getAnimals().length; i++) {
			if (myAnimals[i].isPenguin()) {
				for (int j = 1; j <= 8; j++) {
					for (char c = 'a'; c <= 'h'; c++) {
						if (isRightMoveForPenguin(myAnimals[i].square, c + "" + j) == -1) {
						} else if (isPossibleMove(myAnimals[i].square, c + "" + j) == -1) {
						} else {
							counter++;
							s += myAnimals[i].square + "" + c + "" + j + " ";
							if (counter % 4 == 0) {
								s += "\n  ";
							}
						}

					}
				}
			}
		}

		System.out.println("Mögliche Züge für die Pinguine: \n" + "{ " + s + "}");

	}

	//Hier wird getestet, ob ein spezieller Zug aus Moves möglich ist
	public int isRightMoveForElephant(String from, String to) {
		String speicher1 = from;

		char currentchar1 = speicher1.charAt(0); // Buchstabe
		char currentchar2 = speicher1.charAt(1); // Zahl

		// Test nach oben
		Loop: while (currentchar2 < '8') {
			currentchar2++;
			for (int i = 0; i < getAnimals().length; i++) {
				if ((currentchar1 + "" + currentchar2).equals(to)) {
					return 1;
				}
				if (myAnimals[i].square.equals(currentchar1 + "" + currentchar2)
						&& !((currentchar1 + "" + currentchar2).equals(to))) {
					break Loop;
				}

			}
		}

		// Test nach unten
		currentchar2 = speicher1.charAt(1);
		Loop: while (currentchar2 > '1') {
			currentchar2--;
			for (int i = 0; i < getAnimals().length; i++) {
				if ((currentchar1 + "" + currentchar2).equals(to)) {
					return 1;
				}
				if (myAnimals[i].square.equals(currentchar1 + "" + currentchar2)
						&& !((currentchar1 + "" + currentchar2).equals(to))) {
					break Loop;
				}

			}
		}

		// Test nach rechts
		currentchar2 = speicher1.charAt(1);
		Loop: while (currentchar1 < 'h') {
			currentchar1++;
			for (int i = 0; i < getAnimals().length; i++) {
				if ((currentchar1 + "" + currentchar2).equals(to)) {
					return 1;
				}
				if (myAnimals[i].square.equals(currentchar1 + "" + currentchar2)
						&& !((currentchar1 + "" + currentchar2).equals(to))) {
					break Loop;
				}

			}
		}

		// Test nach links
		currentchar1 = speicher1.charAt(0);
		Loop: while (currentchar1 > 'a') {
			currentchar1--;
			for (int i = 0; i < getAnimals().length; i++) {
				if ((currentchar1 + "" + currentchar2).equals(to)) {
					return 1;
				}
				if (myAnimals[i].square.equals(currentchar1 + "" + currentchar2)
						&& !((currentchar1 + "" + currentchar2).equals(to))) {
					break Loop;
				}

			}

		}
		return -1;
	}
	
	//Hier werden alle möglichen Züge eines Elefanten ausgeprinted
	public void TestMovesForElephant() {

		String s = "";
		int counter = 0;
		for (int i = 0; i < getAnimals().length; i++) {
			if (myAnimals[i].isElephant()) {
				for (int j = 1; j <= 8; j++) {
					for (char c = 'a'; c <= 'h'; c++) {
						if (isRightMoveForElephant(myAnimals[i].square, c + "" + j) == -1) {
						} else if (isPossibleMove(myAnimals[i].square, c + "" + j) == -1) {
						} else {
							counter++;
							s += myAnimals[i].square + "" + c + "" + j + " ";
							if (counter % 4 == 0) {
								s += "\n ";
							}
						}

					}
				}
			}
		}

		System.out.println("Mögliche Züge für die Elephanten: \n" + "{ " + s + "}");

	}

	//Hier wird getestet, ob ein spezieller Zug aus Moves möglich ist
	public int isRightMoveForHorse(String from, String to) {
		String speicher1 = from;
		String speicher2 = to;

		char currentchar1 = speicher1.charAt(0); // Buchstabe
		char currentchar2 = speicher1.charAt(1); // Zahl
		char currentchar3 = speicher2.charAt(0); // Buchstabe
		char currentchar4 = speicher2.charAt(1); // Zahl

		if (currentchar1 == currentchar3 - 1 && currentchar2 == currentchar4
				|| currentchar1 == currentchar3 + 1 && currentchar2 == currentchar4) {
			return 1;
		}

		if (currentchar2 == currentchar4 - 1 && currentchar1 == currentchar3
				|| currentchar2 == currentchar4 + 1 && currentchar1 == currentchar3) {
			return 1;
		}

		if (currentchar2 == currentchar4 + 2 && currentchar1 == currentchar3 + 2
				|| currentchar2 == currentchar4 + 3 && currentchar1 == currentchar3 + 3) {
			return 1;
		}

		if (currentchar2 == currentchar4 - 2 && currentchar1 == currentchar3 - 2
				|| currentchar2 == currentchar4 - 3 && currentchar1 == currentchar3 - 3) {
			return 1;
		}

		if (currentchar2 == currentchar4 + 2 && currentchar1 == currentchar3 - 2
				|| currentchar2 == currentchar4 + 3 && currentchar1 == currentchar3 - 3) {
			return 1;
		}

		if (currentchar2 == currentchar4 - 2 && currentchar1 == currentchar3 + 2
				|| currentchar2 == currentchar4 - 3 && currentchar1 == currentchar3 + 3) {
			return 1;
		}

		return -1;

	}

	//Hier werden alle möglichen Züge eines Pferdes ausgeprinted
	public void TestMovesForHorse() {

		String s = "";
		int counter = 0;
		for (int i = 0; i < getAnimals().length; i++) {
			if (myAnimals[i].isHorse()) {
				for (int j = 1; j <= 8; j++) {
					for (char c = 'a'; c <= 'h'; c++) {
						if (isRightMoveForHorse(myAnimals[i].square, c + "" + j) == -1) {
						} else if (isPossibleMove(myAnimals[i].square, c + "" + j) == -1) {
						} else {
							counter++;
							s += myAnimals[i].square + "" + c + "" + j + " ";
							if (counter % 4 == 0) {
								s += "\n ";
							}
						}

					}
				}
			}
		}

		System.out.println("Mögliche Züge für die Pferde: \n" + "{ " + s + "}");

	}

	//Hier wird getestet, ob ein spezieller Zug aus Moves möglich ist
	public int isRightMoveForSnake(String from, String to) {
		String speicher1 = from;

		char currentchar1 = speicher1.charAt(0); // Buchstabe
		char currentchar2 = speicher1.charAt(1); // Zahl

		// Test nach oben geschlängelt
		Loop: while (currentchar2 < '8' && currentchar1 != 'a') {
			currentchar2++;
			currentchar1--;
			for (int i = 0; i < getAnimals().length; i++) {
				if ((currentchar1 + "" + currentchar2).equals(to)) {
					return 1;
				}
				if (myAnimals[i].square.equals(currentchar1 + "" + currentchar2)
						&& !((currentchar1 + "" + currentchar2).equals(to))) {
					break Loop;
				}

			}
			currentchar2++;
			currentchar1++;
			for (int i = 0; i < getAnimals().length; i++) {
				if ((currentchar1 + "" + currentchar2).equals(to)) {
					return 1;
				}
				if (myAnimals[i].square.equals(currentchar1 + "" + currentchar2)
						&& !((currentchar1 + "" + currentchar2).equals(to))) {
					break Loop;
				}
			}
		}

		// Test nach unten geschlängelt
		currentchar1 = speicher1.charAt(0);
		currentchar2 = speicher1.charAt(1);
		Loop: while (currentchar2 > '1' && currentchar1 != 'h') {
			currentchar2--;
			currentchar1++;
			for (int i = 0; i < getAnimals().length; i++) {
				if ((currentchar1 + "" + currentchar2).equals(to)) {
					return 1;
				}
				if (myAnimals[i].square.equals(currentchar1 + "" + currentchar2)
						&& !((currentchar1 + "" + currentchar2).equals(to))) {
					break Loop;
				}

			}
			currentchar2--;
			currentchar1--;
			for (int i = 0; i < getAnimals().length; i++) {
				if ((currentchar1 + "" + currentchar2).equals(to)) {
					return 1;
				}
				if (myAnimals[i].square.equals(currentchar1 + "" + currentchar2)
						&& !((currentchar1 + "" + currentchar2).equals(to))) {
					break Loop;
				}
			}
		}

		// Test nach rechts geschlängelt
		currentchar1 = speicher1.charAt(0);
		currentchar2 = speicher1.charAt(1);
		Loop: while (currentchar1 < 'h' && currentchar2 != '8') {
			currentchar2++;
			currentchar1++;
			for (int i = 0; i < getAnimals().length; i++) {
				if ((currentchar1 + "" + currentchar2).equals(to)) {
					return 1;
				}
				if (myAnimals[i].square.equals(currentchar1 + "" + currentchar2)
						&& !((currentchar1 + "" + currentchar2).equals(to))) {
					break Loop;
				}

			}
			currentchar2--;
			currentchar1++;
			for (int i = 0; i < getAnimals().length; i++) {
				if ((currentchar1 + "" + currentchar2).equals(to)) {
					return 1;
				}
				if (myAnimals[i].square.equals(currentchar1 + "" + currentchar2)
						&& !((currentchar1 + "" + currentchar2).equals(to))) {
					break Loop;
				}
			}
		}

		// Test nach links geschlängelt
		currentchar1 = speicher1.charAt(0);
		currentchar2 = speicher1.charAt(1);
		Loop: while (currentchar1 > 'a' && currentchar2 != '1') {
			currentchar2++;
			currentchar1--;
			for (int i = 0; i < getAnimals().length; i++) {
				if ((currentchar1 + "" + currentchar2).equals(to)) {
					return 1;
				}
				if (myAnimals[i].square.equals(currentchar1 + "" + currentchar2)
						&& !((currentchar1 + "" + currentchar2).equals(to))) {
					break Loop;
				}

			}
			currentchar2--;
			currentchar1--;
			for (int i = 0; i < getAnimals().length; i++) {
				if ((currentchar1 + "" + currentchar2).equals(to)) {
					return 1;
				}
				if (myAnimals[i].square.equals(currentchar1 + "" + currentchar2)
						&& !((currentchar1 + "" + currentchar2).equals(to))) {
					break Loop;
				}
			}
		}
		return -1;
	}

	//Hier werden alle möglichen Züge einer Schlange ausgeprinted
	public void TestMovesForSnake() {

		String s = "";
		int counter = 0;
		for (int i = 0; i < getAnimals().length; i++) {
			if (myAnimals[i].isSnake()) {
				for (int j = 1; j <= 8; j++) {
					for (char c = 'a'; c <= 'h'; c++) {
						if (isRightMoveForSnake(myAnimals[i].square, c + "" + j) == -1) {
						} else if (isPossibleMove(myAnimals[i].square, c + "" + j) == -1) {
						} else {
							counter++;
							s += myAnimals[i].square + "" + c + "" + j + " ";
							if (counter % 4 == 0) {
								s += "\n ";
							}
						}

					}
				}
			}
		}

		System.out.println("Mögliche Züge für die Schlangen: \n" + "{ " + s + "}");

	}

	//Hier wird getestet, ob ein spezieller Zug aus Moves möglich ist
	public int isRightMoveForLeopard(String from, String to) {
		if (isRightMoveForElephant(from, to) == 1) {
			return 1;
		}

		String speicher1 = from;

		char currentchar1 = speicher1.charAt(0); // Buchstabe
		char currentchar2 = speicher1.charAt(1); // Zahl

		// Test nach oben, rechts
		Loop: while (currentchar2 < '8') {
			currentchar1++;
			currentchar2++;
			for (int i = 0; i < getAnimals().length; i++) {
				if ((currentchar1 + "" + currentchar2).equals(to)) {
					return 1;
				}
				if (myAnimals[i].square.equals(currentchar1 + "" + currentchar2)
						&& !((currentchar1 + "" + currentchar2).equals(to))) {
					break Loop;
				}

			}
		}

		// Test nach oben, links
		currentchar1 = speicher1.charAt(0);
		currentchar2 = speicher1.charAt(1);
		Loop: while (currentchar2 < '8') {
			currentchar1--;
			currentchar2++;
			for (int i = 0; i < getAnimals().length; i++) {
				if ((currentchar1 + "" + currentchar2).equals(to)) {
					return 1;
				}
				if (myAnimals[i].square.equals(currentchar1 + "" + currentchar2)
						&& !((currentchar1 + "" + currentchar2).equals(to))) {
					break Loop;
				}

			}
		}

		// Test nach unten, rechts
		currentchar1 = speicher1.charAt(0);
		currentchar2 = speicher1.charAt(1);
		Loop: while (currentchar2 > '1') {
			currentchar1++;
			currentchar2--;
			for (int i = 0; i < getAnimals().length; i++) {
				if ((currentchar1 + "" + currentchar2).equals(to)) {
					return 1;
				}
				if (myAnimals[i].square.equals(currentchar1 + "" + currentchar2)
						&& !((currentchar1 + "" + currentchar2).equals(to))) {
					break Loop;
				}

			}
		}

		// Test nach unten, links
		currentchar1 = speicher1.charAt(0);
		currentchar2 = speicher1.charAt(1);
		Loop: while (currentchar2 > '1') {
			currentchar1--;
			currentchar2--;
			for (int i = 0; i < getAnimals().length; i++) {
				if ((currentchar1 + "" + currentchar2).equals(to)) {
					return 1;
				}
				if (myAnimals[i].square.equals(currentchar1 + "" + currentchar2)
						&& !((currentchar1 + "" + currentchar2).equals(to))) {
					break Loop;
				}

			}
		}

		return -1;
	}

	//Hier werden alle möglichen Züge eines Leoparden ausgeprinted
	public void TestMovesForLeopard() {

		String s = "";
		int counter = 0;
		for (int i = 0; i < getAnimals().length; i++) {
			if (myAnimals[i].isLeopard()) {
				for (int j = 1; j <= 8; j++) {
					for (char c = 'a'; c <= 'h'; c++) {
						if (isRightMoveForLeopard(myAnimals[i].square, c + "" + j) == -1) {
						} else if (isPossibleMove(myAnimals[i].square, c + "" + j) == -1) {
						} else {
							counter++;
							s += myAnimals[i].square + "" + c + "" + j + " ";
							if (counter % 4 == 0) {
								s += "\n ";
							}
						}

					}
				}
			}
		}

		System.out.println("Mögliche Züge für die Leoparden: \n" + "{ " + s + "}");

	}

	/**
	 * Ermittelt, ob/wer gewonnen hat.
	 *
	 * @return 'W' falls W gewonnen hat, 'M' falls M gewonnen hat, 'N' falls das
	 *         Spiel unentschieden zu Ende ist, 'X' falls das Spiel noch nicht
	 *         zu Ende ist.
	 *
	 */
	public char theWinner() {
		int female = 0;
		int notfemale = 0;
		// Bei keine nTieren auf dem Spielfeld
		if (nrAnimals == 0) {
			return 'N';
		}

		// Wenn beide Spieler noch Tiere auf dem Spielfeld haben
		for (int i = 0; i < getAnimals().length; i++) {
			if (myAnimals[i].female == true && myAnimals[i].alive) {
				female++;
			}
			if (myAnimals[i].female == false && myAnimals[i].alive) {
				notfemale++;
			}
		}

		// Sieger wenn einer keine Tiere auf dem Spielfeld mehr hat
		if (female == 0 || notfemale == 0) {
			if (female == 0) {
				return 'M';
			} else {
				return 'W';
			}
		}

		// Bei kein Fleischfresser auf dem Spielfeld
		for (int i = 0; i < getAnimals().length; i++) {
			if (myAnimals[i].isPredator() && myAnimals[i].alive) {
				return 'X';
			}

		}

		if (getPlayer()) {
			return 'W';
		} else {
			return 'M';
		}

	}

	// Ausgabe der Spielposition

	private static final int[] I = { 8, 7, 6, 5, 4, 3, 2, 1 };
	private static final String[] J = { "a", "b", "c", "d", "e", "f", "g", "h" };

	private static int toIndex(String s) {
		return (s.charAt(0) - 'a');
	}

	// Erzeugt eine 2D-Repraesentation der Spielposition.
	// Darf ggf. auf neue Datenstruktur angepasst werden (s.o.)
	// Die Rueckgabe ist ein zweidimensionales Array, welches
	// jedem Feld das darauf befindliche Tier (oder null) zuordnet.
	// Dabei laeuft der erste Index von der 'a'-Linie zur 'h'-Linie,
	// der zweite von der 1. zur 8. Reihe. D.h. wenn z.B. bei a[3][7]
	// ein Tier ist, ist das zugehörige Feld "d8" (vierter Buchstabe,
	// achte Zahl).
	public Animal[][] boardRepresentation() {
		Animal[][] a = new Animal[8][8];
		for (int i : I) {
			for (String j : J) {
				for (int k = 0; k < myAnimals.length; k++) {
					if (null == myAnimals[k]) {
						break;
					}
					if (myAnimals[k].square.equals(j + i)) {
						a[toIndex(j)][i - 1] = myAnimals[k];
					}
				}
			}
		}
		return a;
	}

	@Override
	public String toString() {
		String str = "   a b c d e f g h\n";
		Animal[][] ani = boardRepresentation();
		for (int i : I) {
			str += (i + " ");
			for (String j : J) {
				if (null == ani[toIndex(j)][i - 1]) {
					str += (i + toIndex(j)) % 2 == 1 ? Globals.ts_empty_square_dark : Globals.ts_empty_square_light;
				} else {
					str += ani[toIndex(j)][i - 1].toString();
				}
			}
			str += " " + i + "\n";
		}
		str += "   a b c d e f g h\nIt is " + next + "'s turn.\n";
		return str;
	}

}
