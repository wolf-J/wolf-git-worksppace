/**
 * 
 */
package wolf_j.com.github.relation.presistence;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author wolf
 *
 */
public class TestPasswordEncoding {

	@Test
	public void testPasswordEncoding() {
		assertTrue(new BCryptPasswordEncoder().matches("1111",
				"$2a$10$Tj4vMkLISQpwjv0EoToG.OsYxGS945b2U4Kn0Bh4qg3axyFMYfiQG"));
		System.out.println(System.currentTimeMillis());
	}

	@Test
	public void testBCryptPasswordEncoder() {
		assertTrue(new BCryptPasswordEncoder().matches("1111", new BCryptPasswordEncoder().encode("1111")));
	}

}
