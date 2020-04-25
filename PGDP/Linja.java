public class Linja extends MiniJava {

	private static int[][] spielfeld = new int[8][6];

	/**
	 * initialisiert das Spielfeld Ziellinie fuer Spieler 1 ist Zeile 7
	 * Ziellinie fuer Spieler -1 ist Zeile 0
	 */
	private static void initSpiel() {
		for (int i = 0; i < spielfeld.length; i++) {
			if (i != 0 && i != spielfeld.length - 1) {
				spielfeld[i] = new int[] { -(12 - i + 1), 0, 0, 0, 0, 6 + i };
			}
			if (i == 0) {
				spielfeld[i] = new int[] { 1, 2, 3, 4, 5, 6 };
			}
			if (i == spielfeld.length - 1) {
				spielfeld[i] = new int[] { -6, -5, -4, -3, -2, -1 };
			}
		}

	}

	/**
	 *
	 * @return formatiertes aktuelles Spielfeld
	 */
	private static String output() {
		String tmp = "Spieler 1 spielt von oben nach unten\n" + "Spieler -1 spielt von unten nach oben\n";
		for (int i = 0; i < spielfeld.length; i++) {
			for (int j = 0; j < spielfeld[i].length; j++) {
				tmp = tmp + "\t" + spielfeld[i][j];
			}
			tmp = tmp + "\n";
		}
		return tmp;
	}

	/**
	 * @return true, wenn die Eingabe stein im richtigen Wertebereich liegt und
	 *         zum Spieler gehoert; false, sonst
	 */
	private static boolean gueltigeEingabe(int stein, int spieler) {
		boolean output = true;
		if (spieler == 1) {
			if (stein > 0 && stein < 13) { // Spieler 1 gültige Eingabe: 1-12
				output = true;
			} else {
				output = false;
			}
		}
		if (spieler == -1) {
			if (stein > -13 && stein < 0) { // Spieler 2 gültige Eingabe:
											// -(1)-(-12)
				output = true;
			} else {
				output = false;
			}
		}
		return output;
	}

	/**
	 * @param stein
	 *            kann Werte -1 bis -12 und 1 bis 12 haben
	 * @return gibt x-Koordinate von stein an Position 0 und die y-Koordinaten
	 *         von stein an Position 1 zurueck; falls stein nicht gefunden, wird
	 *         {-1,-1} zurueckgegeben
	 */
	private static int[] findeStein(int stein) {
		int[] output = { -1, -1 };
		for (int i = 0; i < spielfeld.length; i++) { // y-Koordinatenwerte
														// werden überprüft
			for (int j = 0; j < spielfeld[i].length; j++) { // x-Koordinatenwerte
															// werden überprüft
				if (spielfeld[i][j] == stein) {
					output = new int[] { j, i };
					break;
				}
			}
		}

		return output; // Koordinatenausgabe mit x,y -1!
	}

	/**
	 * @param reihe
	 *            hat Werte 0 bis 7
	 * @return Anzahl der Steine in einer Reihe
	 */
	private static int steineInReihe(int reihe) { // je nach Reiheneingabe kommt

		int output = 0;

		if (reihe < 0 || reihe > 7) {
			output = 0;
			return output;
		}

		for (int i = 0; i < spielfeld[reihe].length; i++) { // x-Koordinatenwerte
			// werden überprüft

			if (spielfeld[reihe][i] != 0) {
				output += 1;

			}

		}
		return output;
	}

	/**
	 * Ueberprueft, ob der Zug zulaessig ist und fuehrt diesen aus, wenn er
	 * zulaessig ist.
	 *
	 * @param vorwaerts
	 *            == true: Zug erfolgt vorwaerts aus Sicht des Spielers/Steins
	 *            vorwearts == false: Zug erfolgt rueckwaerts aus Sicht des
	 *            Spielers/Steins
	 * @return Rueckgabe -1: Zug nicht zulaessig Rueckgabe 0-5: Weite des
	 *         potentiellen naechsten Zugs (falls Folgezug folgt) Rueckgabe 6:
	 *         Ziellinie wurde genau getroffen (potentieller Bonuszug)
	 *
	 */
	private static int setzeZug(int stein, int weite, boolean vorwaerts) {

		int output = 0;
		int sumx = 0;
		int sumy = 0;

		// x und y Koordinaten speichern
		sumx = findeStein(stein)[0];
		sumy = findeStein(stein)[1];

		// Je nach Reichweite wird die y Koordinate erweitert
		if (vorwaerts == true) { // Zug für 1 Spieler
			sumy += weite;
		}
		if (vorwaerts == false) { // Zug für -1 Spieler
			sumy -= weite;
		}
		
		// Sonderfall für 6 Steine in Reihe (Ausnahme 0te und 7te Reihe)
		if (steineInReihe(sumy) == 6 && sumy != 7 && sumy != 0) {
			output = -1;
			return output;
		}
		if (steineInReihe(sumy) == 6 && sumy == 0 && vorwaerts == false && stein > 0) {
			output = -1;
			return output;
		}
		if (steineInReihe(sumy) == 6 && sumy == 7 && vorwaerts == true && stein < 0) {
			output = -1;
			return output;
		}

		// Sonderfall Spieler will mit eigenen Stein über seine eigene Linie
		// hinaus
		if (sumy > 7 && vorwaerts == true && stein < 0) {
			output = -1;
			return output;
		}

		if (sumy < 0 && vorwaerts == false && stein > 0) {
			output = -1;
			return output;
		}

		// Sonderfall Spieler will in seine eigene Lücke seiner Reihe
		if (sumy == 0 && vorwaerts == false && stein > 0) {
			for (int j = 0; j < spielfeld[sumy].length; j++) // x-Koordinaten
																// werden
																// durchgelaufgen
				if (spielfeld[sumy][j] == 0) {
					output = steineInReihe(sumy);
					spielfeld[sumy][j] = stein;
					if (vorwaerts == false) {
						sumy += weite;
						spielfeld[sumy][sumx] = 0;
						return output;
					}
				}
		}

		if (sumy == 7 && vorwaerts == true && stein < 0) {
			for (int j = 0; j < spielfeld[sumy].length; j++) { // x-Koordinate
				// werden abgelaufen
				if (spielfeld[sumy][j] == 0) {
					output = steineInReihe(sumy);
					spielfeld[sumy][j] = stein;
					if (vorwaerts == true) {
						sumy -= weite;
						spielfeld[sumy][sumx] = 0;
						return output; // Nur eine Nullstelle soll gefüllt
										// werden
					}

				}
			}
		}

		// Kein Bonuszug; Beim Überschreiten der letzten Reihe
		if (sumy > 7 && vorwaerts == true && stein > 0) {
			sumy -= weite;
			spielfeld[sumy][sumx] = 0;
			output = 0;
			return output;
		}
		if (sumy < 0 && vorwaerts == false && stein < 0) {
			sumy += weite;
			spielfeld[sumy][sumx] = 0;
			output = 0;
			return output;
		}

		// Bonuszug; Wenn die letzte Reihe exakt getroffen wird
		if (sumy == 7 && vorwaerts == true) {
			sumy -= weite;
			spielfeld[sumy][sumx] = 0;
			output = 6;
			return output;
		}
		if (sumy == 0 && vorwaerts == false) {
			sumy += weite;
			spielfeld[sumy][sumx] = 0;
			output = 6;
			return output;
		}
		
		if (sumy > 7 || sumy < 0){
			output = 0;
			return output;
		}

		// Nullstelle in der entsprechenden Reihe soll gefunden und gefüllt
		// werden
		for (int j = 0; j < spielfeld[sumy].length; j++) { // x-Koordinate
															// werden abgelaufen
			if (spielfeld[sumy][j] == 0) {
				output = steineInReihe(sumy);
				spielfeld[sumy][j] = stein;
				if (vorwaerts == true) {
					sumy -= weite;
					spielfeld[sumy][sumx] = 0;
					return output; // Nur eine Nullstelle soll gefüllt werden
				}
				if (vorwaerts == false) {
					sumy += weite;
					spielfeld[sumy][sumx] = 0;
					return output; // Nur eine Nullstelle soll gefüllt werden
				}

			}
		}

		return output;
	}

	/**
	 * @return true, falls die Bedingungen des Spielendes erfuellt sind, d.h.
	 *         alle Steine des einen Spielers sind an den Steinen des
	 *         gegnerischen Spielers vorbeigezogen
	 *
	 */
	private static boolean spielende() {

		boolean output = false;

		int verschwundeneZahlenSpieler1 = 0;
		int verschwundeneZahlenSpielerMinus1 = 0;


		// Bei postiven und negativen Zahlen in einer Reihe -> kein Ende
		for (int i = 0; i < spielfeld.length; i++) { // y-Koordinatenwerte
			int esgibteinepositiveZahl = 0;
			int esgibteinenegativeZahl = 0; // werden überprüft
			for (int j = 0; j < spielfeld[i].length; j++) { // x-Koordinatenwerte
															// werden überprüft
				if (spielfeld[i][j] > 0) {
					esgibteinepositiveZahl += 1;
				}
				if (spielfeld[i][j] < 0) {
					esgibteinenegativeZahl += 1;
				}
				if (esgibteinepositiveZahl > 0 && esgibteinenegativeZahl > 0) {
					output = false;
					return output;
				}
			}
		}
		
		//Solange eine postive Zahl auf der 0 Reihe steht kann das Spiel nicht zu Ende sein
		for (int i = 0; i < spielfeld[0].length; i++){
			if (spielfeld[0][i] > 0)
			{
			output = false;
			return output;
			
			}
		}
		
		//Solange eine negative Zahl auf der 7 Reihe steht kann das Spiel nicht zu Ende sein
		for (int i = 0; i < spielfeld[0].length; i++){
			if (spielfeld[7][i] < 0)
			{
			output = false;
			return output;
			}
		}
		
		
		// Algorithmus schaut sich Zahlen an, wenn eine negative Zahl gefunden
		// wurde, dann Zeilen davor anschauen und die gleiche Zeile nach
		// postiven -> kein Spielende
		for (int i = 0; i < spielfeld.length; i++) { // y-Koordinatenwerte
														// werden überprüft
			for (int j = 0; j < spielfeld[i].length; j++) { // x-Koordinatenwerte
															// werden überprüft
				if (spielfeld[i][j] < 0) {

					while (i-- > 0) {
						while (j++ < spielfeld[i].length) {
							if (spielfeld[j][i] > 0) {
								output = true;
								return output;
							}
						}
					}
					output = true;
					return output;
				}
			}
		}

		return output;
	}

	/**
	 * zaehlt die Punkte der beiden Spieler und gibt das Ergebnis aus
	 */
	private static void zaehlePunkte() {
		int PunkteSpieler1 = 0;
		int PunkteSpielerMinus1 = 0;

		// Sucht für Spieler 1 verschwundene Zahlen -> +5 Punkte
		for (int j = 1; j < 13; j++) {
			if (findeStein(j)[0] == -1)
				PunkteSpieler1 = +5;
		}
		// Sucht für Spieler -1 verschwundene Zahlen -> +5 Punkte
		for (int k = -1; k > -13; k--) {
			if (findeStein(k)[0] == -1)
				PunkteSpielerMinus1 += 5;
		}

		for (int i = 0; i < spielfeld[i].length; i++) { // x-Koordinatenwerte
														// werden überprüft

			if (spielfeld[7][i] != 0) {
				if (spielfeld[7][i] > 0) {
					PunkteSpieler1 += 5;
				} else {
					PunkteSpielerMinus1 -= 5;
				}
			}

			if (spielfeld[6][i] != 0) {
				if (spielfeld[6][i] > 0) {
					PunkteSpieler1 += 3;
				} else {
					PunkteSpielerMinus1 -= 3;
				}
			}

			if (spielfeld[5][i] != 0) {
				if (spielfeld[5][i] > 0) {
					PunkteSpieler1 += 2;
				} else {
					PunkteSpielerMinus1 -= 2;
				}
			}

			if (spielfeld[4][i] != 0) {
				if (spielfeld[4][i] > 0) {
					PunkteSpieler1 += 1;
				} else {
					PunkteSpielerMinus1 -= 1;
				}
			}

			if (spielfeld[3][i] != 0) {
				if (spielfeld[3][i] > 0) {
					PunkteSpieler1 -= 1;
				} else {
					PunkteSpielerMinus1 += 1;
				}
			}

			if (spielfeld[2][i] != 0) {
				if (spielfeld[2][i] > 0) {
					PunkteSpieler1 -= 2;
				} else {
					PunkteSpielerMinus1 += 2;
				}
			}

			if (spielfeld[1][i] != 0) {
				if (spielfeld[1][i] > 0) {
					PunkteSpieler1 -= 3;
				} else {
					PunkteSpielerMinus1 += 3;
				}
			}

			if (spielfeld[0][i] != 0) {
				if (spielfeld[0][i] > 0) {
					PunkteSpieler1 -= 5;
				} else {
					PunkteSpielerMinus1 += 5;
				}
			}

		}
		System.out.println("Punkte Spieler 1: " + PunkteSpieler1 + " Punkte Spieler -1: " + PunkteSpielerMinus1);
	}

	/**
	 * Spielablauf entsprechend Anfangszug, Folgezug, Bonuszug
	 *
	 * @param spieler
	 *            ist 1 (Spielsteine 1 bis 12) oder -1 (Spielsteine -1 bis -12)
	 */
	private static void spielerZieht(int spieler, boolean bonus) {

		boolean vorwaerts = true;

		// Zugwechsel //boolean ändert sich!!!
		if (spieler == 1) {
			vorwaerts = true;
			write("Spieler 1 ist dran");
		} else {
			vorwaerts = false;
			write("Spieler -1 ist dran.");
		}

		int stein = readInt("Anfangszug: Welchen Stein willst du ziehen?");
		gueltigeEingabe(stein, spieler); // 1 bedeutet, dass Spieler = 1 ist

		while (gueltigeEingabe(stein, spieler) == false || findeStein(stein)[0] == -1) {
			write("Anfangszug: Bitte gebe eine gültige Eingabe ein!");
			stein = readInt("Anfangszug: Welchen Stein willst du ziehen?");
		}

		// Anfangszug
		int weite = 1;
		int Anfangszug = setzeZug(stein, weite, vorwaerts);
		if (Anfangszug == -1) {
			write("Anfangszug: Bitte gebe eine gültige Eingabe ein!");
			stein = readInt("Anfangszug: Welchen Stein willst du ziehen?");
			gueltigeEingabe(stein, spieler);
			Anfangszug = setzeZug(stein, weite, vorwaerts);
			while (Anfangszug == -1 || gueltigeEingabe(stein, spieler) == false || findeStein(stein)[0] == -1) {
				write("Anfangszug: Bitte gebe eine gültige Eingabe ein!");
				stein = readInt("Anfangszug: Welchen Stein willst du ziehen?");
				gueltigeEingabe(stein, spieler); // 1 bedeutet, dass Spieler = 1
													// ist
				Anfangszug = setzeZug(stein, weite, vorwaerts);

			}
		}

		System.out.println(output()); // Spielfeld wird gezeichnet

		// Folgezug
		int Folgezug = 0;
		if (Anfangszug > 0 && Anfangszug < 6) {
			write("Folgezug: Dein nächster Stein kann " + Anfangszug + " Felder bewegt werden!");
			weite = Anfangszug; // sollte anfangs 2 rauskommen
			stein = readInt("Folgezug: Welchen Stein willst du jetzt ziehen?");

			while (gueltigeEingabe(stein, spieler) == false || findeStein(stein)[0] == -1 || Anfangszug == -1) {

				write("Bitte gebe eine gültige Eingabe ein!");
				stein = readInt("Folgezug: Welchen Stein willst du jetzt ziehen?");
			}

			Folgezug = setzeZug(stein, weite, vorwaerts);
			if (Folgezug == -1) {
				write("falscher Zug");
			}

			if (Folgezug == -1) {
				write("Folgezug: Bitte gebe eine gültige Eingabe ein!");
				stein = readInt("Folgezug: Welchen Stein willst du ziehen?");
				Folgezug = setzeZug(stein, weite, vorwaerts);
				while (Folgezug == -1 || gueltigeEingabe(stein, spieler) == false || findeStein(stein)[0] == -1) {
					write("Folgezug: Bitte gebe eine gültige Eingabe ein!");
					stein = readInt("Folgezug: Welchen Stein willst du ziehen?");
					Folgezug = setzeZug(stein, weite, vorwaerts);

				}
			}
			System.out.println(output()); // Spielfeld wird gezeichnet
		}
		// Bonuszug, mit Bonusregel //nicht Testen auf gültigeEingabe
		if (bonus == true) {
			if (Anfangszug == 6 || Anfangszug == -1 || Folgezug == 6 || Folgezug == -1) {
				int Bonuszug = 0;
				weite = 1;
				stein = readInt("Bonuszug: Welchen Stein willst du ziehen?");

				while (findeStein(stein)[0] == -1) {

					write("Den eingegebenen Stein gibt es nicht! Bitte gebe eine vorhandenen Stein ein!");
					stein = readInt("Bonuszug: Welchen Stein willst du ziehen?");
					Bonuszug = setzeZug(stein, weite, vorwaerts);
				}

				int vorwaertsAbfrage = readInt("Für vorwärts gebe 1 für rückwärts 0 ein. (jeweils auch der jeweiligen Sicht des Spielers)");

				while (vorwaertsAbfrage != 0 && vorwaertsAbfrage != 1) {
					write("Bitte gebe entweder 1 oder 0 ein!");
					stein = readInt("Für vorwärts gebe 1 für rückwärts 0 ein.");
				}

				// Gegenerischer Stein // Spieler 1
				if (vorwaertsAbfrage == 1 && spieler == 1 && stein < 0) {
					vorwaerts = true;
				}
				if (vorwaertsAbfrage == 0 && spieler == 1 && stein < 0) {
					vorwaerts = false;
				}
				// Gegenerischer Stein // Spieler -1
				if (vorwaertsAbfrage == 1 && spieler == -1 && stein > 0) {
					vorwaerts = false;
				}
				if (vorwaertsAbfrage == 0 && spieler == -1 && stein > 0) {
					vorwaerts = true;
				}
				// Normaler Stein // Spieler 1
				if (vorwaertsAbfrage == 1 && spieler == 1 && stein > 0) {
					vorwaerts = true;
				}
				if (vorwaertsAbfrage == 0 && spieler == 1 && stein > 0) {
					vorwaerts = false;
				}
				// Normaler Stein // Spieler -1
				if (vorwaertsAbfrage == 1 && spieler == -1 && stein < 0) {
					vorwaerts = false;
				}
				if (vorwaertsAbfrage == 0 && spieler == -1 && stein < 0) {
					vorwaerts = true;
				}
				Bonuszug = setzeZug(stein, weite, vorwaerts);

				while (Bonuszug == -1) {
					write("Biite gebe eine gültige Eingabe ein!");
					stein = readInt("Bonuszug: Welchen Stein willst du ziehen?");

					while (findeStein(stein)[0] == -1) {

						write("Den eingegebenen Stein gibt es nicht! Bitte gebe eine vorhandenen Stein ein!!");
						stein = readInt("Bonuszug: Welchen Stein willst du ziehen?");
						Bonuszug = setzeZug(stein, weite, vorwaerts);
					}

					vorwaertsAbfrage = readInt("Für vorwärts gebe 1 für rückwärts 0 ein.");

					while (vorwaertsAbfrage != 0 && vorwaertsAbfrage != 1) {
						write("Bitte gebe entweder 1 oder 0 ein!");
						stein = readInt("Für vorwärts gebe 1 für rückwärts 0 ein.");
					}
					// Gegenerischer Stein
					if (vorwaertsAbfrage == 1 && spieler == 1 && stein < 0) {
						vorwaerts = true;
					}
					if (vorwaertsAbfrage == 0 && spieler == 1 && stein < 0) {
						vorwaerts = false;
					}
					if (vorwaertsAbfrage == 1 && spieler == -1 && stein > 0) {
						vorwaerts = false;
					}
					if (vorwaertsAbfrage == 0 && spieler == -1 && stein > 0) {
						vorwaerts = true;
					}
					// Normaler Stein
					if (vorwaertsAbfrage == 1 && spieler == 1 && stein > 0) {
						vorwaerts = true;
					}
					if (vorwaertsAbfrage == 0 && spieler == 1 && stein > 0) {
						vorwaerts = false;
					}
					if (vorwaertsAbfrage == 1 && spieler == -1 && stein < 0) {
						vorwaerts = false;
					}
					if (vorwaertsAbfrage == 0 && spieler == -1 && stein < 0) {
						vorwaerts = true;
					}
					Bonuszug = setzeZug(stein, weite, vorwaerts);

				}
				System.out.println(output()); // Spielfeld wird gezeichnet}
			}

		}
		// Bonuszug, ohne Bonusregel
		if (bonus == false) {
			if (Anfangszug == 6 || Anfangszug == -1 || Folgezug == 6 || Folgezug == -1) {
				int Bonuszug = 0;
				weite = 1;
				stein = readInt("Bonuszug: Welchen Stein willst du ziehen?");

				while (gueltigeEingabe(stein, spieler) == false || findeStein(stein)[0] == -1) {

					write("Bitte gebe eine gültige Eingabe ein!");
					stein = readInt("Bonuszug: Welchen Stein willst du ziehen?");
					Bonuszug = setzeZug(stein, weite, vorwaerts);
				}

				int vorwaertsAbfrage = readInt("Für vorwärts gebe 1 für rückwärts 0 ein.(jeweils auch der jeweiligen Sicht des Spielers)");

				while (vorwaertsAbfrage != 0 && vorwaertsAbfrage != 1) {
					write("Bitte gebe enteder 1 oder 0 ein!");
					stein = readInt("Bonuszug: Welchen Stein willst du ziehen?");
				}
				//Normaler Zug mit eigenen Stein
				if (vorwaertsAbfrage == 1 && spieler == 1) {
					vorwaerts = true;
				}
				if (vorwaertsAbfrage == 0 && spieler == 1) {
					vorwaerts = false;
				}
				if (vorwaertsAbfrage == 1 && spieler == -1) {
					vorwaerts = false;
				}
				if (vorwaertsAbfrage == 0 && spieler == -1) {
					vorwaerts = true;
				}
				Bonuszug = setzeZug(stein, weite, vorwaerts);

				while (Bonuszug == -1) {
					write("Biite gebe eine gültige Eingabe ein!");
					stein = readInt("Bonuszug: Welchen Stein willst du ziehen?");

					while (gueltigeEingabe(stein, spieler) == false || findeStein(stein)[0] == -1) {

						write("Bitte gebe eine gültige Eingabe ein!");
						stein = readInt("Bonuszug: Welchen Stein willst du ziehen?");
						Bonuszug = setzeZug(stein, weite, vorwaerts);
					}

					vorwaertsAbfrage = readInt("Für vorwärts gebe 1 für rückwärts 0 ein.");

					while (vorwaertsAbfrage != 0 && vorwaertsAbfrage != 1) {
						write("Bitte gebe enteder 1 oder 0 ein!");
						stein = readInt("Bonuszug: Welchen Stein willst du ziehen?");
					}
					
					if (vorwaertsAbfrage == 1 && spieler == 1 && bonus == false) {
						vorwaerts = true;
					}
					if (vorwaertsAbfrage == 0 && spieler == 1 && bonus == false) {
						vorwaerts = false;
					}
					if (vorwaertsAbfrage == 1 && spieler == -1 && bonus == false) {
						vorwaerts = false;
					}
					if (vorwaertsAbfrage == 0 && spieler == -1 && bonus == false) {
						vorwaerts = true;
					}
					Bonuszug = setzeZug(stein, weite, vorwaerts);

				}
				System.out.println(output()); // Spielfeld wird gezeichnet
			}
		}
	}

	public static void main(String args[]) {

		int spieler = -1;
		int Bonusregel = 1;
		boolean bonus = false;

		initSpiel();
		System.out.println(output()); // Anfangsfeld wird "gezeichnet"

		Bonusregel = readInt("Willst du mit der Bonusregel spielen? Gebe 1 für ja und 0 für nein ein!");
		while (Bonusregel != 0 && Bonusregel != 1) {
			write("Bitte gebe enteder 1 oder 0 ein!");
			Bonusregel = readInt("Willst du mit der Bonusregel spielen? Gebe 1 für ja und 0 für nein ein!");
		}
		if (Bonusregel == 1) {
			bonus = true;
			write("Du spielst jetzt mit Bonusregel: Beim Bonuszug darfst du jetzt auch die gegenerischen Steine ziehen!");
		}

		while (spielende() == false) {
			if (spieler == -1) {
				spieler = 1;
			} else {
				spieler = -1;
			}
			spielerZieht(spieler, bonus);
			zaehlePunkte(); // muss nicht umbedingt sein

		}
		System.out.println("Endresultat!");
		zaehlePunkte();
		write("Spielende!");

	}
}
