/*
 * Created on May 10, 2005
 */
package wfrpv2.helpers;
import wfrpv2.dataTypes.Character;

/**
 * @author Mathew Anderson
 * @author http://www.snotling.org
 *
 * Prints the character to standard out. Used for testing.
 */
public class print {
//TODO need to implement 
	public static void main(Character character) {
		System.out.println("Character printing...");
		System.out.println("Name: "+ character.name);
		System.out.println("race: "+ character.race);
		System.out.println("gender: "+ character.gender);
		System.out.println("exp: "+ character.exp);
		System.out.println("height: "+ character.height);
		System.out.println("weight: "+ character.weight);
		System.out.println("age: "+ character.age);
		System.out.println("haircolor: "+ character.haircolor);
		System.out.println("hairtype: "+ character.hairtype);
		System.out.println("eyecolor: "+ character.eyecolor);
		System.out.println("birthplace: "+ character.birthplace);
		System.out.println("starsign: "+ character.starsign);
		System.out.println("marks: "+ character.marks);
		System.out.println("siblings: "+ character.siblings);
		System.out.println("Profile:");
		for (int j=0; j<16; j++) {
			System.out.print(character.starting_profile[j]+" ");
		}
		System.out.print("\n");
		for (int j=0; j<16; j++) {
			System.out.print(character.advance_scheme[j]+" ");
		}
		System.out.print("\n");
		for (int j=0; j<16; j++) {
			System.out.print(character.advance_taken[j]+" ");
		}
		System.out.print("\n");
		for (int j=0; j<16; j++) {
			System.out.print(character.talent_bonus[j]+" ");
		}
		System.out.print("\n");
		for (int j=0; j<16; j++) {
			System.out.print(character.current_profile[j]+" ");
		}
		System.out.print("\n");
		
		System.out.println("career: "+ character.career);
		System.out.println("career_path: "+ character.career_path);
		System.out.println("skills: "+ character.skills);
		System.out.println("talents: "+ character.talents);
		System.out.println("trappings: "+ character.trappings);
	}
}
