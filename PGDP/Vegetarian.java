/**
 * Klasse der Vegetarier.
 */
public class Vegetarian extends Animal {

    /**
     * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
     *
     */
    public Vegetarian(boolean female, boolean alive, String square, Position position, int withoutFood) {
       super(female, alive, square, position, withoutFood);
    }
    
    public boolean isVegetarian(){
    	return true;
    }
    
    public void sunset(){
    	return;
    }
    

    


}

