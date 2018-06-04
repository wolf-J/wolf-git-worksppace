/**
 * 
 */
package wolf_j.com.github.relation.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import wolf_j.com.github.relation.presistence.UserEntity;
import wolf_j.com.github.relation.presistence.UserMessageEntity;
import wolf_j.com.github.relation.presistence.UserMessageRepository;
import wolf_j.com.github.relation.presistence.UserRepository;
import wolf_j.com.github.relation.service.RegisterService;
import wolf_j.com.github.relation.service.bean.RegisterMessage;
import wolf_j.com.github.relation.service.bean.UserFromFrontEnd;

/**
 * @author wolf-J
 *
 */

@Service("registerOrdinaryUserServiceImpl")
public class RegisterOrdinaryUserServiceImpl implements RegisterService {

	private static final int MAX_USERNAME_LENGTH = 20;
	private static final int MIN_USERNAME_LENGTH = 6;
	private static final int MAX_PASSWORD_LENGTH = 20;
	private static final int MIN_PASSWORD_LENGTH = 6;
	private static final String ROLE = "ROLE_user";
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserMessageRepository userMessageRepository;
	
	@Override
	@Transactional
	public RegisterMessage signUpUser(UserFromFrontEnd userFromFrontEnd) {
		RegisterMessage registerMessage = new RegisterMessage();
		if (!validateUserMessage(userFromFrontEnd)) {
			registerMessage.setError(true);
			return registerMessage;
		}
		String password = new BCryptPasswordEncoder().encode(userFromFrontEnd.getPassword());
		UserEntity user = new UserEntity(userFromFrontEnd.getUsername(), password, ROLE);
		UserMessageEntity userMessage = userFromFrontEnd.toUserMessageEntity();
		if (isExiestsUser(user))
			registerMessage.setFail(true);
		else {
			saveUserAndInfo(user, userMessage);
			registerMessage.setSuccess(true);
		}
		return registerMessage;
	}

	private boolean validateUserMessage(UserFromFrontEnd userFromFrontEnd) {
		return userFromFrontEnd.getUsername().length() >= MIN_USERNAME_LENGTH
				&& userFromFrontEnd.getUsername().length() <= MAX_USERNAME_LENGTH
				&& userFromFrontEnd.getPassword().length() >= MIN_PASSWORD_LENGTH
				&& userFromFrontEnd.getPassword().length() <= MAX_PASSWORD_LENGTH;
	}
	private boolean isExiestsUser(UserEntity user) {
		return userRepository.findByUserName(user.getUsername()) == null ? false : true;
	}

	private void saveUserAndInfo(UserEntity user, UserMessageEntity userMessage) {
		userRepository.save(user);
		userMessageRepository.save(userMessage);
	}


}
