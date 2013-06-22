package organisms.g2.data;

import java.util.ArrayList;

import organisms.Constants;

public class Waypoint implements Constants {
	private Point destination;
	// Where someone is relative to 
	private Point offset;
	// STAYPUT, WEST, EAST, NORTH, SOUTH
	private static Point[] offsetsForDirections = new Point[]{
		new Point(0, 0),
		new Point(-1, 0),
		new Point(1, 0),
		new Point(0, 1),
		new Point(0, -1)
	};
	
	public Waypoint(Point destination) {
		setDestination(destination);
		setOffset(Point.origin());
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
		ArrayList<Integer> directions = new ArrayList<Integer>();
		
		Point delta = Point.subtract(getDestination(), getOffset());
		if(delta.x != 0) {
			directions.add(delta.x < 0 ? WEST : EAST);
		}
		if(delta.y != 0) {
			directions.add(delta.y < 0 ? SOUTH : NORTH);
		}
		
		return directions;
	}
	public void moveInDirection(int direction) {
		Point moveOffset = offsetsForDirections[direction];
		if(moveOffset == null) return;
		
		getOffset().add(moveOffset);
	}
	public Integer taxicabDistance() {
		return getOffset().getTaxiCabDistance(getDestination());
	}
}
