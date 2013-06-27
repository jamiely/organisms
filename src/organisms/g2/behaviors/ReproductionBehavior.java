package organisms.g2.behaviors;

import organisms.Move;
import organisms.g2.PlayerBase;
import organisms.g2.PlayerUtil;
import organisms.g2.data.ChildEncoder;
import organisms.g2.data.ChildMessage;
import organisms.g2.data.MoveInput;
import organisms.g2.data.Point;
import organisms.g2.data.PointUtil;
import organisms.g2.signals.FoodIsInDirection;
import organisms.g2.signals.SignalMapper;
import organisms.g2.stats.Stats;

public class ReproductionBehavior extends BehaviorBase {
	private double factorOfTotalEnergyToReproduce = .85;
	private Double brethrenCutoff = 0.0;
	//private int gestationPeriod = 10;
	
	
	public ReproductionBehavior(PlayerBase player) {
		super(player);
	}
	
	public ReproductionBehavior(PlayerBase player, double BrethrenCutoff) {
		super(player);
		this.brethrenCutoff = BrethrenCutoff;
	}
	
	@Override
	public Move move(MoveInput input) {
		if(getMemory().getOwnPopulationRatio() < brethrenCutoff) return null;
		if(PlayerUtil.noEmptyNeighboringSpaces(input)) return null;
		//reproducing would block parent in
		if(PlayerUtil.numberOfEmptySpacesAround(input) < 2) return null;
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
		return input.getEnergyLeft() > getPlayer().factorOfMaximumEnergyPerOrganism(factorOfTotalEnergyToReproduce);
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

	protected int getMessageForChild(int directionOfChild){
		Point lastSeenFood = getMemory().getMostRecentlySeenFoodLocation();
		Point foodRelativeToChild = PointUtil.pointOffsetInDirection(lastSeenFood, directionOfChild);
		
		ChildMessage message = new ChildMessage();
		message.setBiomassRatio(getMemory().getBiomassRatio());
		message.setPopulationRatio(getMemory().getPopulationRatio());
		message.setLastSeenFood(foodRelativeToChild);
		
		return new ChildEncoder().encodeChildState(message);
	}
	
	protected Move randomReproduce(MoveInput input) {
		int direction;
		do{
			direction = PlayerUtil.getRandomCardinalDirection(getPlayer().getRand());
		}while (!okToMoveToLocation(direction, input));
		
		return reproductionMove(direction);
	}
	
	protected Move reproductionMove(int direction) {
		ChildMessage message = new ChildMessage();
		message.setBiomassRatio(getMemory().getBiomassRatio());
		message.setPopulationRatio(getMemory().getPopulationRatio());
		message.setLastSeenFood(getMemory().getMostRecentlySeenFoodLocation());
		
		ChildEncoder encoder = new ChildEncoder();
		int encodedMessage = encoder.encodeChildState(message);
		
		return getMoveFactory().reproductionMove(direction, encodedMessage);
	}
}
