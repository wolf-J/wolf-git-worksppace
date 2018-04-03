/**
 * 
 */
package wolf_j.com.github.tdd.zipcode;

/**
 * @author wolf-J
 *
 */
public class BarCode {

	/**
	 * 
	 */
	private static final String PRE_FIX = "|";
	private static final String POST_FIX = "|";

	/**
	 * 
	 */
	private static final int BARCODE_MAP_ZIP_NUMBER = 5;

	private String value;

	/**
	 * @throws Throwable @throws
	 * 
	 */
	public BarCode(String value) throws Throwable {
		if (isBarCode(value))
			this.value = value;
		else
			throw new Exception("Please input a right BarCode!");
	}

	/**
	 * @param input
	 * @return
	 */
	public static boolean isBarCode(String code) {
		if (code.startsWith(PRE_FIX) && code.endsWith(POST_FIX)) {
			try {
				String bodyNumber = convertNumbers(getBody(code));
				String keyKumber = convertNumbers(getValidationKey(code));
				if (validateBodyNumberLength(bodyNumber) && validateKey(bodyNumber, keyKumber))
					return true;
			} catch (Throwable e) {
				return false;
			}
		}
		return false;
	}

	/**
	 * @param string
	 * @return
	 * @throws Throwable
	 */
	public static BarCode convertZipCodeToBarCode(ZipCode zipCode) throws Throwable {
		StringBuilder barCode = new StringBuilder();
		barCode.append(BarCode.PRE_FIX);
		barCode.append(convertToBody(zipCode));
		barCode.append(BarCode.POST_FIX);
		return new BarCode(barCode.toString());
	
	}

	/**
	 * @param bodyNumber
	 * @return
	 */
	private static String convertToBody(ZipCode zipCode) {
		String bodyNumber = zipCode.getValueNumber();
		StringBuilder body = new StringBuilder();
		Integer total = 0;
		for (int i = 0; i < bodyNumber.length(); i++) {
			String singleNumber = Character.toString(bodyNumber.charAt(i));
			body.append(CodeMap.getValuefromCodeMap(singleNumber));
			total += Integer.valueOf(singleNumber);
		}
		String validationKey = CodeMap.getValuefromCodeMap(Integer.toString(Math.abs(10 - total % 10)));
		return body.toString() + validationKey;
	}

	/**
	 * @param bodyNumber
	 * @return
	 */
	private static boolean validateBodyNumberLength(String bodyNumber) {
		return CodeMap.ZIPCODE_NUMBER_RANGE.contains(bodyNumber.length());
	}

	/**
	 * @param bodyNumber
	 * @param keyKumber
	 * @return
	 */
	private static boolean validateKey(String bodyNumber, String keyKumber) {
		Integer bodyTotal = 0;
		for (char c : bodyNumber.toCharArray()) {
			bodyTotal += Integer.valueOf(Character.toString(c));
		}
		return ((Integer.valueOf(keyKumber) + bodyTotal) % 10 == 0) ? true : false;
	}

	/**
	 * @return
	 */
	private static String getBody(String code) {
		return code.substring(code.indexOf(PRE_FIX) + 1, code.lastIndexOf(POST_FIX) - BarCode.BARCODE_MAP_ZIP_NUMBER);
	}

	/**
	 * @return
	 * @throws Throwable
	 */
	private String getBodyNumber(String barcodeValue) throws Throwable {
		return convertNumbers(getBody(barcodeValue));
	}

	/**
	 * @param barCodeValue
	 * @return
	 */
	private static String getValidationKey(String barCodeValue) {
		return barCodeValue.substring(barCodeValue.lastIndexOf(PRE_FIX) - BarCode.BARCODE_MAP_ZIP_NUMBER,
				barCodeValue.lastIndexOf(POST_FIX));
	}

	/**
	 * @param body
	 * @return
	 * @throws Throwable
	 */
	private static String convertNumbers(String body) throws Throwable {
		StringBuilder numbers = new StringBuilder();
		for (int i = 0; i < body.length() / BARCODE_MAP_ZIP_NUMBER; i++) {
			String singleValueNumber = CodeMap
					.getKeyfromCodeMap(body.substring(i * BARCODE_MAP_ZIP_NUMBER, (i + 1) * BARCODE_MAP_ZIP_NUMBER));
			if (singleValueNumber == null || singleValueNumber.isEmpty())
				throw new Exception("convert fail!");
			numbers.append(singleValueNumber);
		}
		return numbers.toString();
	}

	/**
	 * @return
	 * @throws Throwable
	 */
	public String getBodyNumber() throws Throwable {
		return getBodyNumber(getValue());
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

}
