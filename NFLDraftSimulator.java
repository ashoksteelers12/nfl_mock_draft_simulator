import java.io.*;
import java.util.*;

public class NFLDraftSimulator 
{
	//Private Object of the Class
	private NFLDraft draft;
	
	//Default Constructor
	NFLDraftSimulator()
	{
		draft = new NFLDraft();
	}
	
	//Constructor
	NFLDraftSimulator(NFLDraft draft)
	{
		this.draft = draft;
	}
	
	//Setter and Getter Functions
	public void setNFLDraft(NFLDraft draft) {this.draft = draft;}
	public NFLDraft getNFLDraft() {return draft;}
	
	//Perform Mock Draft of All Rounds
	public void simulateDraft()
	{
		//Request User the Specific Year
		System.out.println("What draft year do you want mock draft of?");
		Scanner scan = new Scanner(System.in);
		boolean valid = false;
		int year;
		//Exit Loop After Draft of the Year Selected is Performed
		while (!valid) 
		{
			//Check if Input is an Integer
			if (scan.hasNextInt())
			{
				//Check if the Input is Equal to One of the Available Years
				if ((year = scan.nextInt()) == 2020)
				{
					draft.createDraftPool(year); //Create Draft Pool
					draft.roundOneOfDraft(year); //Do Round 1
					draft.roundTwoOfDraft(year); //Do Round 2
					draft.roundThreeOfDraft(year); //Do Round 3
					draft.roundFourOfDraft(year); //Do Round 4
					draft.roundFiveOfDraft(year); //Do Round 5
					draft.roundSixOfDraft(year); //Do Round 6
					draft.roundSevenOfDraft(year); //Do Round 7
					valid = true; //Exit Loop
				}
				//Request Another Year Input
				else
				{
					System.out.println("Unable to get mock draft for that year.");
					System.out.println("Enter Again: ");
				}
			}
			//Exit Program if User Chooses to Leave
			else if (scan.nextLine().contains("exit"))
			{
				System.out.println("Exiting Program.");
				System.exit(1);
			}
			//If Invalid Input, Ask for Another Input
			else
			{
				System.out.println("Incorrect Input.");
				System.out.println("Enter Again: ");
			}
		}
	}
}
