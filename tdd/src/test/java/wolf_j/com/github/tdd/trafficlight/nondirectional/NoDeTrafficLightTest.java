/**
 * 
 */
package wolf_j.com.github.tdd.trafficlight.nondirectional;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import wolf_j.com.github.tdd.trafficlight.nondirectional.Permission;
import wolf_j.com.github.tdd.trafficlight.nondirectional.Traffic;
import wolf_j.com.github.tdd.trafficlight.nondirectional.NoDeTrafficLight;

/**
 * @author wolf
 *
 */
class NoDeTrafficLightTest {

	@Test
	void testGetPermissionsWhenGiveRedlightAndStraightCrossedLineCar() {

		NoDeTrafficLight redLight = NoDeTrafficLight.REDLIGHT;
		CarStatus carStatus = new CarStatus(Deriction.STRAIGHT, true);

		Permission permission = Traffic.getPermission(redLight, carStatus);

		assertEquals(false, permission.isStraight());
		assertEquals(true, permission.isTurnLeft());
		assertEquals(true, permission.isTurnRight());
		assertEquals(false, Traffic.isAccess(permission, carStatus));
	}

	@Test
	void testGetPermissionsWhenGiveRedlightAndStraightNotCrossedLineCar() {

		NoDeTrafficLight redLight = NoDeTrafficLight.REDLIGHT;
		CarStatus carStatus = new CarStatus(Deriction.STRAIGHT, false);

		Permission permission = Traffic.getPermission(redLight, carStatus);

		assertEquals(false, permission.isStraight());
		assertEquals(true, permission.isTurnLeft());
		assertEquals(true, permission.isTurnRight());
		assertEquals(false, Traffic.isAccess(permission, carStatus));
	}
	
	@Test
	void testGetPermissionsWhenGiveRedlightAndLeftNotCrossedLineCar() {

		NoDeTrafficLight redLight = NoDeTrafficLight.REDLIGHT;
		CarStatus carStatus = new CarStatus(Deriction.LEFT, false);

		Permission permission = Traffic.getPermission(redLight, carStatus);

		assertEquals(false, permission.isStraight());
		assertEquals(true, permission.isTurnLeft());
		assertEquals(true, permission.isTurnRight());
		assertEquals(true, Traffic.isAccess(permission, carStatus));
	}
	
	@Test
	void testGetPermissionsWhenGiveYellowlightAndLeftCrossedLineCar() {

		NoDeTrafficLight yellowLight = NoDeTrafficLight.YELLOWLIGHT;
		CarStatus carStatus = new CarStatus(Deriction.STRAIGHT, true);

		Permission permission = Traffic.getPermission(yellowLight, carStatus);

		assertEquals(true, permission.isStraight());
		assertEquals(true, permission.isTurnLeft());
		assertEquals(false, permission.isTurnRight());
		assertEquals(true, Traffic.isAccess(permission, carStatus));
	}
	
	@Test
	void testGetPermissionsWhenGiveYellowlightAndLeftNotCrossedLineCar() {

		NoDeTrafficLight yellowLight = NoDeTrafficLight.YELLOWLIGHT;
		CarStatus carStatus = new CarStatus(Deriction.STRAIGHT, false);

		Permission permission = Traffic.getPermission(yellowLight, carStatus);

		assertEquals(false, permission.isStraight());
		assertEquals(true, permission.isTurnLeft());
		assertEquals(false, permission.isTurnRight());
		assertEquals(false, Traffic.isAccess(permission, carStatus));
	}
	
	@Test
	void testGetPermissionsWhenGiveGreenlightAndLeftNotCrossedLineCar() {

		NoDeTrafficLight greenLight = NoDeTrafficLight.GREENLIGHT;
		CarStatus carStatus = new CarStatus(Deriction.STRAIGHT, false);

		Permission permission = Traffic.getPermission(greenLight, carStatus);

		assertEquals(true, permission.isStraight());
		assertEquals(true, permission.isTurnLeft());
		assertEquals(false, permission.isTurnRight());
		assertEquals(true, Traffic.isAccess(permission, carStatus));
	}




}
