
public class NotLongEnoughExc extends Exception {
	private final int should, is;
	
	public NotLongEnoughExc(int should, int is){
		this.should = should;
		this.is = is;
	}
	
	public String toString(){
		return "Das Passwort entspricht nicht der Mindestlänge. \nErforderlich: " + should + " Derzeit: " + is;
	}
}
