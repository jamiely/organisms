package organisms.g2.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import organisms.g2.data.ChildEncoder;
import organisms.g2.data.ChildMessage;
import organisms.g2.data.Point;

public class ChildEncoderTest {

	@Test
	public void testEncoder() {
		ChildMessage message = new ChildMessage();
		message.setBiomassRatio(0.01);
		message.setPopulationRatio(0.20);
		
		Point lastFood = new Point(-8, -8);
		message.setLastSeenFood(lastFood);
		
		ChildEncoder encoder = new ChildEncoder();
		ChildMessage decoded = encoder.decodeChildState(encoder.encodeChildState(message));
		
		assertEquals("Decoded biomass is the same", 0.01, (double)decoded.getBiomassRatio(), 0.001);
		assertEquals("Population ratio is the same", 0.20, (double)decoded.getPopulationRatio(), 0.001);
		assertEquals("Last Seen food is the same", lastFood, decoded.getLastSeenFood());
	}
	
	@Test
	public void testEncoder2() {
		ChildMessage message = new ChildMessage();
		message.setBiomassRatio(0.01);
		message.setPopulationRatio(0.20);
		
		Point lastFood = new Point(-8, -8);
		message.setLastSeenFood(lastFood);
		
		ChildEncoder encoder = new ChildEncoder();
		int state = encoder.encodeChildState(message);
		
		assertEquals("Decoded biomass is the same", 148, state);
	}
	
}
