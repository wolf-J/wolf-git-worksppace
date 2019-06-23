/**
 * 
 */
package wolf_j.com.github.relation.service;

import wolf_j.com.github.relation.service.bean.RegisterMessage;
import wolf_j.com.github.relation.service.bean.UserFromFrontEnd;

/**
 * @author wolf-J
 *
 */

public interface RegisterService {

	RegisterMessage signUpUser(UserFromFrontEnd userVOFromFrontEnd);

}
