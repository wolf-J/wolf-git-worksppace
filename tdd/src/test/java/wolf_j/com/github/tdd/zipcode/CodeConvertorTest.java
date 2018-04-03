/**
 * 
 */
package wolf_j.com.github.tdd.zipcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author wolf-J
 *
 */

class CodeConvertorTest {

	public static Stream<String[]> generateData() {
		return Stream.of(new String[][] { 
				{ "3215", CodeConvertor.INPUT_EXCEPTTION_MESSAGE },
				{ "|:|:|::|:|:||:::||:::::||:|:::||", "55003" }, 
				{ "55003", "|:|:|::|:|:||:::||:::::||:|:::||" },
				{ ":::|||", CodeConvertor.INPUT_EXCEPTTION_MESSAGE },
				// 550035500 3
				{ "|:|:|::|:|:||:::||:::::||::|:|::|:|:||:::||:::::||:|", CodeConvertor.INPUT_EXCEPTTION_MESSAGE },
				// 550035500 7
				{ "|:|:|::|:|:||:::||:::::||::|:|::|:|:||:::||:::|:::||", "55003-5500" },
				{ "55003-5500", "|:|:|::|:|:||:::||:::::||::|:|::|:|:||:::||:::|:::||" },
				{ "550035500", "|:|:|::|:|:||:::||:::::||::|:|::|:|:||:::||:::|:::||" } });
	}

	@ParameterizedTest
	@MethodSource("generateData")
	@DisplayName("Test CodeConvertor.showCodeConvertResult by Parameterized")
	void testShowCodeConvertResult_given_parameters(String input, String output) {
		assertEquals(output, CodeConvertor.showCodeConvertResult(input));
	}
}
