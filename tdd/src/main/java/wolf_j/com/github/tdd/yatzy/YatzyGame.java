package wolf_j.com.github.tdd.yatzy;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class YatzyGame {

	private List<Integer> yatzyNumbers;

	public YatzyGame(List<Integer> yatzyNumbers) {
		this.setYatzyNumbers(yatzyNumbers);
	}

	public int calculate() {
		Map<Integer, List<Integer>> group = yatzyNumbers.stream().collect(Collectors.groupingBy(Function.identity()));	
		return group.entrySet().stream()
				.map(Entry::getValue)
				.filter(l -> l.size() > 1)
				.map(l -> l.size() * l.get(0))
				.max(Integer::compare ).orElse(0);

	}

	public List<Integer> getYatzyNumbers() {
		return yatzyNumbers;
	}

	public void setYatzyNumbers(List<Integer> yatzyNumbers) {
		this.yatzyNumbers = yatzyNumbers;
	}

}
