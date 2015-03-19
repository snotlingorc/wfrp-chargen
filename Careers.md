# Introduction #

This section is about careers.  How to add them, change them and such.
Also information dealing with how to work with careers better


# Details #

The xml markup for a Career is as follows.  I am using the Agitator as my example.
```
<?xml version="1.0" encoding="UTF-8"?>

<Career>
  <Name>Agitator</Name>
  <WS>5</WS>
  <BS>5</BS>
  <S>0</S>
  <T>0</T>
  <Ag>5</Ag>
  <Int>10</Int>
  <WP>0</WP>
  <Fel>10</Fel>
  <A>0</A>
  <W>2</W>
  <SB>0</SB>
  <TB>0</TB>
  <M>0</M>
  <Mag>0</Mag>
  <IP>0</IP>
  <FP>0</FP>

  <Skills>Academic Knowledge (History) OR Gossip</Skills>
  <Skills>Academic Knowledge (Law) OR Common Knowledge (the Empire)</Skills>
  <Skills>Concealment</Skills>
  <Skills>Charm</Skills>
  <Skills>Perception</Skills>
  <Skills>Read/Write</Skills>
  <Skills>Speak Language (Breton) OR Speak Language (Tilean)</Skills>
  <Skills>Speak Language (Reikspiel)</Skills>

  <Talents>Coolheaded OR Street Fighting</Talents>
  <Talents>Flee!</Talents>
  <Talents>Public Speaking</Talents>

  <Trappings>Light Armour (Leather Jack)</Trappings>
  <Trappings>One set of Good Craftmenship Clothes</Trappings>
  <Trappings>2d10 leaflets for various causes</Trappings>
  
  <Exits>Charlatan</Exits>
  <Exits>Demagogue</Exits>
  <Exits>Outlaw</Exits>
  <Exits>Politician</Exits>
  <Exits>Rogue</Exits>
  <Exits>Zealot</Exits>
  
</Career>
```

## New Careers ##
Adding new careers is easy. Follow the example above and put the career in the dataFiles/Career directory.  You will need to edit the careers that lead into this career and add it and an exit.

In addition, if you want to make it a basic career for a race, add it to the list of carrers for the racial profile.

## Changes ##
  * Add the source of the career.  Example:
```
<source book="Core Rule Book" page="31" />
```

Multiple sources could used if the career appears in more then one book (like the Career Compendium).

Another source could be something like:
```
 <source book="Home Rule" page="http://some.site/path/to/career" />
```

## Notation ##
The following will be used for each book:
  * Core - Core Rule Book
  * AoM - Ashes of Middenheim
  * BotD - Barony of the Damned
  * CC - Career Compendium
  * CotHR - Children of the Horned Rat
  * FoN - Forges of Nuln
  * KotG - Knights of the Grail
  * NDM - Night's Dark Masters
  * RotIQ - Realm of the Ice Queen
  * RoS - Realms of Sorcery
  * SoE - Shades of Empire
  * SH - Sigmar's Heirs
  * SoA - Spires of Altdorf
  * TiT - Terror in Talabheim
  * ToC - Tome of Corruption
  * ToS - Tome of Salvation
  * WC - Warhammer Companion

and the following for other sources:
  * Web - Web Page
  * HR - House Rule
  * PDF - PDF document