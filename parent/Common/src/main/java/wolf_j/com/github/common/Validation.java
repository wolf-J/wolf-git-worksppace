/**
 * 
 */
package wolf_j.com.github.common;

/**
 * @author wolf-J
 *
 */
public class Validation {

	private String factoryid;
	private Class<?> prodClass;

	/**
	 * @param factorid
	 * @param prodBeanId
	 */
	public Validation(String factorid, Class<?> prodClass) {
		this.factoryid = factorid;
		this.prodClass = prodClass;
		System.out.println("validation init Success!");
	}

}
