/**
 * 
 */
package wolf_J.com.github.Tdd;

import static org.junit.Assert.*;

import org.junit.Test;

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
	public void should_be_true_when_given_4_and_2000()	{
		assertTrue(MathUtil.isMod(4, 2000));
	}
	
	@Test
	public void should_be_false_when_given_3_and_1999()	{
		assertFalse(MathUtil.isMod(3, 1999));
	}
	
	@Test
	public void should_be_not_leapyear_when_given_1900()	{
		assertFalse(LeapYear.isLeapYear(1900));
	}
}
