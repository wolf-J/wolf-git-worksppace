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
	 * @param year
	 * @return
	 */
	/**
	 * 
	 */
	private LeapYear() {
	}
	public static boolean isLeapYear(int year) {
		if (MathUtil.isMod(100, year)) {
			if (MathUtil.isMod(400, year))
				return true;
			return false;
		}
		return MathUtil.isMod(4, year);
	}

}
