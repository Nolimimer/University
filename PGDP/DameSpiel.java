public class DameSpiel extends MiniJava {

	private int nrRows, nrColumns; // Board dimensions
	private boolean[][] board; // true = queen, false = empty
	private boolean whiteToMove; // Whose turn it is
	private String white, black; // Players' names
	private boolean surrender = false;

	/**
	 * Der Konstruktor registriert Spielernamen fuer Weiss und Schwarz.
	 *
	 * @param white
	 *            Name des als 'Weiss' bezeichneten Spielers
	 * @param black
	 *            Name des als 'Schwarz' bezeichneten Spielers
	 */
	public DameSpiel(String white, String black) {
		this.white = readString("Gebe den Namen für Spieler 1 an:");
		this.black = readString("Gebe den Namen für Spieler 2 an:");
	}

	/**
	 * Gibt das Spielbrett aus.
	 */
	private void printBoard() {
		for (int j = board[0].length - 1; j >= 0; j--) {
			System.out.print("\n " + (1 + j));
			for (int i = 0; i < board.length; i++) {
				System.out.print(board[i][j] ? " X" : " -");
			}
		}
		System.out.print("\n  ");
		for (int i = 1; i <= board.length; i++) {
			System.out.print(" " + i);
		}
		System.out.println("\n" + (whiteToMove ? white : black) + " ist am Zug.");
	}

	/**
	 * Initialisiert das Spielbrett ueberall mit false. Dazu wird (ggf. neuer)
	 * Speicher allokiert.
	 */
	private void initBoard() {
		board = new boolean[nrRows][nrColumns]; // Array mit nrRows und
												// nrColumns
	}

	/**
	 * Ermittelt die Groesse des Spielbretts gemaess den Spielregeln. Das
	 * Ergebnis der Abfrage wird in den Attributen nrRows und nrColumns
	 * abgelegt.
	 */
	private void determineBoardSize() {
		nrRows = readInt(white + ": Wähle für die Breite eine Zahl von 5-8");
		if (nrRows != 5 && nrRows != 6 && nrRows != 7 && nrRows != 8) {
			determineBoardSize();
			return;
		}
		int previous = nrRows;
		nrColumns = readInt(black + ": Wähle für die Länge eine Zahl von " + (previous - 1) + "-" + (previous + 1));
		while (nrColumns != previous - 1 && nrColumns != previous && nrColumns != previous + 1)
			nrColumns = readInt(black + ": Wähle für die Länge eine Zahl von " + (previous - 1) + "-" + (previous + 1));
	}

	/**
	 * Ermittelt, wer anfaengt zu ziehen. Das Ergebnis der Abfrage wird im
	 * Attribut whiteToMove abgelegt.
	 */
	private void determineFirstPlayer() {
		int whosBeginning = readInt(white + ": 0 damit " + white + " beginnt, 1 damit " + black + " beginnt");
		if (whosBeginning == 0)
			whiteToMove = true;
		else if (whosBeginning == 1)
			whiteToMove = false;
		else
			determineFirstPlayer();
	}

	/**
	 * Fuehrt den Zug aus.
	 *
	 * @param move
	 *            der auszufuehrende Zug!
	 */
	private void applyMove(int move) {

		int r = (move / 10) - 1; // die Zahl die man als erstes eingibt
		int c = (move % 10) - 1; // die Zahl die man als zweites eingibt

		while (r > nrRows - 1 || c > nrColumns - 1 || r < 0 || c < 0 || move > nrRows * 10 + nrColumns
				|| validMove(r, c) == -1 || board[r][c] == true) {
			move = readInt("Wohin willst du deine Dame setzen\n (Gebe -1 fürs Aufgeben ein)");
			if (move == -1) {
				surrender = true;
				return;
			}
			r = (move / 10) - 1;
			c = (move % 10) - 1;
		}

		board[r][c] = true;
	}

	private int validMove(int r, int c) {

		// Test rows
		for (int q = 0; q < nrRows; q++) {
			if (board[q][c] == true) {
				return -1;
			}
		}

		// Test columns
		for (int m = 0; m < nrColumns; m++) {
			if (board[r][m] == true) {
				return -1;
			}
		}

		// Test für Diagonalen mit Dame, geht nach unten/links, also oben/rechts
		// hinsetzen
		int row = r;
		int column = c;
		while (column > 0 && row > 0) {
			row = row - 1;
			column = column - 1;
			if (board[row][column] == true) {
				return -1;
			}
		}
		// Test für Diagonalen mit Dame, überprüft nach unten/rechts, also
		// oben/links hinsetzen
		row = r;
		column = c;
		while (column < nrColumns - 1 && row > 0) {
			row = row - 1;
			column = column + 1;
			if (board[row][column] == true) {
				return -1;
			}
		}
		// Test für Diagonalen mit Dame, oben/links, also unten/rechts hinsetzen
		row = r;
		column = c;
		while (column > 0 && row < nrRows - 1) {
			row = row + 1;
			column = column - 1;
			if (board[row][column] == true) {
				return -1;
			}
		}
		// Test für Diagonalen mit Dame, oben/rechts, also unten/links hinsetzen
		row = r;
		column = c;
		while (column < nrColumns - 1 && row < nrRows - 1) {
			row = row + 1;
			column = column + 1;
			if (board[row][column] == true) {
				return -1;
			}

		}
		return 1;

	}

	/**
	 * Startet die Hauptschleife des Spiels mit der Abfrage nach Zuegen. Die
	 * Schleife wird durch Eingabe von -1 beendet.
	 */
	private void mainLoop() {
		while (endGame() == false) {
			int move = readInt(
					"Wohin willst du deine Dame setzen, die erste Zahl setzt die Reihe, die zweite Zahl setzt die Spalte!\n (Gebe -1 fürs Aufgeben ein)");
			if (move == -1) {
				break;
			}
			applyMove(move);
			if (surrender == true) {
				break;
			}
			if (whiteToMove == true)
				whiteToMove = false;
			else
				whiteToMove = true;
			printBoard();
		}
	}

	private boolean endGame() {
		for (int r = 0; r < nrRows; r++) {
			for (int c = 0; c < nrColumns; c++) {
				// write("Es wird gerade c mit " + c + " und r mit " + r +"
				// überprüft");
				if (validMove(r, c) == 1) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Informiert die Benutzerin ueber den Ausgang des Spiels. Speziell: Wer hat
	 * gewonnen (Weiss oder Schwarz)?
	 */
	private void reportWinner() {
		if (surrender == true) {
			if (whiteToMove == true)
				{write(black + " hat gewonnen!");
			return;
		} else{ 
			write(white + " hat gewonnen!");
			return;}
		}

		if (whiteToMove == true) {
			write(black + " hat gewonnen!");
		} else {
			write(white + " hat gewonnen!");
		}
	}

	/**
	 * Startet das Spiel.
	 */
	public void startGame() {
		determineBoardSize();
		initBoard();
		determineFirstPlayer();
		printBoard();
		mainLoop();
		reportWinner();
	}

	public static void main(String[] args) {
		DameSpiel ds = new DameSpiel("Weiß", "Schwarz");
		ds.startGame();
	}

}
