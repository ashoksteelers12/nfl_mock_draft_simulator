
public class Player 
{
	//Protected Objects of the Player Class Which are Used for the DraftPick Class as Well
	protected String name;
	protected String position;
	protected String college;
	protected boolean graduated;
	protected double grade;
	
	//Default Constructor
	Player() 
	{
		this.name = null;
		this.position = null;
		this.college = null;
		this.graduated = false;
		this.grade = 0.0;
	}
	
	//Constructor
	Player(String name, String position, String college, boolean graduated, double grade) 
	{
		this.name = name;
		this.position = position;
		this.college = college;
		this.graduated = graduated;
		this.grade = grade;
	}
	
	//Getter and Setter Functions
	public void setName(String name) {this.name = name;}
	public String getName() {return name;}
	public void setPosition(String position) {this.position = position;}
	public String getPosition() {return position;}
	public void setCollege(String college) {this.college = college;}
	public String getCollege() {return college;}
	public void setStatus(boolean graduated) {this.graduated = graduated;}
	public boolean getStatus() {return graduated;}
	public void setGrade(double grade) {this.grade = grade;}
	public double getGrade() {return grade;}
	
	//Defining the Comparator Within the Class
	public int compare(Player p1, Player p2)
	{
		return Double.compare(p2.grade, p1.grade);
	}
	
	//Display Player Information Prior Being Drafter or Not
	/*
	 * Not Used in Final Output.
	 * Mostly used for Testing Purposes and Kept for Extra Applications Added to the
	 * Program Needing the Display of this Information.
	 */
	public void preDraftPlayerInfo()
	{
		//If Statement for Checking if Player Graduated or Not
		if (graduated)
			System.out.println("Name: " + name 
					+ "\nPosition: " + position 
					+ "\nCollege: " + college 
					+ "\nStatus: Graduated" 
					+ "\nGrade: " + grade);
		else 
			System.out.println("Name: " + name 
					+ "\nPosition: " + position 
					+ "\nCollege: " + college 
					+ "\nStatus: Underclassman" 
					+ "\nGrade: " + grade);
	}
}
