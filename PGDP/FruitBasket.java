package pgdp;

import java.util.LinkedList;

public class FruitBasket extends Fruit {

	public LinkedList<Fruit> fruits = new LinkedList<>();

	//Früchte werden der Liste hinzugefügt
	public void addFruit(Fruit f) {
		fruits.add(f);
		
	}

	//gibt Liste zurück, die nur aus Äpfeln besteht
	public LinkedList<Apple> getApples() { 
		
		LinkedList<Apple> apple = new LinkedList<>();
		for (Fruit current : fruits) {
			if (current.isApple()) {
				if (current.isGrannySmith()) {

					apple.add(new GrannySmith());
				}
				else if (current.isPinkLady()) {
					apple.add(new PinkLady());

				} else {
					apple.add(new Apple());
				}
			}
		}
		return apple;
	}

	//gibt Liste zurück, die mindestens n haltbar ist
	public LinkedList<Fruit> getEqualOrLongerShelfLife(int n) {
		LinkedList<Fruit> fruitslongsl = new LinkedList<>();
		for (Fruit current : fruits) {
			if (current.shelfLife() >= n) {
				fruitslongsl.add(current);
			}
		}
		return fruitslongsl;
	}

//	public String contentOfFruitBasket(int n) {
//
//		System.out.println("Der Früchtekorb beinhaltet:");
//		for (Fruit current : fruits)
//			System.out.println(current.toString());
//
//		System.out.println("---------------------------------------------------");
//
//		System.out.println("Früchte mit der Lebensdauer " + n + " oder mehr: ");
//		for (Fruit current : getEqualOrLongerShelfLife(n))
//			System.out.println(current.toString());
//
//		System.out.println("---------------------------------------------------");
//
//		System.out.println("Vorhandene Äpfel: ");
//
//		for (Fruit current : getApples())
//			System.out.println(current.toString());
//
//		System.out.println("---------------------------------------------------");
//
//		return "\n";
//	}

	public static void main(String[] args) {

//		Testmethode
//		FruitBasket fb = new FruitBasket();
//		fb.contentOfFruitBasket(15);
//		fb.addFruit(new Banana());
//		fb.addFruit(new Apple());
//		fb.addFruit(new GrannySmith());
//		fb.addFruit(new PinkLady());
//		fb.addFruit(new Pineapple());
//		fb.contentOfFruitBasket(20);
//		System.out.println("---------------------------------------------------");
//		System.out.println("---------------------------------------------------");
//		fb.addFruit(new PinkLady());
//		fb.addFruit(new Pineapple());
//		fb.addFruit(new GrannySmith());
//		fb.addFruit(new Apple());
//		fb.addFruit(new Banana());
//		fb.contentOfFruitBasket(40);
		


		
	}

}
