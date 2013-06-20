package organisms.g2.signals;

import organisms.g2.DirectionProvider;

public class FoodIsInDirection extends SignalBase implements DirectionProvider {
	private Integer direction;
	public FoodIsInDirection(Integer direction) {
		setDirection(direction);
	}

	@Override
	public Integer getDirection() {
		return this.direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

}
