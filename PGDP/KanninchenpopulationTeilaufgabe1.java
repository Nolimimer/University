
public class KanninchenpopulationTeilaufgabe1 extends MiniJava {

	public static void main(String[] args) {

		int firstgen = 1; // Zahl der 1. Generation
		int secondgen = 0; // Zahl der 2. Generation
		int thirdgen = 0; // Zahl der 3. Generation
		int x;
		int y;
		int z; // Zwischenspeicher
		int number = 1;

		int month = readInt("Die Anzahl der Kanninchenpaare nach wievielen Monaten?");
		if (month <1)
			write ("Bitte gebe eine Zahl größer als 1 ein."); //Überprüfen auf Gültigkeit
			

		while (number < month) {
			x = firstgen;
			y = secondgen;
			z = thirdgen;
			thirdgen = secondgen; // Zweite Generation wird zur dritten
			secondgen = firstgen; // Erste Generation wird zur zweiten
			firstgen = +x + y + z ;
			number += 1;}

		if (number == month) {
			int all = firstgen + secondgen + thirdgen;
				write("Nach " + month + " Monat(en) sind es \n" + firstgen + " Kanninchenpaar(e) in der ersten Generation \n" + secondgen + " Kanninchenpaar(e) in der zweiten Generationv \n"
						 + thirdgen + " Kanninchenpaar(e) in der dritten Generation \nund insgesamt " + all + " Kanninchenpaar(e). ");
			

		}
	}
}
