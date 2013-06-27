package organisms.g2.behaviors;

import organisms.Move;
import organisms.g2.PlayerBase;
import organisms.g2.PlayerUtil;
import organisms.g2.data.MoveInput;

public class CompetitionReproduceBehavior extends ReproductionBehavior {
	
	private Integer preferredReproduceDirection;
	
	public CompetitionReproduceBehavior(PlayerBase player, int preferredReproduceDirection) {
		super(player);
		setPreferredReproduceDirection(preferredReproduceDirection);
	}
	
	@Override
	public Move move(MoveInput input) {
		if(PlayerUtil.noEmptyNeighboringSpaces(input)) return null;
		//reproducing would block parent in
		//if(PlayerUtil.numberOfEmptySpacesAround(input) < 2) return null;
		if(!shouldReproduce(input)) return null;
		
		Move move = reproduceTowardsFood(input);
		if(move != null) return move;
		
		if(input.noNeighborAt(getPreferredReproduceDirection())) 
			return reproductionMove(getPreferredReproduceDirection());
		
		return randomReproduce(input);
	}

	protected boolean shouldReproduce(MoveInput input){
//		if (getPlayer().getAge() > 1 && 
//				getPlayer().nOutOfMTimes(1, 3)){
//			return false;
//		}
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
		return input.getEnergyLeft() > getPlayer().getMaximumEnergyPerOrganismM() * 0.75;// &&
//				input.getEnergyLeft() > getPlayer().getEnergyConsumedByMovingOrReproducingV() * 2;
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

	public Integer getPreferredReproduceDirection() {
		return preferredReproduceDirection;
	}

	public void setPreferredReproduceDirection(
			Integer preferredReproduceDirection) {
		this.preferredReproduceDirection = preferredReproduceDirection;
	}
}
