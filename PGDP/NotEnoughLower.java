
public class NotEnoughLower extends NotEnoughExc{
	public NotEnoughLower(int should, int is){
		super(should, is);
	}
	
	public String toString(){
		return "Das Passwort hat nicht die Mindestanzahl an Kleinbuchstaben\nErforderlich: " + getShould() +  " Derzeit: " + getIs();
	}
}
