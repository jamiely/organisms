package organisms.g2.behaviors;

import organisms.Move;
import organisms.g2.PlayerBase;
import organisms.g2.data.MoveInput;

public class HungryBehavior extends BehaviorBase {
	public HungryBehavior(PlayerBase player) {
		super(player);
	}
	@Override
	public Move move(MoveInput input) {
		if(!iAmHungry(input)) return null;
		
		if(input.getFoodLeft() > 0 && shouldConsume(input)) {
			return getMoveFactory().stayPutMove();
		}
		
		for(int i = 1, size = input.getFoodPresent().length; i < size; i ++) {
			if(!shouldMoveToLocation(i, input)) continue;
	
			return new Move(i);
		}
		
		return null;
	}
	
	protected Boolean iAmHungry(MoveInput input) {
		return !hasAlotOfEnergy(input);
	}
	
	protected boolean hasAlotOfEnergy(MoveInput input) {
		return input.getEnergyLeft() > getPlayer().getEnergyConsumedByMovingOrReproducingV() * 2;
	}
	
	protected boolean shouldConsume(MoveInput input){
		if(input.getFoodLeft() < 1){
			return false;
		}
		return true;
	}
	protected boolean shouldMoveToLocation(int i, MoveInput input) {
		return input.isFoodPresentAt(i) && !input.isNeighborAt(i) && shouldConsume(input);
	}
}
