/**
 * 
 */
package wolf_j.com.github.tdd;

/**
 * @author wolf-J
 *
 */
public class LeapYear {
	
	/**
	 * 
	 */
	private LeapYear() {
	}

	/**
	 * @param year
	 * @return
	 */
	/**
	 * 
	 */
	public static boolean isLeapYear(int year) {
		if (MathUtil.isMod(100, year)) {
				return MathUtil.isMod(400, year);
		}
		return MathUtil.isMod(4, year);
	}

}
