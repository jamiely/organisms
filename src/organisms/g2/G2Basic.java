package organisms.g2;

import java.awt.Color;

import organisms.*;
import organisms.g2.behaviors.BehaviorCoordinator;
import organisms.g2.behaviors.ExploreBehavior;
import organisms.g2.behaviors.HungryBehavior;
import organisms.g2.behaviors.ReproductionBehavior;
import organisms.g2.data.MoveInput;
import organisms.g2.stats.Stats;

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
		setCoordinator(newBehaviorCoordinator());
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
		
//		if(shouldReproduce(input)) {
//			return reproduceTowardsFood(input);
//		}	
//		
//		if(input.getFoodLeft() > 0 && shouldConsume(input)) {
//			return createStayPutMove();
//		}
//		
//		for(int i = 1, size = input.getFoodPresent().length; i < size; i ++) {
//			if(!shouldMoveToLocation(i, input)) continue;
//	
//			return createMove(i);
//		}
//		
//		return getMoveFactory().randomMoveAwayFromNeighbors(input);
	}

	protected boolean shouldMoveToLocation(int i, MoveInput input) {
		return input.isFoodPresentAt(i) && !input.isNeighborAt(i) && shouldConsume(input);
	}
	
	protected boolean shouldReproduce(MoveInput input){
		if(nStepsHavePassedSinceWeLastHadChild(10) && nOutOfMTimes(2, 3)){
			return false;
		}
		if (nOutOfMTimes(1, 3)){
			return false;
		}
		if(weHaveReproductionEnergyAndArentCrowded(input)){
			return true;
		}
		return false;
	}

	protected boolean weHaveReproductionEnergyAndArentCrowded(MoveInput input) {
		return weHaveEnoughEnergyToReproduce(input) && weHaveFewNeighbors(input);
	}

	protected boolean weHaveEnoughEnergyToReproduce(MoveInput input) {
		return input.getEnergyLeft() > factorOfMaximumEnergyPerOrganism(0.85);
	}

	protected boolean shouldConsume(MoveInput input){
		if (hasAlotOfEnergy(input.getEnergyLeft())){
			return false;
		}
		if(input.getFoodLeft() < 1){
			return false;
		}
		return true;
	}

	protected boolean hasAlotOfEnergy(int energyLeft) {
		return energyLeft > getEnergyConsumedByMovingOrReproducingV() * 5;
	}
	
	protected Move reproduceTowardsFood(MoveInput input) {
		for(int i = 1, size = input.getFoodPresent().length; i < size; i ++) {
			if(!shouldMoveToLocation(i, input)) continue;
			
			return createReproductionMove(i);
		}
		
		return randomReproduce();
	}
	public static BehaviorCoordinator getCoordinator() {
		return coordinator;
	}
	public static void setCoordinator(BehaviorCoordinator coordinator) {
		G2Basic.coordinator = coordinator;
	}

	protected static BehaviorCoordinator newBehaviorCoordinator() {
		BehaviorCoordinator coordinator = new BehaviorCoordinator();
		coordinator.addBehavior(new HungryBehavior());
		coordinator.addBehavior(new ReproductionBehavior());
		coordinator.addBehavior(new ExploreBehavior());
		return coordinator;
	}
}
