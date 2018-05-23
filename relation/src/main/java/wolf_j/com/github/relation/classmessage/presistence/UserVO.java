/**
 * 
 */
package wolf_j.com.github.relation.classmessage.presistence;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wolf-J
 *
 */
public class UserVO implements Serializable {

	private static final long serialVersionUID = -304027077679282838L;
	private Integer id;
	private String username;
	private String phoneNumber;
	private String password;
	private Date creatTime;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the creatTime
	 */
	public Date getCreatTime() {
		return creatTime;
	}

	/**
	 * @param creatTime
	 *            the creatTime to set
	 */
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
