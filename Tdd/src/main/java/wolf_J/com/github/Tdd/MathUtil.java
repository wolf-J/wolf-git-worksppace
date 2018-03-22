/**
 * 
 */
package wolf_J.com.github.Tdd;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wolf-J
 *
 */
public class MathUtil {

	/**
	 * @param scores
	 * @return
	 */
	public static double total(List<Double> values) {
		Double total = new Double(0);
		for (Double value : values) {
			total += value;
		}
		return total;
	}

	/**
	 * @param scores
	 * @return
	 */
	public static double average(List<Double> values) {
		Double total = new Double(0);
		for (Double value : values) {
			total += value;
		}
		return total / values.size();
	}

	/**
	 * @param scores
	 * @return
	 */
	public static double median(List<Double> values) {
		int size = values.size();
		if (!Mod.isMod(2, size))
			return values.get(size / 2);
		return (values.get((size-1)/2)+values.get((size+1)/2))/2;
	}

}
