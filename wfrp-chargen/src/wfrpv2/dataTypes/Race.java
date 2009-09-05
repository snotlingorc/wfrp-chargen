package wfrpv2.dataTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rpg.XMLFile.XMLDocument;
import rpg.dieRoller.dieRoller;
import org.w3c.dom.Element;

/*
 * Created on May 10, 2005
 */

/**
 * @author Mathew Anderson
 * @author http://www.snotling.org
 * 
 *         Race template. Used when loading a race. Contains all the information
 *         about a character.
 */
public class Race {

	public String description = "Race Type";
	public List<String> career = new ArrayList<String>();
	public List<String> name = new ArrayList<String>();
	public int[] profile = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	public HashMap<String, String> wounds = new HashMap<String,String>();
	public HashMap<String, String> fate = new HashMap<String,String>();
	public HashMap<String, String> age = new HashMap<String,String>();
	public HashMap<String, String> height = new HashMap<String,String>();
	public HashMap<String, String> weight = new HashMap<String,String>();
	public HashMap<String, String> eyecolor = new HashMap<String,String>();
	public HashMap<String, String> haircolor = new HashMap<String,String>();
	public HashMap<String, String> hairstyle = new HashMap<String,String>();
	public HashMap<String, String> siblings = new HashMap<String,String>();
	public HashMap<String, String> birthplace = new HashMap<String,String>();
	public List<String> skills = new ArrayList<String>();
	public List<String> talents = new ArrayList<String>();
	public List<String> trappings = new ArrayList<String>();

	public static void main(String[] args) {
	}

	public static Race initilizeRace(String race) {
		Race thisRace = new Race();
		XMLDocument XMLRace = new XMLDocument("race");
		String file = openRaceFile(race);

		//System.out.println("Looking for file: " + file);

		XMLRace.setFileName(file);
		XMLRace.loadFile(false);
		try {
			thisRace.description = XMLRace.getValue("/Race/Description");
			thisRace.profile[0] = dieRoller.DString(XMLRace
					.getValue("/Race/Profile/WS"));
			thisRace.profile[1] = dieRoller.DString(XMLRace
					.getValue("/Race/Profile/BS"));
			thisRace.profile[2] = dieRoller.DString(XMLRace
					.getValue("/Race/Profile/S"));
			thisRace.profile[3] = dieRoller.DString(XMLRace
					.getValue("/Race/Profile/T"));
			thisRace.profile[4] = dieRoller.DString(XMLRace
					.getValue("/Race/Profile/Ag"));
			thisRace.profile[5] = dieRoller.DString(XMLRace
					.getValue("/Race/Profile/Int"));
			thisRace.profile[6] = dieRoller.DString(XMLRace
					.getValue("/Race/Profile/WP"));
			thisRace.profile[7] = dieRoller.DString(XMLRace
					.getValue("/Race/Profile/Fel"));
			thisRace.profile[8] = dieRoller.DString(XMLRace
					.getValue("/Race/Profile/A"));
			thisRace.profile[9] = dieRoller.DString(XMLRace
					.getValue("/Race/Profile/W"));
			thisRace.profile[10] = dieRoller.DString(XMLRace
					.getValue("/Race/Profile/SB"));
			thisRace.profile[11] = dieRoller.DString(XMLRace
					.getValue("/Race/Profile/TB"));
			thisRace.profile[12] = dieRoller.DString(XMLRace
					.getValue("/Race/Profile/M"));
			thisRace.profile[13] = dieRoller.DString(XMLRace
					.getValue("/Race/Profile/Mag"));
			thisRace.profile[14] = dieRoller.DString(XMLRace
					.getValue("/Race/Profile/IP"));
			thisRace.profile[15] = dieRoller.DString(XMLRace
					.getValue("/Race/Profile/Fate"));
		} catch (Exception e1) {
			//e1.printStackTrace();
		}
		
		for (int i = 1; i < 40; i++) {
			try {
				thisRace.career
						.add(XMLRace.getValue("/Race/Career[" + i + "]"));
			} catch (Exception e2) {
				//e2.printStackTrace();
			}
			try {
				thisRace.name.add(XMLRace.getValue("/Race/Name[" + i + "]"));
			} catch (Exception e3) {
				//e3.printStackTrace();
			}

			try {
				Element wounds = XMLRace.getElement("/Race/Profile/Wounds["+ i + "]");
				String name = wounds.getAttribute("name");
				String value = wounds.getAttribute("value");
				thisRace.wounds.put(name,value);
			} catch (Exception e) {
				//e.printStackTrace();
			}
			try {
				Element fate = XMLRace.getElement("/Race/Profile/Fate["+ i + "]");
				String name = fate.getAttribute("name");
				String value = fate.getAttribute("value");
				thisRace.fate.put(name,value);
			} catch (Exception e) {
				//e.printStackTrace();
			}
			
			try {
				Element age = XMLRace.getElement("/Race/Characteristics/Age["+ i + "]");
				String name = age.getAttribute("name");
				String value = age.getAttribute("value");
				thisRace.age.put(name,value);
			} catch (Exception e) {
				//e.printStackTrace();
			}
			try {
				Element height = XMLRace.getElement("/Race/Characteristics/Height["+ i + "]");
				String name = height.getAttribute("name");
				String value = height.getAttribute("value");
				thisRace.height.put(name,value);
			} catch (Exception e) {
				//e.printStackTrace();
			}
			try {
				Element weight = XMLRace.getElement("/Race/Characteristics/Weight["+ i + "]");
				String name = weight.getAttribute("name");
				String value = weight.getAttribute("value");
				thisRace.weight.put(name,value);
			} catch (Exception e) {
				//e.printStackTrace();
			}
			try {
				Element eyecolor = XMLRace.getElement("/Race/Characteristics/Eyecolor["+ i + "]");
				String name = eyecolor.getAttribute("name");
				String value = eyecolor.getAttribute("value");
				thisRace.eyecolor.put(name,value);
			} catch (Exception e) {
				//e.printStackTrace();
			}
			try {
				Element haircolor = XMLRace.getElement("/Race/Characteristics/Haircolor["+ i + "]");
				String name = haircolor.getAttribute("name");
				String value = haircolor.getAttribute("value");
				thisRace.haircolor.put(name,value);
			} catch (Exception e) {
				//e.printStackTrace();
			}
			try {
				Element hairstyle = XMLRace.getElement("/Race/Characteristics/Hairstyle["+ i + "]");
				String name = hairstyle.getAttribute("name");
				String value = hairstyle.getAttribute("value");
				thisRace.hairstyle.put(name,value);
			} catch (Exception e) {
				//e.printStackTrace();
			}
			try {
				Element siblings = XMLRace.getElement("/Race/Characteristics/Siblings["+ i + "]");
				String name = siblings.getAttribute("name");
				String value = siblings.getAttribute("value");
				thisRace.siblings.put(name,value);
			} catch (Exception e) {
				//e.printStackTrace();
			}
			try {
				Element birthplace = XMLRace.getElement("/Race/Characteristics/Birthplace["+ i + "]");
				String name = birthplace.getAttribute("name");
				String value = birthplace.getAttribute("value");
				thisRace.birthplace.put(name,value);
			} catch (Exception e) {
				//e.printStackTrace();
			}

			
			try {
				thisRace.talents.add(XMLRace.getValue("/Race/Talents[" + i
						+ "]"));
			} catch (Exception e4) {
				//e4.printStackTrace();
			}
			try {
				thisRace.skills
						.add(XMLRace.getValue("/Race/Skills[" + i + "]"));
			} catch (Exception e5) {
				//e5.printStackTrace();
			}
			try {
				thisRace.trappings
						.add(XMLRace.getValue("/Race/Trappings[" + i + "]"));
			} catch (Exception e6) {
				//e6.printStackTrace();
			}
		}
		return thisRace;
	}

	public static String openRaceFile(String race) {
		String file = System.getProperty("user.dir");
		file = file.concat("/dataFiles/profile" + race + ".xml");
		return file;
	}

	public static int[] getMercyStats(String race) {
		int[] profile = { 0, 0, 0, 0, 0, 0, 0, 0 };
		XMLDocument XMLRace = new XMLDocument("race");
		String file = openRaceFile(race);

		XMLRace.setFileName(file);
		XMLRace.loadFile(false);
		try {
			profile[0] = 11+dieRoller.DRString(XMLRace.getValue("/Race/Profile/WS"));
			profile[1] = 11+dieRoller.DRString(XMLRace.getValue("/Race/Profile/BS"));
			profile[2] = 11+dieRoller.DRString(XMLRace.getValue("/Race/Profile/S"));
			profile[3] = 11+dieRoller.DRString(XMLRace.getValue("/Race/Profile/T"));
			profile[4] = 11+dieRoller.DRString(XMLRace.getValue("/Race/Profile/Ag"));
			profile[5] = 11+dieRoller.DRString(XMLRace.getValue("/Race/Profile/Int"));
			profile[6] = 11+dieRoller.DRString(XMLRace.getValue("/Race/Profile/WP"));
			profile[7] = 11+dieRoller.DRString(XMLRace.getValue("/Race/Profile/Fel"));
		} catch (Exception e1) {
			//e1.printStackTrace();
		}
		return profile;
	}
}
