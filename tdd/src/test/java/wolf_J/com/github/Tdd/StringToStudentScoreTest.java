/**
 * 
 */
package wolf_j.com.github.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author wolf-J
 *
 */
public class StringToStudentScoreTest {
	
	@Test
	@DisplayName("convertStringToStudentScore should be throws Exception when given a uncomplete Student Score List")
	public void should_be_throws_Exception_when_given_uncomplete_StudentScoreList() throws Exception {
		String studentScoreString = "[\n[\"zhangsan\",\"95\",\"80\",\"75\",\"80\"],\n"
				+ "[\"lisi\",\"80\",\"70\",\"85\"]\n]";
		assertThrows(Exception.class,  () -> {StringToStudentScore.convertStringToStudentScore(studentScoreString);});
	}


	@Test
	@DisplayName("convertStringToStudentScore should be convert true studengtScoreList")
	public void should_be_convert_true_studengtScoreList() throws Exception {
		String studentScoreString = "[\n[\"zhangsan\",\"95\",\"80\",\"75\",\"80\"],\n"
				+ "[\"lisi\",\"80\",\"70\",\"85\",\"90\"]\n]";
		List<StudentScore> studentScores = StringToStudentScore.convertStringToStudentScore(studentScoreString);
		assertEquals(new StudentScore("zhangsan", 95d, 80d, 75d, 80d), studentScores.get(0));
		assertEquals(new StudentScore("lisi", 80d, 70d, 85d, 90d), studentScores.get(1));
	}
	
	@Test
	public void should_be_throws_Exception_when_given_error_StudentScoreList( ) throws Exception {
		String studentScoreString = "";
		assertThrows(Exception.class, () -> {StringToStudentScore.convertStringToStudentScore(studentScoreString).get(0);});
	}
}
