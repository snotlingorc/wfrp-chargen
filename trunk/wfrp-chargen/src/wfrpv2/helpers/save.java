/*
 * Created on May 10, 2005
 */
package wfrpv2.helpers;
import rpg.XMLFile.XMLDocument;
import wfrpv2.dataTypes.Character;
import misc.intsStrings;

/**
 * @author Mathew Anderson
 * @author http://www.snotling.org
 *
 * Saves the character to an XML file.
 */
public class save {

	public static void main(Character character) throws Exception {
		XMLDocument XMLCharacter = new XMLDocument("Character");
		
		XMLCharacter.setFileName(character.name);
		
		XMLCharacter.setValue("/Character/Name", character.name);
		XMLCharacter.setValue("/Character/Race", character.race);
		XMLCharacter.setValue("/Character/Career", character.career);
		// Career path
		for ( int i=0; i<character.career_path.size(); i++ ) {
			String element = (String) character.career_path.get(i);
			System.out.print("\nElement "+ element);
			XMLCharacter.addValue("/Character", "career_path", element);
		}
		XMLCharacter.setValue("/Character/Gender", character.gender);
		XMLCharacter.setValue("/Character/Exp", intsStrings.toString(character.exp));
		XMLCharacter.setValue("/Character/Height", intsStrings.toString(character.height));
		XMLCharacter.setValue("/Character/Weight", intsStrings.toString(character.weight));
		XMLCharacter.setValue("/Character/Age", intsStrings.toString(character.age));
		XMLCharacter.setValue("/Character/Haircolor", character.haircolor);
		XMLCharacter.setValue("/Character/Hairtype", character.hairtype);
		XMLCharacter.setValue("/Character/Eyecolor", character.eyecolor);
		XMLCharacter.setValue("/Character/Birthplace", character.birthplace);
		XMLCharacter.setValue("/Character/Starsign", character.starsign);
		XMLCharacter.setValue("/Character/Marks", character.marks);
		XMLCharacter.setValue("/Character/Siblings", intsStrings.toString(character.siblings));
		
		// Skills - talents -trappings
		for ( int i=0; i<character.skills.size(); i++ ) {
			String element = (String) character.skills.get(i);
			XMLCharacter.addValue("/Character", "skills", element);
		}
		for ( int i=0; i<character.talents.size(); i++ ) {
			String element = (String) character.talents.get(i);
				XMLCharacter.addValue("/Character", "talents", element);
		}
		for ( int i=0; i<character.trappings.size(); i++ ) {
			String element = (String) character.trappings.get(i);
			XMLCharacter.addValue("/Character", "trappings", element);
		}
		
		for ( int i=0; i<character.available_skills.size(); i++ ) {
			String element = (String) character.available_skills.get(i);
			XMLCharacter.addValue("/Character", "available_skills", element);
		}
		for ( int i=0; i<character.available_talents.size(); i++ ) {
			String element = (String) character.available_talents.get(i);
			XMLCharacter.addValue("/Character", "available_talents", element);
		}
		
		// And now for the profiles
		XMLCharacter.setValue("/Character/starting_profile/WS", intsStrings.toString(character.starting_profile[0]));
		XMLCharacter.setValue("/Character/starting_profile/BS", intsStrings.toString(character.starting_profile[1]));
		XMLCharacter.setValue("/Character/starting_profile/S", intsStrings.toString(character.starting_profile[2]));
		XMLCharacter.setValue("/Character/starting_profile/T", intsStrings.toString(character.starting_profile[3]));
		XMLCharacter.setValue("/Character/starting_profile/Ag", intsStrings.toString(character.starting_profile[4]));
		XMLCharacter.setValue("/Character/starting_profile/Int", intsStrings.toString(character.starting_profile[5]));
		XMLCharacter.setValue("/Character/starting_profile/WP", intsStrings.toString(character.starting_profile[6]));
		XMLCharacter.setValue("/Character/starting_profile/Fel", intsStrings.toString(character.starting_profile[7]));
		XMLCharacter.setValue("/Character/starting_profile/A", intsStrings.toString(character.starting_profile[8]));
		XMLCharacter.setValue("/Character/starting_profile/W", intsStrings.toString(character.starting_profile[9]));
		XMLCharacter.setValue("/Character/starting_profile/SB", intsStrings.toString(character.starting_profile[10]));
		XMLCharacter.setValue("/Character/starting_profile/TB", intsStrings.toString(character.starting_profile[11]));
		XMLCharacter.setValue("/Character/starting_profile/M", intsStrings.toString(character.starting_profile[12]));
		XMLCharacter.setValue("/Character/starting_profile/Mag", intsStrings.toString(character.starting_profile[13]));
		XMLCharacter.setValue("/Character/starting_profile/IP", intsStrings.toString(character.starting_profile[14]));
		XMLCharacter.setValue("/Character/starting_profile/FP", intsStrings.toString(character.starting_profile[15]));
		
		XMLCharacter.setValue("/Character/advance_scheme/WS", intsStrings.toString(character.advance_scheme[0]));
		XMLCharacter.setValue("/Character/advance_scheme/BS", intsStrings.toString(character.advance_scheme[1]));
		XMLCharacter.setValue("/Character/advance_scheme/S", intsStrings.toString(character.advance_scheme[2]));
		XMLCharacter.setValue("/Character/advance_scheme/T", intsStrings.toString(character.advance_scheme[3]));
		XMLCharacter.setValue("/Character/advance_scheme/Ag", intsStrings.toString(character.advance_scheme[4]));
		XMLCharacter.setValue("/Character/advance_scheme/Int", intsStrings.toString(character.advance_scheme[5]));
		XMLCharacter.setValue("/Character/advance_scheme/WP", intsStrings.toString(character.advance_scheme[6]));
		XMLCharacter.setValue("/Character/advance_scheme/Fel", intsStrings.toString(character.advance_scheme[7]));
		XMLCharacter.setValue("/Character/advance_scheme/A", intsStrings.toString(character.advance_scheme[8]));
		XMLCharacter.setValue("/Character/advance_scheme/W", intsStrings.toString(character.advance_scheme[9]));
		XMLCharacter.setValue("/Character/advance_scheme/SB", intsStrings.toString(character.advance_scheme[10]));
		XMLCharacter.setValue("/Character/advance_scheme/TB", intsStrings.toString(character.advance_scheme[11]));
		XMLCharacter.setValue("/Character/advance_scheme/M", intsStrings.toString(character.advance_scheme[12]));
		XMLCharacter.setValue("/Character/advance_scheme/Mag", intsStrings.toString(character.advance_scheme[13]));
		XMLCharacter.setValue("/Character/advance_scheme/IP", intsStrings.toString(character.advance_scheme[14]));
		XMLCharacter.setValue("/Character/advance_scheme/FP", intsStrings.toString(character.advance_scheme[15]));
		
		XMLCharacter.setValue("/Character/advance_taken/WS", intsStrings.toString(character.advance_taken[0]));
		XMLCharacter.setValue("/Character/advance_taken/BS", intsStrings.toString(character.advance_taken[1]));
		XMLCharacter.setValue("/Character/advance_taken/S", intsStrings.toString(character.advance_taken[2]));
		XMLCharacter.setValue("/Character/advance_taken/T", intsStrings.toString(character.advance_taken[3]));
		XMLCharacter.setValue("/Character/advance_taken/Ag", intsStrings.toString(character.advance_taken[4]));
		XMLCharacter.setValue("/Character/advance_taken/Int", intsStrings.toString(character.advance_taken[5]));
		XMLCharacter.setValue("/Character/advance_taken/WP", intsStrings.toString(character.advance_taken[6]));
		XMLCharacter.setValue("/Character/advance_taken/Fel", intsStrings.toString(character.advance_taken[7]));
		XMLCharacter.setValue("/Character/advance_taken/A", intsStrings.toString(character.advance_taken[8]));
		XMLCharacter.setValue("/Character/advance_taken/W", intsStrings.toString(character.advance_taken[9]));
		XMLCharacter.setValue("/Character/advance_taken/SB", intsStrings.toString(character.advance_taken[10]));
		XMLCharacter.setValue("/Character/advance_taken/TB", intsStrings.toString(character.advance_taken[11]));
		XMLCharacter.setValue("/Character/advance_taken/M", intsStrings.toString(character.advance_taken[12]));
		XMLCharacter.setValue("/Character/advance_taken/Mag", intsStrings.toString(character.advance_taken[13]));
		XMLCharacter.setValue("/Character/advance_taken/IP", intsStrings.toString(character.advance_taken[14]));
		XMLCharacter.setValue("/Character/advance_taken/FP", intsStrings.toString(character.advance_taken[15]));
		
		XMLCharacter.setValue("/Character/current_profile/WS", intsStrings.toString(character.current_profile[0]));
		XMLCharacter.setValue("/Character/current_profile/BS", intsStrings.toString(character.current_profile[1]));
		XMLCharacter.setValue("/Character/current_profile/S", intsStrings.toString(character.current_profile[2]));
		XMLCharacter.setValue("/Character/current_profile/T", intsStrings.toString(character.current_profile[3]));
		XMLCharacter.setValue("/Character/current_profile/Ag", intsStrings.toString(character.current_profile[4]));
		XMLCharacter.setValue("/Character/current_profile/Int", intsStrings.toString(character.current_profile[5]));
		XMLCharacter.setValue("/Character/current_profile/WP", intsStrings.toString(character.current_profile[6]));
		XMLCharacter.setValue("/Character/current_profile/Fel", intsStrings.toString(character.current_profile[7]));
		XMLCharacter.setValue("/Character/current_profile/A", intsStrings.toString(character.current_profile[8]));
		XMLCharacter.setValue("/Character/current_profile/W", intsStrings.toString(character.current_profile[9]));
		XMLCharacter.setValue("/Character/current_profile/SB", intsStrings.toString(character.current_profile[10]));
		XMLCharacter.setValue("/Character/current_profile/TB", intsStrings.toString(character.current_profile[11]));
		XMLCharacter.setValue("/Character/current_profile/M", intsStrings.toString(character.current_profile[12]));
		XMLCharacter.setValue("/Character/current_profile/Mag", intsStrings.toString(character.current_profile[13]));
		XMLCharacter.setValue("/Character/current_profile/IP", intsStrings.toString(character.current_profile[14]));
		XMLCharacter.setValue("/Character/current_profile/FP", intsStrings.toString(character.current_profile[15]));
			
		// Now that the xml datatype is loaded.  save the file out.
		XMLCharacter.writeFile();
	}
}