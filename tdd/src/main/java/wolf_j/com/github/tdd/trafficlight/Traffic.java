/**
 * 
 */
package wolf_j.com.github.tdd.trafficlight;

import wolf_j.com.github.tdd.trafficlight.directional.DirectionalTrafficLight;
import wolf_j.com.github.tdd.trafficlight.nondirectional.NonDirectionalTrafficLight;

/**
 * @author wolf
 *
 */
public class Traffic {

	public static Permission getPermission(NonDirectionalTrafficLight nonDirectionalTrafficLight, CarStatus carStatus) {

		if (nonDirectionalTrafficLight.compareTo(NonDirectionalTrafficLight.REDLIGHT) == 0)
			return new Permission(false, true, true);
		if (nonDirectionalTrafficLight.compareTo(NonDirectionalTrafficLight.YELLOWLIGHT) == 0) {
			if (carStatus.isCrossedYellowLine())
				return new Permission(true, true, false);
			return new Permission(false, true, false);
		}
		if (nonDirectionalTrafficLight.compareTo(NonDirectionalTrafficLight.GREENLIGHT) == 0)
			return new Permission(true, true, false);
		return null;
	}

	public static Permission getPermission(DirectionalTrafficLight directionalTrafficLight, CarStatus carStatus) {

		Permission permission = new Permission();
		permission.setStraight(isAccessOfDeTrafficLight(carStatus, directionalTrafficLight.getStraightLight()));
		permission.setTurnLeft(isAccessOfDeTrafficLight(carStatus, directionalTrafficLight.getLeftLight()));
		permission.setTurnRight(isAccessOfDeTrafficLight(carStatus, directionalTrafficLight.getRightLight()));

		return permission;
	}

	private static Boolean isAccessOfDeTrafficLight(CarStatus carStatus, NonDirectionalTrafficLight straightLight) {
		if (straightLight.compareTo(NonDirectionalTrafficLight.GREENLIGHT) == 0)
			return true;
		if (straightLight.compareTo(NonDirectionalTrafficLight.YELLOWLIGHT) == 0)
			if (carStatus.isCrossedYellowLine())
				return true;
		return false;

	}

	public static Boolean isAccess(Permission permission, CarStatus carStatus) {

		Deriction deriction = carStatus.getDeriction();
		if (deriction.compareTo(Deriction.STRAIGHT) == 0)
			return true && permission.isStraight();
		if (deriction.compareTo(Deriction.LEFT) == 0)
			return true && permission.isTurnLeft();
		return true && permission.isTurnRight();

	}

}
