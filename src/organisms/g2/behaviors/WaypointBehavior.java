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
		int finalDirection = directionForMoveGivenPossibleDirections(input, directions);
		getWaypoints().first().moveInDirection(finalDirection);
		
		return new Move(finalDirection);
	}
	
	protected Integer directionForMoveGivenPossibleDirections(
			MoveInput input, ArrayList<Integer> directions) {
		
		// are any of the given directions available?
		for(Integer dir: directions) {
			if(input.noNeighborAt(dir)) return dir;
		}
		
		// otherwise, try to move in a way that isn't counter to 
		Boolean[] moveInDirection = new Boolean[]{false, true, true, true, true};
		for(Integer dir: directions) {
			moveInDirection[dir] = false;
			moveInDirection[PlayerUtil.oppositeDirection(dir)] = false;
		}
		for(int i = 0; i < moveInDirection.length; i++) {
			if(moveInDirection[i]) return i;
		}
		
		return 0;
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
