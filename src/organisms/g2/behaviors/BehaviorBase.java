package organisms.g2.behaviors;

import organisms.Constants;
import organisms.Move;
import organisms.g2.MoveProvider;
import organisms.g2.data.MoveInput;

public abstract class BehaviorBase implements MoveProvider, Constants {
	@Override
	public abstract Move move(MoveInput input);
}
