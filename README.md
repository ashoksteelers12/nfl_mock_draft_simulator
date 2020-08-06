<p align="center">
  <img src="https://upload.wikimedia.org/wikipedia/commons/f/f6/NFL_logo.png" length="200" width="300">
  Personal Project
</p>

## Project-Description
Performs a 7-round mock draft from a user requested year (2020 - present). Data is 
manually gathered from various sites into Excel/Text files. The program goes through
the draft order for each round selecting the best player, based on several factors, 
for each team. The results are then pasted into text files for viewing. 

## Technology
**Language(s):** Java <br>
**IDE(s):** Eclipse <br>
**Software(s):** Microsoft Excel <br>
**Text Edito(s)r:** Notepad <br>

## Program-Structure
![Program Structure](/Images/NFLMockDraftSimulator_ProgramStructure.JPG)

## Data-Collection
As mentioned in the project description, the data was manually collected and inserted into 
files. The player data was placed into Excel files, with an Excel file for each position. 
The player data consisted of name, college, graduated (yes/no), and grade. The team needs
and round order were placed into text files. 

*Note: Not all the data files were added to the Github repository due to space concerns*

## Limitations
- Does not simulate mock drafts for years before 2020
  - *Why?* Previous data was not collected
- Currently only simulates the 2020 NFL Draft
  - *Why?* Future data has not been collected yet.
- Draft pool does not contain all the draft-eligible players
  - *Why?* Players with a round 1-7 draft projection were only incorporated

## References
#### [ESPN](https://www.espn.com/)
- Utilized their player grading system to manually grade players 
#### [DraftScout](https://draftscout.com/)
- Gathered player data and respective positions
- Utilized their draft projections for players to find cut line 
#### [WashingtonPost](https://www.washingtonpost.com/sports/2020/04/17/nfl-draft-needs-all-32-teams/)
- Collected each teams' needs
- Link specific to 2020's team needs
