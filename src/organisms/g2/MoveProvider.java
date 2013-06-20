package organisms.g2;

import organisms.Move;
import organisms.g2.data.MoveInput;

public interface MoveProvider {
	public Move move(MoveInput input);
}
