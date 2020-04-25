public class Snake extends Predator {

	// Eine Schlange kann 9 Tage bzw. Spielrunden ohne Essen auskommen.
	// Die Deklaration darf entfernt (und der Wert z. B. direkt im Code
	// verwendet) werden.

	/**
	 * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
	 *
	 */
	public Snake(boolean female, boolean alive, String square, Position position, int withoutFood) {
		super(female, alive, square, position, withoutFood);
	}

	public boolean isSnake() {
		return true;
	}
	
	public Move[] possibleMoves() {
		Position p = new Position();
		p.TestMovesForSnake();
		//return null, weil Stringausgabe schon in Klasse Position und hier deswegen nicht nötig;
		return null;
	}

	@Override
	public String toString() {
		return this.female
				? (Globals.darkSquare(this.square) ? Globals.ts_female_snake_dark : Globals.ts_female_snake_light)
				: (Globals.darkSquare(this.square) ? Globals.ts_male_snake_dark : Globals.ts_male_snake_light);
	}

}
