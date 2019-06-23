/**
 * 
 */
package wolf_j.com.github.common;

import org.springframework.stereotype.Component;

import wolf_j.com.github.common.annotation.ValidationBean;

/**
 * @author wolf-J
 *
 */
@ValidationBean
@Component
public class ValidationCase2 extends Validation {

	private static final String FACTORYID = "validationCaseFacotory";
	
	private String value = "validationCase222222222222";
	
	static String prodBeanId = "wolf_j.com.github.common.Product";

	/**
	 * @throws ClassNotFoundException
	 * 
	 */
	public ValidationCase2() throws ClassNotFoundException {
		super(FACTORYID, Class.forName(prodBeanId));
		System.out.println("validationCase2 Create Success!");
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
