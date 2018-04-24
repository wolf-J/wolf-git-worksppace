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

	static HarryBook harryBookA;
	static HarryBook harryBookB;
	static HarryBook harryBookC;
	static HarryBook harryBookD;
	static HarryBook harryBookE;

	static final double UNITPRICE = 8d;
	static {
		HarryBook.harryBookA = new HarryBook("HarryBookA");
		HarryBook.harryBookB = new HarryBook("HarryBookB");
		HarryBook.harryBookC = new HarryBook("HarryBookC");
		HarryBook.harryBookD = new HarryBook("HarryBookD");
		HarryBook.harryBookE = new HarryBook("HarryBookE");
	}

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
		int maxItemsNumber = getItemsNumber(actualHarryBooks);
		return getTotalPrice(actualHarryBooks, maxItemsNumber);
	}

	private static Map<HarryBook, Integer> clearEmptyBooks(Map<HarryBook, Integer> harryBooks) {
		Map<HarryBook, Integer> harryBooksTemp = harryBooks;
		List<HarryBook> removedList = new ArrayList<>();
		for (Entry<HarryBook, Integer> bookEntry : harryBooksTemp.entrySet()) {
			if (bookEntry.getValue().equals(0))
				removedList.add(bookEntry.getKey());
		}
		for (HarryBook harryBook : removedList)
			harryBooksTemp.remove(harryBook);
		return harryBooksTemp;
	}

	private static int getItemsNumber(Map<HarryBook, Integer> harryBooksTemp) {
		List<Integer> values = new ArrayList<>(harryBooksTemp.values());
		values.sort((Integer a, Integer b) -> {
			return a.compareTo(b);
		});
		return values.get(0);
	}

	private static double getTotalPrice(Map<HarryBook, Integer> harryBooks, int maxItmsNumber)
			throws HarryBookException {

		Map<HarryBook, Integer> remainingHarryBooks = getRainingHarryBooks(harryBooks, maxItmsNumber);

		int harryBooksSize = harryBooks.size();
		if (harryBooksSize == 1)
			return maxItmsNumber * getPriceOfBook(harryBooksSize, 0);
		if (harryBooksSize == 2)
			return maxItmsNumber * getPriceOfBook(harryBooksSize, 0.05)
					+ HarryBook.getHarryTotalPrice(remainingHarryBooks);
		if (harryBooksSize == 3)
			return maxItmsNumber * getPriceOfBook(harryBooksSize, 0.10)
					+ HarryBook.getHarryTotalPrice(remainingHarryBooks);
		if (harryBooksSize == 4)
			return maxItmsNumber * getPriceOfBook(harryBooksSize, 0.20)
					+ HarryBook.getHarryTotalPrice(remainingHarryBooks);
		if (harryBooksSize == 5)
			return maxItmsNumber * getPriceOfBook(harryBooksSize, 0.25)
					+ HarryBook.getHarryTotalPrice(remainingHarryBooks);
		throw new HarryBookException();
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
