/**
 * 
 */
package wolf_j.com.github.tdd.trafficlight.nondirectional;

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
class NondirectionalTrafficLightTest {

	@Test
	void testGetPermissionsWhenGiveRedlightAndStraightCrossedLineCar() {

		NonDirectionalTrafficLight redLight = NonDirectionalTrafficLight.REDLIGHT;
		CarStatus carStatus = new CarStatus(Deriction.STRAIGHT, true);

		Permission permission = Traffic.getPermission(redLight, carStatus);

		assertEquals(false, permission.isStraight());
		assertEquals(true, permission.isTurnLeft());
		assertEquals(true, permission.isTurnRight());
		assertEquals(false, Traffic.isAccess(permission, carStatus));
	}

	@Test
	void testGetPermissionsWhenGiveRedlightAndStraightNotCrossedLineCar() {

		NonDirectionalTrafficLight redLight = NonDirectionalTrafficLight.REDLIGHT;
		CarStatus carStatus = new CarStatus(Deriction.STRAIGHT, false);

		Permission permission = Traffic.getPermission(redLight, carStatus);

		assertEquals(false, permission.isStraight());
		assertEquals(true, permission.isTurnLeft());
		assertEquals(true, permission.isTurnRight());
		assertEquals(false, Traffic.isAccess(permission, carStatus));
	}
	
	@Test
	void testGetPermissionsWhenGiveRedlightAndLeftNotCrossedLineCar() {

		NonDirectionalTrafficLight redLight = NonDirectionalTrafficLight.REDLIGHT;
		CarStatus carStatus = new CarStatus(Deriction.LEFT, false);

		Permission permission = Traffic.getPermission(redLight, carStatus);

		assertEquals(false, permission.isStraight());
		assertEquals(true, permission.isTurnLeft());
		assertEquals(true, permission.isTurnRight());
		assertEquals(true, Traffic.isAccess(permission, carStatus));
	}
	
	@Test
	void testGetPermissionsWhenGiveYellowlightAndLeftCrossedLineCar() {

		NonDirectionalTrafficLight yellowLight = NonDirectionalTrafficLight.YELLOWLIGHT;
		CarStatus carStatus = new CarStatus(Deriction.STRAIGHT, true);

		Permission permission = Traffic.getPermission(yellowLight, carStatus);

		assertEquals(true, permission.isStraight());
		assertEquals(true, permission.isTurnLeft());
		assertEquals(false, permission.isTurnRight());
		assertEquals(true, Traffic.isAccess(permission, carStatus));
	}
	
	@Test
	void testGetPermissionsWhenGiveYellowlightAndLeftNotCrossedLineCar() {

		NonDirectionalTrafficLight yellowLight = NonDirectionalTrafficLight.YELLOWLIGHT;
		CarStatus carStatus = new CarStatus(Deriction.STRAIGHT, false);

		Permission permission = Traffic.getPermission(yellowLight, carStatus);

		assertEquals(false, permission.isStraight());
		assertEquals(true, permission.isTurnLeft());
		assertEquals(false, permission.isTurnRight());
		assertEquals(false, Traffic.isAccess(permission, carStatus));
	}
	
	@Test
	void testGetPermissionsWhenGiveGreenlightAndLeftNotCrossedLineCar() {

		NonDirectionalTrafficLight greenLight = NonDirectionalTrafficLight.GREENLIGHT;
		CarStatus carStatus = new CarStatus(Deriction.STRAIGHT, false);

		Permission permission = Traffic.getPermission(greenLight, carStatus);

		assertEquals(true, permission.isStraight());
		assertEquals(true, permission.isTurnLeft());
		assertEquals(false, permission.isTurnRight());
		assertEquals(true, Traffic.isAccess(permission, carStatus));
	}




}
