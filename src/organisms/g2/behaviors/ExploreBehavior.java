package organisms.g2.behaviors;

import organisms.Move;
import organisms.g2.PlayerBase;
import organisms.g2.data.MoveInput;

public class ExploreBehavior extends BehaviorBase {
	public ExploreBehavior(PlayerBase player) {
		super(player);
	}
	@Override
	public Move move(MoveInput input) {
		if(iDontKnowWhatToDo()) return null;
		if(itIsBetterToStayPut()) return getMoveFactory().stayPutMove();
		
		// TODO implement me! Return null if we don't want to use any move
		return null;
	}
	
	protected Boolean iDontKnowWhatToDo() {
		return false;
	}
	
	protected Boolean itIsBetterToStayPut() {
		return false;
	}
}
