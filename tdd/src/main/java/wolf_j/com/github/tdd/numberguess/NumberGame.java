/**
 * 
 */
package wolf_j.com.github.tdd.numberguess;

import java.util.HashSet;
import java.util.List;

import org.assertj.core.internal.bytebuddy.implementation.bytecode.Throw;

/**
 * @author wolf-J
 * 
 *
 */
public class NumberGame {

	public static final String ILLE_GAL_INIT = "Illegal Init!";

	public static final String GAME_OVER = "Game Already Over!";

	public static final String ILLEGAL_INPUT = "Illegal Input!";

	private int remainTries;

	private List<Integer> realNumbers;

	private Stage currentStage;

	public NumberGame(List<Integer> realNumbers) throws Exception {
		this(realNumbers, 6);
	}

	public NumberGame(List<Integer> realNumbers, int remainTries) throws Exception {
		if(realNumbers.size() != 4 || remainTries < 1)
			throw new Exception(ILLE_GAL_INIT);
		currentStage = Stage.GAMING;
		this.setRealNumbers(realNumbers);
		this.remainTries = remainTries;
	}

	public String guess(List<Integer> guessNumbers) {
		if (remainTries <= 0)
			return GAME_OVER;

		remainTries -= 1;
		
		if (guessNumbers.size() != 4 || guessNumbers.size() != new HashSet<Integer>(guessNumbers).size()) {
			if (remainTries == 0)
				currentStage = Stage.FAIL;
			return ILLEGAL_INPUT;
		}

		String result = getResult(guessNumbers);
		if (result.equals("4A0B"))
			currentStage = Stage.SUCESSS;
		if (remainTries == 0)
			currentStage = Stage.FAIL;
		return result;
	}

	private String getResult(List<Integer> guessNumbers) {
		String result = "";

		int countOfSameNumberAndPosition = 0;
		for (int i = 0; i < guessNumbers.size(); i++) {
			if (guessNumbers.get(i) == realNumbers.get(i))
				countOfSameNumberAndPosition++;
		}
		result += countOfSameNumberAndPosition + "A";

		int countOfSameNumberAndNotSamePosition = 0;
		for (int i = 0; i < guessNumbers.size(); i++) {
			if (guessNumbers.get(i) != realNumbers.get(i) && realNumbers.contains(guessNumbers.get(i)))
				countOfSameNumberAndNotSamePosition++;
		}
		result += countOfSameNumberAndNotSamePosition + "B";

		return result;
	}

	public int getRemainTries() {
		return remainTries;
	}

	public Stage getCurrentStage() {
		return currentStage;
	};

	public List<Integer> getRealNumbers() {
		return realNumbers;
	}

	public void setRealNumbers(List<Integer> realNumbers) {
		this.realNumbers = realNumbers;
	}

	public enum Stage {
		GAMING("gaming"), SUCESSS("success"), FAIL("fail");
		private String name;

		private Stage(String name) {
			this.setName(name);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

}
