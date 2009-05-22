/*
 * Created on May 10, 2005
 */
package wfrpv2.helpers;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;

import rpg.dieRoller.dieRoller;
import misc.intsStrings;

/**
 * @author Mathew Anderson
 * @author www.snotling.org
 *
 * creates the profile of a new character
 */
public class get_profile {

	public static int[] main(String race) {
		int[] profile = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		//using the race value to read in the additions to the start profile
		int timesToRoll, sides;
		int value=0;
		int counter=0;
		String filename = System.getProperty("user.dir");
		filename = filename.concat("/dataFiles/");
		filename = filename.concat(race);
		filename = filename.concat("/profile");
		
		try {
			FileInputStream fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			while (((FilterInputStream) in).available() !=0)  {
				// read  the line, split on : 
				String Data = ((DataInput) in).readLine();
				String attribute = Data.substring(0, Data.indexOf(":") );
				String restOfIt = Data.substring(Data.indexOf(":") +1, Data.length());
				String whatToRoll = restOfIt.substring(0, restOfIt.indexOf(":") );
				String whatToAdd = restOfIt.substring(restOfIt.indexOf(":")+1, restOfIt.length() );
				System.out.print("\nData: "+Data);
				System.out.print("\nCharacter "+ attribute +" and "+whatToRoll+" and "+whatToAdd+" and parsed "+whatToRoll.substring(0, whatToRoll.indexOf("d") ));
				timesToRoll = intsStrings.toInt(whatToRoll.substring(0, whatToRoll.indexOf("d") ));
				sides = intsStrings.toInt(whatToRoll.substring(whatToRoll.indexOf("d") +1, whatToRoll.length()));
				value = dieRoller.main(timesToRoll,sides) + intsStrings.toInt(whatToAdd);
				profile[counter] = value;
				
				counter++;
			}
			in.close();
		}
		catch (IOException e) {
			System.out.print("\n File Exception: "+filename+"\n");
		}
		// getting some of the attributes
		profile[15] = get_attribute.RaceRollI(race, "fate");
		profile[9] = get_attribute.RaceRollI(race, "wounds");

		// make sure SB and TB are calculated
		// SB
		profile[10] = profile[2]/10;
		//TB
		profile[11] = profile[3]/10;
		
		return profile;
	}
}
