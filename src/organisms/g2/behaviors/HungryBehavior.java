package organisms.g2.behaviors;

import java.util.ArrayList;

import organisms.Move;
import organisms.g2.PlayerBase;
import organisms.g2.PlayerUtil;
import organisms.g2.data.MoveInput;
import organisms.g2.data.Point;
import organisms.g2.data.PointUtil;

public class HungryBehavior extends BehaviorBase {
	
	private Double brethrenCutoff = 0.0;
	
	public HungryBehavior(PlayerBase player) {
		super(player);
	}
	/**
	 * @param player
	 * @param brethrenCutoff
	 */
	public HungryBehavior(PlayerBase player, double BrethrenCutoff) {
		super(player);
		this.brethrenCutoff = BrethrenCutoff;
	}
	
	@Override
	public Move move(MoveInput input) {
		if(getMemory().getOwnPopulationRatio() > brethrenCutoff) return null;
		if(!iAmHungry(input)) return null;
		
		if(input.getFoodLeft() > 2) {
			return getMoveFactory().stayPutMove();
		}
		
		for(int i = 1, size = input.getFoodPresent().length; i < size; i ++) {
			if(!shouldMoveToLocation(i, input)) continue;
	
			return new Move(i);
		}
		
		return getMoveTowardsMostRecentlySeenFood(input);
	}
	
	protected Move getMoveTowardsMostRecentlySeenFood(MoveInput input) {
		int direction = getDirectionTowardsMostRecentlySeenFood(input);
		if(direction == -1) return null;
		
		return new Move(direction);
	}
	
	protected Integer getDirectionTowardsMostRecentlySeenFood(MoveInput input) {
		Point p = getMemory().getMostRecentlySeenFoodLocation();
		if(p == null) return -1;
		
		ArrayList<Integer> directions = PointUtil.getPossibleDirectionsTowardsPoint(p);
		return PlayerUtil.directionForMoveGivenPossibleDirections(input, directions);
	}
	
	protected Boolean iAmHungry(MoveInput input) {
		return !hasAlotOfEnergy(input);
	}
	
	protected boolean hasAlotOfEnergy(MoveInput input) {
		return input.getEnergyLeft() > getPlayer().getEnergyConsumedByMovingOrReproducingV() * 5;
	}
	
	protected boolean shouldMoveToLocation(int i, MoveInput input) {
		return input.isFoodPresentAt(i) && !input.isNeighborAt(i);
	}
}
