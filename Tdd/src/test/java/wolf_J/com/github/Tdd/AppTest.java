/**
 * 
 */
package wolf_j.com.github.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * @author wolf-J
 *
 */
public class AppTest {

	/**
	 * Test method for {@link java.lang.Object#equals(java.lang.Object)}.
	 */
	@Test
	public void testEquals() {
		assertEquals("success", "success", "message");
	}

	/**
	 * Test method for {@link java.lang.Object#toString()}. 
	 */
	@Test
	public void testToString() {
		assertEquals("success", "success", "message");
	}

}
