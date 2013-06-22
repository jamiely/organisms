package organisms.g2;

import java.awt.Color;

import organisms.OrganismsGame;
import organisms.g2.behaviors.CoordinatorFactory;

public class Group2TestPlayer extends CoordinatedPlayerBase {
	private static final long serialVersionUID = -5381547021566768269L;
	
	/*
	 * This method is called when the Organism is created.
	 * The key is the value that is passed to this organism by its parent (not used here)
	 */
	public void register(OrganismsGame game, int key) throws Exception
	{
		super.register(game, key);
		setName("Group 2 Test Player");
		setColor(Color.YELLOW);
		setCoordinator(CoordinatorFactory.waypointCoordinator(this));
	}
}
