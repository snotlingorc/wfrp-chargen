package misc;

import java.io.File;
import java.util.List;

/*
 * Class is used for converting ints to Strings and vice versa.
 *
 * @version 	1.00 April 2009
 * @author 	Mathew Anderson  
 * @author  snotling@gmail.com
 */
public class intsStrings {
	
	/**
	 * Converts a String to and int
	 * 
	 * @param i (int)
	 * @return string version of the int i
	 */
	public static String toString(int i) {
		return String.valueOf(i);
	}
	
	/**
	 * Converts a String to and int
	 * 
	 * @param i (string)
	 * @return int version of the string i
	 */
	public static int toInt(String i) {
		Integer b =  Integer.decode(i);
		int c = b.intValue();
		return c;
	}
	
	/**
	 * Concatenates the list of items into a string
	 * 
	 * @param  List listOfStrings
	 * @return String
	 */
	public static String toString(List<String> listOfStrings) {
		int i;
		String result = "";
		for ( i=0; i<listOfStrings.size(); i++ ) {
			result = result.concat(" - ");
			result = result.concat((String) listOfStrings.get(i));
		}
		return result;
	}

	/**
	 * Concatenates the lists into a string
	 * 
	 * @param  File    a File type to be converted into a string
	 * @return String  a string version of the file
	 */
	public static String toString(File file) {
		String result = file.getAbsolutePath();
		return result;
	}
}
