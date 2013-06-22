package organisms.g2;

import java.util.ArrayList;
import java.util.Random;
import organisms.Constants;
import organisms.g2.data.MoveInput;

public class PlayerUtil implements Constants {
	public static int[] getCardinalDirections() {
		final int[] directions = new int[]{WEST, EAST, NORTH, SOUTH};
		return directions;
	}
	
	public static Boolean isCardinalDirection(Integer direction) {
		switch(direction) {
		case WEST:
		case EAST:
		case SOUTH:
		case NORTH:
			return true;
		}
		return false;
	}
	
	public static Boolean isValidDirection(int direction) {
		return direction >= STAYPUT && direction <= SOUTH;
	}
	
	public static Integer getRandomCardinalDirection(Random rand) {
		return randomArrayElement(getCardinalDirections(), rand);
	}
	
	public static Integer getRandomCardinalDirectionExcept(Random rand, int exceptDirection) {
		int newDirection;
		do {
			newDirection = PlayerUtil.getRandomCardinalDirection(rand);
		} while (exceptDirection == newDirection);
		return newDirection;
	}
	
	public static Integer randomArrayElement(int[] items, Random rand) {
		return items[rand.nextInt(items.length)];
	}
	
	public static Boolean nOutOfMTimes(int n, int m, Random rand) {
		return n > rand.nextInt(m);
	}
	
	public static int oppositeDirection(int direction) {
		switch (direction) {
		case STAYPUT: return STAYPUT;
		case WEST: return EAST;
		case EAST: return WEST;
		case NORTH: return SOUTH;
		case SOUTH: return NORTH;
		}
		return STAYPUT;
	}
	
	public static Boolean atLeastOneEmptyNeighboringSpace(MoveInput input) {
		Integer[] neighbors = input.getNeighbors();
		for(int i = 1, size = neighbors.length; i < size; i ++) {
			if(input.noNeighborAt(i)) return true;
		}
		return false;
	}
	
	public static Boolean noEmptyNeighboringSpaces(MoveInput input) {
		return !atLeastOneEmptyNeighboringSpace(input);
	}
	
	public static Integer directionForMoveGivenPossibleDirections(
			MoveInput input, ArrayList<Integer> directions) {
		
		// are any of the given directions available?
		for(Integer dir: directions) {
			if(input.noNeighborAt(dir)) return dir;
		}
		
		// otherwise, try to move in a way that isn't counter to 
		Boolean[] moveInDirection = new Boolean[]{false, true, true, true, true};
		for(Integer dir: directions) {
			moveInDirection[dir] = false;
			moveInDirection[PlayerUtil.oppositeDirection(dir)] = false;
		}
		for(int i = 0; i < moveInDirection.length; i++) {
			if(moveInDirection[i]) return i;
		}
		
		return -1;
	}
}
