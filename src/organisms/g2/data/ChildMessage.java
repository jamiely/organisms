package organisms.g2.data;

public class ChildMessage {
	private Point lastSeenFood;
	private Double biomassRatio;
	private Double populationRatio;
	public Point getLastSeenFood() {
		return lastSeenFood;
	}
	public void setLastSeenFood(Point lastSeenFood) {
		this.lastSeenFood = lastSeenFood;
	}
	public Double getBiomassRatio() {
		return biomassRatio;
	}
	public void setBiomassRatio(Double biomassRatio) {
		this.biomassRatio = biomassRatio;
	}
	public Double getPopulationRatio() {
		return populationRatio;
	}
	public void setPopulationRatio(Double populationRatio) {
		this.populationRatio = populationRatio;
	}
}
