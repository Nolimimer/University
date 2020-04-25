/**
 * Die Klasse Move repraesentiert einen einzelnen Zug.
 *
 * Es gibt zwei Konstruktoren. Einer bekommt Ausgangsfeld und Zielfeld
 * uebergeben, der andere bekommt nur den eingegebenen Zug in der Form
 * <Ausgangsfeld><Zielfeld> als String uebergeben, also z. B. "a7c5" fuer den
 * Zug von "a7" nach "c5".
 */
public class Move {

	private String from;
	private String to;
	private boolean passen;
	public char currChar0, currChar1, currChar2, currChar3;
	

	public Move() {
	}

	public String getMoveFrom() {
		return from;
	}

	public String getMoveTo() {
		return to;
	}
	
	public boolean getPassen() {
		return passen;
	}
	

	//Konstruktor zerlegt move in seine Einzelteile
	public Move(String move) {
		
		this.currChar0 = move.charAt(0); // Buchstabe
		this.currChar1 = move.charAt(1); // Zahl
		this.currChar2 = move.charAt(2); // Buchstabe
		this.currChar3 = move.charAt(3); // Zahl

		//Überpürfen auf Korrektheit
		if (correctInput(currChar0, currChar1, currChar2, currChar3) == false) {
			//dies führt zu Zug ist nicht möglich -> in Klasse position
			this.from = "e4";
			this.to = "e4";
			return;
		}

		String from = currChar0 + "" + currChar1;
		String to = currChar2 + "" + currChar3;

		this.from = from;
		this.to = to;
	}
	
	//Für ApplyMove
	public Move(String from, String to, boolean passen){
		this.from = from;
		this.to = to;
		this.passen = passen;
	}
	

	//Überprüfen auf vier chars und darauf, dass die chars auf dem Spielfeld liegen
	public boolean correctInput(char a, char b, char c, char d) {

		int counter = 0;

		if (a >= 'a' && a <= 'h') {
			counter++;
		}

		if (c >= 'a' && c <= 'h') {
			counter++;
		}

		if (b >= '1' && b <= '8') {
			counter++;
		}

		if (d >= '1' && d <= '8') {
			counter++;
		}

		if (counter == 4) {
			return true;
		} else
			return false;
	}

	@Override
	public String toString() {
		return from + to;
		// Rueckgabe exakt in der Form <Ausgangsfeld><Zielfeld> als String,
		// also z. B. "b2b3" fuer den Zug eines Tiers von "b2" nach "b3".
	}

	public boolean equals(Object other) {
		return true;
	}

}
