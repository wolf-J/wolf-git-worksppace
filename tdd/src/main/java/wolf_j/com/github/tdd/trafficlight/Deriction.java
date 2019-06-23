/**
 * 
 */
package wolf_j.com.github.tdd.trafficlight;

/**
 * @author wolf
 *
 */
public enum Deriction {
	STRAIGHT("s"), LEFT("l"), RIGHT("r");

	final String name;

	private Deriction(String name) {
		this.name = name;
	}

}
