package organisms.g2;

import java.awt.Color;

import organisms.*;
import organisms.g2.behaviors.BehaviorCoordinator;
import organisms.g2.behaviors.ExploreBehavior;
import organisms.g2.behaviors.HungryBehavior;
import organisms.g2.behaviors.RandomBehavior;
import organisms.g2.behaviors.ReproductionBehavior;
import organisms.g2.data.MoveInput;

public final class G2Basic extends PlayerBase {
	private static final long serialVersionUID = -3612443241236355755L;
	private static BehaviorCoordinator coordinator = null;
	
	/*
	 * This method is called when the Organism is created.
	 * The key is the value that is passed to this organism by its parent (not used here)
	 */
	public void register(OrganismsGame game, int key) throws Exception
	{
		super.register(game, key);
		setName("G2Basic");
		setColor(Color.PINK);
		setCoordinator(newBehaviorCoordinator(this));
	}

	/*
	 * This is called by the simulator to determine how this Organism should move.
	 * foodpresent is a four-element array that indicates whether any food is in adjacent squares
	 * neighbors is a four-element array that holds the externalState for any organism in an adjacent square
	 * foodleft is how much food is left on the current square
	 * energyleft is this organism's remaining energy
	 */
	public Move move(MoveInput input) {
		return coordinator.move(input);
	}

	public static BehaviorCoordinator getCoordinator() {
		return coordinator;
	}
	public static void setCoordinator(BehaviorCoordinator coordinator) {
		G2Basic.coordinator = coordinator;
	}

	protected static BehaviorCoordinator newBehaviorCoordinator(PlayerBase player) {
		BehaviorCoordinator coordinator = new BehaviorCoordinator();
		coordinator.addBehavior(new HungryBehavior(player));
		coordinator.addBehavior(new ReproductionBehavior(player));
		coordinator.addBehavior(new ExploreBehavior(player));
		coordinator.addBehavior(new RandomBehavior(player));
		return coordinator;
	}
}
