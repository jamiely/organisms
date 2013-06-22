package organisms.g2.waypoints;

import java.util.ArrayList;

import organisms.Constants;
import organisms.g2.data.Point;

public class WaypointListFactory implements Constants {
	
	protected static Point pointNorth(int magnitude) {
		return new Point(0, magnitude);
	}
	
	protected static Point pointSouth(int magnitude) {
		return new Point(0, -magnitude);
	}
	
	protected static Point pointEast(int magnitude) {
		return new Point(magnitude, 0);
	}
	
	protected static Point pointWest(int magnitude) {
		return new Point(-magnitude, 0);
	}
	
	protected static ArrayList<Point> pointSquareStartingWithDirectionWithMagnitude(int direction, Boolean clockwise, int magnitude) {
		Integer[] directions = clockDirectionsStartingWith(direction, clockwise);
		ArrayList<Point> points = new ArrayList<Point>();
		for(Integer dir: directions) {
			points.add(pointInDirection(dir, magnitude));
		}
		return points;
	}
	
	protected static Integer[] clockDirectionsStartingWith(Integer direction, Boolean clockwise) {
		final int[] counterClockwiseDirections = new int[]{NORTH, WEST, SOUTH, EAST};
		final int[] clockwiseDirections = new int[]{NORTH, EAST, SOUTH, WEST};
		
		int[] directions = clockwise ? clockwiseDirections : counterClockwiseDirections;
		// find the index of the passed direction
		int indexOfPassedDirection = -1;
		for(int i = 0, size = 4; i < size; i++) {
			if(directions[i] != direction) continue;
			indexOfPassedDirection = i;
			break;
		}
		
		int size = 4;
		Integer[] revisedDirections = new Integer[size];
		for(int i = 0; i < size; i++) {
			revisedDirections[i] = directions[(indexOfPassedDirection + i) % size];
		}
		return revisedDirections;
	}
	
	protected static Point pointInDirection(int direction, int magnitude) {
		switch(direction) {
		case NORTH: return pointNorth(magnitude);
		case SOUTH: return pointSouth(magnitude);
		case EAST: return pointEast(magnitude);
		case WEST: return pointWest(magnitude);
		}
		return Point.origin();
	}
	
	public static WaypointList squareWaypointList() {
		return listFromPoints(pointSquareStartingWithDirectionWithMagnitude(EAST, false, 4));
	}
	public static WaypointList listFromPoints(Iterable<Point> points) {
		WaypointList list = new WaypointList();
		for(Point p: points) {
			list.addWaypoint(new Waypoint(p));
		}
		return list;
	}
}
