package organisms.g2.behaviors;

import organisms.Move;
import organisms.g2.MoveFactory;
import organisms.g2.PlayerBase;
import organisms.g2.data.MoveInput;

public class RandomBehavior extends BehaviorBase {
	public RandomBehavior(PlayerBase player) {
		super(player);
	}	
	@Override
	public Move move(MoveInput input) {
		// TODO actually implement a random move
		return getMoveFactory().randomMoveIncludingReproduce();
	}
}
