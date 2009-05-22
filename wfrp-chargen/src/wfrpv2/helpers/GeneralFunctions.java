package wfrpv2.helpers;


import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Element;

import rpg.XMLFile.XMLDocument;
import rpg.dieRoller.dieRoller;
import wfrpv2.dataTypes.CareerList;
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
public class GeneralFunctions {

	// main is when you create the character for the first time
	

	static List checkForOR(List listOfStuff) {
		//Checking for OR in  a list and picking only one.
		for (int i=0; i<listOfStuff.size(); i++) {
			//System.out.println("List item "+i+": "+listOfStuff.get(i));
			String itemElement = (String) listOfStuff.get(i);
			if (itemElement.contains(" OR ")) {
				//System.out.println("Found an OR");
				//Split out the parts
				String[] ORS = itemElement.split(" OR ");
				// get the number of elements, return one of them at random
				int keeper = rpg.dieRoller.dieRoller.main(ORS.length)-1;
				//System.out.println("Keeping "+keeper+" "+ORS[keeper]);
				listOfStuff.set(i, ORS[keeper]);
			}
		}
		return listOfStuff;
	}

	public static List checkForRandom(List listOfStuff, String listName, String race) {
		//Checking for RANDOM in one of the lists
		for (int i=0; i<listOfStuff.size(); i++) {
			String itemElement = (String) listOfStuff.get(i);
			if (itemElement.equals("RANDOM")) {
				String randomValue = getRandom(listName, race);
				listOfStuff.set(i, randomValue);
			}
		}
		return listOfStuff;
	}

	private static String getRandom(String listName, String race) {
		// Open Race file and pull out a random item. example RandomTalent
		XMLDocument XMLGeneric = new XMLDocument("race");
		String file = wfrpv2.dataTypes.Race.openRaceFile(race);

		XMLGeneric.setFileName(file);
		XMLGeneric.loadFile(false);
		HashMap<String, String> randomBits = new HashMap<String,String>();
		
		for (int i = 1; i < 40; i++) {
			Element bits;
			try {
				bits = XMLGeneric.getElement("/Race/Random"+listName+"["+ i + "]");
				String name = bits.getAttribute("name");
				String value = bits.getAttribute("value");
				randomBits.put(name,value);
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		
		
		// Roll to see which one to return
		//Roll 1d100
		int die = rpg.dieRoller.dieRoller.main(1, 100);
		while (!randomBits.containsKey(misc.intsStrings.toString(die))) {
			die++;
			if (die == 101) {
				System.out.println("Error in Obtaining Random "+listName+": XML badly formatted");
				break;
			}
		}
		String result = randomBits.get(misc.intsStrings.toString(die));
		return result;
	}

	public static boolean checkForOR(String value) {
		if (value.contains(" OR ")) {
			return true;
		} else {
			return false;
		}
	}

	public static String[] getORs(String value) {
		
			if (value.contains(" OR ")) {
				//Split out the parts
				String[] ORS = value.split(" OR ");
				return ORS;
			}
	
		return null;
	}

	public static Character checkForTalentBonus(Character character, Object myTalent) {
    	// we need to look for
		// some special cases (they increase stats)
		if (myTalent.equals("Coolheaded")) {character.addtostat(6, 5);} //WP
		if (myTalent.equals("Fleet Footed")) {character.addtostat(12, 1); } //M
		if (myTalent.equals("Hardy")) {character.addtostat(9, 1); } //W
		if (myTalent.equals("Lightning Reflexes")) {character.addtostat(4, 5); } //Ag
		if (myTalent.equals("Marksman")) {character.addtostat(1, 5); } //BS
		if (myTalent.equals("Savvy")) {character.addtostat(5, 5); } //Int
		if (myTalent.equals("Sauve")) {character.addtostat(7, 5); } //Fel
		if (myTalent.equals("Very Resilient")) {character.addtostat(3, 5); } //T
		if (myTalent.equals("Very Strong")) {character.addtostat(2, 5); } //S
		if (myTalent.equals("Warrior Born")) {character.addtostat(0, 5); } //WS
			
		return character;
	}

	
	
}
