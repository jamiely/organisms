package organisms.g2.memory;

public class LocationNode {
	private Boolean hasBeenExplored = false;
	private LocationNode northNode;
	private LocationNode southNode;
	private LocationNode eastNode;
	private LocationNode westNode;
	
	private Boolean hasFood = false;
	private Integer foodCount = 0;
	
	// the age at which this node was last visited
	private Integer visitAge = 0;
	
	// the age at which this was last observed (not visited)
	private Integer observeAge = 0;

	public Boolean getHasBeenExplored() {
		return hasBeenExplored;
	}

	public void setHasBeenExplored(Boolean hasBeenExplored) {
		this.hasBeenExplored = hasBeenExplored;
	}

	public LocationNode getSouthNode() {
		return southNode;
	}

	public void setSouthNode(LocationNode southNode) {
		this.southNode = southNode;
	}

	public LocationNode getNorthNode() {
		return northNode;
	}

	public void setNorthNode(LocationNode northNode) {
		this.northNode = northNode;
	}

	public LocationNode getEastNode() {
		return eastNode;
	}

	public void setEastNode(LocationNode eastNode) {
		this.eastNode = eastNode;
	}

	public LocationNode getWestNode() {
		return westNode;
	}

	public void setWestNode(LocationNode westNode) {
		this.westNode = westNode;
	}

	public Boolean getHasFood() {
		return hasFood;
	}

	public void setHasFood(Boolean hasFood) {
		this.hasFood = hasFood;
	}

	public Integer getFoodCount() {
		return foodCount;
	}

	public void setFoodCount(Integer foodCount) {
		this.foodCount = foodCount;
	}

	public Integer getVisitAge() {
		return visitAge;
	}

	public void setVisitAge(Integer visitAge) {
		this.visitAge = visitAge;
	}

	public Integer getObserveAge() {
		return observeAge;
	}

	public void setObserveAge(Integer observeAge) {
		this.observeAge = observeAge;
	}
}
