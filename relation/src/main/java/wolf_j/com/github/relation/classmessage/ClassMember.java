/**
 * 
 */
package wolf_j.com.github.relation.classmessage;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wolf-J
 *
 */
public class ClassMember implements Serializable {

	private static final long serialVersionUID = -304027077679282838L;
	private Integer id;
	private String name;
	private String phoneNumber;
	private String password;
	private Date creatTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

}
