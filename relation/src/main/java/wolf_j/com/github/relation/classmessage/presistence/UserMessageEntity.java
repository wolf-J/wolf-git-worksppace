/**
 * 
 */
package wolf_j.com.github.relation.classmessage.presistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import wolf_j.com.github.relation.classmessage.service.UserVOFromFrontEnd;

/**
 * @author wolf-J
 *
 */

@Entity
@Table(name = "UsersMessage")
public class UserMessageEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3052332122030341362L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "username")
	private String username;

	@Column(name = "fullName")
	private String fullName;

	@Column(name = "phoneNumber")
	private String phoneNumber;

	@Column(name = "sex")
	private String sex;

	@Column(name = "birthDay")
	private String birthDay;

	public UserMessageEntity() {
	}

	public UserMessageEntity(String username, String fullName, String phoneNumber, String sex, String birthDay,
			String address, String organization, String whatUp) {
		super();
		this.username = username;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.sex = sex;
		this.birthDay = birthDay;
		this.address = address;
		this.organization = organization;
		this.whatUp = whatUp;
	}
	
	public UserMessageEntity(UserVOFromFrontEnd userVOFromFrontEnd) {
		super();
		this.username = userVOFromFrontEnd.getUsername();
		this.fullName = userVOFromFrontEnd.getFullName();
		this.phoneNumber = userVOFromFrontEnd.getPhoneNumber();
		this.sex = userVOFromFrontEnd.getSex();
		this.birthDay = userVOFromFrontEnd.getBirthDay();
		this.address = userVOFromFrontEnd.getAddress();
		this.organization = userVOFromFrontEnd.getOrganization();
		this.whatUp = userVOFromFrontEnd.getWhatUp();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getWhatUp() {
		return whatUp;
	}

	public void setWhatUp(String whatUp) {
		this.whatUp = whatUp;
	}

	@Column(name = "address")
	private String address;

	@Column(name = "organization")
	private String organization;

	@Column(name = "whatUp")
	private String whatUp;

}
