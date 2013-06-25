package organisms.g2.behaviors;

import java.util.ArrayList;

import organisms.Move;
import organisms.g2.MoveProvider;
import organisms.g2.data.MoveInput;

public class BehaviorCoordinator implements MoveProvider {
	private ArrayList<BehaviorBase> behaviors;

	public BehaviorCoordinator(){
		setBehaviors(new ArrayList<BehaviorBase>());
	}
	
	public BehaviorBase addBehavior(BehaviorBase behavior) {
		getBehaviors().add(behavior);
		return behavior;
	}
	
	public ArrayList<BehaviorBase> getBehaviors() {
		return behaviors;
	}

	protected void setBehaviors(ArrayList<BehaviorBase> behaviors) {
		this.behaviors = behaviors;
	}

	@Override
	public Move move(MoveInput input) {
		for(BehaviorBase behavior: getBehaviors()) {
			Move move = behavior.move(input);
			if(move != null) {
				//System.out.println("Using move from behavior: " + behavior);
				return move;
			}
		}
		return null;
	}	
}
