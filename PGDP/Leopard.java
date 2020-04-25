public class Leopard extends Predator {

	// Ein Leopard kann nur 5 Tage bzw. Spielrunden ohne Essen auskommen.
	// Die Deklaration darf entfernt (und der Wert z. B. direkt im Code
	// verwendet) werden.

	/**
	 * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
	 *
	 */
	public Leopard(boolean female, boolean alive, String square, Position position, int withoutFood) {
		super(female, alive, square, position, withoutFood);
	}

	public boolean isLeopard() {
		return true;
	}
	
	public Move[] possibleMoves() {
		Position p = new Position();
		p.TestMovesForLeopard();
		//return null, weil Stringausgabe schon in Klasse Position und hier deswegen nicht nötig;
		return null;
	}

	@Override
	public String toString() {
		return this.female
				? (Globals.darkSquare(this.square) ? Globals.ts_female_leopard_dark : Globals.ts_female_leopard_light)
				: (Globals.darkSquare(this.square) ? Globals.ts_male_leopard_dark : Globals.ts_male_leopard_light);
	}

}
