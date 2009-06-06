package wfrpv2.gui;

/*
 * Created on
 * 
 * 
 *
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import wfrpv2.dataTypes.Career;
import wfrpv2.dataTypes.Character;
import wfrpv2.helpers.CharacterFunctions;
import wfrpv2.helpers.CreateCharacter;
import wfrpv2.helpers.GeneralFunctions;
import wfrpv2.helpers.IOFunctions;
import wfrpv2.helpers.load;
import wfrpv2.helpers.print;
import wfrpv2.helpers.save;

/**
 * @author Mathew
 *
 */
public class frontend extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame mainFrame;
	JPanel leftPanel, mainPanel;
	JPanel createPanel, buttonPanel, purchasePanel;
	JTabbedPane rightPane;
	static JComponent characterFrontPanel;
	static JComponent characterBackPanel;
	JButton generateCharacter, saveCharacter, loadCharacter, printCharacter, about, edit;
	JLabel raceLabel, sexLabel, careerLabel, optionalLabel;
	JLabel blankLabel, purchaseLabel;
	JLabel warhammerLogo;
	JComboBox raceChoices = null;
	JComboBox sexChoices = null;
	JComboBox careerChoices = null;
	
	JFileChooser fc;
	
	// sheet labels and boxes
	JLabel testLabel;
	String generatedCharacter;
	
	// components of the purchase frame
	JComboBox pSkills = null;
	JComboBox pTalents = null;
	JComboBox pTrappings = null;
	JComboBox pCareerExits = null;
	JComboBox pCareerChange = null;
	JComboBox pAttribChange = null;
	
	
	JLabel pSkillLabel, pTalentLabel, pTrappingLabel, pCareerExitLabel, pCareerLabel;
	JLabel pMoneyLabel, pExpLabel, pExpLeftLabel, pAttribLabel;
	JLabel pMoneyChangeLabel, pExpChangeLabel, pExpLeftChangeLabel;
	JLabel pMoneyChangeGLabel, pMoneyChangeSLabel, pMoneyChangeCLabel;

	String[] blank = {"None"};
	String[] sexes = {"Random", "Male", "Female"};
	
	//Need to make this dynamic
	String[] races = IOFunctions.getRaces();
	//{"Human", "Elf", "Dwarf", "Halfling"};
	String currentRace = races[0];
	String[] careers = IOFunctions.getCareers(currentRace);
	
	Character character = new Character();
	
	Boolean firstTime=true;
	
    public frontend() {
        //Create and set up the window.
        mainFrame = new JFrame("Warhammer PC Generator");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(new Dimension(120, 140));

        //Create and set up the panel.
        //createPanel = new JPanel(new GridLayout(4, 4));
        createPanel = new JPanel(); //new GridLayout(1, 2, 3, 4));
        createPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        buttonPanel = new JPanel();
        purchasePanel = new JPanel(); //new GridLayout(1, 2, 3, 4));
        purchasePanel.setLayout(new GridBagLayout());
        GridBagConstraints p = new GridBagConstraints();
        p.fill = GridBagConstraints.HORIZONTAL;

        //Add the widgets.
        //Start with the createPanel
        
    	ImageIcon warhammerimage = guiHelpers.createImageIcon("wfrplogo.png");
    	warhammerLogo = new JLabel(warhammerimage);
    	c.weightx = 0.5;
    	c.gridx = 0;
    	c.gridy = 0;
    	c.gridwidth = 3;
    	createPanel.add(warhammerLogo, c);
    	
    	raceLabel = new JLabel("Select Race", SwingConstants.LEFT);
    	c.weightx = 0.5;
    	c.gridx = 0;
    	c.gridy = 1;
    	c.gridwidth = 1;
    	createPanel.add(raceLabel, c);
    	
    	//Create combo box with race choices.
    	raceChoices = new JComboBox(races);
    	raceChoices.setSelectedIndex(0);
    	c.weightx = 0.5;
    	c.gridx = 0;
    	c.gridy = 2;
    	c.gridwidth = 1;
    	createPanel.add(raceChoices,c);
    	
    	optionalLabel = new JLabel("Optional", SwingConstants.CENTER);
    	c.weightx = 0.5;
    	c.gridx = 1;
    	c.gridy = 1;
    	c.gridwidth = 2;
    	createPanel.add(optionalLabel,c);
    	
    	sexLabel = new JLabel("Select Gender", SwingConstants.RIGHT);
    	c.weightx = 0.5;
    	c.gridx = 1;
    	c.gridy = 2;
    	c.gridwidth = 1;
    	createPanel.add(sexLabel,c);
    	
    	sexChoices = new JComboBox(sexes);
    	sexChoices.setSelectedIndex(0);
    	c.weightx = 0.5;
    	c.gridx = 2;
    	c.gridy = 2;
    	c.gridwidth = 1;
    	createPanel.add(sexChoices,c);
  
    	// commented out - thinking of moving this below.
    	// generation will create the basic character - then the first career 
    	// can be applied
    	careerLabel = new JLabel("Select Career", SwingConstants.RIGHT);
    	c.weightx = 0.5;
    	c.gridx = 1;
    	c.gridy = 3;
    	c.gridwidth = 1;
    	createPanel.add(careerLabel,c);
    	
    	careerChoices = new JComboBox(careers);
    	careerChoices.setSelectedIndex(0);
    	c.weightx = 0.5;
    	c.gridx = 2;
    	c.gridy = 3;
    	c.gridwidth = 1;
    	createPanel.add(careerChoices,c);
    	
    	generateCharacter = new JButton("Generate");
    	c.weightx = 0.5;
    	c.gridx = 1;
    	c.gridy = 4;
    	c.gridwidth = 1;
    	createPanel.add(generateCharacter,c);
    	
    	//Listen to events from the generate button.
        generateCharacter.addActionListener(this);
        //Listen to events from the combo box raceChoices.
        raceChoices.addActionListener(this);
    	
        // Create the right side tabbed panel
        rightPane = new JTabbedPane();

        characterFrontPanel = guiHelpers.makeTextPanel("Panel #1");
        rightPane.addTab("Front", characterFrontPanel);
        rightPane.setMnemonicAt(0, KeyEvent.VK_1);
        characterFrontPanel.setPreferredSize(new Dimension(410, 50));

       characterBackPanel = guiHelpers.makeTextPanel("Panel #2");
        rightPane.addTab("Back", characterBackPanel);
        rightPane.setMnemonicAt(1, KeyEvent.VK_2);
        
        //Uncomment the following line to use scrolling tabs.
        //tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
          
        //Start with the createPanel
        purchaseLabel = new JLabel("Purchase Area - Buy skills and Talents! - Enter new EXP!");
        p.weightx = 0.5;
        p.gridx = 0;
        p.gridy = 0;
        p.gridwidth = 4;
        purchasePanel.add(purchaseLabel, p);

       	pSkillLabel = new JLabel("Skills");
     	p.weightx = 0.5;
    	p.gridx = 0;
    	p.gridy = 3;
    	p.gridwidth = 1;
    	purchasePanel.add(pSkillLabel, p);
    	
    	pTalentLabel = new JLabel("Talents");
    	p.weightx = 0.5;
    	p.gridx = 2;
    	p.gridy = 3;
    	p.gridwidth = 1;
    	purchasePanel.add(pTalentLabel, p);
    	
    	pTrappingLabel = new JLabel("Trappings");
    	p.weightx = 0.5;
    	p.gridx = 0;
    	p.gridy = 4;
    	p.gridwidth = 1;
    	purchasePanel.add(pTrappingLabel, p);
    	
    	pCareerExitLabel = new JLabel("Career Exit");
    	p.weightx = 0.5;
    	p.gridx = 2;
    	p.gridy = 4;
    	p.gridwidth = 1;
    	purchasePanel.add(pCareerExitLabel, p);
    	
    	pCareerLabel = new JLabel("Career Change");
    	p.weightx = 0.5;
    	p.gridx = 2;
    	p.gridy = 5;
    	p.gridwidth = 1;
    	purchasePanel.add(pCareerLabel, p);
    	
    	pMoneyLabel = new JLabel("Money");
    	p.weightx = 0.5;
    	p.gridx = 0;
    	p.gridy = 1;
    	p.gridwidth = 1;
    	purchasePanel.add(pMoneyLabel, p);
    	
    	pExpLabel = new JLabel("Exp.Total");
    	p.weightx = 0.5;
    	p.gridx = 0;
    	p.gridy = 2;
    	p.gridwidth = 1;
    	purchasePanel.add(pExpLabel, p);
    	
    	pExpLeftLabel = new JLabel("Left");
    	p.weightx = 0.5;
    	p.gridx = 2;
    	p.gridy = 2;
    	p.gridwidth = 1;
    	purchasePanel.add(pExpLeftLabel, p);
    	
    	pMoneyChangeGLabel = new JLabel("0 gc");
    	p.weightx = 0.5;
    	p.gridx = 1;
    	p.gridy = 1;
    	p.gridwidth = 3;
    	purchasePanel.add(pMoneyChangeGLabel, p);
    	
    	pMoneyChangeSLabel = new JLabel("0 ss");
    	p.weightx = 0.5;
    	p.gridx = 2;
    	p.gridy = 1;
    	p.gridwidth = 3;
    	purchasePanel.add(pMoneyChangeSLabel, p);
    	
    	pMoneyChangeCLabel = new JLabel("0 cp");
    	p.weightx = 0.5;
    	p.gridx = 3;
    	p.gridy = 1;
    	p.gridwidth = 3;
    	purchasePanel.add(pMoneyChangeCLabel, p);
    	
    	pExpChangeLabel = new JLabel("0");
    	p.weightx = 0.5;
    	p.gridx = 1;
    	p.gridy = 2;
    	p.gridwidth = 1;
    	purchasePanel.add(pExpChangeLabel, p);
    	
    	pExpLeftChangeLabel = new JLabel("0");
    	p.weightx = 0.5;
    	p.gridx = 3;
    	p.gridy = 2;
    	p.gridwidth = 1;
    	purchasePanel.add(pExpLeftChangeLabel, p);
    	
    	pAttribLabel = new JLabel("Advance");
    	p.weightx = 0.5;
    	p.gridx = 0;
    	p.gridy = 5;
    	p.gridwidth = 1;
    	purchasePanel.add(pAttribLabel, p);
    	
    	ComboBoxRenderer renderer = new ComboBoxRenderer();
    	renderer.setPreferredSize(new Dimension(80, 15));
    	
    	pSkills = new JComboBox(blank);
    	pSkills.setRenderer(renderer);
    	pSkills.setSelectedIndex(0);
    	p.weightx = 0.5;
    	p.gridx = 1;
    	p.gridy = 3;
    	p.gridwidth = 1;
    	purchasePanel.add(pSkills, p);
    	pSkills.addActionListener(pSkillsAction);

    	
    	pTalents = new JComboBox(blank);
    	pTalents.setRenderer(renderer);
    	pTalents.setSelectedIndex(0);
    	p.weightx = 0.5;
    	p.gridx = 3;
    	p.gridy = 3;
    	p.gridwidth = 1;
    	purchasePanel.add(pTalents, p);
    	pTalents.addActionListener(pTalentsAction);
    	
    	pTrappings = new JComboBox(blank);
    	pTrappings.setRenderer(renderer);
    	pTrappings.setSelectedIndex(0);
    	p.weightx = 0.5;
    	p.gridx = 1;
    	p.gridy = 4;
    	p.gridwidth = 1;
    	purchasePanel.add(pTrappings, p);
    	pTrappings.addActionListener(pTrappingsAction);

    	pCareerExits = new JComboBox(blank);
    	pCareerExits.setRenderer(renderer);
    	pCareerExits.setSelectedIndex(0);
    	p.weightx = 0.5;
    	p.gridx = 3;
    	p.gridy = 4;
    	p.gridwidth = 1;
    	purchasePanel.add(pCareerExits, p);
    	pCareerExits.addActionListener(pCareerExitsAction);
 	
    	pCareerChange = new JComboBox(blank);
    	pCareerChange.setRenderer(renderer);
    	pCareerChange.setSelectedIndex(0);
    	p.weightx = 0.5;
    	p.gridx = 3;
    	p.gridy = 5;
    	p.gridwidth = 1;
    	purchasePanel.add(pCareerChange, p);
    	pCareerChange.addActionListener(pCareerChangeAction);
 	
    	pAttribChange = new JComboBox(blank);
    	pAttribChange.setRenderer(renderer);
    	pAttribChange.setSelectedIndex(0);
    	p.weightx = 0.5;
    	p.gridx = 1;
    	p.gridy = 5;
    	p.gridwidth = 1;
    	purchasePanel.add(pAttribChange, p);
    	pAttribChange.addActionListener(pAttribChangeAction);
    	
    	//    	Add the widgets.
        addWidgets();

        //Done adding the widgets
        //Set the default button.
        mainFrame.getRootPane().setDefaultButton(generateCharacter);
        

        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        //Add the panel to the window.
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        createPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Character Creation"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        purchasePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Purchase Stats"),
                BorderFactory.createEmptyBorder(5,5,5,5)));

        //Add the select and display panels to the main panel.
        leftPanel.add(createPanel, BorderLayout.PAGE_START);
        leftPanel.add(buttonPanel);
        leftPanel.add(purchasePanel);
        
        mainPanel.add(leftPanel);
       // mainPanel.add(characterPanel);
        mainPanel.add(rightPane);
        mainFrame.getContentPane().add(mainPanel);
      //  mainFrame.getContentPane().add(rightPane, BorderLayout.CENTER);
        

        //Display the window.
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    
    //private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
    //    JFrame.setDefaultLookAndFeelDecorated(true);

    //    frontend converter = new frontend();
    //}
    
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //createAndShowGUI();
                new frontend();
            }
        });
    }

    
	/**
	 * @param character
	 * @return displays the character sheet in the display panel
	 * also create and write the purchase display panel
	 */
    public void displaySheet(Character character) {
    	//System.out.println("Character:" +character.name);
 
    	//TODO - need to finish
    	try {
			characterFrontPanel = guiHelpers.makeTextPanel(guiHelpers.update_sheet_front(character));
		} catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		
    	try {
			characterBackPanel = guiHelpers.makeTextPanel(guiHelpers.update_sheet_back(character));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	rightPane.removeAll();
    	rightPane.addTab("Front", characterFrontPanel);
    	rightPane.addTab("Back", characterBackPanel);
    	mainFrame.pack();
    	
    	// build the Purchase area
    	
    	//Career Advancement
    	GridBagConstraints p = new GridBagConstraints();
    	//    	p.fill = GridBagConstraints.HORIZONTAL;
    	p.weightx = 0.5;
    	p.gridx = 3;
    	p.gridy = 4;
    	p.gridwidth = 1;
    	
    	// remove the dropdown box and fill in the right careers
    	pCareerExits.removeActionListener(pCareerExitsAction);
    	pAttribChange.removeActionListener(pAttribChangeAction);
    	pSkills.removeActionListener(pSkillsAction);
    	pTalents.removeActionListener(pTalentsAction);
    	pCareerExits.removeAllItems();
    	pAttribChange.removeAllItems();
    	pSkills.removeAllItems();
    	pTalents.removeAllItems();
    	
    	Career career = new Career();
    	if (character.career.equals("Peeon")) {
    	} else {
    		career = Career.initilizeCareer(character.career);
    	}

		if (career.exits.size() == 0) {
			pCareerExits.addItem("None");
		} else {
			pCareerExits.addItem("Select Exit");
		}
    	for ( int i=0; i< career.exits.size(); i++ ) {
    			String element = (String) career.exits.get(i);
    			pCareerExits.addItem(element);
    	}
    	if (character.available_skills.size() == 0) {
    		pSkills.addItem("None");
		} else {
			pSkills.addItem("Select Skills");
		}
    	for ( int i=0; i< character.available_skills.size(); i++ ) {
			String element = (String) character.available_skills.get(i);
			pSkills.addItem(element);
		}
    	if (character.available_talents.size() == 0) {
    		pTalents.addItem("None");
		} else {
			pTalents.addItem("Select Talents");
		}
    	for ( int i=0; i< character.available_talents.size(); i++ ) {
			String element = (String) character.available_talents.get(i);
			pTalents.addItem(element);
		}
    	pCareerExits.setSelectedIndex(0);
    	
    	//TODO make it say none of the text below
    	pAttribChange.addItem("Select Attributes");
		
    	// do the primary ones first..
    	for ( int i=0; i<8; i++ ) {
			int value = career.advance_scheme[i];
			// check if the element is 0 (do NOT add)
			if (value > 0) {
				// Now see what advances have been taken, and only
				// display the ones that CAN be taken.
				if ((value / 5) > character.advance_taken[i] ) {
					// Beauty, the character can take more in the profile
					String element = career.profile[i];
					//element = element.concat(": "+die_roll.toString(value));
					pAttribChange.addItem(element);
				}
			}
			
		}
		// now the secondary ones
    	for ( int i=8; i<16; i++ ) {
			int value = career.advance_scheme[i];
			// check if the element is 0 (do NOT add)
			if (value > 0) {
				// Now see what advances have been taken, and only
				// display the ones that CAN be taken.
				
				if (value > character.advance_taken[i]) {
					// Beauty, the character can take more in the profile
					String element = career.profile[i];
					//element = element.concat(": "+die_roll.toString(value));
					pAttribChange.addItem(element);
				}
			}    		
    	}
    	mainFrame.pack();
    	
    	pCareerExits.addActionListener(pCareerExitsAction);
    	pAttribChange.addActionListener(pAttribChangeAction);
    	pSkills.addActionListener(pSkillsAction);
    	pTalents.addActionListener(pTalentsAction);
    }  
    
    
    
 private void addWidgets() {
    	
    	// widgets for buttonPanel
    	saveCharacter = new JButton("Save");
    	saveCharacter.addActionListener(this);
    	loadCharacter = new JButton("Load");
    	loadCharacter.addActionListener(this);
    	printCharacter = new JButton("Print");
    	printCharacter.addActionListener(this);
    	about = new JButton("About");
    	about.addActionListener(this);
    	//
    	//TODO move this guy somewhere else
    	edit = new JButton("Edit");
    	edit.addActionListener(this);
    	generatedCharacter = "Test Character goes in this panel.";
    
    	//add the bits to the container
    	buttonPanel.add(saveCharacter);
    	buttonPanel.add(loadCharacter);
    	buttonPanel.add(printCharacter);
    	buttonPanel.add(about);
    	buttonPanel.add(edit);
    	
 ////   	characterFrontPanel = makeTextPanel(generatedCharacter);
    	
    }
    
    public void actionPerformed(ActionEvent event) {
    	System.out.println(" 1 "+event.getActionCommand());
    	System.out.println(" 2 "+event.getSource());
    	System.out.println(" 3 "+event.getClass());
    	
    	// Saving a Character
    	if (event.getSource() == saveCharacter) {
    		try {
				save.main(character);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	// Loading a Character
    	if (event.getSource() == loadCharacter) {
    		final JFileChooser fc = new JFileChooser();
    		String curDir = System.getProperty("user.dir");
    		File startingDir = new File(curDir+"/dataFiles/Characters/");
    		fc.setSelectedFile(startingDir) ;
    		int returnVal = fc.showOpenDialog(frontend.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try {
					character = load.main(file);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//now that we have the character, we need to 
				//build the panels	
				// display the sheet
				displaySheet(character);
            }
    	}
    	
    	// Printing a Character
    	// TODO - revisit in new  version
    	if (event.getSource() == printCharacter) {
    		try {
				print.main(character);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	// About the application
    	if (event.getSource() == about) {
    			guiHelpers.showAbout();
    	}
    	
    	// Edit the characteristics
    	if (event.getSource() == edit) {
    			character = guiHelpers.editCharacter(character);
    			displaySheet(character);
    	}
    	
    	
    	// TODO - revisit in new version
       if ("comboBoxChanged".equals(event.getActionCommand())) {
  
            //Update the career table
        	GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
        	c.weightx = 0.5;
        	c.gridx = 4;
        	c.gridy = 3;
        	c.gridwidth = 1;
        	// remove the dropdown box and fill in the right careers
        	careerChoices.removeAllItems();
        	
        	//event.getSource
        	careers = IOFunctions.getCareers((String) raceChoices.getSelectedItem());
        	   	
    		for (int i=0; i < careers.length; i++) {
    			careerChoices.addItem(careers[i]);
       		}
    		careerChoices.setSelectedIndex(0);
    		
        	mainFrame.pack();
        } 
       
	   	// Generating a Character

        if (event.getSource() == generateCharacter) {
        	//let us create the character
        	String gender = sexes[sexChoices.getSelectedIndex()];
        	String race = races[raceChoices.getSelectedIndex()];
    	   	String myCareer = careers[careerChoices.getSelectedIndex()];
    	   
        	// Create a Character
        	character = CreateCharacter.main(race, gender);
        	displaySheet(character);
        	character = CharacterFunctions.ShallyaMercy(character);
        	character.AddCareer(myCareer, true);
        	
        	//load the current career profile
        	displaySheet(character);
        	// check to see if this is the first time.. if so, take the free advance
           	if (firstTime) {
        		character = guiHelpers.freeAdvance(character, myCareer);
        		displaySheet(character);
        	}
        }
        
    }
    
    
    
    
    
//The various actions that happen when buttons are pressed.
    
    ActionListener pSkillsAction = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                String value = (String)cb.getSelectedItem();
                // Check for OR in the still list
                // prompt and add only that one
                character.available_skills.remove(value);
                String Type = "Skill";
                Object mySkill = guiHelpers.promptForOr(value, Type);
                boolean any = GeneralFunctions.checkForANY((String) mySkill);
                if (any) { 
                	character.skills.add((String) guiHelpers.promptForAny(mySkill, Type));
                } else {
                	character.skills.add((String) mySkill);
                }
                
                character = wfrpv2.helpers.GeneralFunctions.sortSkills(character);
                displaySheet(character);
		}
     };
     
    ActionListener pTalentsAction = new ActionListener()  {
        public void actionPerformed(ActionEvent e) {
                 JComboBox cb = (JComboBox)e.getSource();
                 String value = (String)cb.getSelectedItem();
                 //character.talents.add(value);
                 String Type = "Talent";
                 character.available_talents.remove(value);
                 Object myTalent = guiHelpers.promptForOr(value, Type);
                 boolean any = GeneralFunctions.checkForANY((String) myTalent);
                 if (any) { 
                	 Object thisTalent = guiHelpers.promptForAny(myTalent, Type);
                 	character.talents.add((String) thisTalent);
                     // Check for bonus and apply
                 	character = wfrpv2.helpers.GeneralFunctions.checkForTalentBonus(character, thisTalent);
                 } else {
                	 character.talents.add((String) myTalent);
                	 // Check for bonus and apply
                	 character = wfrpv2.helpers.GeneralFunctions.checkForTalentBonus(character, myTalent);
                 }

                 character = wfrpv2.helpers.GeneralFunctions.sortTalents(character);
                 displaySheet(character);
        }
      };
    ActionListener pTrappingsAction = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                String value = (String)cb.getSelectedItem();
                System.out.println("a trapping was selected "+ value);
                // TODO  need to do something with the trappings
                character = wfrpv2.helpers.GeneralFunctions.sortTrappings(character);
        }
      }; 
  	ActionListener pCareerExitsAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JComboBox cb = (JComboBox) e.getSource();
			String value = (String) cb.getSelectedItem();
			System.out.println("a Carrer exit was selected " + value);
			// remove current skills/talents/attributes
			character.available_skills = new ArrayList<String>();
			character.available_talents = new ArrayList<String>();

			character.AddCareer(value);
			
			displaySheet(character);
		}
	};
    ActionListener pCareerChangeAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    JComboBox cb = (JComboBox)e.getSource();
                    String value = (String)cb.getSelectedItem();
                    System.out.println("a Carrer change was selected"+ value);
            }
     	};
    ActionListener pAttribChangeAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                   	String bits = (String) pAttribChange.getSelectedItem();
                   	if (bits != null) {
                   		int Modifier = 1;
                   		
                   		//Check the number of the index. if it is greater than the 8th, then it is 1s else 5s
                   		int attribIndex = guiHelpers.lookupAttrib(bits);
                   		if (attribIndex < 8) {Modifier = 5; }
        				
        				character.current_profile[attribIndex] = character.current_profile[attribIndex] + Modifier;
                   		character.advance_taken[attribIndex]++;
                   		character.advance_scheme[attribIndex] = character.advance_scheme[attribIndex] - Modifier;
                   		
                   		pAttribChange.removeItem(bits);
                	
                  		displaySheet(character);
                   	}
            	}
            };

	
}