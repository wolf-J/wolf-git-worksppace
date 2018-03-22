/**
 * 
 */
package wolf_J.com.github.Tdd;

/**
 * @author wolf-J
 *
 */
public class LeapYear {

	/**
	 * @param i
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		if (Mod.isMod(100, year)) {
			if (Mod.isMod(400, year))
				return true;
			return false;
		}
		return Mod.isMod(4, year);
	}

}
