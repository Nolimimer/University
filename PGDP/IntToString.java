
public class IntToString implements Fun<Integer, String> {

	@Override
	public String apply(Integer x) {
		String output = "" + x + "";
		return output;
	}

}
