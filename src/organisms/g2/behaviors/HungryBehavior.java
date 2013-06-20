package organisms.g2.behaviors;

import organisms.Move;
import organisms.g2.PlayerBase;
import organisms.g2.data.MoveInput;

public class HungryBehavior extends BehaviorBase {
	public HungryBehavior(PlayerBase player) {
		super(player);
	}
	@Override
	public Move move(MoveInput input) {
		// TODO implement me! Return null if we don't want to use any move
		return null;
	}
	
	protected Boolean amIHungry() {
		return false;
	}
}
