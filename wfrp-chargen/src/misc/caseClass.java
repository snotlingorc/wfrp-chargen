package misc;

/**
 * Class is used for case settings. Example would be checking for all capitals or changing
 * the  case of a sting.
 *
 * @version 	1.00 April 2009
 * @author 	Mathew Anderson
 * @author  snotling@gmail.com
 */

public class caseClass {
	
	/**
	 * Returns true or false depending on if the string is
	 * all capitalized.
	 *
	 * @param  String  a string that will be check
	 * @return true    if all characters are capitalized
	 * @return false   if one or more of the characters not not capitalized
	 */
	public static boolean isUpper(String myString) {
		boolean result = false;
		// true if all characters in myString are uppercase
		int length = myString.length();
		for ( int upperIdx = 0 ; upperIdx < length ; ++upperIdx ) {
			char ch = myString.charAt( upperIdx );
			if (Character.isUpperCase(ch)) {
				result = true;
			} else {
				result = false;
			}

		}
		return result;
	}

	
	/**
	 * Returns the submitted string but makes sure only the first
	 * letter of the string is capitalized.
	 *
	 * @param  String  a string to be changed to initial capital
	 * @return         Same sting but with an initial capital
	 */
	public static String initialCaps(String myString) {
		myString = myString.toLowerCase();
		String lastPart = myString.substring(1);
		String firstPart = myString.substring(0, 1);
		firstPart = firstPart.toUpperCase();
		firstPart = firstPart.concat(lastPart);		
		return firstPart;
	}
	
}
