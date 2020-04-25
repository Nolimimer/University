public class NotEnoughNumber extends NotEnoughExc {
	public NotEnoughNumber(int should, int is){
		super(should, is);
	}
	
	public String toString(){
		return "Das Passwort hat nicht die Mindestanzahl an Ziffern\nErforderlich: " + getShould() + " Derzeit: " + getIs();

	}
}
