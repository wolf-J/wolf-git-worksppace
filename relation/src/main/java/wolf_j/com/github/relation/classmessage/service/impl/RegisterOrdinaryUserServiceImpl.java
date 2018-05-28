/**
 * 
 */
package wolf_j.com.github.relation.classmessage.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import wolf_j.com.github.relation.classmessage.domain.UserFromFrontEnd;
import wolf_j.com.github.relation.classmessage.presistence.UserEntity;
import wolf_j.com.github.relation.classmessage.presistence.UserMessageEntity;
import wolf_j.com.github.relation.classmessage.presistence.UserMessageRepository;
import wolf_j.com.github.relation.classmessage.presistence.UserOperation;
import wolf_j.com.github.relation.classmessage.presistence.UserRepository;
import wolf_j.com.github.relation.classmessage.service.RegisterService;

/**
 * @author wolf-J
 *
 */

@Service("registerOrdinaryUserServiceImpl")
public class RegisterOrdinaryUserServiceImpl implements RegisterService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserMessageRepository userMessageRepository;

	@Override
	@Transactional
	public boolean signUpUser(UserFromFrontEnd userFromFrontEnd) {
		String password = new BCryptPasswordEncoder().encode(userFromFrontEnd.getPassword());
		UserEntity user = new UserEntity(userFromFrontEnd.getUsername(), password, "ROLE_user");
		UserMessageEntity userMessage = userFromFrontEnd.toUserMessageEntity();
		if (userRepository.findByUserName(user.getUsername()) != null)
			return false;
		userRepository.save(user);
		userMessageRepository.save(userMessage);
		return true;
	}

}
