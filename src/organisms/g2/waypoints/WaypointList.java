package organisms.g2.waypoints;

import java.util.ArrayList;

public class WaypointList {
	private ArrayList<Waypoint> waypoints;
	public WaypointList() {
		setWaypoints(new ArrayList<Waypoint>());
	}
	public ArrayList<Waypoint> getWaypoints() {
		return waypoints;
	}
	public void setWaypoints(ArrayList<Waypoint> waypoints) {
		this.waypoints = waypoints;
	}
	/// Useful for reusing waypoints, as in the case of 
	/// circular paths. Pops the first waypoint,
	/// resets the offset, and then puts it back onto the end
	/// of the queue.
	public void popAndQueueWaypoint() {
		if(getWaypoints().isEmpty()) return;
		
		first().resetOffset();
		getWaypoints().add(getWaypoints().remove(0));
	}
	public Waypoint first() {
		return getWaypoints().get(0);
	}
	public Boolean hasReachedWaypoint() {
		if(getWaypoints().isEmpty()) return false;
		
		return first().waypointHasBeenReached();
	}
	public void addWaypoint(Waypoint waypoint) {
		getWaypoints().add(waypoint);
	}
}
