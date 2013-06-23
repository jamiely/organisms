package organisms.g2;

import java.awt.Color;

import organisms.*;
import organisms.g2.behaviors.CoordinatorFactory;

public final class G2Basic extends CoordinatedPlayerBase {
	private static final long serialVersionUID = -3612443241236355755L;
	
	/*
	 * This method is called when the Organism is created.
	 * The key is the value that is passed to this organism by its parent (not used here)
	 */
	public void register(OrganismsGame game, int key) throws Exception
	{
		super.register(game, key);
		setName("G2Basic Intraspecies");
		setColor(Color.GREEN);
		setCoordinator(CoordinatorFactory.hungryReproduceExploreCoordinator(this));
	}

}
