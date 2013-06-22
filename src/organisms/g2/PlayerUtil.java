package organisms.g2;

import java.util.Random;
import organisms.Constants;
import organisms.g2.data.MoveInput;

public class PlayerUtil implements Constants {
	public static int[] getCardinalDirections() {
		final int[] directions = new int[]{WEST, EAST, NORTH, SOUTH};
		return directions;
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
}
