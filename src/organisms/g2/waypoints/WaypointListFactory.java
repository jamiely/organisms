package organisms.g2.waypoints;

import organisms.g2.data.Point;

public class WaypointListFactory {
	public static WaypointList squareWaypointList() {
		Point[] pts = new Point[] {
				new Point(4, 0),
				new Point(0, 4),
				new Point(-4, 0),
				new Point(0, -4)
		};
		return listFromPoints(pts);
	}
	public static WaypointList listFromPoints(Point[] points) {
		WaypointList list = new WaypointList();
		for(Point p: points) {
			list.addWaypoint(new Waypoint(p));
		}
		return list;
	}
}
