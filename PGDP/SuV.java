
public class SuV extends MiniJava {

	public static void main(String[] args) {

		int round = 0;
		int valuetocompare = 0;

		while (round < 2) {

			int value = 0;
			round = round + 1;
			int card = drawCard();
			int card2 = drawCard();
			value = card + card2;
			if (round == 1) {
				valuetocompare = value;
			}


			if (value > 21 && round == 1) {
				write("Deine erste Karte: " + card + "\nDeine zweite Karte: " + card2 + "\nÜberschreiten des Wertes mit " + value + "!\nSpieler 2 hat gewonnen!");  //Sofortbeendigung bei Überschreiten vom Wert 21
				System.exit(0);
			}
			if (value > 21 && round == 2) {					  
				write("Deine erste Karte: " + card + "\nDeine zweite Karte: " + card2 + "\nÜberstreiten des Wertes mit " +value + "!\nSpieler 1 hat gewonnen!");  //Sofortbeendigung bei Überschreiten vom Wert 21
				System.exit(0);
			}

			int yesorno = readInt(
					"Deine erste Karte: " + card + "\nDeine zweite Karte: " + card2 + "\nInsgesamt der Wert von "
							+ value + ".\n\nWillst du eine weitere Karte ziehen?\n Tippe 1 für Ja und 0 für Nein!");

			while (yesorno > 1 || yesorno < 0) {
				write("Bitte gebe entweder die Zahl 1 oder 0 ein!");
			yesorno = readInt(
						"Deine erste Karte: " + card + "\nDeine zweite Karte: " + card2 + "\nInsgesamt der Wert von "
								+ value + ".\n\nWillst du eine weitere Karte ziehen?\n Tippe 1 für Ja und 0 für Nein!");
			}

			while (yesorno == 1) {
				int card3 = drawCard();
				value += card3;
				if (round == 1) {
					valuetocompare += card3;
				}


				if (value > 21 && round == 1) {
					write("Deine weitere Karte: " + card3 + "\nÜberschreiten des Wertes mit " + value + "!\nSpieler 2 hat gewonnen!");
					System.exit(0);
				}
				if (value > 21 && round == 2) {
					write("Deine weitere Karte: " + card3 + "\nÜberschreiten des Wertes mit " + value + "!\nSpieler 1 hat gewonnen!");
					System.exit(0);
				}

				yesorno = readInt("Deine weitere Karte: " + card3 + "\nInsgesamt der Wert von " + value
						+ ".\n\nWillst du eine weitere Karte ziehen?\nTippe 1 für Ja und 0 für Nein!");
			
			while (yesorno > 1 || yesorno < 0) {
				write("Bitte gebe entweder die Zahl 1 oder 0 ein!");
				yesorno = readInt("Deine weitere Karte: " + card3 + "\nInsgesamt der Wert von " + value
						+ ".\n\nWillst du eine weitere Karte ziehen?\nTippe 1 für Ja und 0 für Nein!");
			
			
			}

			
			}

			if (yesorno == 0 && round == 1) {
				write("Spieler zwei ist dran!");
			}
			

			if (yesorno == 0 && round == 2) {
				if (valuetocompare > value) {
					write("Spieler 1 hat gewonnen. \nEr hat " + valuetocompare + " im Gegensatz zu " + value + ".");
				}
				
				if (valuetocompare == value) {
					write("Beide Spieler haben: " + value + "\nSomit hat Spieler 1 gewonnen!");
				}
				if (value > valuetocompare) {
					write("Spieler 2 hat gewonnen. \nEr hat " + value + " im Gegensatz zu " + valuetocompare + ".");
				}
			}
		}
	}
}
