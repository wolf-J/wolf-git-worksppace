/**
 * 
 */
package wolf_j.com.github.tdd.trafficlight;

/**
 * @author wolf
 *
 */
public class CarStatus {

	private Deriction deriction;
	private boolean crossedYellowLine;

	public CarStatus(Deriction deriction, boolean crossedYellowLine) {
		this.deriction = deriction;
		this.crossedYellowLine = crossedYellowLine;
	}

	public Deriction getDeriction() {
		return deriction;
	}

	public boolean isCrossedYellowLine() {
		return crossedYellowLine;
	}

}
