package organisms.g2.behaviors;

import java.util.ArrayList;

import organisms.Move;
import organisms.g2.PlayerBase;
import organisms.g2.PlayerUtil;
import organisms.g2.data.MoveInput;
import organisms.g2.stats.Stats;

public class CompetionExploreRiskBehavior extends ExploreBehavior {
	
	private Integer preferredExplorationDirection;
	private Double cutoff;
	
	public CompetionExploreRiskBehavior(PlayerBase player, Integer preferredExplorationDirection) {
		super(player);
		setPreferredExplorationDirection(preferredExplorationDirection);
	}

	public CompetionExploreRiskBehavior(PlayerBase player, Integer preferredExplorationDirection, Double cutoff) {
		super(player);
		setPreferredExplorationDirection(preferredExplorationDirection);
		this.cutoff = cutoff;
	}

	@Override
	public Move move(MoveInput input) {
		if(getMemory().getBiomassRatio() < cutoff) return null;
		Integer foodDir = foodDirection(input);
		if(foodDir != null) return new Move(foodDir);
		
		if(weHaveStayedTooMuch())  {
			if(preferredDirectionIsFree(input)) return getMoveInPreferredDirection();
			
			Integer direction = randomDirectionOfEmptySpace(input);
			if(direction != null) return new Move(direction);
		}
		if(itIsBetterToStayPut(input)) return getMoveFactory().stayPutMove();
		if(PlayerUtil.noEmptyNeighboringSpaces(input)) return getMoveFactory().stayPutMove();
		
		if(getPlayer().nOutOfMTimes(2, 5)) return getMoveFactory().stayPutMove();
		
		if(preferredDirectionIsFree(input)) return getMoveInPreferredDirection();
		Integer direction = randomDirectionOfEmptySpace(input);
		if(direction != null) return new Move(direction);
		
		return null;
	}

	protected Move getMoveInPreferredDirection() {
		return new Move(getPreferredExplorationDirection());
	}

	protected Boolean preferredDirectionIsFree(MoveInput input) {
		return input.noNeighborAt(getPreferredExplorationDirection());
	}
	
	protected Integer foodDirection(MoveInput input) {
		if(input.isFoodPresentAt(NORTH)) return NORTH;
		for(Integer dir: PlayerUtil.getCardinalDirections()) {
			if(input.isFoodPresentAt(dir)) return dir;
		}
		return null;
	}
	
	protected Integer randomDirectionOfEmptySpace(MoveInput input) {
		ArrayList<Integer> directions = PlayerUtil.getDirectionsOfEmptySpaces(input);
		return PlayerUtil.randomItem(directions, getPlayer().getRand());
	}
	
	protected Boolean weHaveStayedTooMuch() {
		return getMemory().getCountOfConsecutiveStayPutMoves() > 5;
	}
	
	protected Boolean itIsBetterToStayPut(MoveInput input) {
		return input.getEnergyLeft() < getPlayer().getEnergyConsumedByMovingOrReproducingV() + 1 ||
				Stats.neighborCount(input.getNeighbors()) > 2;
	}

	public Integer getPreferredExplorationDirection() {
		return preferredExplorationDirection;
	}

	public void setPreferredExplorationDirection(
			Integer preferredExplorationDirection) {
		this.preferredExplorationDirection = preferredExplorationDirection;
	}
}
