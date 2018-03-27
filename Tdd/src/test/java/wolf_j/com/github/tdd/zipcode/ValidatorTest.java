/**
 * 
 */
package wolf_j.com.github.tdd.zipcode;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author wolf-J
 *
 */
class ValidatorTest {

	@Test
	void should_fail_validation_when_given_a_error_BarCode() {
		assertFalse("valatieFail", Validatior.validateCode("3215"));
	}
	
	@Test
	@DisplayName(value = "Test isZipCode(\"11223_\") == FALSE")
	void should_isnot_ZipCode_when_given_11223_() {
		assertFalse(Validatior.isZipCode("11223_"));
	}
	
	@Test
	@DisplayName(value = "Test isZipCode(\"11223_9854\") == TRUE")
	void should_isnot_ZipCode_when_given_11223_9854() {
		assertTrue(Validatior.isZipCode("11223_9854"));
	}
}
