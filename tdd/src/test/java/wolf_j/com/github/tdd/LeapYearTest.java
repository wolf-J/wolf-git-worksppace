/**
 * 
 */
package wolf_j.com.github.tdd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author wolf-J
 *
 */
public class LeapYearTest {

	@Test
	public void should_be_leapyear_when_given_2000() {
		assertTrue(LeapYear.isLeapYear(2000));
	}

	@Test
	public void should_be_not_leapyear_when_given_2001() {
		assertFalse(LeapYear.isLeapYear(2001));
	}

	@Test
	public void should_be_not_leapyear_when_given_1900() {
		assertFalse(LeapYear.isLeapYear(1900));
	}

	@Test
	public void should_be_leapyear_when_given_2004() {
		assertTrue(LeapYear.isLeapYear(2004));
	}
}
