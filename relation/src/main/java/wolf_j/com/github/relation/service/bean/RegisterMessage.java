/**
 * 
 */
package wolf_j.com.github.relation.service.bean;

/**
 * @author wolf-J
 *
 */
public class RegisterMessage {
	
	boolean  fail = false;
	boolean error = false;
	boolean  success = false;
	
	public boolean isFail() {
		return fail;
	}
	public void setFail(boolean fail) {
		this.fail = fail;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
