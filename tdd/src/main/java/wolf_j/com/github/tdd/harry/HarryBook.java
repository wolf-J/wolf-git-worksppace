package wolf_j.com.github.tdd.harry;

/**
 * @author wolf-J
 *
 */
public class HarryBook extends Book {

	private static HarryBook instance;

	private HarryBook() {
	}

	HarryBook(String name, double price) {
		this.setName(name);
		this.setPrice(price);
	}

	/**
	 * @param name
	 * @param price
	 * @return the instance
	 */
	public static HarryBook getInstance(String name, double price) {
		if (instance == null) {
			instance = new HarryBook(name, price);
		}
		return instance;
	}

}
