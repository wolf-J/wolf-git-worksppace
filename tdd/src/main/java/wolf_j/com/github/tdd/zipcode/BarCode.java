/**
 * 
 */
package wolf_j.com.github.tdd.zipcode;

/**
 * @author wolf-J
 *
 */
public class BarCode {

	private static final String PRE_FIX = "|";
	private static final String POST_FIX = "|";

	private static final int BARCODE_MAP_ZIP_NUMBER = 5;

	private String value;

	public BarCode(String value) throws Throwable {
		if (isBarCode(value))
			this.value = value;
		else
			throw new Exception("Please input a right BarCode!");
	}

	public static boolean isBarCode(String code) throws Throwable {
		if (code.startsWith(PRE_FIX) && code.endsWith(POST_FIX)) {
			if (validateBodyNumberLength(code) && validateKey(code))
				return true;
		}
		return false;
	}

	public static BarCode convertZipCodeToBarCode(ZipCode zipCode) throws Throwable {
		StringBuilder barCodeValue = new StringBuilder();
		barCodeValue.append(BarCode.PRE_FIX);
		barCodeValue.append(convertToBody(zipCode));
		barCodeValue.append(BarCode.POST_FIX);
		return new BarCode(barCodeValue.toString());

	}

	private static String convertToBody(ZipCode zipCode) {
		String bodyNumber = zipCode.getValueNumber();
		StringBuilder body = new StringBuilder();
		Integer total = 0;
		for (int i = 0; i < bodyNumber.length(); i++) {
			String singleNumber = Character.toString(bodyNumber.charAt(i));
			body.append(CodeRelationShip.getValuefromCodeMap(singleNumber));
			total += Integer.valueOf(singleNumber);
		}
		String validationKey = CodeRelationShip.getValuefromCodeMap(Integer.toString(Math.abs(10 - total % 10)));
		return body.toString() + validationKey;
	}

	private static boolean validateBodyNumberLength(String code) throws Throwable {
		String bodyNumber = convertNumbers(getBody(code));
		return CodeRelationShip.ZIPCODE_NUMBER_RANGE.contains(bodyNumber.length());
	}

	private static boolean validateKey(String code) throws Throwable {
		String bodyNumber = convertNumbers(getBody(code));
		String keyKumber = convertNumbers(getValidationKey(code));
		Integer bodyTotal = 0;
		for (char c : bodyNumber.toCharArray()) {
			bodyTotal += Integer.valueOf(Character.toString(c));
		}
		return ((Integer.valueOf(keyKumber) + bodyTotal) % 10 == 0) ? true : false;
	}

	private static String getBody(String code) {
		return code.substring(code.indexOf(PRE_FIX) + 1, code.lastIndexOf(POST_FIX) - BarCode.BARCODE_MAP_ZIP_NUMBER);
	}

	private String getBodyNumber(String barcodeValue) throws Throwable {
		return convertNumbers(getBody(barcodeValue));
	}

	private static String getValidationKey(String barCodeValue) {
		return barCodeValue.substring(barCodeValue.lastIndexOf(PRE_FIX) - BarCode.BARCODE_MAP_ZIP_NUMBER,
				barCodeValue.lastIndexOf(POST_FIX));
	}

	private static String convertNumbers(String body) throws Throwable {
		StringBuilder numbers = new StringBuilder();
		for (int i = 0; i < body.length() / BARCODE_MAP_ZIP_NUMBER; i++) {
			String singleValueNumber = CodeRelationShip
					.getKeyfromCodeMap(body.substring(i * BARCODE_MAP_ZIP_NUMBER, (i + 1) * BARCODE_MAP_ZIP_NUMBER));
			if (singleValueNumber == null || singleValueNumber.isEmpty())
				throw new Exception("convert fail!");
			numbers.append(singleValueNumber);
		}
		return numbers.toString();
	}

	public String getBodyNumber() throws Throwable {
		return getBodyNumber(value);
	}

	public String getValue() {
		return value;
	}

}
