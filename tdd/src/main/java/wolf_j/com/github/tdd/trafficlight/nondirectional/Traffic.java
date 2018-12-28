/**
 * 
 */
package wolf_j.com.github.tdd.trafficlight.nondirectional;

import wolf_j.com.github.tdd.trafficlight.directional.DeTrafficLight;

/**
 * @author wolf
 *
 */
public class Traffic {

	public static Permission getPermission(NoDeTrafficLight noDeTrafficLight, CarStatus carStatus) {

		if (noDeTrafficLight.compareTo(NoDeTrafficLight.REDLIGHT) == 0)
			return new Permission(false, true, true);
		if (noDeTrafficLight.compareTo(NoDeTrafficLight.YELLOWLIGHT) == 0) {
			if (carStatus.isCrossedYellowLine())
				return new Permission(true, true, false);
			return new Permission(false, true, false);
		}
		if (noDeTrafficLight.compareTo(NoDeTrafficLight.GREENLIGHT) == 0)
			return new Permission(true, true, false);
		return null;
	}

	public static Permission getPermission(DeTrafficLight deTrafficLight, CarStatus carStatus) {

		Permission permission = new Permission();
		permission.setStraight(isAccessOfDeTrafficLight(carStatus, deTrafficLight.getStraightLight()));
		permission.setTurnLeft(isAccessOfDeTrafficLight(carStatus, deTrafficLight.getLeftLight()));
		permission.setTurnRight(isAccessOfDeTrafficLight(carStatus, deTrafficLight.getRightLight()));

		return permission;
	}

	private static Boolean isAccessOfDeTrafficLight(CarStatus carStatus, NoDeTrafficLight straightLight) {
		if (straightLight.compareTo(NoDeTrafficLight.GREENLIGHT) == 0)
			return true;
		if (straightLight.compareTo(NoDeTrafficLight.YELLOWLIGHT) == 0)
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
