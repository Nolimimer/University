public class Horse extends Vegetarian {

    /**
     * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
     *
     */
	public Horse(boolean female, boolean alive, String square, Position position, int withoutFood){
    	super(female, alive, square, position, withoutFood);
    }
	
	public boolean isHorse(){
		return true;
	}
	
	public Move[] possibleMoves() {
		Position p = new Position();
		p.TestMovesForHorse();
		//return null, weil Stringausgabe schon in Klasse Position und hier deswegen nicht nötig;
		return null;
	}

    @Override
    public String toString(){
        return this.female
          ? (Globals.darkSquare(this.square) ? Globals.ts_female_horse_dark : Globals.ts_female_horse_light)
          : (Globals.darkSquare(this.square) ? Globals.ts_male_horse_dark : Globals.ts_male_horse_light);
    }

}
