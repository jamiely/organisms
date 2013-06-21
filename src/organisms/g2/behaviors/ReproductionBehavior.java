package organisms.g2.behaviors;

import organisms.Move;
import organisms.g2.PlayerBase;
import organisms.g2.PlayerUtil;
import organisms.g2.data.MoveInput;

public class ReproductionBehavior extends BehaviorBase {
	private double energyToReporduce = .85;
	private int gestationPeriod = 10;
	
	public ReproductionBehavior(PlayerBase player) {
		super(player);
	}
	@Override
	public Move move(MoveInput input) {
		if(shouldReproduce(input)){
			return reproduceTowardsFood(input);
		}
		return null;
	}
	
	protected boolean shouldReproduce(MoveInput input){
		if(nStepsHavePassedSinceWeLastHadChild(gestationPeriod)){
			return false;
		}
		if (getPlayer().nOutOfMTimes(1, 3)){
			return false;
		}
		if(weHaveReproductionEnergyAndArentCrowded(input)){
			return true;
		}
		return false;
	}
	
	public Boolean nStepsHavePassedSinceWeLastHadChild(int steps) {
		return getStepsSinceWeHadLastChild() > steps;
	}
	
	protected int getStepsSinceWeHadLastChild() {
		return getPlayer().memory.getAge() - getPlayer().memory.getAgeAtWhichWeHadLastChild();
	}

	protected boolean weHaveReproductionEnergyAndArentCrowded(MoveInput input) {
		return weHaveEnoughEnergyToReproduce(input) && getPlayer().weHaveFewNeighbors(input);
	}

	protected boolean weHaveEnoughEnergyToReproduce(MoveInput input) {
		return input.getEnergyLeft() > getPlayer().factorOfMaximumEnergyPerOrganism(energyToReporduce);
	}
	
	protected Move reproduceTowardsFood(MoveInput input) {
		for(int i = 1, size = input.getFoodPresent().length; i < size; i ++) {
			if(!shouldMoveToLocation(i, input)) continue;
			return reproductionMove(i);
		}
		
		
		return randomReproduce(input);
	}
	
	protected boolean shouldMoveToLocation(int i, MoveInput input) {
		return input.isFoodPresentAt(i) && !input.isNeighborAt(i);
	}

	protected int giveMessageToChild(){
		return 0;
	}
	
	protected Move randomReproduce(MoveInput input) {
		int direction = PlayerUtil.getRandomCardinalDirection(getPlayer().getRand());
		while (!shouldMoveToLocation(direction, input)){
			direction = PlayerUtil.getRandomCardinalDirection(getPlayer().getRand());
		}
		return reproductionMove(direction);
	}
	
	protected Move reproductionMove(int direction) {
		return getMoveFactory().reproductionMove(direction, giveMessageToChild());
	}
}
