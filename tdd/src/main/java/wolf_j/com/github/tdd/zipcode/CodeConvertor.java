/**
 * 
 */
package wolf_j.com.github.tdd.zipcode;

/**
 * @author wolf-J
 *
 */
public class CodeConvertor {

	/**
	 * 
	 */
	static final String INPUT_EXCEPTTION_MESSAGE = "Please give right input!";

	public static String showCodeConvertResult(String input) {
		try {
			if (ZipCode.isZipCode(input)) {

				return BarCode.convertZipCodeToBarCode(new ZipCode(input)).getValue();

			}
			if (BarCode.isBarCode(input)) {
				return ZipCode.convertBarCodeToZipCode(new BarCode(input)).getValue();
			}

			throw new Exception(INPUT_EXCEPTTION_MESSAGE);
		} catch (Throwable e) {
			return e.getMessage();
		}

	}

}
