/**
 * 
 */
package wolf_j.com.github.relation.presistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

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
	@GeneratedValue(strategy =  GenerationType.TABLE, generator = "UsersMessage_sequence")
	@TableGenerator(name = "UsersMessage_sequence", allocationSize = 1,table = "SeqTable")
	@Column(unique = true, name = "id")
	private long id;

	@Column(name = "username", nullable = false, unique = true, length = 20)
	private String username;

	@Column(name = "fullName", length = 20)
	private String fullName;

	@Column(name = "phoneNumber", length = 20)
	private String phoneNumber;

	@Column(name = "sex", length = 5)
	private String sex;

	@Column(name = "birthDay", length = 20)
	private String birthDay;

	@Column(name = "address", length = 50)
	private String address;

	@Column(name = "organization", length = 50)
	private String organization;

	@Column(name = "whatUp", length = 50)
	private String whatUp;

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


}
