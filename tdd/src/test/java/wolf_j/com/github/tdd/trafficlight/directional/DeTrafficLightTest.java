/**
 * 
 */
package wolf_j.com.github.tdd.trafficlight.directional;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import wolf_j.com.github.tdd.trafficlight.nondirectional.CarStatus;
import wolf_j.com.github.tdd.trafficlight.nondirectional.Deriction;
import wolf_j.com.github.tdd.trafficlight.nondirectional.NoDeTrafficLight;
import wolf_j.com.github.tdd.trafficlight.nondirectional.Permission;
import wolf_j.com.github.tdd.trafficlight.nondirectional.Traffic;

/**
 * @author wolf
 *
 */
class DeTrafficLightTest {

	@Test
	void testGetPermissionsWhenGiveRedlightsAndStraightCrossedLineCar() {

		DeTrafficLight deTrafficLight = new DeTrafficLight(NoDeTrafficLight.REDLIGHT, NoDeTrafficLight.REDLIGHT, NoDeTrafficLight.REDLIGHT);

		CarStatus carStatus = new CarStatus(Deriction.STRAIGHT, true);

		Permission permission = Traffic.getPermission(deTrafficLight, carStatus);

		assertEquals(false, permission.isStraight());
		assertEquals(false, permission.isTurnLeft());
		assertEquals(false, permission.isTurnRight());
		assertEquals(false, Traffic.isAccess(permission, carStatus));
	}
	
	@Test
	void testGetPermissionsWhenGiveLeftYellowLightAndLeftCrossedLineCar() {

		DeTrafficLight deTrafficLight = new DeTrafficLight(NoDeTrafficLight.REDLIGHT, NoDeTrafficLight.YELLOWLIGHT, NoDeTrafficLight.REDLIGHT);

		CarStatus carStatus = new CarStatus(Deriction.LEFT, true);

		Permission permission = Traffic.getPermission(deTrafficLight, carStatus);

		assertEquals(false, permission.isStraight());
		assertEquals(true, permission.isTurnLeft());
		assertEquals(false, permission.isTurnRight());
		assertEquals(true, Traffic.isAccess(permission, carStatus));
	}
	
	@Test
	void testGetPermissionsWhenGiveLeftYellowLightAndLeftNotCrossedLineCar() {

		DeTrafficLight deTrafficLight = new DeTrafficLight(NoDeTrafficLight.REDLIGHT, NoDeTrafficLight.YELLOWLIGHT, NoDeTrafficLight.REDLIGHT);

		CarStatus carStatus = new CarStatus(Deriction.LEFT, false);

		Permission permission = Traffic.getPermission(deTrafficLight, carStatus);

		assertEquals(false, permission.isStraight());
		assertEquals(false, permission.isTurnLeft());
		assertEquals(false, permission.isTurnRight());
		assertEquals(false, Traffic.isAccess(permission, carStatus));
	}
	
	
	@Test
	void testGetPermissionsWhenGiveLeftGreenLightAndRightNotCrossedLineCar() {

		DeTrafficLight deTrafficLight = new DeTrafficLight(NoDeTrafficLight.REDLIGHT, NoDeTrafficLight.YELLOWLIGHT, NoDeTrafficLight.GREENLIGHT);

		CarStatus carStatus = new CarStatus(Deriction.RIGHT, false);

		Permission permission = Traffic.getPermission(deTrafficLight, carStatus);

		assertEquals(false, permission.isStraight());
		assertEquals(false, permission.isTurnLeft());
		assertEquals(true, permission.isTurnRight());
		assertEquals(true, Traffic.isAccess(permission, carStatus));
	}

}
