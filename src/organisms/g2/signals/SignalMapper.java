package organisms.g2.signals;

import organisms.g2.DirectionProvider;

public class SignalMapper {
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

}
