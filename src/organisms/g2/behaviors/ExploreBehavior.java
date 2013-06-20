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
	
	protected int getNextMoveDirection() {
		int lastDirection = WEST;
		if(getMemory().hasLastDirection() && getMemory().getLastDirection() != STAYPUT) {
			lastDirection = getMemory().getLastDirection();
		}
		if(getPlayer().nOutOfMTimes(2, 10)) {
			lastDirection = PlayerUtil.oppositeDirection(lastDirection);
		}
		return lastDirection;
	}
	
	protected Boolean iDontKnowWhatToDo() {
		return false;
	}
	
	protected Boolean itIsBetterToStayPut() {
		return false;
	}
}
