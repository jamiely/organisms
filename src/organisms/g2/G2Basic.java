package organisms.g2;

import java.util.*;
import java.io.*;
import java.awt.Color;

import organisms.*;
import organisms.g2.data.MoveInput;

public final class G2Basic extends PlayerBase {
	private static final long serialVersionUID = -3612443241236355755L;
	private Random rand;
	private static Color DEFAULT_COLOR = new Color(1.0f, 0.67f, 0.67f);
	
	/*
	 * This method is called when the Organism is created.
	 * The key is the value that is passed to this organism by its parent (not used here)
	 */
	public void register(OrganismsGame game, int key) throws Exception
	{
		super.register(game, key);
		setName("G2Basic");
		rand = new Random();
		setColor(DEFAULT_COLOR);
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
		
		return randomMoveAwayFromNeighbors(input);
//		return randomMove(false);
	}
	
	protected boolean shouldMoveToLocation(int i, MoveInput input) {
		return input.getFoodPresent()[i] && input.getNeighbors()[i] == -1 && shouldConsume(input.getEnergyLeft(), 0);  
	}
	
	protected boolean shouldReproduce(MoveInput input){
		if(getAge() - getAgeAtWhichWeHadLastChild() > 10 && rand.nextInt(3) >=1 ){
			return false;
		}
		if (rand.nextInt(3) == 2){
			return false;
		}
		if(input.getEnergyLeft() > getGame().M() * 1/2 && neighborCount(input.getNeighbors()) < 1){
			setAgeAtWhichWeHadLastChild(getAge());
			return true;
		}
		return false;
	}
	
	protected boolean shouldConsume(int energyLeft, int foodleft){
		if (energyLeft >  getGame().v() * 5){
			return false;
		}
		if(foodleft < 1){
			return false;
		}
		return true;
	}
	
	protected int neighborCount (Integer[] neighbors){
		int neighbor = 0;
		for(int i = 1, size = neighbors.length; i < size; i++) {
			if(neighbors[i] != -1){
				neighbor ++;
			}
		}
		return neighbor;
	}
	protected Move randomMoveAwayFromNeighbors(MoveInput input) {
		ArrayList<Integer> directions = new ArrayList<Integer>();
		for(int i = 1, size = input.getNeighbors().length; i < size; i++) {
			if(input.getNeighbors()[i] != -1) continue; 
			
			directions.add(i);
		}
		if(directions.size() == 0){
			return new Move(0);
		}
		int index = rand.nextInt(directions.size());
		int direction = directions.get(index);
		return new Move(direction);
	}
	
	protected Move randomMove(boolean mayStayPut) throws Exception {
		Move m = null; // placeholder for return value
		int direction = rand.nextInt(5);
		
		if(!mayStayPut) {
			direction = rand.nextInt(4) + 1;
		}
		
		//if(!mayStayPut && direction == STAYPUT) return randomMove(mayStayPut);
		
		// this player selects randomly
		switch (direction) {
		case 0: m = new Move(STAYPUT); break;
		case 1: m = new Move(WEST); break;
		case 2: m = new Move(EAST); break;
		case 3: m = new Move(NORTH); break;
		case 4: m = new Move(SOUTH); break;
		//case 5:	return randomReproduce();
		}
		return m;
	}
	
	protected Move reproduceTowardsFood(MoveInput input) {
		for(int i = 1, size = input.getFoodPresent().length; i < size; i ++) {
			if(!shouldMoveToLocation(i, input)) continue;
			setOffspringCount(getOffspringCount() + 1);
			return new Move(REPRODUCE, i, getState());
		}
		
		return randomReproduce();
	}

	protected Move randomReproduce() {
		Move m = null; // placeholder for return value
		int direction = rand.nextInt(4);
		setOffspringCount(getOffspringCount() + 1);
		// if this organism will reproduce:
		// the second argument to the constructor is the direction to which the offspring should be born
		// the third argument is the initial value for that organism's state variable (passed to its register function)
		if (direction == 0) m = new Move(REPRODUCE, WEST, getState());
		else if (direction == 1) m = new Move(REPRODUCE, EAST, getState());
		else if (direction == 2) m = new Move(REPRODUCE, NORTH, getState());
		else m = new Move(REPRODUCE, SOUTH, getState());
		return m;
	}



}
