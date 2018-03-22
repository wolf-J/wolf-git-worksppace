/**
 * 
 */
package wolf_J.com.github.common;

/**
 * @author wolf-J
 *
 */
public class ClassLoaderT {
	static {
		System.out.println("class load!");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ClassLoaderT();
		new ClassLoaderT();
		

	}

}
