/**
 * 
 */
package wolf_j.com.github.tdd.zipcode;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author wolf-J
 *
 */
/**
 * @author wolf-J
 *
 */
class BarCodeTest {

	/**
	 * Test method for
	 * {@link wolf_j.com.github.tdd.zipcode.BarCode#BarCode(java.lang.String)}.
	 */
	@Test
	void testBarCode() {
		fail("Not yet implemented");
	}

	@Test
	void testGetValue() {

	}

	/**
	 * Test method for
	 * {@link wolf_j.com.github.tdd.zipcode.BarCode#isBarCode(java.lang.String)}.
	 */
	@Test
	@DisplayName("isBarCode(\":::|||\") == False")
	void testIsBarCode_when_given_have_not_prefix() {
		assertFalse(BarCode.isBarCode(":::|||"));
	}

	@Test
	@DisplayName("isBarCode(\"|:|:|::|:|:||:::||:::::||::|:|::|:|:||:::||:::::||:|\") == False")
	void testIsBarCode_when_given_a_error_validationKey_barcode() {
		assertFalse(BarCode.isBarCode("|:|:|::|:|:||:::||:::::||::|:|::|:|:||:::||:::::||:|"));
	}

	@Test
	@DisplayName("isBarCode(\"|:|:|::|:|:||:::||:::::||:|:::||\") == False")
	void testIsBarCode_when_given_five_number_barcode() {
		assertFalse(BarCode.isBarCode("|:|:|::|:|:||:::||:::::||:|:::||"));
	}

	/**
	 * Test method for
	 * {@link wolf_j.com.github.tdd.zipcode.BarCode#convertZipCodeToBarCode(wolf_j.com.github.tdd.zipcode.ZipCode)}.
	 * @throws Throwable 
	 */
	@Test
	void testConvertZipCodeToBarCode() throws Throwable {
		assertEquals("|:|:|::|:|:||:::||:::::||:|:::||", BarCode.convertZipCodeToBarCode(new ZipCode("55003")));
	}

}
