package organisms.g2.behaviors;

import java.util.ArrayList;

import organisms.Move;
import organisms.g2.PlayerBase;
import organisms.g2.PlayerUtil;
import organisms.g2.data.MoveInput;

public class CompetionExploreSafeBehavior extends ExploreBehavior {

	public CompetionExploreSafeBehavior(PlayerBase player) {
		super(player);
	}
	
	@Override
	public Move move(MoveInput input) {
		Integer foodDir = foodDirection(input);
		if(foodDir != null) return new Move(foodDir);
		
		if(weHaveStayedTooMuch())  {
			Integer direction = randomDirectionOfEmptySpace(input);
			if(direction != null) return new Move(direction);
		}
		if(itIsBetterToStayPut(input)) return getMoveFactory().stayPutMove();
		if(PlayerUtil.noEmptyNeighboringSpaces(input)) return getMoveFactory().stayPutMove();
		
		if(getPlayer().nOutOfMTimes(4, 5)) return getMoveFactory().stayPutMove();
		if(getPlayer().nOutOfMTimes(1, 5)) return new Move(EAST);
		
		return new Move(NORTH);
		
//		int lastDirection = getMemory().getLastNonStayDirection();
//		return new Move(PlayerUtil.getRandomCardinalDirectionExcept(
//				getPlayer().getRand(), lastDirection));	
	}
	
	protected Integer foodDirection(MoveInput input) {
		if(input.isFoodPresentAt(NORTH)) return NORTH;
		for(Integer dir: PlayerUtil.getCardinalDirections()) {
			if(input.isFoodPresentAt(dir)) return dir;
		}
		return null;
	}
	
	protected Integer randomDirectionOfEmptySpace(MoveInput input) {
		ArrayList<Integer> directions = PlayerUtil.getDirectionsOfEmptySpaces(input);
		return PlayerUtil.randomItem(directions, getPlayer().getRand());
	}
	
	protected Boolean weHaveStayedTooMuch() {
		return getMemory().getCountOfConsecutiveStayPutMoves() > 15;
	}
	
	protected Boolean itIsBetterToStayPut(MoveInput input) {
		return input.getEnergyLeft() < getPlayer().getEnergyConsumedByMovingOrReproducingV() + 1;
	}
	
//	private int orderedMove(MoveInput input, int lastDirection){
//		//tries going south, then east, then west, then north, except if any of these are the last direction
//		//if no direction is available, stayput
//		int[] directions = PlayerUtil.getCardinalDirections();
//		for(int i = 0, size = directions.length; i < size; i++){
//			if(lastDirection == PlayerUtil.oppositeDirection(lastDirection)){
//				continue;
//			}
//			if(!input.isNeighborAt(i)){
//				return i;
//			}
//		}
//		return STAYPUT;
//	}

}
