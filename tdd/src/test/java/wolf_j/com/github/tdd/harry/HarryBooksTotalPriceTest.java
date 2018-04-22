package wolf_j.com.github.tdd.harry;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import wolf_j.com.github.tdd.harry.HarryBookFactor.HarryBookException;

/**
 * @author wolf-J
 *
 */
public class HarryBooksTotalPriceTest {

	private static final double UNITPRICE = 8d;

	@Test
	void should_be_0_when_given_no_book() throws HarryBookException {
		Map<HarryBook, Integer> harryBooks = new HashMap<>();
		assertEquals(0d, HarryBooksTotalPrice.getHarryTotalPrice(harryBooks), 0.01d);
	}

	@Test
	void should_be_8_when_given_1_bookA() throws HarryBookException {
		Map<HarryBook, Integer> harryBooks = new HashMap<>();
		HarryBook harryBookA = HarryBookFactor.createHarryBook("HarryBookA");
		harryBooks.put(harryBookA, 1);

		assertEquals(HarryBooksTotalPriceTest.UNITPRICE, HarryBooksTotalPrice.getHarryTotalPrice(harryBooks), 0.01d);
	}

}
