/**
 * 
 */
package wolf_j.com.github.tdd.trafficlight.nondirectional;

/**
 * @author wolf
 *
 */
public enum NoDeTrafficLight {
	
	REDLIGHT("red"), YELLOWLIGHT("yellow"),GREENLIGHT("green");
	
	private final String color;
	
	private NoDeTrafficLight(String color) {
		this.color = color;
	}

	public String getLightColor() {
		return color;
	}

}
