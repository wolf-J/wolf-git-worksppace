/**
 * 
 */
package wolf_j.com.github.tdd.zipcode;

/**
 * @author wolf-J
 *
 */
public class ZipCode {

	/**
	 * 
	 */
	private static final String ZIPCODE_SEPARATOR = "-";

	private String value;

	/**
	 * 
	 */
	private static final String ZIP_CODE_PATTERN = "\\d{5}(_?\\d{4})?";

	/**
	 * @param string
	 * @throws Throwable
	 */
	public ZipCode(String value) throws Throwable {
		if (isZipCode(value)) {
			this.value = format(value);
		} else {
			throw new Exception("ZipCode Input Error!");
		}
	}

	/**
	 * @param value2
	 * @return
	 */
	private String format(String zipCodeNumber) {
		if (zipCodeNumber.length() == 9)
			return zipCodeNumber.substring(0, 5) + ZipCode.ZIPCODE_SEPARATOR + zipCodeNumber.substring(5);
		return zipCodeNumber;
	}

	/**
	 * @param input
	 */

	/**
	 * @param code
	 * @return
	 */
	public static boolean isZipCode(String code) {
		if (code.matches(ZIP_CODE_PATTERN))
			return true;
		return false;
	}

	/**
	 * @param input
	 * @return
	 * @throws Throwable
	 */
	public static ZipCode convertBarCodeToZipCode(BarCode barCode) throws Throwable {
		return new ZipCode(barCode.getBodyNumber());
	}

	/**
	 * @return
	 */
	public String getValue() {
		return value;
	}

	public String getValueNumber() {
		if (value.contains(ZipCode.ZIPCODE_SEPARATOR))
			return value.replace(ZipCode.ZIPCODE_SEPARATOR, "");
		return value;

	}

}
