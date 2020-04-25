
public class NotEnoughUpper extends NotEnoughExc {
public NotEnoughUpper(int should, int is){
	super(should, is);
}

public String toString(){
	return "Das Passwort hat nicht die Mindestanzahl an Großbuchstaben.\nErforderlich: " + getShould() + " Derzeit: " + getIs();
}
}
