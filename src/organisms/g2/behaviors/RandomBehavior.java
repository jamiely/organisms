package organisms.g2.behaviors;

import organisms.Move;
import organisms.g2.MoveFactory;
import organisms.g2.data.MoveInput;

public class RandomBehavior extends BehaviorBase {
	private MoveFactory moveFactory;
	public RandomBehavior() {
		moveFactory = new MoveFactory();
	}
	@Override
	public Move move(MoveInput input) {
		// TODO actually implement a random move
		return moveFactory.randomMove();
	}
}
