package organisms.g2.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import organisms.Constants;
import organisms.g2.data.Point;
import organisms.g2.waypoints.Waypoint;

public class WaypointTest implements Constants {

	Waypoint waypoint;
	
	@Before
	public void setup() {
		waypoint = new Waypoint(new Point(3, 3));
	}
	
	@Test
	public void testPossibleDirections1() {
		ArrayList<Integer> directions = waypoint.getPossibleMovesTowardsWaypoint();
		
		assertEquals("Array length", 2, directions.size());
		assertEquals("Horizontal location", EAST, (int)directions.get(0));
		assertEquals("Vertical location", NORTH, (int)directions.get(1));
	}
	
	@Test
	public void testPossibleDirections2() {
		waypoint.setOffset(new Point(0, 3));
		ArrayList<Integer> directions = waypoint.getPossibleMovesTowardsWaypoint();
		
		assertEquals("Array length", 1, directions.size());
		assertEquals("Horizontal location", EAST, (int)directions.get(0));
	}
}
