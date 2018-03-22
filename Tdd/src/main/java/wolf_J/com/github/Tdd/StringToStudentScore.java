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
public class StringToStudentScore {

	/**
	 * @param studentScoreString
	 * @return
	 * @throws Exception
	 */
	public static List<StudentScore> convertStringToStudentScore(String studentScoreString) throws Exception {
		List<StudentScore> studentScores = new ArrayList<>();
		String[] studentScoresString = studentScoreString.trim().split("(\\[\n\\[)|(\\]\n\\])|(\\],\n\\[)", 0);
		for (int i = 1; i < studentScoresString.length; i++) {
			String[] onestudentScoreString = studentScoresString[i].replaceAll("\"", "").split(",");
			if (onestudentScoreString.length != 5) {
				throw new Exception("Input error");
			}
			studentScores.add(new StudentScore(onestudentScoreString[0], Double.parseDouble(onestudentScoreString[1]),
					Double.parseDouble(onestudentScoreString[2]), Double.parseDouble(onestudentScoreString[3]),
					Double.parseDouble(onestudentScoreString[4])));
		}
		return studentScores;
	}

}