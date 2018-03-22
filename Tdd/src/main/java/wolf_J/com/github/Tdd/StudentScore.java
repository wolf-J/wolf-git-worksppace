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
/**
 * @author wolf-J
 *
 */
public class StudentScore {

	private String name;
	private double chineseScore;
	private double englishScore;
	private double mathScore;
	private double programingScore;

	/**
	 * @param name
	 * @param chineseScore
	 * @param englishScore
	 * @param mathScore
	 * @param programingScore
	 */
	public StudentScore(String name, double chineseScore, double englishScore, double mathScore,
			double programingScore) {
		this.name = name;
		this.chineseScore = chineseScore;
		this.englishScore = englishScore;
		this.mathScore = mathScore;
		this.programingScore = programingScore;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the chineseScore
	 */
	public double getChineseScore() {
		return chineseScore;
	}

	/**
	 * @param chineseScore
	 *            the chineseScore to set
	 */
	public void setChineseScore(double chineseScore) {
		this.chineseScore = chineseScore;
	}

	/**
	 * @return the englishScore
	 */
	public double getEnglishScore() {
		return englishScore;
	}

	/**
	 * @param englishScore
	 *            the englishScore to set
	 */
	public void setEnglishScore(double englishScore) {
		this.englishScore = englishScore;
	}

	/**
	 * @return the mathScore
	 */
	public double getMathScore() {
		return mathScore;
	}

	/**
	 * @param mathScore
	 *            the mathScore to set
	 */
	public void setMathScore(double mathScore) {
		this.mathScore = mathScore;
	}

	/**
	 * @return the programingScore
	 */
	public double getProgramingScore() {
		return programingScore;
	}

	/**
	 * @param programingScore
	 *            the programingScore to set
	 */
	public void setProgramingScore(double programingScore) {
		this.programingScore = programingScore;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(chineseScore);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(englishScore);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(mathScore);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		temp = Double.doubleToLongBits(programingScore);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentScore other = (StudentScore) obj;
		if (Double.doubleToLongBits(chineseScore) != Double.doubleToLongBits(other.chineseScore))
			return false;
		if (Double.doubleToLongBits(englishScore) != Double.doubleToLongBits(other.englishScore))
			return false;
		if (Double.doubleToLongBits(mathScore) != Double.doubleToLongBits(other.mathScore))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(programingScore) != Double.doubleToLongBits(other.programingScore))
			return false;
		return true;
	}

	/**
	 * @return
	 */
	public double getAverage() {
		return getTotal() / 4;
	}

	/**
	 * @return
	 */
	public double getTotal() {
		List<Double> Scores = new ArrayList<Double>();
		Scores.add(this.getChineseScore() + this.getEnglishScore() + this.getProgramingScore() + this.getMathScore());
		return MathUtil.total(Scores);
	}

}
