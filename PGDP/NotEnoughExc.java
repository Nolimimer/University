public class NotEnoughExc extends Exception {
private final int should, is;

public int getShould(){
	return should;
}

public int getIs(){
	return this.is;
}

public NotEnoughExc(int should, int is){
	this.should = should;
	this.is = is;
}

public String toString(){
	return "";
}
}
