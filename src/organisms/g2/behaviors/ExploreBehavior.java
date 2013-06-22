package organisms.g2.behaviors;

import organisms.Move;
import organisms.g2.PlayerBase;
import organisms.g2.PlayerUtil;
import organisms.g2.data.MoveInput;

public class ExploreBehavior extends BehaviorBase {
	public ExploreBehavior(PlayerBase player) {
		super(player);
	}
	@Override
	public Move move(MoveInput input) {
		if(iDontKnowWhatToDo()) return null;
		if(itIsBetterToStayPut()) return getMoveFactory().stayPutMove();
		if(PlayerUtil.noEmptyNeighboringSpaces(input)) return getMoveFactory().stayPutMove();
		
		return new Move(getNextMoveDirection());
	}
	
	/**
	 * goes a random direction with 5/10 probability and reverses in 1/10. 
	 * Otherwise it goes in the same direction
	 * @return
	 */
	protected int getNextMoveDirection() {
		int lastDirection = getMemory().getLastNonStayDirection();
		
		if(getPlayer().nOutOfMTimes(5, 10)) {
			return PlayerUtil.getRandomCardinalDirectionExcept(
					getPlayer().getRand(), lastDirection);
		}
		if(getPlayer().nOutOfMTimes(1, 10)) {
			lastDirection = PlayerUtil.oppositeDirection(lastDirection);
		}
		
		return lastDirection;
	}
	
	protected Boolean iDontKnowWhatToDo() {
		return false;
	}
	
	protected Boolean itIsBetterToStayPut() {
		return getPlayer().nOutOfMTimes(7, 10);
	}
	
//	/*not used as results in worse trial results*/
//	private int orderedMove(MoveInput input, int lastDirection){
//		//tries going south, then east, then west, then north, except if any of these are the last direction
//		//if no direction is avalible, stayput
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
