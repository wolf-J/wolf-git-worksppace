/**
 * 
 */
package wolf_j.com.github.tdd.numberguess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import wolf_j.com.github.tdd.numberguess.NumberGame;

/**
 * @author wolf-J
 *
 */
class TestNumberGame {

	@Test
	void should_0A1B_when_given_1234_and_1567() throws Exception {

		ArrayList<Integer> realNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
		NumberGame numberGame = new NumberGame(realNumbers);

		ArrayList<Integer> guessNumbers = new ArrayList<>(Arrays.asList(1, 5, 6, 7));

		assertThat(numberGame.guess(guessNumbers)).isEqualTo("1A0B");
		assertEquals(5, numberGame.getRemainTries());
		assertEquals(NumberGame.Stage.GAMING, numberGame.getCurrentStage());
	}

	@Test
	void should_illegal_when_given_1234_and_1222() throws Exception {

		ArrayList<Integer> realNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
		NumberGame numberGame = new NumberGame(realNumbers);

		ArrayList<Integer> guessNumbers = new ArrayList<>(Arrays.asList(1, 2, 2, 2));

		assertThat(numberGame.guess(guessNumbers)).isEqualTo(NumberGame.ILLEGAL_INPUT);
		assertEquals(5, numberGame.getRemainTries());
		assertEquals(NumberGame.Stage.GAMING, numberGame.getCurrentStage());
	}

	@Test
	void should_2A0B_when_given_1234_and_1256() throws Exception {

		ArrayList<Integer> realNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
		NumberGame numberGame = new NumberGame(realNumbers);

		ArrayList<Integer> guessNumbers = new ArrayList<>(Arrays.asList(1, 2, 5, 6));

		assertThat(numberGame.guess(guessNumbers)).isEqualTo("2A0B");
		assertEquals(5, numberGame.getRemainTries());
		assertEquals(NumberGame.Stage.GAMING, numberGame.getCurrentStage());
	}

	@Test
	void should_2A2B_when_given_1234_and_1243() throws Exception {

		ArrayList<Integer> realNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
		NumberGame numberGame = new NumberGame(realNumbers);

		ArrayList<Integer> guessNumbers = new ArrayList<>(Arrays.asList(1, 2, 4, 3));

		assertThat(numberGame.guess(guessNumbers)).isEqualTo("2A2B");
		assertEquals(5, numberGame.getRemainTries());
		assertEquals(NumberGame.Stage.GAMING, numberGame.getCurrentStage());
	}

	@Test
	void should_4remainTries_when_given_and_5_remaintries() throws Exception {

		ArrayList<Integer> realNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
		NumberGame numberGame = new NumberGame(realNumbers, 5);

		ArrayList<Integer> guessNumbers = new ArrayList<>(Arrays.asList(1, 2, 4, 3));

		assertThat(numberGame.guess(guessNumbers)).isEqualTo("2A2B");
		assertEquals(4, numberGame.getRemainTries());
		assertEquals(NumberGame.Stage.GAMING, numberGame.getCurrentStage());
	}

	@Test
	void should_4A0B_and_SUCCESS_when_given_5678_and_5678() throws Exception {

		ArrayList<Integer> realNumbers = new ArrayList<>(Arrays.asList(5, 6, 7, 8));
		NumberGame numberGame = new NumberGame(realNumbers, 4);

		ArrayList<Integer> guessNumbers = new ArrayList<>(Arrays.asList(5, 6, 7, 8));

		assertThat(numberGame.guess(guessNumbers)).isEqualTo("4A0B");
		assertEquals(3, numberGame.getRemainTries());
		assertEquals(NumberGame.Stage.SUCESSS, numberGame.getCurrentStage());
	}
	
	@Test
	void should_0A4B_and_fail_when_last_remainTryFail() throws Exception {

		ArrayList<Integer> realNumbers = new ArrayList<>(Arrays.asList(5, 6, 7, 8));
		NumberGame numberGame = new NumberGame(realNumbers, 1);

		ArrayList<Integer> guessNumbers = new ArrayList<>(Arrays.asList(6, 7, 8, 5));

		assertThat(numberGame.guess(guessNumbers)).isEqualTo("0A4B");
		assertEquals(0, numberGame.getRemainTries());
		assertEquals(NumberGame.Stage.FAIL, numberGame.getCurrentStage());
	}
	
	@Test
	void should_GAME_OVER_when_have_not_remainTries() throws Exception {

		ArrayList<Integer> realNumbers = new ArrayList<>(Arrays.asList(5, 6, 7, 8));
		NumberGame numberGame = new NumberGame(realNumbers, 1);

		ArrayList<Integer> guessNumbers = new ArrayList<>(Arrays.asList(6, 7, 8, 5));
		numberGame.guess(guessNumbers);
		assertEquals(0, numberGame.getRemainTries());
		
		assertThat(numberGame.guess(guessNumbers)).isEqualTo(NumberGame.GAME_OVER);

	}
	
	@Test
	void should_Illegal_Init_when_given_0_remainTries() throws Exception {

		ArrayList<Integer> realNumbers = new ArrayList<>(Arrays.asList(5, 6, 7, 8));
		
		assertThat(catchThrowable(() -> new NumberGame(realNumbers, 0))).hasMessage(NumberGame.ILLE_GAL_INIT);
		assertThatThrownBy(() -> new NumberGame(realNumbers, 0)).hasMessage(NumberGame.ILLE_GAL_INIT);
	}

}
