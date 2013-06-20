/**
 * 
 */
package organisms.g2;

import java.util.ArrayList;

import organisms.Move;

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
	
	public void updateLocation(Move move) {
		switch(move.type())
	    {
//		    case STAYPUT:
//			    return;
//		    case WEST:
//		    	location[1] -=1;
//			    return;
//		    case EAST:
//		    	location[1] +=1;
//		    	return;
//		    case NORTH:
//		    	location[0] -=1;
//		    	return;
//		    case SOUTH:
//		    	location[0] +=1;
//		    	return;
	    }
	    return;
	}

	/**
	 * @param age2
	 * @return
	 */
	public Object setAge(Integer age2) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return
	 */
	public Integer getOffspringCount() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param offspringCount2
	 */
	public void setOffspringCount(Integer offspringCount2) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return
	 */
	public Integer getAgeAtWhichWeHadLastChild() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param ageAtWhichWeHadLastChild2
	 */
	public void setAgeAtWhichWeHadLastChild(Integer ageAtWhichWeHadLastChild2) {
		// TODO Auto-generated method stub
		
	}

	

}
