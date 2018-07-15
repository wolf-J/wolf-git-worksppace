/**
 * 
 */
package wolf_j.com.github.common;

/**
 * @author wolf-J
 *
 */
public class TestThread implements Runnable {

	private String value;

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
		this.setValue(value);
		int a= 0;
		a++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(getValue() + i);
		}
	}

}
