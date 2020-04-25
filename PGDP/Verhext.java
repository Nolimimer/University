public class Verhext extends MiniJava {

	public static int pow(int x, int y) {
		return java.math.BigInteger.valueOf(x).pow(y).intValueExact();
	}

	public static void main(String[] args) {

		String Text = readString();

		int Textlänge = Text.length();
		int output = 0;
		int y = 0;
		int unnötigesZeichen = 0;
		int ZählerfürMinus = 0;
		int EsgibteinMinus = 0;

		for (int p = 0; p < Textlänge; p++) // BONUSAUFGABE ANFANG

		{
			char controlChar = Text.charAt(p);
			if (Textlänge == 2) {
				write("Fehlerhafte Eingabe, nur 2 Zeichen!");
				System.exit(0);
			}
			if (p == 0 && controlChar == '-') {
				ZählerfürMinus += 1;
			}
			if (Textlänge == 3 && ZählerfürMinus == 1) {
				write("Fehlerhafte Eingabe!");
				System.exit(0);
			}
			if (p == 0 && controlChar != '0' && ZählerfürMinus == 0
					|| p == 1 && controlChar != '0' && ZählerfürMinus == 1) {
				write("Bitte setzte für eine Hexadezimalzahl 0 an den Anfang bzw. bei - an die zweite Stelle!");
				System.exit(0);
			}
			if (p == 1 && controlChar != 'X' && controlChar != 'x' && ZählerfürMinus == 0
					|| p == 2 && controlChar != 'X' && controlChar != 'x' && ZählerfürMinus == 1) {
				write("Bitte setzt für eine Hexadezimalzahl X an die zweite bzw. bei - an die dritte Stelle!");
				System.exit(0);
			}
			if (p == 2 && controlChar == '_') {
				write("Bitte setzte keinen _ an den Anfang deiner Zahl!");
			}

			if (p == 3 && controlChar == '_' && ZählerfürMinus == 1) {
				write("Bitte setzte keinen _ an den Anfang deiner Zahl");
				System.exit(0);
			}
			if (p == Textlänge - 1 && controlChar == '_') {
				write("Bitte setzte keinen _ an den Schluss deiner Zahl!");
				System.exit(0);
			}
			if (p >= 2 && ZählerfürMinus == 0) {
				if (controlChar >= 'A' && controlChar <= 'F' || controlChar >= 'a' && controlChar <= 'f'
						|| controlChar == '_' || controlChar >= '0' && controlChar <= '9')
					;
				else {
					write("Bitte gebe entweder Zahlen oder Buchstaben von a bis f ein bzw. ein Minus oder beliebig viele _ zwischen der Hexadezimalzahlen!");
					System.exit(0);
				}
			}
			if (p >= 3 && ZählerfürMinus == 1) {
				if (controlChar >= 'A' && controlChar <= 'F' || controlChar >= 'a' && controlChar <= 'f'
						|| controlChar == '_' || controlChar >= '0' && controlChar <= '9')
					;
				else {
					write("Bitte gebe entweder Zahlen oder Buchstaben von a bis f ein bzw. ein Minus oder beliebig viele _ zwischen der Hexadezimalzahlen!");
					System.exit(0);
				}
			}
		} 
		// BONUSAUFGABE ENDE

		for (int k = 0; k < Textlänge; k++) // for-Schleife beginnt erst ab 2
											// abzulesen!
		{
			char currentChar = Text.charAt(k);
			if (currentChar == '_' || currentChar == '-') // Einlesen von _ als
															// Zeichen, die bei
															// der
			// Berechnungsformel nicht dazugezählt
			// werden sollen
			{
				unnötigesZeichen += 1;
			}
			if (currentChar == '-') {
				EsgibteinMinus += 1;
			}
		}

		unnötigesZeichen += 2; // 0X soll ebenfalls nicht mit eingelesen werden!
		y = Textlänge - unnötigesZeichen;

		for (int i = 2; i < Textlänge; ++i) { // Text wird ab 3ter Stelle
												// gelesen

			char currentChar = Text.charAt(i);

			if (currentChar > 47 && currentChar < 58) { // aus Zahl '48'... wird
														// einfache Zahl
				currentChar -= 48;
			} // Null ist 48

			if (currentChar > 96 && currentChar < 103)

			{
				currentChar -= 87;
			} // 97-87=10, Aus Kleinbuchstabe wird Hexazahl

			if (currentChar > 64 && currentChar < 71)

			{
				currentChar -= 55;
			} // 65-55=10 Aus Großbuchstabe wird Hexazahl

			if (currentChar != '_' && currentChar != 88 && currentChar != 'x') // Zahl
																				// soll
																				// nur
																				// nach
			// richtigen
			// eingelesenen
			// Zahlen kürzer werden (nicht nach "_"...)
			{
				y -= 1;
			}

			if (currentChar == '_') {
				currentChar -= '_';
			}

			if (currentChar == 88) {
				currentChar -= 88;
			}

			if (currentChar == 'x') {
				currentChar -= 'x';
			}

			output += (currentChar * pow(16, y)); // Berechnung der int-Zahl,
													// Zahl mal die Stelle (z.B.
													// 5 * 16^3)

		}
		if (EsgibteinMinus == 1)
			write("-" + output);
		else
			write(output);
	}

}
