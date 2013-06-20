package organisms.g2.behaviors;

import organisms.Constants;
import organisms.Move;
import organisms.g2.MoveFactory;
import organisms.g2.MoveProvider;
import organisms.g2.PlayerBase;
import organisms.g2.data.MoveInput;

public abstract class BehaviorBase implements MoveProvider, Constants {
	
	private PlayerBase player;
	
	public BehaviorBase(PlayerBase player) {
		setPlayer(player);
	}
	@Override
	public abstract Move move(MoveInput input);
	public PlayerBase getPlayer() {
		return player;
	}
	public void setPlayer(PlayerBase player) {
		this.player = player;
	}
	protected MoveFactory getMoveFactory() {
		return getPlayer().getMoveFactory();
	}
}
