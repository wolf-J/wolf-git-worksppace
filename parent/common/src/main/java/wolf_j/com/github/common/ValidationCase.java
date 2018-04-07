/**
 * 
 */
package wolf_j.com.github.common;

import wolf_j.com.github.common.annotation.ValidationBean;

/**
 * @author wolf-J
 *
 */
//@ValidationBean
public class ValidationCase extends Validation {

	private static final String FACTORYID = "validationCaseFacotory";
	
	private String value;

	/**
	 * @throws ClassNotFoundException
	 * 
	 */
	public ValidationCase(String prodBeanId) throws ClassNotFoundException {
		super(FACTORYID, Class.forName(prodBeanId));
		System.out.println("validationCase Create Success!");
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}


}
