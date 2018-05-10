/**
 * 
 */
package wolf_j.com.github.tdd;

import java.util.List;

/**
 * @author wolf-J
 *
 */
/**
 * @author wolf-J
 *
 */
public class MathUtil {
	
	/**
	 * 
	 */
	private MathUtil() {
	}
	/**
	 * @param values
	 * @return
	 */

	public static double total(List<Double> values) {
		Double total = Double.valueOf(0);
		for (Double value : values) {
			total += value;
		}
		return total;
	}

	/**
	 * @param values
	 * @return
	 */
	public static double average(List<Double> values) {
		Double total = Double.valueOf(0);
		for (Double value : values) {
			total += value;
		}
		return total / values.size();
	}

	/**
	 * @param values
	 * @return
	 */
	public static double median(List<Double> values) {
		int size = values.size();
		if (!MathUtil.isMod(2, size))
			return values.get(size / 2);
		return (values.get((size - 1) / 2) + values.get((size + 1) / 2)) / 2;
	}

	/**
	 * @param denominator
	 * @param divisor
	 * @return
	 */
	public static boolean isMod(int denominator, int divisor) {
		return divisor % denominator == 0;
	}

}
