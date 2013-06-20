package organisms.g2.behaviors;

import organisms.Move;
import organisms.g2.MoveFactory;
import organisms.g2.PlayerBase;
import organisms.g2.data.MoveInput;

public class RandomBehavior extends BehaviorBase {
	private MoveFactory moveFactory;
	public RandomBehavior(PlayerBase player) {
		super(player);
		moveFactory = new MoveFactory();
	}	
	@Override
	public Move move(MoveInput input) {
		// TODO actually implement a random move
		return moveFactory.randomMoveIncludingReproduce();
	}
}
