package wolf_j.com.github.tdd.harry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author wolf-J
 *
 */
public class HarryBook extends Book {

	static HarryBook harryBookA = new HarryBook("HarryBookA");
	static HarryBook harryBookB = new HarryBook("HarryBookB");
	static HarryBook harryBookC = new HarryBook("HarryBookC");
	static HarryBook harryBookD = new HarryBook("HarryBookD");
	static HarryBook harryBookE = new HarryBook("HarryBookE");

	static final double UNITPRICE = 8d;

	private HarryBook(String name) {
		this(name, UNITPRICE);
	}

	private HarryBook(String name, double price) {
		this.setName(name);
		this.setPrice(price);
	}

	public static double getHarryTotalPrice(Map<HarryBook, Integer> harryBooks) throws HarryBookException {
		Map<HarryBook, Integer> actualHarryBooks = clearEmptyBooks(harryBooks);
		if (actualHarryBooks.isEmpty())
			return 0;
		return getTotalPrice(actualHarryBooks);
	}

	private static Map<HarryBook, Integer> clearEmptyBooks(Map<HarryBook, Integer> harryBooks) {
		Map<HarryBook, Integer> actualHarryBooks = new HashMap<>();
		for (Entry<HarryBook, Integer> bookEntry : harryBooks.entrySet()) {
			if (!bookEntry.getValue().equals(0))
				actualHarryBooks.put(bookEntry.getKey(), bookEntry.getValue());
		}
		return actualHarryBooks;
	}

	private static double getTotalPrice(Map<HarryBook, Integer> harryBooks) throws HarryBookException {

		int maxItemsNumber = getItemsNumber(harryBooks);

		Map<HarryBook, Integer> remainingHarryBooks = getRainingHarryBooks(harryBooks, maxItemsNumber);

		int harryBooksSize = harryBooks.size();
		if (harryBooksSize == 1)
			return maxItemsNumber * getPriceOfBook(harryBooksSize, 0);
		if (harryBooksSize == 2)
			return maxItemsNumber * getPriceOfBook(harryBooksSize, 0.05)
					+ HarryBook.getHarryTotalPrice(remainingHarryBooks);
		if (harryBooksSize == 3)
			return maxItemsNumber * getPriceOfBook(harryBooksSize, 0.10)
					+ HarryBook.getHarryTotalPrice(remainingHarryBooks);
		if (harryBooksSize == 4)
			return maxItemsNumber * getPriceOfBook(harryBooksSize, 0.20)
					+ HarryBook.getHarryTotalPrice(remainingHarryBooks);
		if (harryBooksSize == 5)
			return maxItemsNumber * getPriceOfBook(harryBooksSize, 0.25)
					+ HarryBook.getHarryTotalPrice(remainingHarryBooks);
		throw new HarryBookException();
	}

	private static int getItemsNumber(Map<HarryBook, Integer> harryBooksTemp) {
		List<Integer> values = new ArrayList<>(harryBooksTemp.values());
		values.sort((Integer a, Integer b) -> a.compareTo(b));
		return values.get(0);
	}

	private static Map<HarryBook, Integer> getRainingHarryBooks(Map<HarryBook, Integer> harryBooks, int maxItmsNumber) {
		Map<HarryBook, Integer> remainingHarryBooks = new HashMap<>();
		for (Entry<HarryBook, Integer> bookEntry : harryBooks.entrySet())
			remainingHarryBooks.put(bookEntry.getKey(), bookEntry.getValue() - maxItmsNumber);
		return remainingHarryBooks;
	}

	private static double getPriceOfBook(int harryBooksSize, double disCountRate) {
		return UNITPRICE * harryBooksSize * (1 - disCountRate);
	}

	public static class HarryBookException extends Exception {

		private static final long serialVersionUID = -2245482009889848599L;

		public HarryBookException() {
			super("The Illegel Harry Book Name!");
		}
	}

	/**
	 * @return the harryBookA
	 */
	public static HarryBook getHarryBookA() {
		return harryBookA;
	}

	/**
	 * @return the harryBookB
	 */
	public static HarryBook getHarryBookB() {
		return harryBookB;
	}

	/**
	 * @return the harryBookC
	 */
	public static HarryBook getHarryBookC() {
		return harryBookC;
	}

	/**
	 * @return the harryBookD
	 */
	public static HarryBook getHarryBookD() {
		return harryBookD;
	}

	/**
	 * @return the harryBookE
	 */
	public static HarryBook getHarryBookE() {
		return harryBookE;
	}

}
