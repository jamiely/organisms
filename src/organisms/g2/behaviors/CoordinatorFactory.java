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
	
	public static BehaviorCoordinator allCompetionCoordinator(PlayerBase player, double biomassCutoff, Double brethrenCutoff) {
		BehaviorCoordinator coordinator = new BehaviorCoordinator();
		int preferredDirection = PlayerUtil.getRandomCardinalDirection(player.getRand());
		
		coordinator.addBehavior(new CompetitionHungryBehavior(player, brethrenCutoff),1);
		coordinator.addBehavior(new CompetitionReproduceBehavior(player, preferredDirection, biomassCutoff, brethrenCutoff), 0);
		coordinator.addBehavior(new CompetitionReproduceRiskBehavior(player, preferredDirection, biomassCutoff, brethrenCutoff), 1);
		coordinator.addBehavior(new CompetionExploreSafeBehavior(player, biomassCutoff, brethrenCutoff),2);
		coordinator.addBehavior(new CompetionExploreRiskBehavior(player, PlayerUtil.oppositeDirection(preferredDirection), biomassCutoff, brethrenCutoff),3);
		coordinator.addBehavior(new HungryBehavior(player, brethrenCutoff),5);
		coordinator.addBehavior(new ReproductionBehavior(player, brethrenCutoff),4);
		coordinator.addBehavior(new ExploreBehavior(player, brethrenCutoff),6);
		coordinator.addBehavior(new RandomBehavior(player),7);
		return coordinator;
	}	
	
}
