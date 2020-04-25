
public class NotEnoughSpecial extends NotEnoughExc {

	public NotEnoughSpecial(int should, int is){
		super(should, is);
	}
	
	public String toString(){
		return "Das Passwort hat nicht die Mindestanzahl an Sonderzeichen\nErforderlich: " + getShould() + " Derzeit: " + getIs();

	}
}
