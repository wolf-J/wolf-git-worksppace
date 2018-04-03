/**
 * 
 */
package wolf_j.com.github.tdd.zipcode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author wolf-J
 *
 */
public class ZipCodeTest {
	
	@Test
	@DisplayName("ZipCode.isZipCode(\"3215\") == FALSE")
	void testIsZipCode_4number_ZipCode() {
		assertFalse(ZipCode.isZipCode("3215"));
	}
	
	@Test
	@DisplayName("ZipCode.isZipCode(\"11223-\") == FALSE")
	void testIsZipCode_6number_ZipCode() {
		assertFalse(ZipCode.isZipCode("11223-"));
	}
	
	@Test
	@DisplayName(value = "ZipCode.isZipCode(\\\"11223-9854\\\") == TRUE")
	void testIsZipCode_10number_right_ZipCode() {
		assertTrue(ZipCode.isZipCode("11223-9854"));
	}
	
	@Test
	@DisplayName("ZipCode.convertBarCodeToZipCode(\"|:|:|::|:|:||:::||:::::||:|:::||\") == \"55003\"")
	void testConvertToZipCode_when_given_a_five_number_right_ZipCode() throws Throwable {
		assertEquals("55003", ZipCode.convertBarCodeToZipCode(new BarCode("|:|:|::|:|:||:::||:::::||:|:::||")).getValue());
	}
	
	
}
