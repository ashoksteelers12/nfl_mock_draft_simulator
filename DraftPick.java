
public class DraftPick extends Player
{
	//Private Object(s) of the Class
	private NFLTeams team; 
	
	//Default Constructor
	DraftPick()
	{
		super();
		this.team = null;
	}
	
	//Constructor
	DraftPick(String name, String position, String college, boolean graduated, double grade, NFLTeams team)
	{
		super(name, position, college, graduated, grade);
		this.team = team;
	}
	
	//Getter and Setter Functions
	public void setTeam(NFLTeams team) {this.team = team;}
	public NFLTeams getTeam() {return team;}
	
	//Display Player Information Following Being Drafter or Not
	/*
	 * Not Used in Final Output.
	 * Mostly used for Testing Purposes and Kept for Extra Applications Added to the
	 * Program Needing the Display of this Information.
	 */
	public void postDraftPlayerInfo()
	{
		//If Statement for Checking if Player Graduated or Not
		if (graduated)
			System.out.println("Name: " + name 
					+ "\nPosition: " + position 
					+ "\nCollege: " + college 
					+ "\nStatus: Graduated" 
					+ "\nGrade: " + grade
					+ "\nTeam: " + team);
		else 
			System.out.println("Name: " + name 
					+ "\nPosition: " + position 
					+ "\nCollege: " + college 
					+ "\nStatus: Underclassman" 
					+ "\nGrade: " + grade
					+ "\nTeam: " + team);
	}
}
