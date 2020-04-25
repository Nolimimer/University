/**
 * Klasse der Raubtiere.
 */
public class Predator extends Animal {

	public int withoutFood;

	/**
	 * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
	 *
	 */
	public Predator(boolean female, boolean alive, String square, Position position, int withoutFood) {
		super(female, alive, square, position, withoutFood);
	}

	public boolean isPredator() {
		return true;
	}

	public void sunset() {
		withoutFood--;
	}

	public int withoutFood() {
		return withoutFood;
	}
}
