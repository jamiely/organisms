package organisms.g2.behaviors;

import organisms.Move;
import organisms.g2.PlayerBase;
import organisms.g2.PlayerUtil;
import organisms.g2.data.MoveInput;
import organisms.g2.signals.FoodIsInDirection;
import organisms.g2.signals.SignalMapper;
import organisms.g2.stats.Stats;

public class ReproductionBehavior extends BehaviorBase {
	private double energyToReporduce = .85;
	//private int gestationPeriod = 10;
	
	public ReproductionBehavior(PlayerBase player) {
		super(player);
	}
	@Override
	public Move move(MoveInput input) {
		if(PlayerUtil.noEmptyNeighboringSpaces(input)) return null;
		if(!shouldReproduce(input)) return null;
		
		return reproduceTowardsFood(input);
	}

	protected boolean shouldReproduce(MoveInput input){
//		if(nStepsHavePassedSinceWeLastHadChild(gestationPeriod)){
//			return false;
//		}
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
		return getMemory().getStepsSinceWeHadLastChild();
	}

	protected boolean weHaveReproductionEnergyAndArentCrowded(MoveInput input) {
		return weHaveEnoughEnergyToReproduce(input) && weHaveFewNeighbors(input);
	}
	
	public boolean weHaveFewNeighbors(MoveInput input) {
		return Stats.neighborCount(input.getNeighbors()) < 1;
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
	
	protected boolean okToMoveToLocation(int i, MoveInput input) {
		return !input.isNeighborAt(i);
	}
	
	protected int getFoodDirection() {
		// TODO using memory
		return 0;
	}
	
	protected FoodIsInDirection getFoodDirectionSignal() {
		return new FoodIsInDirection(getFoodDirection());
	}

	protected int getMessageForChild(){
		return SignalMapper.getInstance().getStateForSignal(getFoodDirectionSignal());
	}
	
	protected Move randomReproduce(MoveInput input) {
		int direction;
		do{
			direction = PlayerUtil.getRandomCardinalDirection(getPlayer().getRand());
		}while (!okToMoveToLocation(direction, input));
		
		return reproductionMove(direction);
	}
	
	protected Move reproductionMove(int direction) {
		return getMoveFactory().reproductionMove(direction, getMessageForChild());
	}
}
