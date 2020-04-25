public class Penguin extends Predator {

    // Ein Pinguin kann 12 Tage bzw. Spielrunden ohne Essen auskommen.
    // Die Deklaration darf entfernt (und der Wert z. B. direkt im Code
    // verwendet) werden.

    /**
     * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
     *
     */
     
    public Penguin(boolean female, boolean alive, String square, Position position, int withoutFood){
    	super(female, alive, square, position, withoutFood);	
    }
    
	public boolean isPenguin(){
		return true;
	}
    
    public boolean Alive(){
    	return true;
    }
    
    public String square(){	
    return square;
    }
    
	public Move[] possibleMoves() {
		Position p = new Position();
		p.TestMovesForPenguins();
		//return null, weil Stringausgabe schon in Klasse Position und hier deswegen nicht nötig;
		return null;
	}


    @Override
    public String toString(){
        return this.female
          ? (Globals.darkSquare(this.square) ? Globals.ts_female_penguin_dark : Globals.ts_female_penguin_light)
          : (Globals.darkSquare(this.square) ? Globals.ts_male_penguin_dark : Globals.ts_male_penguin_light);
    }

}

