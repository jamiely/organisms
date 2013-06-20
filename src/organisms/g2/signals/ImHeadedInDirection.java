package organisms.g2.signals;

import organisms.g2.DirectionProvider;

public class ImHeadedInDirection extends SignalBase implements DirectionProvider {
	private Integer direction;
	public ImHeadedInDirection(Integer direction) {
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
