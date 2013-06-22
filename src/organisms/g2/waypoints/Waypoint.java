package organisms.g2.waypoints;

import java.util.ArrayList;

import organisms.Constants;
import organisms.g2.data.Point;
import organisms.g2.data.PointUtil;

public class Waypoint implements Constants {
	private Point destination;
	// Where someone is relative to 
	private Point offset;
	// STAYPUT, WEST, EAST, NORTH, SOUTH
	
	public Waypoint(Point destination) {
		setDestination(destination);
		resetOffset();
	}
	public Point getDestination() {
		return destination;
	}
	public void setDestination(Point destination) {
		this.destination = destination;
	}
	public Point getOffset() {
		return offset;
	}
	public void setOffset(Point offset) {
		this.offset = offset;
	}
	public ArrayList<Integer> getPossibleMovesTowardsWaypoint() {
		return PointUtil.getPossibleDirectionsTowardsPoint(getDestination(), getOffset());
	}
	public void moveInDirection(int direction) {
		Point moveOffset = PointUtil.getPointOffsetForDirection(direction);
		if(moveOffset == null) return;
		
		getOffset().add(moveOffset);
	}
	public Integer taxicabDistance() {
		return getOffset().getTaxiCabDistance(getDestination());
	}
	public Boolean waypointHasBeenReached() {
		return getDestination().equals(getOffset());
	}
	public void resetOffset() {
		setOffset(Point.origin());
	}
}
