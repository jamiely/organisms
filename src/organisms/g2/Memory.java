/**
 * 
 */
package organisms.g2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import organisms.Move;
import organisms.Constants;
import organisms.g2.data.MoveInput;
import organisms.g2.data.Point;
import organisms.g2.data.PointUtil;

/**
 * @author Anne
 *
 */
public class Memory implements Constants {
	private Point location;
	private ArrayList<Point> foodLocations;
	
	private int age;
	private Integer ageAtWhichWeHadLastChild;
	private Integer offspringCount;
	
	private HashMap<Integer, Integer> neighbors;
	private Stack<Integer> moves;

	public Memory(){
		location = new Point(0, 0);
		setFoodLocations(new ArrayList<Point> ());
		
		age = 0;
		ageAtWhichWeHadLastChild = 0;
		offspringCount = 0;
		
		neighbors = new HashMap<Integer, Integer>();
		initiateMap();
		
		
		moves = new Stack<Integer>();
	}
	
	private void initiateMap(){
		for(int direction: PlayerUtil.getCardinalDirections()) {
			neighbors.put(direction, 0);
		}
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
		location.add(PointUtil.getPointOffsetForDirection(move.type()));
	}
	
	public int getLastDirection(){
		return moves.peek();
	}
	
	public int getLastNonStayDirection(){
		if(moves.empty()){
			return SOUTH;
		}
		if(moves.peek() != STAYPUT){
			return moves.peek();
		}
		Stack<Integer> history = new Stack<Integer>();
		while(!moves.empty() && moves.peek() == STAYPUT){
			history.push(moves.pop());
		}
		int lastNonStayDirection = moves.peek();
		while(!history.empty()){
			moves.push(history.pop());
		}
		return lastNonStayDirection;
	}
	
	public Boolean hasLastDirection() {
		return !moves.empty();
	}
	
	public void rememberInfo(MoveInput input) {
		rememberNeighbors(input.getNeighbors());
		rememberFood(input.getFoodPresent());
	}
	
	public void rememberFood(Boolean[] foodpresent){
		for(int i = 1, size = foodpresent.length; i < size; i++) {
			if(foodpresent[i]){
				addFood(i);
			} else {
				removeFood(i);
			}
		}
	}
	
	public void removeFood(int i) {
		Point foodLocation = PointUtil.pointOffsetInDirection(location, i);
		getFoodLocations().remove(foodLocation);
	}
	
	public void addFood(int i){
		Point foodLocation = PointUtil.pointOffsetInDirection(location, i);
				
		if (getFoodLocations().contains(foodLocation)){
    		// remove it so that we can add it to the end, we'll 
			// assume that items on the end are more recent
    		getFoodLocations().remove(foodLocation);
    	} 
    	getFoodLocations().add(foodLocation);
	}
	
	
	public void addNeighbor(int i){
		switch(i)
		 {
		    case WEST:
		    case EAST:
		    case NORTH:
		    case SOUTH:
		    	incrementNeighborAtDirection(i);
		    	break;
	    }
	}
	
	protected void incrementNeighborAtDirection(int direction) {
		neighbors.put(direction, neighbors.get(direction)+1);
	}
	
	public void rememberNeighbors(Integer[] neighbors){
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
		    case EAST:
		    case NORTH:
		    case SOUTH:
		    	return neighbors.get(move.type());
	    }
		return 0;
	}
	
	public Point getMostRecentlySeenFoodLocation(){
	 	if(getFoodLocations().isEmpty()) return null;
	 	
	 	return getFoodLocations().get(getFoodLocations().size()-1);
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Integer getOffspringCount() {
		return offspringCount;
	}

	public void setOffspringCount(Integer offspringCount) {
		this.offspringCount = offspringCount;
		
	}
	
	public void increaseOffspringCount() {
		this.offspringCount += 1;
		
	}

	public Integer getAgeAtWhichWeHadLastChild() {
		return ageAtWhichWeHadLastChild;
	}

	public void setAgeAtWhichWeHadLastChild(Integer ageAtWhichWeHadLastChild) {
		this.ageAtWhichWeHadLastChild = ageAtWhichWeHadLastChild;
	}
	
	public Integer getStepsSinceWeHadLastChild() {
		return getAge() - getAgeAtWhichWeHadLastChild();
	}

	public ArrayList<Point> getFoodLocations() {
		return foodLocations;
	}

	public void setFoodLocations(ArrayList<Point> foodLocations) {
		this.foodLocations = foodLocations;
	}
}
