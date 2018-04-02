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
	@DisplayName("validateCode should fail validation when given a error ZipCode")
	void should_fail_validation_when_given_a_error_ZipCode() {
		assertFalse(Validatior.validateCode("3215"));
	}
	
	@Test
	@DisplayName("validateCode should true validation when given a right ZipCode")
	void should_true_validation_when_given_a_right_ZipCode() {
		assertTrue(Validatior.validateCode("12345_6789"));
	}

	
	@Test
	@DisplayName("validateCode should true validation when given a right BarCode")
	void should_true_validation_when_given_a_right_BarCode() {
		assertTrue(Validatior.validateCode("|:|:|::|:|:||:::||:::::||:|:::||"));
	}
	
	@Test
	@DisplayName("validateCode should false validation when given a error BarCode")
	void should_false_validation_when_given_a_error_BarCode() {
		assertFalse(Validatior.validateCode(":::|||"));
	}
	
	@Test
	@DisplayName("validateCode should false validation when given a error 9 bit BarCode")
	void validateCode_should_be_false_when_given_a_error_9_bit_barcode() {
		assertFalse(Validatior.validateCode("|:|:|::|:|:||:::||:::::||::|:|::|:|:||:::||:::::||:|"));
	}
	
	@Test
	@DisplayName(value = "isBarCode(\"|:|:|::|:|:||:::||:::::||:||:::||\") == TRUE")
	void should_is_BarCode_when_given_five_bit_barcode() {
		assertTrue(Validatior.isBarCode("|:|:|::|:|:||:::||:::::||:|:::||"));
	}
	
	@Test
	@DisplayName(value = "isBarCode(\":::|||\") == FALSE")
	void should_isnot_BarCode_when_given_a_error_BarCode() {
		assertFalse(Validatior.isBarCode(":::|||"));
	}
	
	@Test
	@DisplayName(value = "isBarCode(\"|:|:|::|:|:||:::||:::::||::|:|::|:|:||:::||:::::||:|\") == FALSE")
	void should_isnot_BarCode_when_given_a_error_9_bit_BarCode() {
		assertFalse(Validatior.isBarCode("|:|:|::|:|:||:::||:::::||::|:|::|:|:||:::||:::::||:|"));
	}
	
	
}
