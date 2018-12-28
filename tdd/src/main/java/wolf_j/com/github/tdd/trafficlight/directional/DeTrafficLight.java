/**
 * 
 */
package wolf_j.com.github.tdd.trafficlight.directional;

import wolf_j.com.github.tdd.trafficlight.nondirectional.NoDeTrafficLight;

/**
 * @author wolf
 *
 */
public class DeTrafficLight {

	NoDeTrafficLight straightLight;
	NoDeTrafficLight leftLight;
	NoDeTrafficLight rightLight;

	public DeTrafficLight(NoDeTrafficLight straightLight, NoDeTrafficLight leftLight, NoDeTrafficLight rightLight) {
		this.straightLight = straightLight;
		this.leftLight = leftLight;
		this.rightLight = rightLight;
	}

	public NoDeTrafficLight getStraightLight() {
		return straightLight;
	}

	public NoDeTrafficLight getLeftLight() {
		return leftLight;
	}

	public NoDeTrafficLight getRightLight() {
		return rightLight;
	}
}
