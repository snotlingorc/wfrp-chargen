package wfrpv2.helpers;


import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.FilterInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import wfrpv2.dataTypes.Race;


/*
 * Created on May 08, 2009
 */

/**
 * @author Mathew Anderson
 * @author www.snotling.org
 *
 */
public class IOFunctions {

	
	public static String[] getRaces() {
		// Get a list of all the profileX.xml files and add X to the list of Strings
		// read the name types from the xml and create the name
		String file = System.getProperty("user.dir");
		file = file.concat("/dataFiles/");
		File dir = new File(file);
		String[] children = dir.list();

		if (children == null) {
	        // Either dir does not exist or is not a directory
			System.out.println("Found Null - no Profiles found in "+ file);
	    } 
		
	    // It is also possible to filter the list of returned files.
	    // This returns all files that start with 'profile'.
	    FilenameFilter filter = new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	            return name.startsWith("profile");
	        }
	    };
	    children = dir.list(filter);

		//remove profile and .xml from the list of files
	    for (int i=0; i<children.length; i++) {
	    	String newName = children[i];
	    	newName = newName.replace("profile", "");
	    	newName = newName.replace(".xml", "");
	    	children[i] = newName;
	    }
		
		return children;
	}

	public static String[] getCareers(String race) {
		Race myRace = new Race();
		myRace = Race.initilizeRace(race);
		
		String[] stringOfCareers = new String[myRace.career.size()];
		
		for (int i=0; i<myRace.career.size(); i++) {
			stringOfCareers[i] = (String) myRace.career.get(i);
		}
		
		return stringOfCareers;
	}

	/**
	 * @param filename
	 * @return random line out of filename
	 */
	public static String RandomLine(String type) {
		String filename = System.getProperty("user.dir");
		filename = filename.concat("/dataFiles/misc/");
		filename = filename.concat(type);
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

	public static Object[] getAnyList(String string) {
		System.out.println("Found ANY -"+string);
		String filename = System.getProperty("user.dir");
		filename = filename.concat("/dataFiles/any/");
		filename = filename.concat(string);
		System.out.println("Filename: "+filename);
		List myList = new ArrayList();
		int counter=0;
		if ((filename == null) || (filename.length() == 0)) { 
			System.out.println("Bad File in Any: "+filename);
		}
		try {
			FileInputStream fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			//Read in the file and get how many lines
			while (((FilterInputStream) in).available() !=0)  {
				myList.add(((DataInput) in).readLine());
				counter++;
			}
			in.close();
		}
		catch (IOException e) {
			System.out.print("\n File Exception: "+filename+"\n");
		}
		Object[] anyList = new Object[counter];
		for (int i=0; i<counter; i++) {
			anyList[i] = myList.get(i);
		}
		return anyList;
	}
	
}
