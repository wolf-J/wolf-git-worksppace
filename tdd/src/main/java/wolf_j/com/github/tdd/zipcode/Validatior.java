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
	 * 
	 */
	private static final String PRE_AND_END_FIX = "|";
	/**
	 * 
	 */
	private static final int NINE_AND_TEN_BIT_ZIPCODE_BARCODE = 52;
	private static final int FIVE_BIT_ZIPCODE_BARCODE = 32;
	

	/**
	 * @param string
	 * @return
	 */
	public static boolean validateCode(String code) {
		if (ZipCode.isZipCode(code) || isBarCode(code))
			return true;
		return false;
	}

	/**
	 * @param code
	 * @return
	 */
	static boolean isBarCode(String code) {
		if(code.length()==Validatior.FIVE_BIT_ZIPCODE_BARCODE || code.length()==Validatior.NINE_AND_TEN_BIT_ZIPCODE_BARCODE)
			if(code.startsWith(Validatior.PRE_AND_END_FIX) && code.endsWith(Validatior.PRE_AND_END_FIX))
			return true;
		return false;
	}
	public static void main(String[] args) {
		System.out.println(CodeConvertor.showCodeConvertResult("|:|:|::|:|:||:::||:::::||::|:|::|:|:||:::||:::::||:|"));
	}
	

}
