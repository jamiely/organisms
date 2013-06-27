package organisms.g2.behaviors;

import organisms.Constants;
import organisms.g2.PlayerBase;
import organisms.g2.PlayerUtil;

public class CoordinatorFactory implements Constants {
	public static BehaviorCoordinator hungryReproduceExploreCoordinator(PlayerBase player) {
		BehaviorCoordinator coordinator = new BehaviorCoordinator();
		
		coordinator.addBehavior(new HungryBehavior(player),1);
		coordinator.addBehavior(new ReproductionBehavior(player),0);
		coordinator.addBehavior(new ExploreBehavior(player),2);
		coordinator.addBehavior(new RandomBehavior(player),3);
		
		return coordinator;
	}
	public static BehaviorCoordinator waypointCoordinator(PlayerBase player) {
		BehaviorCoordinator coordinator = new BehaviorCoordinator();
		coordinator.addBehavior(WaypointBehavior.squareWaypointBehavior(player),0);
		return coordinator;
	}
	public static BehaviorCoordinator hungryReproduceWaypointCoordinator(PlayerBase player) {
		BehaviorCoordinator coordinator = new BehaviorCoordinator();
		coordinator.addBehavior(new HungryBehavior(player),1);
		coordinator.addBehavior(new ReproductionBehavior(player),0);
		coordinator.addBehavior(WaypointBehavior.squareWaypointBehavior(player),2);
		coordinator.addBehavior(new RandomBehavior(player),3);
		return coordinator;
	}
	public static BehaviorCoordinator hungryWaypointCoordinator(PlayerBase player) {
		BehaviorCoordinator coordinator = new BehaviorCoordinator();
		coordinator.addBehavior(new HungryBehavior(player),0);
		coordinator.addBehavior(WaypointBehavior.squareWaypointBehavior(player),1);
		coordinator.addBehavior(new RandomBehavior(player),2);
		return coordinator;
	}
	public static BehaviorCoordinator competionCoordinator(PlayerBase player) {
		BehaviorCoordinator coordinator = new BehaviorCoordinator();
		coordinator.addBehavior(new CompetitionHungryBehavior(player), 1);
		
		int preferredDirection = PlayerUtil.getRandomCardinalDirection(player.getRand());
		coordinator.addBehavior(new CompetitionReproduceBehavior(player, preferredDirection), 0);
		coordinator.addBehavior(new CompetionExploreRiskBehavior(player, PlayerUtil.oppositeDirection(preferredDirection)),3);
		coordinator.addBehavior(WaypointBehavior.squareWaypointBehavior(player),2);
		coordinator.addBehavior(new RandomBehavior(player), 4);
		return coordinator;
	}	
	
	public static BehaviorCoordinator competionSafeCoordinator(PlayerBase player) {
		BehaviorCoordinator coordinator = new BehaviorCoordinator();
		coordinator.addBehavior(new CompetitionHungryBehavior(player),1);
		
		int preferredDirection = PlayerUtil.getRandomCardinalDirection(player.getRand());
		coordinator.addBehavior(new CompetitionReproduceBehavior(player, preferredDirection), 0);
		coordinator.addBehavior(new CompetionExploreSafeBehavior(player),2);
		coordinator.addBehavior(WaypointBehavior.squareWaypointBehavior(player),3);
		coordinator.addBehavior(new RandomBehavior(player),4);
		return coordinator;
	}	
	
}
