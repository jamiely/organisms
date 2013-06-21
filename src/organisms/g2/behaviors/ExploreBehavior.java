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
		
		return new Move(getNextMoveDirection());
	}
	
	/**
	 * goes a random direction with 5/10 probability and reverses in 1/10. 
	 * @return
	 */
	protected int getNextMoveDirection() {
		int lastDirection = getMemory().getLastNonStayDirection();
		
		if(getPlayer().nOutOfMTimes(5, 10)) {
			int newDirection = PlayerUtil.getRandomCardinalDirection(getPlayer().getRand());
			while (lastDirection == newDirection){
				newDirection = PlayerUtil.getRandomCardinalDirection(getPlayer().getRand());
			}
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
		if(getPlayer().nOutOfMTimes(7, 10)) {
			return true;
		} 
		return false;
	}
}
