/**
 * 
 */
package wolf_j.com.github.relation.service.bean;

import java.io.Serializable;

import wolf_j.com.github.relation.presistence.UserMessageEntity;

/**
 * @author wolf-J
 *
 */

public class UserFromFrontEnd implements Serializable {

	private static final long serialVersionUID = -304027077679282838L;
	private String username;
	private String password;
	private String fullName;
	private String phoneNumber;
	private String sex;
	private String birthDay;
	private String address;
	private String organization;
	private String whatUp;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWhatUp() {
		return whatUp;
	}

	public void setWhatUp(String whatUp) {
		this.whatUp = whatUp;
	}

	public UserMessageEntity toUserMessageEntity() {
		UserMessageEntity userMessageEntity = new UserMessageEntity();
		userMessageEntity.setUsername(this.username);
		userMessageEntity.setFullName(this.fullName);
		userMessageEntity.setPhoneNumber(this.phoneNumber);
		userMessageEntity.setSex(this.getSex());
		userMessageEntity.setBirthDay(this.getBirthDay());
		userMessageEntity.setAddress(this.getAddress());
		userMessageEntity.setOrganization(this.getOrganization());
		userMessageEntity.setWhatUp(this.getWhatUp());
		return userMessageEntity;
	}

}
