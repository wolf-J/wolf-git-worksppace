package wolf_j.com.github.tdd.harry;

import java.util.Arrays;
import java.util.List;

public class HarryBookFactor {

	private HarryBookFactor() {
	}

	static final List<String> BOOKNAMES = Arrays.asList("HarryBookA", "HarryBookB", "HarryBookC", "HarryBookD",
			"HarryBookE");

	static final double UNITPRICE = 8d;

	public static HarryBook createHarryBook(String name) throws HarryBookException {
		if (BOOKNAMES.contains(name))
			return HarryBook.getInstance(name, HarryBookFactor.UNITPRICE);
		throw new HarryBookException();
	}

	static class HarryBookException extends Exception {

		private static final long serialVersionUID = 8811006944592952470L;

		public HarryBookException() {
			super("The Illegel Harry Book Name!");
		}

	}
}
