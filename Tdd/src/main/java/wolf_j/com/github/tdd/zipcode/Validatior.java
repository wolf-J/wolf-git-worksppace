/**
 * 
 */
package wolf_j.com.github.tdd.zipcode;

/**
 * @author wolf-J
 *
 */
public class Validatior {

	/**
	 * @param string
	 * @return
	 */
	public static boolean validateCode(String code) {
		if (isZipCode(code) || isBarCode(code))
			return true;
		return false;
	}

	/**
	 * @param code
	 * @return
	 */
	static boolean isZipCode(String code) {
		if (code.matches("\\d{5}(_?\\d{4})?")) 
			return true;
		return false;
	}

	/**
	 * @param code
	 * @return
	 */
	private static boolean isBarCode(String code) {
		if(code.length()==32 || code.length()==52)
			return true;
		return false;
	}

}
