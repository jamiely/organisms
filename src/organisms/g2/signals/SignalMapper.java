package organisms.g2.signals;

import organisms.g2.DirectionProvider;
import organisms.g2.PlayerUtil;

public class SignalMapper {
	
	private static SignalMapper instance = null; 
	
	/**
	 * Returns a signal corresponding to the passed state
	 * @param state
	 * @return
	 */
	public SignalBase getSignalForState(Integer state) {
		Integer signalId = idForSignalFromState(state);
		if(isSignalIdForDirectionProvider(signalId)) {
			return (SignalBase)getSignalDirectionProviderForState(state, signalId);
		}
		return null;
	}
	
	protected DirectionProvider getSignalDirectionProviderForState(Integer state, Integer signalId) {
		int direction = directionForDirectionProviderState(state);
		if(!PlayerUtil.isValidDirection(direction)) return null;
		
		switch(signalId) {
			case 1: return new FoodIsInDirection(direction);
			case 2: return new ImHeadedInDirection(direction);
			case 3: return new OrganismsInDirection(direction);
		}
		return null;
	}
	
	protected Boolean isSignalIdForDirectionProvider(Integer id) {
		return id >= 1 && id <= 3;
	}
	
	public Integer getStateForSignal(SignalBase base) {
		if(base instanceof DirectionProvider) {
			return getStateForSignalDirectionProvider((DirectionProvider) base);
		}
		return 0;
	}
	
	public Integer getStateForSignalDirectionProvider(DirectionProvider provider) {
		Integer signalId = idForDirectionProvider(provider);
		Integer direction = provider.getDirection();
		
		return signalId + (direction << 3);
	}
	
	public Integer directionForDirectionProviderState(Integer state) {
		return (state >> 3) & 7;
	}
	
	public Integer idForSignalFromState(Integer state) {
		return state & 3;
	}
	
	protected Integer idForDirectionProvider(DirectionProvider provider) {
		if(isOfClass(provider, FoodIsInDirection.class)) {
			return 1;
		} else if(isOfClass(provider, ImHeadedInDirection.class)) {
			return 2;
		} else if(isOfClass(provider, OrganismsInDirection.class)) {
			return 3;
		} 
		return 0;
	}
	
	@SuppressWarnings("rawtypes")
	protected Boolean isOfClass(Object instance, Class cls) {
		return instance.getClass().isAssignableFrom(cls);
	}

	public static SignalMapper getInstance() {
		if(instance == null) instance = new SignalMapper();
		return instance;
	}

}
