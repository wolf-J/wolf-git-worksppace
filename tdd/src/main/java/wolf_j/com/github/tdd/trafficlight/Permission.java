/**
 * 
 */
package wolf_j.com.github.tdd.trafficlight;

/**
 * @author wolf
 *
 */
public class Permission {

	private Boolean straight;
	private Boolean turnRight;
	private Boolean turnLeft;

	public Permission(Boolean straight, Boolean turnLeft, Boolean turnRight) {
		this.straight = straight;
		this.turnLeft = turnLeft;
		this.turnRight = turnRight;
	}


	public Permission() {
	}


	public Boolean isStraight() {
		return straight;
	}

	public Boolean isTurnRight() {
		return turnRight;
	}

	public Boolean isTurnLeft() {
		return turnLeft;
	}
	

	public void setStraight(Boolean straight) {
		this.straight = straight;
	}


	public void setTurnRight(Boolean turnRight) {
		this.turnRight = turnRight;
	}


	public void setTurnLeft(Boolean turnLeft) {
		this.turnLeft = turnLeft;
	}

}
