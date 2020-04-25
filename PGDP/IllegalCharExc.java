public class IllegalCharExc extends Exception {
	private final char used;

	IllegalCharExc(char used) {
		this.used = used;
	}

	public String charToString() {
		String IllegalChars = "";
		char[] c = Password.notallowed;
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\n')
				IllegalChars += "<\\n>";
			else if (c[i] == '\t')
				IllegalChars += "<\\t>";
			else if (c[i] == '\r')
				IllegalChars += "<\\r>";
			else if (c[i] == '\b')
				IllegalChars += "<\\b>";
			else if (c[i] == '\f')
				IllegalChars += "<\\f>";
			else
				IllegalChars += "<" + c[i] + ">";

			if (i == c.length) {
				System.out.println(".");
			} else {
				IllegalChars += ", ";
			}
		}
		return IllegalChars;
	}

	public String controlCharacter(char used) {
		String controlCharacter = "";
		if (used == '\n')
			controlCharacter += "\\n> It's used for a newline in the text.";
		else if (used == '\t')
			controlCharacter += "\\t> It's used for a tab in the text.";
		else if (used == '\r')
			controlCharacter += "\\r> It's used for a carriage return in the text.";
		else if (used == '\b')
			controlCharacter += "\\b> It's used for a backspace in the text.";
		else if (used == '\f')
			controlCharacter += "\\f>  It's used for a formfeed in the text.";
		else
			controlCharacter += used;

		return controlCharacter;
	}

	public String toString() {
		return "Das Passwort darf folgenden Charackter nicht enthalten: <" + controlCharacter(used) + "\nVerboten sind: " + charToString();
	}
}
