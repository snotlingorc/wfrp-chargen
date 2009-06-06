/*
 * Created on May 10, 2005
 */
package wfrpv2.helpers;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rpg.dieRoller.dieRoller;

/**
 * @author Mathew Anderson
 * @author www.snotling.org
 */
public class get_attribute {

	/**
	 * @param filename
	 * @return table string value
	 * Roll on table, read in the data and return the value
	 */
	public static String RootRoll(String filename) {
		int die;
		String result;
		die = dieRoller.main(100);
		
		// now, let us get the correct directory
		String directory = System.getProperty("user.dir");
		directory = directory.concat("/dataFiles/");
		directory = directory.concat(filename);
		
		result = TableLookup(directory, die);
		
		return result;
	}
	
	/**
	 * @param filename
	 * @return List of items from the root dir.
	 * Roll on table, read in the data and return the list value
	 */
	public static List rootlist(String filename) {
		List<String> return_string = new ArrayList<String>();
		// return the information from the file
		String file = System.getProperty("user.dir");
		file = file.concat("/dataFiles/");
		file = file.concat(filename);
		try {
			FileInputStream fstream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fstream);
			while (((FilterInputStream) in).available() !=0)  {
				// read  the line, split on : and see if what is
				// before the : is >= the die roll.  If it is, then
				// found the correct line to save.
				String Data = ((DataInput) in).readLine();
				return_string.add(Data);
			}
			in.close();
		}
		catch (IOException e) {
			System.out.print("\n File Exception: "+file+"\n");
		}
		
		return return_string;
	}
	
	/**
	 * @param race 
	 * @param filename
	 * @return string based on the race input
	 * Roll on table, read in the data and return the value
	 */
	public static String RaceRollS(String race, String filename) {
		// Roll on table, read in the data and return the value
		String result;
		int die;
		die = dieRoller.main(100);
		
		// now, let us get the correct directory
		String directory = System.getProperty("user.dir");
		directory = directory.concat("/dataFiles/");
		directory = directory.concat(race);
		directory = directory.concat("/");
		directory = directory.concat(filename);
		
		result = TableLookup(directory, die);
		
		return result;
	}
	
	/**
	 * @param race 
	 * @param filename
	 * @return integer from the table
	 * Roll on table, read in the data and return the value
	 */
	public static int RaceRollI(String race, String filename) {
		int die, number;
		String result;
		die = dieRoller.main(100);
		
		// now, let us get the correct directory
		String directory = System.getProperty("user.dir");
		directory = directory.concat("/dataFiles/");
		directory = directory.concat(race);
		directory = directory.concat("/");
		directory = directory.concat(filename);
		
		result = TableLookup(directory, die);
		number = Integer.valueOf(result).intValue();
		
		return number;
	}
	
	/**
	 * @param race 
	 * @param filename
	 * @return List from the race table
	 * read in all the bits from a file and return it in array form
	 */
	public static List RaceList(String race, String file) {
		List<String> filecontents = new ArrayList<String>();
		String filename = System.getProperty("user.dir");
		filename = filename.concat("/dataFiles/");
		filename = filename.concat(race);
		filename = filename.concat("/");
		filename = filename.concat(file);
		
		try {
			FileInputStream fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			while (((FilterInputStream) in).available() !=0)  {
				// read  the line, split on : and see if what is
				// before the : is >= the die roll.  If it is, then
				// found the correct line to save.
				String Data = ((DataInput) in).readLine();
				filecontents.add(Data);
			}
			in.close();
		}
		catch (IOException e) {
			System.out.print("\n File Exception: "+filename+"\n");
		}
		
		return filecontents;
	}
	
	/**
	 * @param race 
	 * @param sex
	 * @param filename
	 * @return int
	 * Roll on table, read in the data and return the value
	 */
	public static int RaceSexRollI(String race, String sex, String filename) {
		
		int die, number;
		String result;
		die = dieRoller.main(100);
		// using the table race/sex.filename and pick out the line that matches
		String directory = System.getProperty("user.dir");
		directory = directory.concat("/dataFiles/");
		directory = directory.concat(race);
		directory = directory.concat("/");
		directory = directory.concat(sex);
		directory = directory.concat(".");
		directory = directory.concat(filename);
		
		result = TableLookup(directory, die);
		number = Integer.valueOf(result).intValue();
		
		return number;
	}
	
	/**
	 * @param filename
	 * @param number
	 * @return random line out of filename
	 */
	public static String TableLookup(String filename, int number) {
		// Roll on table, read in the data and return the value
		// using the table race/filename and pick out the line that matches
		if ((filename == null) || (filename.length() == 0)) return("Bad File");
		
		try {
			FileInputStream fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			while (((FilterInputStream) in).available() !=0)  {
				// read  the line, split on : and see if what is
				// before the : is >= the die roll.  If it is, then
				// found the correct line to save.
				String Data = ((DataInput) in).readLine();
				String number2 = Data.substring(0, Data.indexOf(":") );
				
				//int newnumber = Integer.parseInt(number2);
				int newnumber = Integer.valueOf(number2).intValue();
				if (number <= newnumber) {
				   String Data2 = Data.substring(Data.indexOf(":") +1, Data.length());
				   return Data2;
				}
			}
			in.close();
		}
		catch (IOException e) {
			System.out.print("\n File Exception: "+filename+"\n");
		}
		
		return filename;
	}
	
	/**
	 * @param race
	 * @return random line out of filename
	 */
	public static String birthplace(String race) {
		String birthplace;
		String directory = System.getProperty("user.dir");
		directory = directory.concat("/dataFiles/");
		directory = directory.concat(race);
		directory = directory.concat("/birthplace");
		
		int die;
		die = dieRoller.main(100);

		birthplace = get_attribute.TableLookup(directory, die);
	
		return birthplace;
	}
	
	/**
	 * @param filename
	 * @return random line out of filename
	 */
	public static String RandomLine(String filename) {
		if ((filename == null) || (filename.length() == 0)) return("Bad File");
		int counter=0, counter2=0;
		String Data = "";
		try {
			FileInputStream fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			//Read in the file and get how many lines
			while (((FilterInputStream) in).available() !=0)  {
				Data = ((DataInput) in).readLine();
				counter++;
			}
			in.close();
			//Decide what line to keep
			Random rand = new Random();
			int number = rand.nextInt(counter);
			//Re-read the file and pull the random line out
			FileInputStream fstream2 = new FileInputStream(filename);
			DataInputStream in2 = new DataInputStream(fstream2);
			//Read in the file and get how many lines
			while (((FilterInputStream) in2).available() !=0)  {
				Data = ((DataInput) in2).readLine();
				counter2++;
				if (number == counter2) {
					return Data;
				}
			}	
			in.close();
		}
		catch (IOException e) {
			System.out.print("\n File Exception: "+filename+"\n");
		}
		return Data;
	}

	/**
	 * @param race
	 * @param sex
	 * @return name of character
	 */
	public static String Name(String race, String sex) {
		// TODO Complete based on race and sex per
		// character pack.
		String filenamebase = System.getProperty("user.dir");
		filenamebase = filenamebase.concat("/dataFiles/");
		filenamebase = filenamebase.concat(race);
		filenamebase = filenamebase.concat("/");
		String filename = filenamebase.concat(sex);
		filename = filename.concat(".firstname");
		String Firstname = RandomLine(filename);
		filename = filenamebase.concat("lastname");
		String Lastname = RandomLine(filename);
		
		Firstname = Firstname.concat(" ");
		Firstname = Firstname.concat(Lastname);
		return (Firstname);
	}

	/**
	 * @param int
	 * @return name of attribute in the int place
	 */
	public static String convert(int i) {
		String[] attribute = {"WS", "BS", "S", "T", "Ag", "Int", "WP", "Fel", "A", "W", "SB", "TB", "M", "Mag", "IP", "FP"};
		return attribute[i];
	}
	
}
