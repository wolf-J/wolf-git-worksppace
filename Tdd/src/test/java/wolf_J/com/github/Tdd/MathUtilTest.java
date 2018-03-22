/**
 * 
 */
package wolf_J.com.github.Tdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author wolf-J
 *
 */
public class MathUtilTest {
	/**
	 * 
	 */
	private static final double _0_01D = 0.01d;

	@Test
	public void total_should_be_330() {
		List<Double> scores = new ArrayList<>();
		scores.add(95d);
		scores.add(80d);
		scores.add(75d);
		scores.add(80d);
		assertEquals(330d, MathUtil.total(scores), _0_01D);
	}

	@Test
	public void total_should_be_325() {
		List<Double> scores = new ArrayList<>();
		scores.add(85d);
		scores.add(80d);
		scores.add(70d);
		scores.add(90d);
		assertEquals(325d, MathUtil.total(scores), _0_01D);
	}

	@Test
	public void average_should_be_82point5() {
		List<Double> scores = new ArrayList<>();
		scores.add(95d);
		scores.add(80d);
		scores.add(75d);
		scores.add(80d);
		assertEquals(82.5d, MathUtil.average(scores), _0_01D);
	}

	@Test
	public void median_should_be_4() {
		List<Double> scores = new ArrayList<>();
		scores.add(2d);
		scores.add(3d);
		scores.add(4d);
		scores.add(5d);
		scores.add(6d);
		assertEquals(4d, MathUtil.median(scores), _0_01D);
	}

	public void median_should_be_2() {
		List<Double> scores = new ArrayList<>();
		scores.add(1d);
		scores.add(2d);
		scores.add(4d);
		assertEquals(2d, MathUtil.median(scores), _0_01D);
	}

	@Test
	public void should_be_true_when_given_4_and_2000() {
		assertTrue(MathUtil.isMod(4, 2000));
	}

	@Test
	public void should_be_false_when_given_3_and_1999() {
		assertFalse(MathUtil.isMod(3, 1999));
	}
}
