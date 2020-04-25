package pgdp;

public class Money {

	private int cent;

	// Konstruktor
	public Money() {
		this.cent = 0;
	}

	public Money(int cent) {
		this.cent = cent;
	}

	public int getCent() {
		return cent;
	}

	public Money addMoney(Money m) {
		return new Money(this.cent + m.getCent());
	}
	
	public Money removeMoney(Money m) {
		return new Money(this.cent - m.getCent());
	}

	public String toString() {
		
		//negative Zahlen
		if (cent < 0){
			int tmpcent = cent;
			tmpcent *= -1;
			if ((tmpcent % 100) < 10) {
				return "-" + tmpcent / 100 + "," + "0" + tmpcent % 100 + " Euro";}
					 else {
				return "-" + tmpcent / 100 + "," + tmpcent % 100 + " Euro";
			}
		}
		
		//Postive Zahlen
		if ((cent % 100) < 10 && (cent % 100) >= 0) {
			return cent / 100 + "," + "0" + cent % 100 + " Euro";}
				 else {
			return cent / 100 + "," + cent % 100 + " Euro";
		}
	
}

	public static void main(String[] args) {
	}
}
