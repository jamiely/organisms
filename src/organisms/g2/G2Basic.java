package organisms.g2;

import java.util.*;
import java.io.*;
import java.awt.Color;

import organisms.*;

public final class G2Basic implements Player {

	static final String _CNAME = "G2Basic";
	static final Color _CColor = new Color(1.0f, 0.67f, 0.67f);
	private int state;
	private Random rand;
	private OrganismsGame game;

	private int offspring;
	private int age;
	private int lastChild;

	/*
	 * This method is called when the Organism is created.
	 * The key is the value that is passed to this organism by its parent (not used here)
	 */
	public void register(OrganismsGame game, int key) throws Exception
	{
		rand = new Random();
		state = rand.nextInt(256);
		this.game = game;
		offspring = 0;
		age = 0;
		lastChild = 0;
	}

	/*
	 * Return the name to be displayed in the simulator.
	 */
	public String name() throws Exception {
		return _CNAME;
	}

	/*
	 * Return the color to be displayed in the simulator.
	 */
	public Color color() throws Exception {
		return _CColor;
	}

	/*
	 * Not, uh, really sure what this is...
	 */
	public boolean interactive() throws Exception {
		return false;
	}

	/*
	 * This is the state to be displayed to other nearby organisms
	 */
	public int externalState() throws Exception {
		return state;
	}

	/*
	 * This is called by the simulator to determine how this Organism should move.
	 * foodpresent is a four-element array that indicates whether any food is in adjacent squares
	 * neighbors is a four-element array that holds the externalState for any organism in an adjacent square
	 * foodleft is how much food is left on the current square
	 * energyleft is this organism's remaining energy
	 */
	public Move move(boolean[] foodpresent, int[] neighbors, int foodleft, int energyleft) throws Exception {

		//TODO this is a hack to avoid moving to a neighbor
		
		age++;
		
		if(shouldReproduce(energyleft, neighbors)) {
			return reproduceTowardsFood(foodpresent, neighbors, energyleft);
		}	
		
		if(foodleft > 0 && shouldConsume(energyleft, foodleft)) {
			return new Move(STAYPUT);
		}
		
		// redundant for staying put
		for(int i = 1, size = foodpresent.length; i < size; i ++) {
			if(!shouldMoveToLocation(i, foodpresent, neighbors, energyleft)) continue;
	
			return new Move(i);
		}
		
		return randomMoveAwayFromNeighbors(neighbors);
//		return randomMove(false);
	}
	
	protected boolean shouldMoveToLocation(int i, boolean[] foodpresent, int[] neighbors, int energyleft) {
		return foodpresent[i] && neighbors[i] == -1 && shouldConsume(energyleft, 0);  
	}
	
	protected boolean shouldReproduce(int energyleft, int[] neighbors){
		if(age - lastChild > 10 && rand.nextInt(3) >=1 ){
			return false;
		}
		if (rand.nextInt(3) == 2){
			return false;
		}
		if(energyleft > game.M() * 1/2 && neighborCount(neighbors) < 1){
			lastChild = age;
			return true;
		}
		return false;
	}
	
	protected boolean shouldConsume(int energyLeft, int foodleft){
		if (energyLeft >  game.v() * 5){
			return false;
		}
		if(foodleft < 1){
			return false;
		}
		return true;
	}
	
	protected int neighborCount (int[] neighbors){
		int neighbor = 0;
		for(int i = 1, size = neighbors.length; i < size; i++) {
			if(neighbors[i] != -1){
				neighbor ++;
			}
		}
		return neighbor;
	}
	protected Move randomMoveAwayFromNeighbors(int[] neighbors) throws Exception {
		ArrayList<Integer> directions = new ArrayList<Integer>();
		for(int i = 1, size = neighbors.length; i < size; i++) {
			if(neighbors[i] != -1) continue; 
			
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
	
	protected Move reproduceTowardsFood(boolean[] foodpresent, int[] neighbors, int energyleft) throws Exception {
		for(int i = 1, size = foodpresent.length; i < size; i ++) {
			if(!shouldMoveToLocation(i, foodpresent, neighbors, energyleft)) continue;
			offspring ++;
			return new Move(REPRODUCE, i, state);
		}
		
		return randomReproduce();
	}

	protected Move randomReproduce() throws Exception {
		Move m = null; // placeholder for return value
		int direction = rand.nextInt(4);
		offspring ++;
		// if this organism will reproduce:
		// the second argument to the constructor is the direction to which the offspring should be born
		// the third argument is the initial value for that organism's state variable (passed to its register function)
		if (direction == 0) m = new Move(REPRODUCE, WEST, state);
		else if (direction == 1) m = new Move(REPRODUCE, EAST, state);
		else if (direction == 2) m = new Move(REPRODUCE, NORTH, state);
		else m = new Move(REPRODUCE, SOUTH, state);
		return m;
	}
}
