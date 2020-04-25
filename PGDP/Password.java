public class Password extends MiniJava {
	private final int nrUpperShould, nrLowerShould, nrSpecialShould, nrNumbersShould, lengthShould;
	private final char[] illegal;
	//Nach Angabe müssen nur die !vorgegebenen Membervariablen final sein
	//Diese hier dient dazu, dass in IllegalCharExc alle nicht erlaubten Zeichen 
	//angezeigt werden
	public static char[] notallowed; 

	// Konstruktor
	public Password(int nrUpperShould, int nrLowerShould, int nrSpecialShould, int nrNumbersShould, int lengthShould,
			char[] illegalChars) {
		this.nrUpperShould = nrUpperShould;
		this.nrLowerShould = nrLowerShould;
		this.nrSpecialShould = nrSpecialShould;
		this.nrNumbersShould = nrNumbersShould;
		this.lengthShould = lengthShould;
		this.illegal = illegalChars;
		notallowed = illegalChars;
	}

	// getterMethoden
	public int getNrUpperShould() {
		return nrUpperShould;
	}

	public int getNrLowerShould() {
		return nrLowerShould;
	}

	public int getNrSpecialShould() {
		return nrSpecialShould;
	}

	public int getNrNumbersShould() {
		return nrNumbersShould;
	}

	public int getLengthShould() {
		return lengthShould;
	}

	public char[] getIllegal() {
		return illegal;
	}

	// Checkt ob das Format richtig ist
	public void checkFormat(String pwd) throws IllegalCharExc, NotEnoughExc, NotLongEnoughExc {

		// Länge
		int is = pwd.length();
		if (is < getLengthShould()) {
			throw new NotLongEnoughExc(getLengthShould(), is);
		}

		// Großbuchstaben
		is = UpperLetter(pwd);
		if (is < getNrUpperShould()) {
			throw new NotEnoughUpper(getNrUpperShould(), is);
		}

		// Kleinbuchstaben
		is = LowerLetter(pwd);
		if (is < getNrLowerShould()) {
			throw new NotEnoughLower(getNrLowerShould(), is);
		}

		is = Number(pwd);
		if (is < getNrNumbersShould()) {
			throw new NotEnoughNumber(getNrNumbersShould(), is);
		}

		// Specialzeichen
		is = Special(pwd);
		if (is < getNrSpecialShould()) {
			throw new NotEnoughSpecial(getNrLowerShould(), is);
		}

		// Unerblaubte Zeichen
		is = IllegalChar(pwd);
		if (is > 0) {
			throw new IllegalCharExc(getIllegalChar(pwd));
		}
	}

	// Zählt Großbuchstaben
	public int UpperLetter(String pwd) {
		int UpperLetter = 0;
		for (int i = 0; i < pwd.length(); i++) {
			if (pwd.charAt(i) >= 'A' && pwd.charAt(i) <= 'Z') {
				UpperLetter++;
			}
		}
		return UpperLetter;
	}

	// Zählt Kleinbuchstaben
	public int LowerLetter(String pwd) {
		int LowerLetter = 0;
		for (int i = 0; i < pwd.length(); i++) {
			if (pwd.charAt(i) >= 'a' && pwd.charAt(i) <= 'z') {
				LowerLetter++;
			}
		}
		return LowerLetter;
	}

	// Zählt Zahlen
	public int Number(String pwd) {
		int Number = 0;
		for (int i = 0; i < pwd.length(); i++) {
			if (pwd.charAt(i) >= '0' && pwd.charAt(i) <= '9') {
				Number++;
			}
		}
		return Number;
	}

	// Zählt Specialzeichen
	public int Special(String pwd) {
		int Special = 0;
		for (int i = 0; i < pwd.length(); i++) {
			if (!(pwd.charAt(i) >= 'a' && pwd.charAt(i) <= 'z') && !(pwd.charAt(i) >= 'A' && pwd.charAt(i) <= 'Z')
					&& !(pwd.charAt(i) >= '0' && pwd.charAt(i) <= '9')) {
				Special++;
			}
		}
		return Special;
	}

	// Zählt die unerlaubten Zeichen
	public int IllegalChar(String pwd) {
		int IllegalChar = 0;
		for (int i = 0; i < pwd.length(); i++) {
			for (int j = 0; j < getIllegal().length; j++) {
				if (getIllegal()[j] == pwd.charAt(i)) {
					IllegalChar++;
					return IllegalChar;
				}
			}
		}
		return IllegalChar;
	}

	// Gibt das unerlaubte Zeichen zurück
	public char getIllegalChar(String pwd) {
		char IllegalChar = '"';
		for (int i = 0; i < pwd.length(); i++) {
			for (int j = 0; j < getIllegal().length; j++) {
				if (getIllegal()[j] == pwd.charAt(i)) {
					IllegalChar = pwd.charAt(i);
					return IllegalChar;
				}
			}
		}
		return IllegalChar;
	}


	public static void main(String[] args) {
		char[] c = { '&', '%', '$', '/', '\\', '=', '\t', '\n'};
		Password pws = new Password(1, 1, 1, 1, 8, c);
		//Beide Möglichkeiten gehen: Fenster zum eingeben des Passworts oder in Code eingeben
		//String pwd = readString("Gebe dein Passwort ein:");
		String pwd1 = "abRTAsadf5!f";
		String pwd2 = "oXRfA?\t?vf4!";
		
		try {
			
			System.out.println("Dein Passwort: " + pwd1);
			pws.checkFormat(pwd1);
			System.out.println("Dein Passwort: " + pwd2);
			pws.checkFormat(pwd2);
			
		} catch (NullPointerException ex){
			System.out.println("Nullpointer!");
		} catch (NotLongEnoughExc ex) {
			System.out.println(ex.toString());
		} catch (NotEnoughUpper ex) {
			System.out.println(ex.toString());
		} catch (NotEnoughLower ex) {
			System.out.println(ex.toString());
		} catch (NotEnoughNumber ex) {
			System.out.println(ex.toString());
		} catch (NotEnoughSpecial ex) {
			System.out.println(ex.toString());
		} catch (IllegalCharExc ex) {
			System.out.println(ex.toString());
		} catch (NotEnoughExc ex) {
			System.out.println(ex.toString());
		}
	}
}
