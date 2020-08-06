<p align="center">
  <img src="https://upload.wikimedia.org/wikipedia/commons/f/f6/NFL_logo.png" length="200" width="300">
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

## How a Player is Selected?
The code first checks the needs. It gathers all the players for those needs. It takes the highest graded player
out of them and selects them. If the needs of a team are done and they still have picks, it gives the team the
best available player. If a team has a position of need left in which all the players are already taken, then the
best available player is given to the team. This usually happens in the later rounds. 

## Limitations
- Does not simulate mock drafts for years before 2020
  - *Why?* Previous data was not collected
- Currently only simulates the 2020 NFL Draft
  - *Why?* Future data has not been collected yet.
- Draft pool does not contain all the draft-eligible players
  - *Why?* Players with a round 1-7 draft projection were only incorporated

## References
#### [ESPN](https://www.espn.com/)
#### [DraftScout](https://draftscout.com/)
#### [WashingtonPost](https://www.washingtonpost.com/sports/2020/04/17/nfl-draft-needs-all-32-teams/)
