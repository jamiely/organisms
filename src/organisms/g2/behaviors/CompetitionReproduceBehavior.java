package organisms.g2.behaviors;

import organisms.Move;
import organisms.g2.PlayerBase;
import organisms.g2.PlayerUtil;
import organisms.g2.data.MoveInput;
import organisms.g2.stats.Stats;

public class CompetitionReproduceBehavior extends ReproductionBehavior {

	public CompetitionReproduceBehavior(PlayerBase player) {
		super(player);
	}
	
	@Override
	public Move move(MoveInput input) {
		if(PlayerUtil.noEmptyNeighboringSpaces(input)) return null;
		if(!shouldReproduce(input)) return null;
		
		Move move = reproduceTowardsFood(input);
		if(move != null) return move;
		
		if(input.noNeighborAt(NORTH)) return reproductionMove(NORTH);
		return randomReproduce(input);
	}

	protected boolean shouldReproduce(MoveInput input){
		if (getPlayer().getAge() > 1 && 
				getPlayer().nOutOfMTimes(1, 3)){
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
		return getMemory().getStepsSinceWeHadLastChild();
	}

	protected boolean weHaveReproductionEnergyAndArentCrowded(MoveInput input) {
		return weHaveEnoughEnergyToReproduce(input) && PlayerUtil.atLeastOneEmptyNeighboringSpace(input);
	}
	
	protected boolean weHaveEnoughEnergyToReproduce(MoveInput input) {
		return input.getEnergyLeft() > getPlayer().getMaximumEnergyPerOrganismM() * 0.5 &&
				input.getEnergyLeft() > getPlayer().getEnergyConsumedByMovingOrReproducingV() * 2;
	}
	
	protected Move reproduceTowardsFood(MoveInput input) {
		for(int i = 1, size = input.getFoodPresent().length; i < size; i ++) {
			if(!shouldMoveToLocation(i, input)) continue;
			return reproductionMove(i);
		}
		
		return null;
	}
	
	protected boolean shouldMoveToLocation(int i, MoveInput input) {
		return input.isFoodPresentAt(i) && !input.isNeighborAt(i);
	}
	
	protected boolean okToMoveToLocation(int i, MoveInput input) {
		return !input.isNeighborAt(i);
	}
}
