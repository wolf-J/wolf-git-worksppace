/**
 * 
 */
package wolf_j.com.github.relation.presistence;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Wolf
 *
 */
public class TestUserMessageEntity {

	/**
	 * Test method for
	 * {@link wolf_j.com.github.relation.presistence.UserMessageEntity#validateBirthDay()}.
	 */
	@Test
	public void testValidateBirthDay() {
		UserMessageEntity userMessageEntity = new UserMessageEntity();
		
		userMessageEntity.setBirthDay("2018-04-05");
		assertTrue(userMessageEntity.validateBirthDay());
		
		userMessageEntity.setBirthDay("2018-13-05");
		assertFalse(userMessageEntity.validateBirthDay());
		
		userMessageEntity.setBirthDay("2018-11-2");
		assertFalse(userMessageEntity.validateBirthDay());
		
		userMessageEntity.setBirthDay("2018-11-02");
		assertTrue(userMessageEntity.validateBirthDay());
	}

}
