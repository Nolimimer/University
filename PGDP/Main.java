/**
 * Die Klasse Main enthaelt das Hauptprogramm.
 *
 * Im Hauptprogramm wird zuerst der Benutzer gefragt, wer das Spiel beginnen
 * soll.
 *
 * Dann wird das Spiel gestartet.
 *
 */
public class Main {

	public static void main(String args[]) {

		String startplayer;
		boolean ladiesFirst;

		do {
			startplayer = IO.readString(
					"Wer soll das Spiel beginnen? \n Gebe w für den weiblichen Spieler ein oder m für männlichen Spieler.");
		} while (!startplayer.equals("w") && !startplayer.equals("m"));

		//Übergeben, wer beginnt
		if (startplayer.equals("w"))
			ladiesFirst = true;
		else
			ladiesFirst = false;

		//Start eines neuen Spieles
		Game a = new Game();
		a.startGame(ladiesFirst);


	}

}
