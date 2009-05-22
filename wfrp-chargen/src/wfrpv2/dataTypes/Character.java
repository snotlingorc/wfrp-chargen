package wfrpv2.dataTypes;

import java.util.ArrayList;
import java.util.List;

/*
 * Created on May 10, 2005
 */

/**
 * @author Mathew Anderson
 * @author http://www.snotling.org
 *
 * Character template.  Used when loading/generating a
 * character. Contains all the information about a character.
 */
public class Character {
	public String name = "Character Name";
	public String race = "Random";
	public String gender = "Random";
	public int age = 0;
	public int height = 0;
	public int weight = 0;
	public int exp = 0;
	public String eyecolor = "Black";
	public String haircolor = "Black";
	public String hairtype = "None";
	public String birthplace = "Not yet born";
	public String starsign = "None";
	public String marks = "None";
	public int siblings = 0;
	public int[] starting_profile = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	public int[] advance_taken = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	public int[] advance_scheme = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	public int[] talent_bonus = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	public int[] current_profile = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	public List talents = new ArrayList();
	public List skills = new ArrayList();
	public String career = "None";
	public List career_path = new ArrayList();
	public List trappings = new ArrayList();
	public List available_skills = new ArrayList();
	public List available_talents = new ArrayList();
	
	public static void main(String[] args) {
	}

	
	public void AddCareer(String career, boolean firstTime) {
		// Load the career from the files
		System.out.println("adding career "+career+" to "+this.name+"...");
		Career myCareer = new Career();
		myCareer = Career.initilizeCareer(career);
		this.career = career;
		this.career_path.add(career);
		
		for (int i=0; i < 8; i++) {
			this.advance_scheme[i] = myCareer.advance_scheme[i] - (this.advance_taken[i]*5);
			if (this.advance_scheme[i] < 0) { this.advance_scheme[i]=0; }
		}
		for (int i=8; i < 16; i++) {
			this.advance_scheme[i] = myCareer.advance_scheme[i] - this.advance_taken[i];
			if (this.advance_scheme[i] < 0) { this.advance_scheme[i]=0; }

		}
		
		// TODO
		// check for duplicate skills; no more than 3
		// this works.. but need to check for OR and compare each one
		int found=0;
	//	this.available_skills = myCareer.skills;
		for (int i=0; i < myCareer.skills.size(); i++) {
			for (int j=0; j < this.skills.size(); j++) {
				if (this.skills.get(j).equals(myCareer.skills.get(i))) {
					found++;
				}
			}
			if (found < 3) {
				this.available_skills.add(myCareer.skills.get(i));
				found=0;
			} else {
				System.out.println("already have 3 skills in "+myCareer.skills.get(i));
				found=0;
			}
		}
		
		
		
		// check for duplicate talents and do not add them
		// loop through the myCareer.talents and see if they are in
		//  this.talents.  If not, add them to the new array 
		// this.available_talents
		// TODO
		// this works.. but need to check for OR and compare each one
		boolean match = false;
		for (int i=0; i < myCareer.talents.size(); i++) {
			for (int j=0; j < this.talents.size(); j++) {
				if (this.talents.get(j).equals(myCareer.talents.get(i))) {
					match = true;
				}
			}
			if (!match) {
				this.available_talents.add(myCareer.talents.get(i));
			} else {
				match = false;
			}
		}
		
		// if this is the first career, add in all the trappings
		if (firstTime) {
			for (int i=0; i < myCareer.trappings.size(); i++) {
				this.trappings.add(myCareer.trappings.get(i));
			}
			
		}
	}

	public void AddCareer(String career) {
		AddCareer(career, true);
	}
		

	public void addtostat(int j, int i) {
		this.talent_bonus[j]=i;
	}

}
