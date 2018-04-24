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

	public HarryBook(String name) {
		this(name, UNITPRICE);
	}

	public HarryBook(String name, double price) {
		this.setName(name);
		this.setPrice(price);
	}

	public static double getHarryTotalPrice(Map<HarryBook, Integer> harryBooks) {

		List<HarryBook> removedList = new ArrayList<>();
		int itmsNumber = 0;
		for (Entry<HarryBook, Integer> bookEntry : harryBooks.entrySet()) {
			if (bookEntry.getValue().equals(0))
				removedList.add(bookEntry.getKey());
			else
				itmsNumber += bookEntry.getValue();
		}
		for (HarryBook harryBook : removedList) {
			harryBooks.remove(harryBook);
		}

		if (harryBooks.size() == 0)
			return 0;

		if (harryBooks.size() == 1)
			return itmsNumber * UNITPRICE;

		List<Integer> values = new ArrayList<>(harryBooks.values());
		values.sort((Integer a, Integer b) -> {
			return a.compareTo(b);
		});
		itmsNumber = values.get(0);
		Map<HarryBook, Integer> subHarryBooks = new HashMap<>();
		for (Entry<HarryBook, Integer> bookEntry : harryBooks.entrySet()) {
			subHarryBooks.put(bookEntry.getKey(), bookEntry.getValue() - itmsNumber);
		}
		if (harryBooks.size() == 2) {
			return itmsNumber * UNITPRICE * harryBooks.size() * (1 - 0.05)
					+ HarryBook.getHarryTotalPrice(subHarryBooks);
		}
		if (harryBooks.size() == 3)
			return itmsNumber * UNITPRICE * harryBooks.size() * (1 - 0.10)
					+ HarryBook.getHarryTotalPrice(subHarryBooks);
		if (harryBooks.size() == 4)
			return itmsNumber * UNITPRICE * harryBooks.size() * (1 - 0.20)
					+ HarryBook.getHarryTotalPrice(subHarryBooks);
		if (harryBooks.size() == 5)
			return itmsNumber * UNITPRICE * harryBooks.size() * (1 - 0.25)
					+ HarryBook.getHarryTotalPrice(subHarryBooks);
		return 0;
	}

}
