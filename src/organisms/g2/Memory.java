/**
 * 
 */
package organisms.g2;

import java.util.ArrayList;

/**
 * @author Anne
 *
 */
public class Memory {
	private int[] location;
	private ArrayList<int[]> foodLocations;
	
	int age;
	private Integer ageAtWhichWeHadLastChild;
	private Integer offspringCount;
	

	public void Memory(){
		location = new int[] {0,0};
		ArrayList<int[]> foodLocations = new ArrayList<int[]> ();
		age = 0;
	}
	
	public void increaseAge(){
		age++;
		return;
	}
	
	public int getAge(){
		return age;
	}

}
