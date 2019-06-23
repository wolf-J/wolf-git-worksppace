/**
 * 
 */
package wolf_j.com.github.tdd.trafficlight.nondirectional;

/**
 * @author wolf
 *
 */
public enum NonDirectionalTrafficLight {
	
	REDLIGHT("red"), YELLOWLIGHT("yellow"),GREENLIGHT("green");
	
	private final String color;
	
	private NonDirectionalTrafficLight(String color) {
		this.color = color;
	}

	public String getLightColor() {
		return color;
	}

}
