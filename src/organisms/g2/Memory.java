/**
 * 
 */
package organisms.g2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import organisms.Move;
import organisms.Constants;

/**
 * @author Anne
 *
 */
public class Memory implements Constants {
	private int[] location;
	private ArrayList<int[]> foodLocations;
	
	private int age;
	private Integer ageAtWhichWeHadLastChild;
	private Integer offspringCount;
	
	private HashMap<Integer, Integer> neighbors;
	private Stack<Integer> moves;

	public Memory(){
		location = new int[] {0,0};
		foodLocations = new ArrayList<int[]> ();
		
		age = 0;
		ageAtWhichWeHadLastChild = 0;
		offspringCount = 0;
		
		neighbors = new HashMap<Integer, Integer>();
		initiateMap();
		
		
		moves = new Stack<Integer>();
	}
	
	private void initiateMap(){
		neighbors.put(1,0);
		neighbors.put(2,0);
		neighbors.put(3,0);
		neighbors.put(4,0);
	}
	
	public void increaseAge(){
		age++;
		return;
	}
	
	public int getAge(){
		return age;
	}
	
	public void updateLocation(Move move) {
		moves.add(move.type());
		switch(move.type())
	    {
		    case STAYPUT:
			    return;
		    case WEST:
		    	location[1] -=1;
			    return;
		    case EAST:
		    	location[1] +=1;
		    	return;
		    case NORTH:
		    	location[0] -=1;
		    	return;
		    case SOUTH:
		    	location[0] +=1;
		    	return;
	    }
	    return;
	}
	
	public int getLastDirection(){
		return moves.peek();
	}
	
	public void rememberFood(boolean[] foodpresent){
		for(int i = 1, size = foodpresent.length; i < size; i++) {
			if(foodpresent[i]){
				addFood(i);
			}
		}
	}
	
	public void addFood(int i){
		int[] foodLocation = location;
		switch(i)
		 {
		    case STAYPUT:
		    	if (!foodLocations.contains(foodLocation)){
		    		foodLocations.add(foodLocation);
		    	}
			    return;
		    case WEST:
		    	foodLocation[1] -=1;
		    	if (!foodLocations.contains(foodLocation)){
		    		foodLocations.add(foodLocation);
		    	}
			    return;
		    case EAST:
		    	foodLocation[1] +=1;
		    	if (!foodLocations.contains(foodLocation)){
		    		foodLocations.add(foodLocation);
		    	}
		    	return;
		    case NORTH:
		    	foodLocation[0] -=1;
		    	if (!foodLocations.contains(foodLocation)){
		    		foodLocations.add(foodLocation);
		    	}
		    	return;
		    case SOUTH:
		    	foodLocation[0] +=1;
		    	if (!foodLocations.contains(foodLocation)){
		    		foodLocations.add(foodLocation);
		    	}
		    	return;
	    }
	}
	
	
	public void addNeighbor(int i){
		switch(i)
		 {
		    case WEST:
		    	neighbors.put(WEST, neighbors.get(WEST)+1);
			    return;
		    case EAST:
		    	neighbors.put(EAST, neighbors.get(EAST)+1);
			    return;
		    case NORTH:
		    	neighbors.put(NORTH, neighbors.get(NORTH)+1);
			    return;
		    case SOUTH:
		    	neighbors.put(SOUTH, neighbors.get(SOUTH)+1);
			    return;
	    }
	}
	
	public void rememberNeighbors(int[] neighbors){
		for(int i = 1, size = neighbors.length; i < size; i++) {
			if(neighbors[i] != -1){
				addNeighbor(i);
			}
		}
	}
	
	public int getNeighborCount(Move move){
		switch(move.type())
		 {
		    case WEST:
		    	return neighbors.get(WEST);
		    case EAST:
		    	return neighbors.get(EAST);
		    case NORTH:
		    	return neighbors.get(NORTH);
		    case SOUTH:
		    	return neighbors.get(SOUTH);
	    }
		return 0;
	}
	
	public Move getClosetFood(){
		//TODO
		return null;
	}

	/**
	 * @param age2
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return
	 */
	public Integer getOffspringCount() {
		return offspringCount;
	}

	/**
	 * @param offspringCount2
	 */
	public void setOffspringCount(Integer offspringCount) {
		this.offspringCount = offspringCount;
		
	}

	/**
	 * @return
	 */
	public Integer getAgeAtWhichWeHadLastChild() {
		return ageAtWhichWeHadLastChild;
	}

	/**
	 * @param ageAtWhichWeHadLastChild2
	 */
	public void setAgeAtWhichWeHadLastChild(Integer ageAtWhichWeHadLastChild) {
		this.ageAtWhichWeHadLastChild = ageAtWhichWeHadLastChild;
	}

}
