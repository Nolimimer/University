public class Rabbit extends Vegetarian {

	/**
	 * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
	 *
	 */
	public Rabbit(boolean female, boolean alive, String square, Position position, int withoutFood) {
		super(female, alive, square, position, withoutFood);

	}

	public boolean isRabbit() {
		return true;
	}

	public Move[] possibleMoves() {
		Position p = new Position();
		p.TestMovesForRabbits();
		//return null, weil Stringausgabe schon in Klasse Position und hier deswegen nicht nötig;
		return null;
	}
	

	@Override
	public String toString() {
		return this.female
				? (Globals.darkSquare(this.square) ? Globals.ts_female_rabbit_dark : Globals.ts_female_rabbit_light)
				: (Globals.darkSquare(this.square) ? Globals.ts_male_rabbit_dark : Globals.ts_male_rabbit_light);
	}

}
