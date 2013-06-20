package organisms.g2;

import java.awt.Color;

import organisms.*;
import organisms.g2.data.MoveInput;
import organisms.g2.stats.Stats;

public final class G2Basic extends PlayerBase {
	private static final long serialVersionUID = -3612443241236355755L;
	
	/*
	 * This method is called when the Organism is created.
	 * The key is the value that is passed to this organism by its parent (not used here)
	 */
	public void register(OrganismsGame game, int key) throws Exception
	{
		super.register(game, key);
		setName("G2Basic");
		setColor(Color.PINK);
	}

	/*
	 * This is called by the simulator to determine how this Organism should move.
	 * foodpresent is a four-element array that indicates whether any food is in adjacent squares
	 * neighbors is a four-element array that holds the externalState for any organism in an adjacent square
	 * foodleft is how much food is left on the current square
	 * energyleft is this organism's remaining energy
	 */
	public Move move(MoveInput input) {

		//TODO this is a hack to avoid moving to a neighbor
		
		setAge(getAge() + 1);
		
		if(shouldReproduce(input)) {
			return reproduceTowardsFood(input);
		}	
		
		if(input.getFoodLeft() > 0 && shouldConsume(input.getEnergyLeft(), input.getFoodLeft())) {
			return new Move(STAYPUT);
		}
		
		// redundant for staying put
		for(int i = 1, size = input.getFoodPresent().length; i < size; i ++) {
			if(!shouldMoveToLocation(i, input)) continue;
	
			return new Move(i);
		}
		
		return getMoveFactory().randomMoveAwayFromNeighbors(input);
	}
	
	protected boolean shouldMoveToLocation(int i, MoveInput input) {
		return false;
	}
	
	protected boolean shouldReproduce(MoveInput input){
		if(getStepsSinceWeHadLastChild() > 10 && getRand().nextInt(3) >=1 ){
			return false;
		}
		if (nOutOfMTimes(1, 3)){
			return false;
		}
		if(input.getEnergyLeft() > getGame().M() * 1/2 && Stats.neighborCount(input.getNeighbors()) < 1){
			setAgeAtWhichWeHadLastChild(getAge());
			return true;
		}
		return false;
	}
	

	protected boolean shouldConsume(int energyLeft, int foodleft){
		if (hasAlotOfEnergy(energyLeft)){
			return false;
		}
		if(foodleft < 1){
			return false;
		}
		return true;
	}

	protected boolean hasAlotOfEnergy(int energyLeft) {
		return energyLeft >  getGame().v() * 5;
	}
	
	protected Move reproduceTowardsFood(MoveInput input) {
		for(int i = 1, size = input.getFoodPresent().length; i < size; i ++) {
			if(!shouldMoveToLocation(i, input)) continue;
			
			return createReproductionMove(i);
		}
		
		return randomReproduce();
	}

}
