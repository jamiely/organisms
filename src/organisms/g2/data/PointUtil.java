package organisms.g2.data;

import java.util.ArrayList;

import organisms.Constants;

public class PointUtil implements Constants {
	private static Point[] offsetsForDirections = new Point[]{
		new Point(0, 0),
		new Point(-1, 0),
		new Point(1, 0),
		new Point(0, 1),
		new Point(0, -1)
	};
	public static ArrayList<Integer> getPossibleDirectionsTowardsPoint(Point destination) {
		return getPossibleDirectionsTowardsPoint(destination, Point.origin());
	}
	public static ArrayList<Integer> getPossibleDirectionsTowardsPoint(Point destination, Point currentLocation) {
		ArrayList<Integer> directions = new ArrayList<Integer>();
		
		Point delta = Point.subtract(destination, currentLocation);
		if(delta.x != 0) {
			directions.add(delta.x < 0 ? WEST : EAST);
		}
		if(delta.y != 0) {
			directions.add(delta.y < 0 ? SOUTH : NORTH);
		}
		
		return directions;
	}
	public static Point[] getPointOffsetsForDirections() {
		return offsetsForDirections;
	}
	
	public static Point getPointOffsetForDirection(int direction) {
		return getPointOffsetsForDirections()[direction];
	}
	public static Point pointOffsetInDirection(Point p, int direction) {
		return Point.add(p, getPointOffsetForDirection(direction));
	}
}
