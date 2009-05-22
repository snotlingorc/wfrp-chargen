/*
 * Created on May 10, 2005
 */
package wfrpv2.helpers;
import java.io.File;
import wfrpv2.dataTypes.Character;

import rpg.XMLFile.XMLDocument;
import rpg.dieRoller.dieRoller;
import misc.intsStrings;

/**
 * @author Mathew Anderson
 * @author http://www.snotling.org
 *
 * Loads the character from an XML file.
 */
public class load {

	public static Character main(File file) throws Exception {
		Character character = new Character();
		XMLDocument XMLCharacter = new XMLDocument("Character");

		XMLCharacter.setFileName(intsStrings.toString(file));
		XMLCharacter.loadFile(false);
		
		character.name = XMLCharacter.getValue("/Character/name");
		character.race = XMLCharacter.getValue("/Character/race");
		character.career = XMLCharacter.getValue("/Character/career");
		
		//character.career_path = XMLCharacter.getValue("/Character/career_path");
		
		character.gender = XMLCharacter.getValue("/Character/sex");
		character.exp = intsStrings.toInt(XMLCharacter.getValue("/Character/exp"));
		character.height = intsStrings.toInt(XMLCharacter.getValue("/Character/height"));
		character.weight = intsStrings.toInt(XMLCharacter.getValue("/Character/weight"));
		character.age = intsStrings.toInt(XMLCharacter.getValue("/Character/age"));
		character.haircolor = XMLCharacter.getValue("/Character/haircolor");
		character.hairtype = XMLCharacter.getValue("/Character/hairtype");
		character.eyecolor = XMLCharacter.getValue("/Character/eyecolor");
		character.birthplace = XMLCharacter.getValue("/Character/birthplace");
		character.starsign = XMLCharacter.getValue("/Character/starsign");
		character.marks = XMLCharacter.getValue("/Character/marks");
		character.siblings = intsStrings.toInt(XMLCharacter.getValue("/Character/siblings"));
		
		//TODO 
		//character.skills = XMLCharacter.getValue("/Character/skills");
		//character.talents = XMLCharacter.getValue("/Character/talents");
		//character.trappings = XMLCharacter.getValue("/Character/trappings");
		//character.career_path = XMLCharacter.getValue("/Character/career_path");
		for (int i=1; i < 20; i++) {
			try {
				character.talents.add(XMLCharacter.getValue("/Character/talents["+i+"]"));
			} catch (Exception e16) {
				// TODO Auto-generated catch block
				e16.printStackTrace();
			}
			try {
				character.trappings.add(XMLCharacter.getValue("/Character/trappings["+i+"]"));
			} catch (Exception e17) {
				// TODO Auto-generated catch block
				e17.printStackTrace();
			}
			try {
				character.skills.add(XMLCharacter.getValue("/Character/skills["+i+"]"));
			} catch (Exception e18) {
				// TODO Auto-generated catch block
				e18.printStackTrace();
			}
			try {
				character.career_path.add(XMLCharacter.getValue("/Character/career_path["+i+"]"));
			} catch (Exception e19) {
				// TODO Auto-generated catch block
				e19.printStackTrace();
			}
			try {
				character.available_skills.add(XMLCharacter.getValue("/Character/available_skills["+i+"]"));
			} catch (Exception e19) {
				// TODO Auto-generated catch block
				e19.printStackTrace();
			}
			try {
				character.available_talents.add(XMLCharacter.getValue("/Character/available_talents["+i+"]"));
			} catch (Exception e19) {
				// TODO Auto-generated catch block
				e19.printStackTrace();
			}
		}
		
		
		//Profile bits
		character.starting_profile[0] = intsStrings.toInt(XMLCharacter.getValue("/Character/starting_profile/WS"));
		character.starting_profile[1] = intsStrings.toInt(XMLCharacter.getValue("/Character/starting_profile/BS"));
		character.starting_profile[2] = intsStrings.toInt(XMLCharacter.getValue("/Character/starting_profile/S"));
		character.starting_profile[3] = intsStrings.toInt(XMLCharacter.getValue("/Character/starting_profile/T"));
		character.starting_profile[4] = intsStrings.toInt(XMLCharacter.getValue("/Character/starting_profile/Ag"));
		character.starting_profile[5] = intsStrings.toInt(XMLCharacter.getValue("/Character/starting_profile/Int"));
		character.starting_profile[6] = intsStrings.toInt(XMLCharacter.getValue("/Character/starting_profile/WP"));
		character.starting_profile[7] = intsStrings.toInt(XMLCharacter.getValue("/Character/starting_profile/Fel"));
		character.starting_profile[8] = intsStrings.toInt(XMLCharacter.getValue("/Character/starting_profile/A"));
		character.starting_profile[9] = intsStrings.toInt(XMLCharacter.getValue("/Character/starting_profile/W"));
		character.starting_profile[10] = intsStrings.toInt(XMLCharacter.getValue("/Character/starting_profile/SB"));
		character.starting_profile[11] = intsStrings.toInt(XMLCharacter.getValue("/Character/starting_profile/TB"));
		character.starting_profile[12] = intsStrings.toInt(XMLCharacter.getValue("/Character/starting_profile/M"));
		character.starting_profile[13] = intsStrings.toInt(XMLCharacter.getValue("/Character/starting_profile/Mag"));
		character.starting_profile[14] = intsStrings.toInt(XMLCharacter.getValue("/Character/starting_profile/IP"));
		character.starting_profile[15] = intsStrings.toInt(XMLCharacter.getValue("/Character/starting_profile/FP"));
	
		character.advance_scheme[0] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_scheme/WS"));
		character.advance_scheme[1] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_scheme/BS"));
		character.advance_scheme[2] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_scheme/S"));
		character.advance_scheme[3] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_scheme/T"));
		character.advance_scheme[4] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_scheme/Ag"));
		character.advance_scheme[5] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_scheme/Int"));
		character.advance_scheme[6] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_scheme/WP"));
		character.advance_scheme[7] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_scheme/Fel"));
		character.advance_scheme[8] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_scheme/A"));
		character.advance_scheme[9] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_scheme/W"));
		character.advance_scheme[10] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_scheme/SB"));
		character.advance_scheme[11] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_scheme/TB"));
		character.advance_scheme[12] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_scheme/M"));
		character.advance_scheme[13] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_scheme/Mag"));
		character.advance_scheme[14] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_scheme/IP"));
		character.advance_scheme[15] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_scheme/FP"));
		
		character.advance_taken[0] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_taken/WS"));
		character.advance_taken[1] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_taken/BS"));
		character.advance_taken[2] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_taken/S"));
		character.advance_taken[3] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_taken/T"));
		character.advance_taken[4] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_taken/Ag"));
		character.advance_taken[5] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_taken/Int"));
		character.advance_taken[6] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_taken/WP"));
		character.advance_taken[7] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_taken/Fel"));
		character.advance_taken[8] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_taken/A"));
		character.advance_taken[9] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_taken/W"));
		character.advance_taken[10] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_taken/SB"));
		character.advance_taken[11] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_taken/TB"));
		character.advance_taken[12] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_taken/M"));
		character.advance_taken[13] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_taken/Mag"));
		character.advance_taken[14] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_taken/IP"));
		character.advance_taken[15] = intsStrings.toInt(XMLCharacter.getValue("/Character/advance_taken/FP"));
		
		character.current_profile[0] = intsStrings.toInt(XMLCharacter.getValue("/Character/current_profile/WS"));
		character.current_profile[1] = intsStrings.toInt(XMLCharacter.getValue("/Character/current_profile/BS"));
		character.current_profile[2] = intsStrings.toInt(XMLCharacter.getValue("/Character/current_profile/S"));
		character.current_profile[3] = intsStrings.toInt(XMLCharacter.getValue("/Character/current_profile/T"));
		character.current_profile[4] = intsStrings.toInt(XMLCharacter.getValue("/Character/current_profile/Ag"));
		character.current_profile[5] = intsStrings.toInt(XMLCharacter.getValue("/Character/current_profile/Int"));
		character.current_profile[6] = intsStrings.toInt(XMLCharacter.getValue("/Character/current_profile/WP"));
		character.current_profile[7] = intsStrings.toInt(XMLCharacter.getValue("/Character/current_profile/Fel"));
		character.current_profile[8] = intsStrings.toInt(XMLCharacter.getValue("/Character/current_profile/A"));
		character.current_profile[9] = intsStrings.toInt(XMLCharacter.getValue("/Character/current_profile/W"));
		character.current_profile[10] = intsStrings.toInt(XMLCharacter.getValue("/Character/current_profile/SB"));
		character.current_profile[11] = intsStrings.toInt(XMLCharacter.getValue("/Character/current_profile/TB"));
		character.current_profile[12] = intsStrings.toInt(XMLCharacter.getValue("/Character/current_profile/M"));
		character.current_profile[13] = intsStrings.toInt(XMLCharacter.getValue("/Character/current_profile/Mag"));
		character.current_profile[14] = intsStrings.toInt(XMLCharacter.getValue("/Character/current_profile/IP"));
		character.current_profile[15] = intsStrings.toInt(XMLCharacter.getValue("/Character/current_profile/FP"));
		
		return character;	
		
	}
	
}