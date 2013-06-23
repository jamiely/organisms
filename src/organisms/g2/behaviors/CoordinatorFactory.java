package organisms.g2.behaviors;

import organisms.Constants;
import organisms.g2.PlayerBase;
import organisms.g2.PlayerUtil;

public class CoordinatorFactory implements Constants {
	public static BehaviorCoordinator hungryReproduceExploreCoordinator(PlayerBase player) {
		BehaviorCoordinator coordinator = new BehaviorCoordinator();
		coordinator.addBehavior(new HungryBehavior(player));
		coordinator.addBehavior(new ReproductionBehavior(player));
		coordinator.addBehavior(new ExploreBehavior(player));
		coordinator.addBehavior(new RandomBehavior(player));
		return coordinator;
	}
	public static BehaviorCoordinator waypointCoordinator(PlayerBase player) {
		BehaviorCoordinator coordinator = new BehaviorCoordinator();
		coordinator.addBehavior(WaypointBehavior.squareWaypointBehavior(player));
		return coordinator;
	}
	public static BehaviorCoordinator hungryReproduceWaypointCoordinator(PlayerBase player) {
		BehaviorCoordinator coordinator = new BehaviorCoordinator();
		coordinator.addBehavior(new HungryBehavior(player));
		coordinator.addBehavior(new ReproductionBehavior(player));
		coordinator.addBehavior(WaypointBehavior.squareWaypointBehavior(player));
		coordinator.addBehavior(new RandomBehavior(player));
		return coordinator;
	}
	public static BehaviorCoordinator hungryWaypointCoordinator(PlayerBase player) {
		BehaviorCoordinator coordinator = new BehaviorCoordinator();
		coordinator.addBehavior(new HungryBehavior(player));
		coordinator.addBehavior(WaypointBehavior.squareWaypointBehavior(player));
		coordinator.addBehavior(new RandomBehavior(player));
		return coordinator;
	}
	public static BehaviorCoordinator competionCoordinator(PlayerBase player) {
		BehaviorCoordinator coordinator = new BehaviorCoordinator();
		coordinator.addBehavior(new CompetitionHungryBehavior(player));
		
		int preferredDirection = PlayerUtil.getRandomCardinalDirection(player.getRand());
		coordinator.addBehavior(new CompetitionReproduceBehavior(player, preferredDirection));
		coordinator.addBehavior(new CompetionExploreRiskBehavior(player, PlayerUtil.oppositeDirection(preferredDirection)));
		coordinator.addBehavior(WaypointBehavior.squareWaypointBehavior(player));
		coordinator.addBehavior(new RandomBehavior(player));
		return coordinator;
	}
}
