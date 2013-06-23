package organisms.g2.behaviors;


import java.util.ArrayList;
import organisms.Move;
import organisms.g2.PlayerBase;
import organisms.g2.PlayerUtil;
import organisms.g2.data.MoveInput;
import organisms.g2.data.Point;
import organisms.g2.data.PointUtil;

public class CompetitionHungryBehavior extends HungryBehavior {

	public CompetitionHungryBehavior(PlayerBase player) {
		super(player);
	}
	
	@Override
	public Move move(MoveInput input) {
		if(input.getEnergyLeft() == getPlayer().getMaximumEnergyPerOrganismM()) return null;
		
		if(input.getFoodLeft() > 0) {
			return getMoveFactory().stayPutMove();
		}
		
		for(int i = 1, size = input.getFoodPresent().length; i < size; i ++) {
			if(!input.isFoodPresentAt(i)) continue;
			if(input.isNeighborAt(i)) continue;
	
			return new Move(i);
		}
		
		if(shouldSearchForFood(input) && 
				lastSeenFoodIsCloseEnough(input)) {
			Move m = getMoveTowardsMostRecentlySeenFood(input);
			if(m != null) return m;
		}
		
		return null;
	}
	
	protected Move getMoveTowardsMostRecentlySeenFood(MoveInput input) {
		Point p = getMemory().getMostRecentlySeenFoodLocation();
		if(p == null) return null;
		
		ArrayList<Integer> directions = PointUtil.getPossibleDirectionsTowardsPoint(p);
		return new Move(PlayerUtil.directionForMoveGivenPossibleDirections(input, directions));
	}
	
	/**
	 * When should we go in search for food.
	 * @return
	 */
	protected Boolean shouldSearchForFood(MoveInput input) {
		return input.getEnergyLeft() < getPlayer().getMaximumEnergyPerOrganismM() * 0.4;
	}
	
	protected Boolean lastSeenFoodIsCloseEnough(MoveInput input) {
		Point p = getMemory().getMostRecentlySeenFoodLocation();
		if(p == null) return false;
		
		Integer distance = p.getTaxiCabDistance(Point.origin());
		Integer energyToGetThere = distance * getPlayer().getEnergyConsumedByMovingOrReproducingV();
		Integer energyBuffer = getPlayer().getEnergyConsumedByMovingOrReproducingV() * 3;
		if(input.getEnergyLeft() < energyToGetThere + energyBuffer) return false;
		
		System.out.println(String.format("Last seen food is %d steps away", distance));
		
		return true;
	}
	
	protected Integer randomDirectionOfEmptySpace(MoveInput input) {
		ArrayList<Integer> directions = PlayerUtil.getDirectionsOfEmptySpaces(input);
		return PlayerUtil.randomItem(directions, getPlayer().getRand());
	}
	
	protected Boolean weHaveStayedTooMuch() {
		return getMemory().getCountOfConsecutiveStayPutMoves() > 3;
	}
}
