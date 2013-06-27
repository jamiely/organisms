package organisms.g2.data;

public class ChildEncoder {
	public int encodeChildState(ChildMessage message) {
		Integer biomass = (int) (message.getBiomassRatio() * 100); // 7
		Integer population = (int) (message.getPopulationRatio() * 100); // 7
		
		// We can only encode points with a magnitude in each direction 
		// less than or equal to 8.
		int adjustedX = 8;
		int adjustedY = 8;
		
		if(Math.abs(message.getLastSeenFood().x) <= 8 &&
				Math.abs(message.getLastSeenFood().y) <= 8) {
			adjustedX = message.getLastSeenFood().x + 8;
			adjustedY = message.getLastSeenFood().y + 8;
		}
		
		return population + (biomass << 7) + (adjustedX << (7+4)) + (adjustedY << (7+4+4));   
	}
	
	public ChildMessage decodeChildState(int state) {
		ChildMessage message = new ChildMessage();
		
		message.setPopulationRatio((state & 127) / 100.);
		message.setBiomassRatio(((state >> 7) & 127) / 100.);
		
		int adjustedX = (state >> (7 + 7)) & 15;
		int adjustedY = (state >> (7 + 7 + 4)) & 15;
		
		int x = adjustedX - 8;
		int y = adjustedY - 8;
		
		message.setLastSeenFood(new Point(x, y));
		
		return message;
	}
	
	public boolean isEncodedMessage(int state){
		if (state == 0) return false;
		double pop = (state & 127) / 100.;
		double bio = ((state >> 7) & 127) / 100.;
		int adjustedX = (state >> (7 + 7)) & 15;
		int adjustedY = (state >> (7 + 7 + 4)) & 15;
		
		return((bio > 0 && bio < 100 && pop > 0 && bio < 100 && adjustedX >= 0 && adjustedX <= 16) && (adjustedY >= 0 && adjustedY <= 16));		
	}
	
//	public boolean isEncodedMessage(int state){
//		int adjustedX = (state >> (7 + 7)) & 15;
//		int adjustedY = (state >> (7 + 7 + 4)) & 15;
//		
//		return (adjustedX > 0 && adjustedX < 16 && adjustedY > 0 && adjustedY < 16);
//	}
}
