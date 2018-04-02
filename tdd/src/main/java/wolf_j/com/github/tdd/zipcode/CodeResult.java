/**
 * 
 */
package wolf_j.com.github.tdd.zipcode;

/**
 * @author wolf-J
 *
 */
public class CodeResult {
	
	public String showCodeResult(String input) throws Throwable {
	if(ZipCode.isZipCode(input)) {
		return BarCode.convertZipCodeToBarCode(new ZipCode(input)).getValue();
	}
	if(BarCode.isBarCode(input)) {
		return ZipCode.convertBarCodeToZipCode(new BarCode(input)).getValue();
	}
	throw new Exception("Inout Error!");
	}
	
}
