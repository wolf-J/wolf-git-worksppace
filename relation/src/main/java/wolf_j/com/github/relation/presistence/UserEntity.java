/**
 * 
 */
package wolf_j.com.github.relation.presistence;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author wolf-J
 *
 */

@Entity
@Table(name = "Users")
public class UserEntity implements Serializable, UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5113359539035265304L;
	@Id
	@GeneratedValue(strategy =  GenerationType.TABLE, generator = "User_sequence")
	@TableGenerator(name = "User_sequence", allocationSize = 1,table = "SeqTable")
	@Column(unique = true, name = "id")
	private long id;

	@Column(name = "username", nullable = false, unique = true, length = 20)
	private String username;

	@Column(name = "password", nullable = false, length = 100)
	private String password;

	@Column(name = "role")
	private String role;

	public UserEntity() {
	}

	public UserEntity(String username, String password, String role) {

		this.setUsername(username);
		this.setPassword(password);
		this.setRole(role);

	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(getRole()));
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isSameUserProperties(UserEntity user) {
		if (user.id == this.id && user.username.equals(this.username) && user.password.equals(this.password)
				&& user.role.equals(this.role))
			return true;
		return false;
	}
}
