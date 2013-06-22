package organisms.g2;

import organisms.Move;
import organisms.g2.behaviors.BehaviorCoordinator;
import organisms.g2.data.MoveInput;

public abstract class CoordinatedPlayerBase extends PlayerBase {
	private static final long serialVersionUID = 5151039396410862725L;
	private BehaviorCoordinator coordinator = null;

	/*
	 * This is called by the simulator to determine how this Organism should move.
	 * foodpresent is a four-element array that indicates whether any food is in adjacent squares
	 * neighbors is a four-element array that holds the externalState for any organism in an adjacent square
	 * foodleft is how much food is left on the current square
	 * energyleft is this organism's remaining energy
	 */
	@Override
	public Move move(MoveInput input) {
		return coordinator.move(input);
	}

	public BehaviorCoordinator getCoordinator() {
		return coordinator;
	}
	public void setCoordinator(BehaviorCoordinator coordinator) {
		this.coordinator = coordinator;
	}
}
