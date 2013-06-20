package organisms.g2;

import java.util.ArrayList;
import java.util.Random;

import organisms.Constants;
import organisms.Move;
import organisms.g2.data.MoveInput;

public class MoveFactory implements Constants {
	private Random rand;
	public MoveFactory() {
		rand = new Random();
	}
	public Move stayPutMove() {
		return new Move(STAYPUT);
	}
	public Move randomMoveAwayFromNeighbors(MoveInput input) {
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
	public Move randomMoveWithoutStayingPut() {
		return randomMove(false);
	}
	public Move randomMove() {
		return randomMove(true);
	}
	public Move randomReproduce() {
		return randomReproduce(rand);
	}
	public Move randomReproduce(Random rand) {
		return new Move(REPRODUCE, PlayerUtil.getRandomCardinalDirection(rand), 0);
	}
	public Move randomMoveIncludingReproduce() {
		if(rand.nextInt(6) == 5) return randomReproduce();
		return randomMove();
	}
	public Move randomMove(boolean mayStayPut) {
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
}
