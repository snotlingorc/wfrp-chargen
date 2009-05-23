package wfrpv2.helpers;

import java.util.List;

import wfrpv2.dataTypes.Character;
import wfrpv2.dataTypes.Race;
import wfrpv2.helpers.CharacterFunctions;


/*
 * Created on May 10, 2005
 */

/**
 * @author Mathew Anderson
 * @author www.snotling.org
 *
 */
public class CreateCharacter {

	// main is when you create the character for the first time
	public static Character main(String race, String gender) {
		// Initialize the character based on a race
		Character myCharacter = new Character();
		
		myCharacter.race = race;
		// See if the gender is going to be randomized
		gender = CharacterFunctions.randomGender(gender);
		myCharacter.gender = gender;
		myCharacter.career = "Peeon";
		// getting the main characteristics
		
		// Read in the xml file and pull out the right bits
		   // read in the xml race file 
		Race myRace = new Race();
		myRace = Race.initilizeRace(race);
	
		// Next add the various attributes to the character
		// add in the profile to the character
		for (int i=0; i < 16; i++) {
			myCharacter.starting_profile[i] = myRace.profile[i];
			myCharacter.current_profile[i] = myRace.profile[i];
		}
		
		// get the starting wounds
		myCharacter.starting_profile[9] = CharacterFunctions.getWounds(myRace);
		myCharacter.current_profile[9] = myCharacter.starting_profile[9];
		
		// get the starting fate
		myCharacter.starting_profile[15] = CharacterFunctions.getFate(myRace);
		myCharacter.current_profile[15] = myCharacter.starting_profile[15];
		
		
		myCharacter.name = CharacterFunctions.genName(myRace, gender);
		// Getting physical attributes
		myCharacter.age = CharacterFunctions.getAge(myRace);
		myCharacter.height = CharacterFunctions.getHeight(myRace, gender);
		myCharacter.weight = CharacterFunctions.getWeight(myRace);
		myCharacter.eyecolor = CharacterFunctions.getEyeColor(myRace);
		myCharacter.haircolor = CharacterFunctions.getHairColor(myRace);
		myCharacter.hairtype = CharacterFunctions.getHairType(myRace);
		// Getting background
		myCharacter.siblings = CharacterFunctions.getSiblings(myRace);
		myCharacter.birthplace = CharacterFunctions.getBirthplace(myRace);
		
		myCharacter.starsign = CharacterFunctions.getStarsign(myRace);
		myCharacter.marks = CharacterFunctions.getMarks(myRace);
		
		// get any racial skills, talents and trappings
		myCharacter.skills = myRace.skills;
		myCharacter.talents = myRace.talents;
		//Loop the talents and check for the specials
		// where it adds to the profile
		for (int i=0; i<myCharacter.talents.size(); i++) {
			myCharacter = wfrpv2.helpers.GeneralFunctions.checkForTalentBonus(myCharacter, myCharacter.talents.get(i));
		}
		myCharacter.trappings = myRace.trappings;
		
		// Check for RANDOM and OR
		List tempList = GeneralFunctions.checkForOR(myCharacter.skills);
		myCharacter.skills = tempList;
		tempList = GeneralFunctions.checkForOR(myCharacter.talents);
		myCharacter.talents = tempList;
		tempList = GeneralFunctions.checkForOR(myCharacter.trappings);
		myCharacter.trappings = tempList;
	    
		//tempList = GeneralFunctions.checkForRandom(myCharacter.skills, "Skills", myCharacter.race);
		//myCharacter.skills = tempList;
		tempList = GeneralFunctions.checkForRandom(myCharacter.talents, "Talents", myCharacter.race);
		myCharacter.talents = tempList;
		tempList = GeneralFunctions.checkForRandom(myCharacter.trappings, "Trappings", myCharacter.race);
		myCharacter.trappings = tempList;
		
		return(myCharacter);	
	}
}
