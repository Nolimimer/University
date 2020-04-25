package pgdp;

public class Fruit {

	public boolean isApple() {
		return false;
	}

	public boolean isGrannySmith() {
		return false;
	}

	public boolean isPinkLady() {
		return false;
	}

	public boolean isBanana() {
		return false;
	}

	public boolean isPineapple() {
		return false;
	}

	public int shelfLife() {

		if (isApple()) {
			if (isGrannySmith()) {
				return 50;
			}
			if (isPinkLady()) {
				return 20;
			}
			return 30;
		}
		if (isBanana()) {
			return 7;
		}

		if (isPineapple()) {
			return 20;
		}

		return -1;

	}


}
