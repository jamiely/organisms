package organisms.g2.behaviors;

import java.util.ArrayList;

import organisms.Move;
import organisms.g2.PlayerBase;
import organisms.g2.PlayerUtil;
import organisms.g2.data.MoveInput;
import organisms.g2.waypoints.WaypointList;
import organisms.g2.waypoints.WaypointListFactory;

public class WaypointBehavior extends BehaviorBase {

	private WaypointList waypoints;
	
	public WaypointBehavior(PlayerBase player, WaypointList waypoints) {
		super(player);
		setWaypoints(waypoints);
	}

	@Override
	public Move move(MoveInput input) {
		if(getWaypoints().hasReachedWaypoint()) {
			getWaypoints().popAndQueueWaypoint();
		}
		
		ArrayList<Integer> directions = getWaypoints().first().getPossibleMovesTowardsWaypoint();
		int finalDirection = PlayerUtil.directionForMoveGivenPossibleDirections(input, directions);
		if(finalDirection == -1) return null;
		getWaypoints().first().moveInDirection(finalDirection);
		
		return new Move(finalDirection);
	}

	public WaypointList getWaypoints() {
		return waypoints;
	}

	public void setWaypoints(WaypointList waypoints) {
		this.waypoints = waypoints;
	}
	
	public static WaypointBehavior squareWaypointBehavior(PlayerBase player) {
		return new WaypointBehavior(player, WaypointListFactory.squareWaypointList());
	}
}
