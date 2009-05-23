package wfrpv2.helpers;


import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.util.Random;

import rpg.dieRoller.dieRoller;
import wfrpv2.dataTypes.Character;
import wfrpv2.dataTypes.Race;



/*
 * Created on May 08, 2009
 */

/**
 * @author Mathew Anderson
 * @author www.snotling.org
 *
 */
public class CharacterFunctions {
	
	/**
	 * @param Race
	 * @param String
	 * @return a valid name for the Race of gender String
	 */
	static String genName(Race myRace, String gender) {
		// read the name types from the xml and create the name
		String file = System.getProperty("user.dir");
		file = file.concat("/datafiles/Names/");
		file = file.concat(myRace.description);
		String myName = "";
		for (int i=0; i<myRace.name.size(); i++) {
			String NewFile = file.concat("."+gender+"."+myRace.name.get(i));
			myName = myName+""+RandomLine(NewFile)+" ";
		}
		return myName.trim();
	}

	
	// TODO
	// * Return a random career for a given race
	// * Return a list of careers for a given race
	
	/**
	 * @param career
	 * @return
	 */
	// TODO  need to find a way to make this dynamic.
	static String randomCareer(Race myRace, String career) {
//		if (career == "Random") {
//			CareerList careerlist = new CareerList();
//			if (race =="Human") {
//				return careerlist.Human[dieRoller.main(1, (careerlist.Human.length -1))];
//			} else if (race == "Elf") {
//				return careerlist.Elf[dieRoller.main(1, (careerlist.Elf.length -1))];
//			} else if (race == "Dwarf") {
//				return careerlist.Dwarf[dieRoller.main(1, (careerlist.Dwarf.length -1))];
//			} else if (race == "Halfling") {
//				return careerlist.Halfling[dieRoller.main(1, (careerlist.Halfling.length -1))];
//			} else {
//				return "Invalid Race";
//			}
//		} else {
//			return career;
//		}
		return "career";
	}

	/**
	 * @param gender
	 * @return correct gender of character
	 */
	static String randomGender(String gender) {
		if (gender.equals("Random")) {
			if (dieRoller.main(1,2) == 1) {
				return "Male";
			} else {
				return "Female";
			}
		} else {
			return gender;
		}
	}

	//pull out that element - if it does not exist.. add one
	//  repeat until a match is found.. Possible inifite loop
	//  if 100 is reached do something
	public static int getAge(Race myRace) {
		//Roll 1d100
		int die = rpg.dieRoller.dieRoller.main(1, 100);
		while (!myRace.age.containsKey(misc.intsStrings.toString(die))) {
			if (die == 101) {
				System.out.println("Error in Obtaining age: XML badly formatted");
				break;
			}
			die++;
		}
		String result = myRace.age.get(misc.intsStrings.toString(die));
		return misc.intsStrings.toInt(result);
	}

	public static int getHeight(Race myRace, String gender) {
		String toRoll = myRace.height.get(gender);
                int result = rpg.dieRoller.dieRoller.DString(toRoll);
		return result;
	}

	public static int getWeight(Race myRace) {
		//Roll 1d100
		int die = rpg.dieRoller.dieRoller.main(1, 100);
		while (!myRace.weight.containsKey(misc.intsStrings.toString(die))) {
			die++;
			if (die == 101) {
				System.out.println("Error in Obtaining weight: XML badly formatted");
				break;
			}
		}
		String result = myRace.weight.get(misc.intsStrings.toString(die));
		return misc.intsStrings.toInt(result);
	}

	public static String getEyeColor(Race myRace) {
		//Roll 1d100
		int die = rpg.dieRoller.dieRoller.main(1, 100);
		while (!myRace.eyecolor.containsKey(misc.intsStrings.toString(die))) {
			die++;
			if (die == 101) {
				System.out.println("Error in Obtaining eyecolor: XML badly formatted");
				break;
			}
		}
		String result = myRace.eyecolor.get(misc.intsStrings.toString(die));
		return result;
	}

	public static String getHairColor(Race myRace) {
		//Roll 1d100
		int die = rpg.dieRoller.dieRoller.main(1, 100);
		while (!myRace.haircolor.containsKey(misc.intsStrings.toString(die))) {
			die++;
			if (die == 101) {
				System.out.println("Error in Obtaining haircolor: XML badly formatted");
				break;
			}
		}
		String result = myRace.haircolor.get(misc.intsStrings.toString(die));
		return result;
	}

	public static String getHairType(Race myRace) {
		//Roll 1d100
		int die = rpg.dieRoller.dieRoller.main(1, 100);
		while (!myRace.hairstyle.containsKey(misc.intsStrings.toString(die))) {
			die++;
			if (die == 101) {
				System.out.println("Error in Obtaining hairstyle: XML badly formatted");
				break;
			}
		}
		String result = myRace.hairstyle.get(misc.intsStrings.toString(die));
		return result;
	}

	public static String getBirthplace(Race myRace) {
		//Roll 1d100
		int die = rpg.dieRoller.dieRoller.main(1, 100);
		while (!myRace.birthplace.containsKey(misc.intsStrings.toString(die))) {
			die++;
			if (die == 101) {
				System.out.println("Error in Obtaining birthplace: XML badly formatted");
				break;
			}
		}
		String result = myRace.birthplace.get(misc.intsStrings.toString(die));
		
		//TODO - Check for birthplace of another race
		// example if result is "HUMAN" load the human profile and select a
		// birthplace from that.
		if (misc.caseClass.isUpper(result)) {
			// Convert the result to initialcaps and the rest lower
			String racialProfile = misc.caseClass.initialCaps(result);
			System.out.println("going to look at profile: "+racialProfile);
			
			// load the profile
			Race backgroundRace = new Race();
			backgroundRace = Race.initilizeRace(racialProfile);
			result = CharacterFunctions.getBirthplace(backgroundRace);
		}
		
		return result;
	}

	public static String getStarsign(Race myRace) {
		String filename="starsign";
		String value = IOFunctions.RandomLine(filename);
		return value;
	}

	public static String getMarks(Race myRace) {
		String filename="marks";
		String value = IOFunctions.RandomLine(filename);
		return value;
	}

	public static int getSiblings(Race myRace) {
		//Roll 1d100
		int die = rpg.dieRoller.dieRoller.main(1, 100);
		while (!myRace.siblings.containsKey(misc.intsStrings.toString(die))) {
			die++;
			if (die == 101) {
				System.out.println("Error in Obtaining siblings: XML badly formatted");
				break;
			}
		}
		String result = myRace.siblings.get(misc.intsStrings.toString(die));
		return misc.intsStrings.toInt(result);
	}

	public static int getWounds(Race myRace) {
		int die = rpg.dieRoller.dieRoller.main(1, 100);
		while (!myRace.wounds.containsKey(misc.intsStrings.toString(die))) {
			die++;
			if (die == 101) {
				System.out.println("Error in Obtaining wounds: XML badly formatted");
				break;
			}
		}
		String result = myRace.wounds.get(misc.intsStrings.toString(die));
		return misc.intsStrings.toInt(result);
	}

	public static int getFate(Race myRace) {
		int die = rpg.dieRoller.dieRoller.main(1, 100);
		while (!myRace.fate.containsKey(misc.intsStrings.toString(die))) {
			die++;
			if (die == 101) {
				System.out.println("Error in Obtaining fate: XML badly formatted");
				break;
			}
		}
		String result = myRace.fate.get(misc.intsStrings.toString(die));
		return misc.intsStrings.toInt(result);
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


	public static Character ShallyaMercy(Character character) {
		// Prompt if they want to use Shallya's Mercy
		// if they do
		//    Prompt the user for which skill to replace
		// otherwise return unchanged
		//
		// TODO
		return character;
	}
	
}
