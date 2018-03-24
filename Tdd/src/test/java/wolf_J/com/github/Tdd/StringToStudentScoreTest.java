/**
 * 
 */
package wolf_j.com.github.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * @author wolf-J
 *
 */
public class StringToStudentScoreTest {

	@Test
	public void should_be_convert_true_studengtScoreList() throws Exception {
		String studentScoreString = "[\n[\"zhangsan\",\"95\",\"80\",\"75\",\"80\"],\n"
				+ "[\"lisi\",\"80\",\"70\",\"85\",\"90\"]\n]";
		List<StudentScore> studentScores = StringToStudentScore.convertStringToStudentScore(studentScoreString);
		assertEquals(new StudentScore("zhangsan", 95d, 80d, 75d, 80d), studentScores.get(0));
		assertEquals(new StudentScore("lisi", 80d, 70d, 85d, 90d), studentScores.get(1));
	}

}
