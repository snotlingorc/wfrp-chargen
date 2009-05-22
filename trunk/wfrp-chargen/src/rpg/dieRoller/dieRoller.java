package rpg.dieRoller;
/*
 * Class is used for converting ints to Strings and vice versa.
 * @version 	1.00 April 2009
 * @author Mathew Anderson
 * @author www.snotling.org
 *
 */

import java.util.Random;

public class dieRoller {
	
	/**
	 * Creates a random number between 1 and x
	 * 
	 * @param  int maxium number the result could be
	 * @return int number
	 */
	public static int main(int x) {
		int number;
		Random rand = new Random();
		number = rand.nextInt(x) +1;
		return(number);
	}
	
	/**
	 * Rolls a dice, example 3d6 - rolls a 6 sided dice 3 times
	 * and returns the sum of the 3diced
	 * 
	 * @param x (int) 3 in the example above
	 * @param y (int) 6 in the example above
	 * @return total value of x rolls on a y sided die
	 */
	public static int main(int x, int y) {
		// x is the number of times to roll y sided die
		int number=0, i;
		for ( i=1; i<=x; i++ ) {
			number = (int)(y*Math.random() + 1)+ number;   // Range 1-y
		}
		return(number);
		}
	
	/**
	 * Rolls a dice and adds some value, example 1d10 + 20
	 * 
	 * @param value (String) in format <x>d<y>:<z>  Example:  1d10:20
	 * @return integer of that roll
	 */
	public static int DString(String value) {
		// Look at string and see if it is in that the format
		// looking for (number of dice)d(sides of dice):(value to add)
		//   the :(value to add) may be optional
		String[] tempString = null;
		String[] tempString2 = null;
		int extra = 0;
		int nDice = 0;
		int sDice = 0;
		
		tempString = value.split(":");
		tempString2 = tempString[0].split("d");
		
		extra = Integer.parseInt(tempString[1]);
		nDice = Integer.parseInt(tempString2[0]);
		sDice = Integer.parseInt(tempString2[1]);
		
		int number = main(nDice, sDice) + extra; 
		
		// System.out.println("Converted "+ value + " to "+number);
		return number;
	}
	
	
}
