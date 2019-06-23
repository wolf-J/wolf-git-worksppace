/**
 * 
 */
package wolf_j.com.github.tdd.trafficlight.directional;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import wolf_j.com.github.tdd.trafficlight.CarStatus;
import wolf_j.com.github.tdd.trafficlight.Deriction;
import wolf_j.com.github.tdd.trafficlight.Permission;
import wolf_j.com.github.tdd.trafficlight.Traffic;
import wolf_j.com.github.tdd.trafficlight.nondirectional.NonDirectionalTrafficLight;

/**
 * @author wolf
 *
 */
class DirectionalTrafficLightTest {

	@Test
	void testGetPermissionsWhenGiveRedlightsAndStraightCrossedLineCar() {

		DirectionalTrafficLight directionalTrafficLight = new DirectionalTrafficLight(NonDirectionalTrafficLight.REDLIGHT, NonDirectionalTrafficLight.REDLIGHT, NonDirectionalTrafficLight.REDLIGHT);

		CarStatus carStatus = new CarStatus(Deriction.STRAIGHT, true);

		Permission permission = Traffic.getPermission(directionalTrafficLight, carStatus);

		assertEquals(false, permission.isStraight());
		assertEquals(false, permission.isTurnLeft());
		assertEquals(false, permission.isTurnRight());
		assertEquals(false, Traffic.isAccess(permission, carStatus));
	}
	
	@Test
	void testGetPermissionsWhenGiveLeftYellowLightAndLeftCrossedLineCar() {

		DirectionalTrafficLight directionalTrafficLight = new DirectionalTrafficLight(NonDirectionalTrafficLight.REDLIGHT, NonDirectionalTrafficLight.YELLOWLIGHT, NonDirectionalTrafficLight.REDLIGHT);

		CarStatus carStatus = new CarStatus(Deriction.LEFT, true);

		Permission permission = Traffic.getPermission(directionalTrafficLight, carStatus);

		assertEquals(false, permission.isStraight());
		assertEquals(true, permission.isTurnLeft());
		assertEquals(false, permission.isTurnRight());
		assertEquals(true, Traffic.isAccess(permission, carStatus));
	}
	
	@Test
	void testGetPermissionsWhenGiveLeftYellowLightAndLeftNotCrossedLineCar() {

		DirectionalTrafficLight directionalTrafficLight = new DirectionalTrafficLight(NonDirectionalTrafficLight.REDLIGHT, NonDirectionalTrafficLight.YELLOWLIGHT, NonDirectionalTrafficLight.REDLIGHT);

		CarStatus carStatus = new CarStatus(Deriction.LEFT, false);

		Permission permission = Traffic.getPermission(directionalTrafficLight, carStatus);

		assertEquals(false, permission.isStraight());
		assertEquals(false, permission.isTurnLeft());
		assertEquals(false, permission.isTurnRight());
		assertEquals(false, Traffic.isAccess(permission, carStatus));
	}
	
	
	@Test
	void testGetPermissionsWhenGiveLeftGreenLightAndRightNotCrossedLineCar() {

		DirectionalTrafficLight directionalTrafficLight = new DirectionalTrafficLight(NonDirectionalTrafficLight.REDLIGHT, NonDirectionalTrafficLight.YELLOWLIGHT, NonDirectionalTrafficLight.GREENLIGHT);

		CarStatus carStatus = new CarStatus(Deriction.RIGHT, false);

		Permission permission = Traffic.getPermission(directionalTrafficLight, carStatus);

		assertEquals(false, permission.isStraight());
		assertEquals(false, permission.isTurnLeft());
		assertEquals(true, permission.isTurnRight());
		assertEquals(true, Traffic.isAccess(permission, carStatus));
	}

}
