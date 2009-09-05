package wfrpv2.dataTypes;

import java.util.ArrayList;
import java.util.List;

import rpg.XMLFile.XMLDocument;

/*
 * Created on May 10, 2005
 */

/**
 * @author Mathew Anderson
 * @author http://www.snotling.org
 *
 * Career template.  Used when reading in a career
 * from a file. Contains all the information about a
 * career that is needed.
 */
public class Career {

	public String name = "Career Name";
	public String[] profile = {"WS", "BS", "S", "T", "Ag", "Int", "WP", "Fel", "A", "W", "SB", "TB", "M", "Mag", "IP", "FP"};
	public int[] advance_scheme = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	public List<String> skills = new ArrayList<String>();
	public List<String> talents = new ArrayList<String>();
	public List<String> trappings = new ArrayList<String>();
	public List<String> exits = new ArrayList<String>();
	
	public static void main(String[] args) { 
	}
	
	public static Career initilizeCareer(String career) {
		Career thisCareer = new Career();

		XMLDocument XMLCareer = new XMLDocument("career");
		String file = System.getProperty("user.dir");
		file = file.concat("/dataFiles/Career/"+ career + ".xml");

		//System.out.println("Looking for file: " + file);
		XMLCareer.setFileName(file);
		XMLCareer.loadFile(false);
		try {
			thisCareer.name = XMLCareer.getValue("/Career/Name");
			thisCareer.advance_scheme[0] = misc.intsStrings.toInt(XMLCareer.getValue("/Career/WS"));
			thisCareer.advance_scheme[1] = misc.intsStrings.toInt(XMLCareer.getValue("/Career/BS"));
			thisCareer.advance_scheme[2] = misc.intsStrings.toInt(XMLCareer.getValue("/Career/S"));
			thisCareer.advance_scheme[3] = misc.intsStrings.toInt(XMLCareer.getValue("/Career/T"));
			thisCareer.advance_scheme[4] = misc.intsStrings.toInt(XMLCareer.getValue("/Career/Ag"));
			thisCareer.advance_scheme[5] = misc.intsStrings.toInt(XMLCareer.getValue("/Career/Int"));
			thisCareer.advance_scheme[6] = misc.intsStrings.toInt(XMLCareer.getValue("/Career/WP"));
			thisCareer.advance_scheme[7] = misc.intsStrings.toInt(XMLCareer.getValue("/Career/Fel"));
			thisCareer.advance_scheme[8] = misc.intsStrings.toInt(XMLCareer.getValue("/Career/A"));
			thisCareer.advance_scheme[9] = misc.intsStrings.toInt(XMLCareer.getValue("/Career/W"));
			thisCareer.advance_scheme[10] = misc.intsStrings.toInt(XMLCareer.getValue("/Career/SB"));
			thisCareer.advance_scheme[11] = misc.intsStrings.toInt(XMLCareer.getValue("/Career/TB"));
			thisCareer.advance_scheme[12] = misc.intsStrings.toInt(XMLCareer.getValue("/Career/M"));
			thisCareer.advance_scheme[13] = misc.intsStrings.toInt(XMLCareer.getValue("/Career/Mag"));
			thisCareer.advance_scheme[14] = misc.intsStrings.toInt(XMLCareer.getValue("/Career/IP"));
			thisCareer.advance_scheme[15] = misc.intsStrings.toInt(XMLCareer.getValue("/Career/FP"));
		} catch (Exception e1) {
			System.out.println ("Error in getting Career information from "+career);
			e1.printStackTrace();
		}
		for (int i = 1; i < 40; i++) {
		try {
			thisCareer.skills
					.add(XMLCareer.getValue("/Career/Skills[" + i + "]"));
		} catch (Exception e5) {
			//e5.printStackTrace();
		}
		try {
			thisCareer.talents
					.add(XMLCareer.getValue("/Career/Talents[" + i + "]"));
		} catch (Exception e5) {
			//e5.printStackTrace();
		}
		try {
			thisCareer.trappings
					.add(XMLCareer.getValue("/Career/Trappings[" + i + "]"));
		} catch (Exception e5) {
			//e5.printStackTrace();
		}
		try {
			thisCareer.exits
					.add(XMLCareer.getValue("/Career/Exits[" + i + "]"));
		} catch (Exception e5) {
			//e5.printStackTrace();
		}
		}
		
		
		return thisCareer;
	}
}
