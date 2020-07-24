This program performs a simulated mock draft for specific years. Players are added per position into
a draft pool. The players that are added are those given draftable grades by draftscout.com. The grades
are given by the grade range provided on espn.com. The team needs was (specifically 2020) was gathered 
from a washingtonpost.com article. This data was manually calculated and entered into excel files. 

Currently this program only does the mock draft for the year 2020. This program can run for future
years or past years. All that would be done is copying and pasting all the csv file and folder names, and
changing the year on the folder name to the necessary year. The contents of the csv file would be cleared
and the data of new players would be added. This way, the 2020 mock draft data still remains and can still 
run if the user inputs 2020. 

On GitHub, the PostDraftData-2020 and PreDraftData-2020 files are not added. Just the code and the output
files regarding the draft picks selection in each round were pushed onto there. The output files would have
been in the post draft folder had the folders been added. 

The PostDraftData-2020 folder would have had the updated team needs and the selections, The PreDraftData-2020
folder would have had the CSVs of each position consisting of draftable players and the round order. Also, it
would have had the team needs. 

How does your simulation select players for teams?
The code first checks the needs. It gathers all the players for those needs. It takes the highest graded player
out of them and selects them. If the needs of a team are done and they still have picks, it gives the team the
best available player. If a team has a position of need left in which all the players are already taken, then the
best available player is given to the team. This usually happens in the later rounds. 
