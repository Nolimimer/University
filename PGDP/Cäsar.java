
public class C�sar extends MiniJava {

	public static void main(String[] args) {

		 String Text = readString("Gebe hier deinen Text ein der verschl�sselt werden soll:");	
		 String Output = "";
		 
		 int Shift = read("Gebe eine Zahl f�r den zyklischen Shift ein!");
		 
		 while (Shift >= 26)
		 {Shift -= 26;}
		 
		int Textl�nge = Text.length();
		 
		for (int i = 0; i < Textl�nge; i++){
			
			char currentChar = Text.charAt(i);
			
			if(currentChar > 64 && currentChar < 91)
				
				{currentChar += Shift;
			
			while (currentChar > 90)
			{currentChar -= 26;}
			
			while (currentChar < 65)    // Negative Werte
			{currentChar += 26;}
				}
			
				else if(currentChar > 96 && currentChar < 123)
				
				{	currentChar += Shift;
			
			while (currentChar > 122)
			{currentChar -= 26;}
			
			while (currentChar < 97)
			{currentChar += 26;}
			
				}
			Output += currentChar;

		}
		write (Output);
	}
}
