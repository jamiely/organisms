package organisms.g2.behaviors;

import organisms.Move;
import organisms.g2.PlayerBase;
import organisms.g2.PlayerUtil;
import organisms.g2.data.MoveInput;

public class CompetitionReproduceRiskBehavior extends ReproductionBehavior {
	
	private Integer preferredReproduceDirection;
	private Double cutoff;
	private Double brethrenCutoff;
	
	public CompetitionReproduceRiskBehavior(PlayerBase player, int preferredReproduceDirection) {
		super(player);
		setPreferredReproduceDirection(preferredReproduceDirection);
	}
	
	public CompetitionReproduceRiskBehavior(PlayerBase player, int preferredReproduceDirection, double cutoff2, double brethrenCutoff2) {
		super(player);
		setPreferredReproduceDirection(preferredReproduceDirection);
		cutoff = cutoff2;
		brethrenCutoff = brethrenCutoff2;
	}

	@Override
	public Move move(MoveInput input) {
		if(getMemory().getBiomassRatio() < cutoff) return null;
		if(getMemory().getOwnPopulationRatio() > brethrenCutoff) return null;
		if(PlayerUtil.noEmptyNeighboringSpaces(input)) return null;
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
		return input.getEnergyLeft() > getPlayer().getMaximumEnergyPerOrganismM() * 0.5;//  &&
				//nStepsHavePassedSinceWeLastHadChild(10);
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
