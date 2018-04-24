package wolf_j.com.github.tdd.harry;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * @author wolf-J
 *
 */
public class HarryBooksTest {

	private static final double UNITPRICE = 8d;

	@Test
	void should_be_0_when_given_no_book() {
		Map<HarryBook, Integer> harryBooks = new HashMap<>();
		assertEquals(0d, HarryBook.getHarryTotalPrice(harryBooks), 0.01d);
	}

	@Test
	void should_be_not_discount_when_given_1_bookA() {
		Map<HarryBook, Integer> harryBooks = new HashMap<>();
		harryBooks.put(HarryBook.getHarryBookA(), 1);

		assertEquals(HarryBooksTest.UNITPRICE, HarryBook.getHarryTotalPrice(harryBooks), 0.01d);
	}

	@Test
	void should_be_not_discount_when_given_A1_B0() {
		Map<HarryBook, Integer> harryBooks = new HashMap<>();
		harryBooks.put(HarryBook.getHarryBookA(), 1);
		harryBooks.put(HarryBook.getHarryBookB(), 0);

		assertEquals(HarryBooksTest.UNITPRICE, HarryBook.getHarryTotalPrice(harryBooks), 0.01d);
	}

	@Test
	void should_be_not_discount_when_given_1_bookB() {
		Map<HarryBook, Integer> harryBooks = new HashMap<>();

		harryBooks.put(HarryBook.getHarryBookB(), 1);

		assertEquals(HarryBooksTest.UNITPRICE, HarryBook.getHarryTotalPrice(harryBooks), 0.01d);
	}

	@Test
	void should_be_not_discount_when_given_2_bookA() {
		Map<HarryBook, Integer> harryBooks = new HashMap<>();

		harryBooks.put(HarryBook.getHarryBookA(), 2);

		assertEquals(HarryBooksTest.UNITPRICE * 2, HarryBook.getHarryTotalPrice(harryBooks), 0.01d);
	}

	@Test
	void should_be_discount_when_given_A1_B1() {
		Map<HarryBook, Integer> harryBooks = new HashMap<>();
		harryBooks.put(HarryBook.getHarryBookA(), 1);
		harryBooks.put(HarryBook.getHarryBookB(), 1);

		assertEquals(HarryBooksTest.UNITPRICE * 2 * (1 - 0.05), HarryBook.getHarryTotalPrice(harryBooks), 0.01d);
	}

	@Test
	void should_be_discount_when_given_A1_C1() {
		Map<HarryBook, Integer> harryBooks = new HashMap<>();
		harryBooks.put(HarryBook.getHarryBookA(), 1);
		harryBooks.put(HarryBook.getHarryBookC(), 1);

		assertEquals(HarryBooksTest.UNITPRICE * 2 * (1 - 0.05), HarryBook.getHarryTotalPrice(harryBooks), 0.01d);
	}

	@Test
	void should_be_not_discount_when_given_A1_B1_C1() {
		Map<HarryBook, Integer> harryBooks = new HashMap<>();
		harryBooks.put(HarryBook.getHarryBookA(), 1);
		harryBooks.put(HarryBook.getHarryBookB(), 1);
		harryBooks.put(HarryBook.getHarryBookC(), 1);

		assertEquals(HarryBooksTest.UNITPRICE * 3 * (1 - 0.1), HarryBook.getHarryTotalPrice(harryBooks), 0.01d);
	}

	@Test
	void should_be_not_discount_when_given_A2_B1() {
		Map<HarryBook, Integer> harryBooks = new HashMap<>();
		harryBooks.put(HarryBook.getHarryBookA(), 2);
		harryBooks.put(HarryBook.getHarryBookB(), 1);

		assertEquals(HarryBooksTest.UNITPRICE * (2 * (1 - 0.05) + 1), HarryBook.getHarryTotalPrice(harryBooks), 0.01d);
	}

	@Test
	void should_be_not_discount_when_given_A2_B3_C1_D4_E3() {
		Map<HarryBook, Integer> harryBooks = new HashMap<>();
		harryBooks.put(HarryBook.getHarryBookA(), 2);
		harryBooks.put(HarryBook.getHarryBookB(), 3);
		harryBooks.put(HarryBook.getHarryBookC(), 1);
		harryBooks.put(HarryBook.getHarryBookD(), 4);
		harryBooks.put(HarryBook.getHarryBookE(), 3);

		assertEquals(HarryBooksTest.UNITPRICE * (5 * 0.75 + 4 * 0.8 + 3 * 0.9 + 1),
				HarryBook.getHarryTotalPrice(harryBooks), 0.01d);
	}
}
