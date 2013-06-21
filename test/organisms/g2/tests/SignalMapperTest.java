package organisms.g2.tests;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import organisms.Constants;
import organisms.g2.DirectionProvider;
import organisms.g2.signals.FoodIsInDirection;
import organisms.g2.signals.SignalBase;
import organisms.g2.signals.SignalMapper;


public class SignalMapperTest implements Constants {

	SignalMapper mapper;
	
	@Before
	public void setup() {
		mapper = new SignalMapper();
	}
	
	protected int getTestState(int direction) {
		DirectionProvider provider = new FoodIsInDirection(direction);
		return mapper.getStateForSignalDirectionProvider(provider);
	}
	
	@Test
	public void testStatesMatchWest() {
		testStatesMatch(WEST);
	}
	
	@Test
	public void testStatesMatchSOUTH() {
		testStatesMatch(SOUTH);
	}
	
	public void testStatesMatch(int direction) {
		int state = getTestState(direction);
		
		assertEquals("States match", 1 + (direction << 3), state);
		assertEquals("Direction is correct", direction, (int)mapper.directionForDirectionProviderState(state));
	}
	
	@Test
	public void testDirectionExtractionSouth() {
		assertEquals("Direction is correct", SOUTH, (int)mapper.directionForDirectionProviderState(getTestState(SOUTH)));
	}
	
	@Test
	public void testDirectionExtractionWest() {
		assertEquals("Direction is correct", WEST, (int)mapper.directionForDirectionProviderState(getTestState(WEST)));
	}
	
	@Test
	public void testReconstructSignalFromState() {
		SignalBase signal = mapper.getSignalForState(getTestState(SOUTH));
		assertEquals("Signal has correct class", FoodIsInDirection.class, signal.getClass());
		assertEquals("Signal has correct direction", SOUTH, (int)((FoodIsInDirection) signal).getDirection());
	}

}
