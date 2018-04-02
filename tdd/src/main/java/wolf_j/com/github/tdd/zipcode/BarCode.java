/**
 * 
 */
package wolf_j.com.github.tdd.zipcode;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author wolf-J
 *
 */
public class BarCode {

	/**
	 * 
	 */
	private static final int BARCODE_MAP_ZIP_NUMBER = 5;

	/**
	 * 
	 */
	private static final String PRE_AND_POST_FIX = "|";

	private String value;

	/**
	 * @throws Throwable @throws
	 * 
	 */
	public BarCode(String value) throws Throwable {
		if (isBarCode(value))
			this.setValue(value);
		else
			throw new Exception("This is not a BarCode!");
	}

	/**
	 * @param input
	 * @return
	 */
	public static boolean isBarCode(String code) {
		if (code.startsWith(PRE_AND_POST_FIX) && code.endsWith(PRE_AND_POST_FIX)) {
			String body = getBody(code);
			Integer numberOfBody = (body.length()) / BarCode.BARCODE_MAP_ZIP_NUMBER;
			if (!CodeMap.ZIPCODE_NUMBER_RANGE.contains(numberOfBody))
				return false;
			Integer total = 0;
			for (int i = 0; i < numberOfBody; i++) {
				String singleValue = body.substring(i * BarCode.BARCODE_MAP_ZIP_NUMBER,
						i * BarCode.BARCODE_MAP_ZIP_NUMBER + BarCode.BARCODE_MAP_ZIP_NUMBER);
				String singleValueNumber = getKeyfromCodeMap(singleValue);
				if (singleValueNumber == null || singleValueNumber.isEmpty()) {
					return false;
				}
				total += Integer.valueOf(singleValueNumber);
			}
			if ((total + Integer.valueOf(getKeyfromCodeMap(getValidationKey(code)))) % 10 == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param barCodeValue
	 * @return
	 */
	static String getValidationKey(String barCodeValue) {
		return barCodeValue.substring(barCodeValue.lastIndexOf(PRE_AND_POST_FIX) - BarCode.BARCODE_MAP_ZIP_NUMBER,
				barCodeValue.lastIndexOf(PRE_AND_POST_FIX));
	}

	/**
	 * @param string
	 * @return
	 * @throws Throwable
	 */
	public static BarCode convertZipCodeToBarCode(ZipCode zipCode) throws Throwable {
		StringBuilder barCode = new StringBuilder(BarCode.PRE_AND_POST_FIX);

		barCode.append(BarCode.PRE_AND_POST_FIX);
		return new BarCode(barCode.toString());

	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	private void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return
	 * @throws Throwable
	 */
	private String getBodyNumber(String barcodeValue) throws Throwable {
		StringBuilder bodyNumber = new StringBuilder();
		String body = getBody(barcodeValue);
		Integer numberOfBody = (body.length()) / BarCode.BARCODE_MAP_ZIP_NUMBER;
		for (int i = 0; i < numberOfBody; i++) {
			String singleValue = body.substring(i * BarCode.BARCODE_MAP_ZIP_NUMBER,
					i * BarCode.BARCODE_MAP_ZIP_NUMBER + BarCode.BARCODE_MAP_ZIP_NUMBER);
			String singleValueNumber = getKeyfromCodeMap(singleValue);
			if (singleValueNumber == null || singleValueNumber.isEmpty()) {
				throw new Exception("Error Barcode!");
			}
			bodyNumber.append(singleValueNumber);
		}
		return bodyNumber.toString();
	}

	/**
	 * @param singleValue
	 * @return
	 */
	static String getKeyfromCodeMap(String singleValue) {
		Iterator<Entry<String, String>> iterator = CodeMap.CODE_MAP.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<java.lang.String, java.lang.String> entry = (Map.Entry<java.lang.String, java.lang.String>) iterator
					.next();
			if (entry.getValue().equals(singleValue)) {
				return entry.getKey();
			}

		}
		return null;
	}

	/**
	 * @return
	 */
	private static String getBody(String code) {
		return code.substring(code.indexOf(PRE_AND_POST_FIX) + 1,
				code.lastIndexOf(PRE_AND_POST_FIX) - BarCode.BARCODE_MAP_ZIP_NUMBER);
	}

	/**
	 * @return
	 * @throws Throwable
	 */
	public String getBodyNumber() throws Throwable {
		return getBodyNumber(getValue());
	}

}
