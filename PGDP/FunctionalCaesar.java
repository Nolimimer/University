
public class FunctionalCaesar extends MiniJava {

	public static char shift(char c, int k) {

		//Shift
		while (k >= 26) {
			k -= 26;
		}

		//Großbuchstaben
		if (c > 64 && c < 91)

		{
			c += k;

			while (c > 90) {
				c -= 26;
			}

			while (c < 65) {
				c += 26;
			}
		}

		//Kleinbuchstaben
		else if (c > 96 && c < 123)

		{
			c += k;

			while (c > 122) {
				c -= 26;
			}

			while (c < 97) {
				c += 26;
			}

		}

		return c;
	}

	// Verschlüsselen
	public static String encrypt(String s, int k) {

		String output = "";
		for (int i = 0; i < s.length(); i++) {
			output += shift(s.charAt(i), k);
		}
		return output;
	}

	// Entschlüsseln
	public static String decrypt(String s, int k) {
		return encrypt(s, -(k % 26));
	}

	// Main
	public static void main(String[] args) {
		String input = readString();
		int k = read();
		String out = encrypt(input, k);
		write(out);
		
		//Entschlüsselter Geheimtext: Die gemeinsten Aufgaben stellt Raphaela
		
		//Komposition ergibt Identitätsfunktion
		/*out = decrypt(encrypt(input, k), k);
		write(out);
		out = encrypt(decrypt(input, k), k);
		write(out);*/
	}

}
