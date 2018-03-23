/**
 * 
 */
package wolf_J.com.github.Tdd;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * @author wolf-J
 *
 */
public class ScoreTableTest {

	/**
	 * 
	 */

	@Test
	public void total_average_should_be_327point5() {
		List<StudentScore> studentScores = new ArrayList<StudentScore>();
		studentScores.add(new StudentScore("zhangsan", 95d, 80d, 75d, 80d));
		studentScores.add(new StudentScore("lisi", 80d, 70d, 85d, 90d));
		ScoreTable scoreTable = new ScoreTable(studentScores);
		assertEquals(327.5d, scoreTable.getTotalAverage(), 0.01);
	}

	@Test
	public void print_ScoreTable() {
		List<StudentScore> studentScores = new ArrayList<StudentScore>();
		studentScores.add(new StudentScore("zhangsan", 95d, 80d, 75d, 80d));
		studentScores.add(new StudentScore("lisi", 80d, 70d, 85d, 90d));
		ScoreTable scoreTable = new ScoreTable(studentScores);
		assertEquals(0,
				scoreTable.toString().compareTo("Scoretable:\n" + "Name|Math|Chinese|English|Programing|Average|Total\n"
						+ "======================================\n" + "zhangsan|75.0|95.0|80.0|80.0|82.5|330.0\n"
						+ "lisi|85.0|80.0|70.0|90.0|81.25|325.0\n" + "======================================\n"
						+ "AllTotalAverage: 327.5\n" + "AllMedian: 327.5"));
	}

}
