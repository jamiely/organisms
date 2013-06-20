package organisms.g2;

import java.util.Random;

import organisms.Constants;
import organisms.Move;

public class PlayerUtil implements Constants {
	public static int[] getCardinalDirections() {
		final int[] directions = new int[]{WEST, EAST, NORTH, SOUTH};
		return directions;
	}
	
	public static Integer getRandomCardinalDirection(Random rand) {
		return randomArrayElement(getCardinalDirections(), rand);
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
}
