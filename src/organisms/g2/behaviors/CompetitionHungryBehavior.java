package organisms.g2.behaviors;


import java.util.ArrayList;

import organisms.Move;
import organisms.g2.PlayerBase;
import organisms.g2.PlayerUtil;
import organisms.g2.data.MoveInput;

public class CompetitionHungryBehavior extends HungryBehavior {

	public CompetitionHungryBehavior(PlayerBase player) {
		super(player);
	}
	
	@Override
	public Move move(MoveInput input) {
		if(!iAmHungry(input)) return null;
		
		if(input.getFoodLeft() > 0) {
			return getMoveFactory().stayPutMove();
		}
		
		for(int i = 1, size = input.getFoodPresent().length; i < size; i ++) {
			if(!shouldMoveToLocation(i, input)) continue;
	
			return new Move(i);
		}
		
		Move move = getMoveTowardsMostRecentlySeenFood(input);
		if(move != null) return move;
		
		if(weHaveStayedTooMuch()) {
			Integer direction = randomDirectionOfEmptySpace(input);
			if(direction != null) return new Move(direction);  
		}
		
		return null;
	}
	
	protected Integer randomDirectionOfEmptySpace(MoveInput input) {
		ArrayList<Integer> directions = PlayerUtil.getDirectionsOfEmptySpaces(input);
		return PlayerUtil.randomItem(directions, getPlayer().getRand());
	}
	
	protected Boolean weHaveStayedTooMuch() {
		return getMemory().getCountOfConsecutiveStayPutMoves() > 3;
	}
	
	protected Boolean iAmHungry(MoveInput input) {
		return input.getEnergyLeft() < getPlayer().getMaximumEnergyPerOrganismM() * 0.75;
	}
	
	protected boolean shouldMoveToLocation(int i, MoveInput input) {
		return input.isFoodPresentAt(i) && !input.isNeighborAt(i);
	}

}
