package organisms.g2.behaviors;

import organisms.g2.PlayerBase;

public class CoordinatorFactory {
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
}
