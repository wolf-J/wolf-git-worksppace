/**
 * 
 */
package wolf_J.com.github.common;

/**
 * @author wolf-J
 *
 */
public class TestThread implements Runnable {

	String value;

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

	/**
	 * 
	 */
	public TestThread(String value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			System.out.println(value + i);
		}
	}

}
