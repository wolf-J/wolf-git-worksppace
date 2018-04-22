package wolf_j.com.github.tdd.harry;

import java.util.Map;

import wolf_j.com.github.tdd.harry.HarryBookFactor.HarryBookException;

public class HarryBooksTotalPrice {

	public static double getHarryTotalPrice(Map<HarryBook, Integer> harryBooks) throws HarryBookException {
		HarryBook harryBook = HarryBookFactor.createHarryBook("HarryBookA");
		int unitNumber = harryBooks.get(harryBook) == null ? 0 : harryBooks.get(harryBook);
		return unitNumber * HarryBookFactor.UNITPRICE;
	}

}
