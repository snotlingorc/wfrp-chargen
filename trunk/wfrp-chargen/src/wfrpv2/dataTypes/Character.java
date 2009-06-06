package wfrpv2.dataTypes;

import java.util.ArrayList;
import java.util.List;

import wfrpv2.helpers.GeneralFunctions;

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
	public List<String> talents = new ArrayList<String>();
	public List<String> skills = new ArrayList<String>();
	public String career = "None";
	public List<String> career_path = new ArrayList<String>();
	public List<String> trappings = new ArrayList<String>();
	public List<String> available_skills = new ArrayList<String>();
	public List<String> available_talents = new ArrayList<String>();
	
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
		
	
		// check for duplicate skills; no more than 3
		int found=0;
		for (int i=0; i < myCareer.skills.size(); i++) {
			for (int j=0; j < this.skills.size(); j++) {
				if (this.skills.get(j).equals(myCareer.skills.get(i))) {
					found++;
				} else {
					String Element = (String) myCareer.skills.get(i);
					// check for OR and compare each part
					if (wfrpv2.helpers.GeneralFunctions.checkForOR(Element)) {
						// if OR found - check each part if found - remove from list
						String[] ORS = Element.split(" OR ");
						for (int x =0; x<ORS.length; x++) {
							if (ORS[x].equals(this.skills.get(j))) {
								found++;								
								if (found == 3) {
									// remove from list
									// get the length and rebuilt to String with all but x
									this.available_skills.add(GeneralFunctions.removeFromOR(ORS, this.skills.get(j)));
								}
							}
						}
					} 
				}
			}
			if (found < 3) {
				this.available_skills.add((String) myCareer.skills.get(i));
			}
			found=0;
			
		}
		
		
		
		// check for duplicate talents and do not add them
		// loop through the myCareer.talents and see if they are in
		//  this.talents.  If not, add them to the new array 
		// this.available_talents
		boolean match = false;
		for (int i=0; i < myCareer.talents.size(); i++) {
			for (int j=0; j < this.talents.size(); j++) {
				if (this.talents.get(j).equals(myCareer.talents.get(i))) {
					match = true;
				} else {  // Check for OR
					String Element = (String) myCareer.talents.get(i);
					// check for OR and compare each part
					if (wfrpv2.helpers.GeneralFunctions.checkForOR(Element)) {
						// if OR found - check each part if found - remove from list
						String[] ORS = Element.split(" OR ");
						for (int x =0; x<ORS.length; x++) {
							if (ORS[x].equals(this.talents.get(j))) {
								match = true;								
								if (match) {
									// remove from list
									// get the length and rebuilt to String with all but x
									this.available_talents.add(GeneralFunctions.removeFromOR(ORS, this.talents.get(j)));
								}
							}
						}
					} 
				}
			}
			if (!match) {
				this.available_talents.add((String) myCareer.talents.get(i));
			} else {
				match = false;
			}
		}
		
		
		
		
		// if this is the first career, add in all the trappings
		if (firstTime) {
			for (int i=0; i < myCareer.trappings.size(); i++) {
				this.trappings.add((String) myCareer.trappings.get(i));
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
