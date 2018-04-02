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
	private static final String PRE_AND_END_FIX = "|";
	static {
		CodeMap.CODE_MAP.put("0", "||:::");
		CodeMap.CODE_MAP.put("1", ":::||");
		CodeMap.CODE_MAP.put("2", "::|:|");
		CodeMap.CODE_MAP.put("3", "::||:");
		CodeMap.CODE_MAP.put("4", ":|::|");
		CodeMap.CODE_MAP.put("5", ":|:|:");
		CodeMap.CODE_MAP.put("6", ":||::");
		CodeMap.CODE_MAP.put("7", "|:::|");
		CodeMap.CODE_MAP.put("8", "|::|:");
		CodeMap.CODE_MAP.put("9", "|:|::");
	}

	private String value;

	/**
	 * @throws Throwable
	 * 			@throws
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
		if (code.startsWith(PRE_AND_END_FIX) && code.endsWith(PRE_AND_END_FIX)) {
			String body = getBody(code);
			Integer numberOfBody = (body.length()) / 5;
			Integer total = 0;
			for (int i = 0; i < numberOfBody; i++) {
				String singleValue = body.substring(i * 5, i * 5 + 5);
				String singleValueNumber = getKeyfromCodeMap(singleValue);
				if (singleValueNumber == null || singleValueNumber.isEmpty()) {
					return false;
				}
				total += Integer.valueOf(singleValueNumber);
			}
			if ((total + Integer.valueOf(getKeyfromCodeMap(
					getValidationKey(code))))
					% 10 == 0) {
				return true;
			}
			System.err.println(total);
		}
		return false;
	}

	/**
	 * @param barCodeValue
	 * @return
	 */
	static String getValidationKey(String barCodeValue) {
		return barCodeValue.substring(barCodeValue.lastIndexOf(PRE_AND_END_FIX) - 5, barCodeValue.lastIndexOf(PRE_AND_END_FIX));
	}

	/**
	 * @param string
	 * @return
	 * @throws Throwable
	 */
	public static BarCode convertZipCodeToBarCode(ZipCode zipCode) throws Throwable {
		StringBuilder barCode = new StringBuilder(BarCode.PRE_AND_END_FIX);
		barCode.append(BarCode.PRE_AND_END_FIX);
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
		Integer numberOfBody = (body.length()) / 5;
		for (int i = 0; i < numberOfBody; i++) {
			String singleValue = body.substring(i * 5, i * 5 + 5);
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
		return code.substring(code.indexOf(PRE_AND_END_FIX) + 1, code.lastIndexOf(PRE_AND_END_FIX) - 5);
	}

	/**
	 * @return
	 * @throws Throwable 
	 */
	public String getBodyNumber() throws Throwable {
		return getBodyNumber(getValue());
	}

}
