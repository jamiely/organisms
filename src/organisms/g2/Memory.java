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
	
	private Integer countOfConsecutiveStayPutMoves;
	
	
	private Integer spacesSeen = 0;
	private Integer foodSeen = 0;
	private Integer neighborsSeen = 0;

	public Memory(){
		location = new Point(0, 0);
		setFoodLocations(new ArrayList<Point> ());
		
		age = 0;
		ageAtWhichWeHadLastChild = 0;
		offspringCount = 0;
		
		neighbors = new HashMap<Integer, Integer>();
		initiateMap();
		
		
		moves = new Stack<Integer>();
		spacesSeen = 0;
		setCountOfConsecutiveStayPutMoves(0);
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
	
	public void rememberMove(Move move) {
		if(move.type() == STAYPUT) {
			incrementCountOfConsecutiveStayPutMoves();
		} else {
			setCountOfConsecutiveStayPutMoves(0);
			spacesSeen += 4;
		}
		updateLocation(move);
	}
	
	protected void updateLocation(Move move) {
		int direction = move.type();
		moves.add(direction);
		
		if(!PlayerUtil.isCardinalDirection(direction)) return;
		
		location.add(PointUtil.getPointOffsetForDirection(direction));
		// update all locations so that they are relative to where we are now
		for(Point foodLocation: getFoodLocations()) {
			foodLocation.add(PointUtil.getPointOppositeOffsetForDirection(direction));
		}
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
		if(moves.empty()){
			return SOUTH;
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
		foodSeen ++;
		
		Point foodLocation = PointUtil.pointOffsetInDirection(location, i);
				
		if (getFoodLocations().contains(foodLocation)){
    		// remove it so that we can add it to the end, we'll 
			// assume that items on the end are more recent
    		getFoodLocations().remove(foodLocation);
    	} 
    	getFoodLocations().add(foodLocation);
    	
    	// only store the last 10 locations of food
    	while(getFoodLocations().size() > 10) {
    		getFoodLocations().remove(0);
    	}
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
				neighborsSeen++;
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

	public Integer getCountOfConsecutiveStayPutMoves() {
		return countOfConsecutiveStayPutMoves;
	}

	public void setCountOfConsecutiveStayPutMoves(
			Integer countOfConsecutiveStayPutMoves) {
		this.countOfConsecutiveStayPutMoves = countOfConsecutiveStayPutMoves;
	}
	
	public void incrementCountOfConsecutiveStayPutMoves() {
		setCountOfConsecutiveStayPutMoves(getCountOfConsecutiveStayPutMoves()+1);
	}
	
	public Double getBiomassRatio() {
		return (foodSeen + .0) / (spacesSeen + .0);
	}
	
	public Double getPopulationRatio() {
		return (neighborsSeen + .0) / (spacesSeen + .0);
	}
}
