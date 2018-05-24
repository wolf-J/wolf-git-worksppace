/**
 * 
 */
package wolf_j.com.github.relation.classmessage.presistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wolf-J
 *
 */

@Repository
public class UserOperation {
	@Autowired
	UserRepository userRepository;

	public UserEntity saveUser(UserEntity user) {
		UserEntity foundUserByUserName = userRepository.findByUserName(user.getUsername());
		if (foundUserByUserName == null)
			return userRepository.save(user);
		return foundUserByUserName;

	}
	
	public UserEntity findByUserName(String username) {
		return userRepository.findByUserName(username);
	}
	
	

}
