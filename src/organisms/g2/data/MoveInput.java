package organisms.g2.data;

public class MoveInput {
	private Boolean[] foodPresent;
	private Integer[] neighbors;
	private Integer foodLeft;
	private Integer energyLeft;
	public Integer[] getNeighbors() {
		return neighbors;
	}
	public void setNeighbors(Integer[] neighbors) {
		this.neighbors = neighbors;
	}
	public Boolean[] getFoodPresent() {
		return foodPresent;
	}
	public void setFoodPresent(Boolean[] foodPresent) {
		this.foodPresent = foodPresent;
	}
	public Integer getFoodLeft() {
		return foodLeft;
	}
	public void setFoodLeft(Integer foodLeft) {
		this.foodLeft = foodLeft;
	}
	public Integer getEnergyLeft() {
		return energyLeft;
	}
	public void setEnergyLeft(Integer energyLeft) {
		this.energyLeft = energyLeft;
	}
	
	public static MoveInput createMoveInput(boolean[] foodpresent, 
			int[] neighbors, int foodleft, int energyleft) {
		MoveInput input = new MoveInput();
		// Box array
		Boolean[] foodPresent = new Boolean[foodpresent.length];
		for(int i = 0, count = foodpresent.length; i < count; i++) {
			foodPresent[i] = foodpresent[i];
		}
		input.setFoodPresent(foodPresent);
		
		Integer[] neighborsBoxed = new Integer[neighbors.length];
		for(int i = 0, count = neighbors.length; i < count; i++) {
			neighborsBoxed[i] = neighbors[i];
		}
		input.setNeighbors(neighborsBoxed);
		
		input.setFoodLeft(foodleft);
		input.setEnergyLeft(energyleft);
		
		return input;
	}
}
