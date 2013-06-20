package organisms.g2.stats;

public class Stats {
	/**
	 * Returns the count of neighbors. Ignores the current location.
	 * @param neighbors
	 * @return
	 */
	public static int neighborCount (Integer[] neighbors){
		int neighbor = 0;
		for(int i = 1, size = neighbors.length; i < size; i++) {
			if(neighbors[i] != -1){
				neighbor ++;
			}
		}
		return neighbor;
	}
}
