/**
 * 
 */
package wolf_j.com.github.tdd.trafficlight.directional;

import wolf_j.com.github.tdd.trafficlight.nondirectional.NonDirectionalTrafficLight;

/**
 * @author wolf
 *
 */
public class DirectionalTrafficLight {

	NonDirectionalTrafficLight straightLight;
	NonDirectionalTrafficLight leftLight;
	NonDirectionalTrafficLight rightLight;

	public DirectionalTrafficLight(NonDirectionalTrafficLight straightLight, NonDirectionalTrafficLight leftLight, NonDirectionalTrafficLight rightLight) {
		this.straightLight = straightLight;
		this.leftLight = leftLight;
		this.rightLight = rightLight;
	}

	public NonDirectionalTrafficLight getStraightLight() {
		return straightLight;
	}

	public NonDirectionalTrafficLight getLeftLight() {
		return leftLight;
	}

	public NonDirectionalTrafficLight getRightLight() {
		return rightLight;
	}
}
