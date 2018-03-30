/**
 * 
 */
package wolf_j.com.github.tdd;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wolf-J
 *
 */
public class ScoreTable {

	private List<StudentScore> studentScores;

	/**
	 * @param studentScores
	 */
	public ScoreTable(List<StudentScore> studentScores) {
		this.studentScores = studentScores;

	}

	/**
	 * @return
	 */
	public double getTotalAverage() {
		Double totalAverage = Double.valueOf(0);
		for (StudentScore studentScore : studentScores) {
			totalAverage += studentScore.getTotal();
		}
		return totalAverage / studentScores.size();
	}

	public double getMedian() {
		List<Double> scores = new ArrayList<>();
		for (StudentScore studentScore : studentScores) {
			scores.add(studentScore.getTotal());
		}
		return MathUtil.median(scores);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder stringBuffer = new StringBuilder();
		stringBuffer.append("Scoretable:\n" + "Name|Math|Chinese|English|Programing|Average|Total\n"
				+ "======================================\n");
		for (StudentScore studentScore : studentScores) {
			stringBuffer.append(
					studentScore.getName() + "|" + studentScore.getMathScore() + "|" + studentScore.getChineseScore()
							+ "|" + studentScore.getEnglishScore() + "|" + studentScore.getProgramingScore() + "|");
			stringBuffer.append(studentScore.getAverage() + "|" + studentScore.getTotal() + "\n");
		}
		stringBuffer.append("======================================\n");
		stringBuffer.append("AllTotalAverage: " + this.getTotalAverage() + "\n");
		stringBuffer.append("AllMedian: " + this.getMedian());
		return stringBuffer.toString();

	}
}
