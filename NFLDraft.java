/*
 * Rounds 2-7 Not as much commented as Round 1, since code very similar. 
 * The few different aspects are commented, such as the possibility of 
 * running out of needs in the future rounds. Same goes for Round 2 in 
 * comparison to the other rounds.
 */

import java.util.*;
import java.io.*;

public class NFLDraft 
{
	//Private Objects of the Class
	private ArrayList<Player> draftPool; //For All the Players with Draftable Grades
	private GradeComparator compareGrades; 
	//Arrays for the Order of Each Round and Selections
	private ArrayList<NFLTeams> roundOneOrder; 
	private ArrayList<DraftPick> roundOneSelections;
	private ArrayList<NFLTeams> roundTwoOrder;
	private ArrayList<DraftPick> roundTwoSelections;
	private ArrayList<NFLTeams> roundThreeOrder;
	private ArrayList<DraftPick> roundThreeSelections;
	private ArrayList<NFLTeams> roundFourOrder;
	private ArrayList<DraftPick> roundFourSelections;
	private ArrayList<NFLTeams> roundFiveOrder;
	private ArrayList<DraftPick> roundFiveSelections;
	private ArrayList<NFLTeams> roundSixOrder;
	private ArrayList<DraftPick> roundSixSelections;
	private ArrayList<NFLTeams> roundSevenOrder;
	private ArrayList<DraftPick> roundSevenSelections;
	
	//Default Constructor
	NFLDraft()
	{
		draftPool = new ArrayList<Player>();
		compareGrades = new GradeComparator();
		roundOneOrder = new ArrayList<NFLTeams>();
		roundOneSelections = new ArrayList<DraftPick>();
		roundTwoOrder = new ArrayList<NFLTeams>();
		roundTwoSelections = new ArrayList<DraftPick>();
		roundThreeOrder = new ArrayList<NFLTeams>();
		roundThreeSelections = new ArrayList<DraftPick>();
		roundFourOrder = new ArrayList<NFLTeams>();
		roundFourSelections = new ArrayList<DraftPick>();
		roundFiveOrder = new ArrayList<NFLTeams>();
		roundFiveSelections = new ArrayList<DraftPick>();
		roundSixOrder = new ArrayList<NFLTeams>();
		roundSixSelections = new ArrayList<DraftPick>();
		roundSevenOrder = new ArrayList<NFLTeams>();
		roundSevenSelections = new ArrayList<DraftPick>();
	}
	
	//Setter and Getter Functions
	public ArrayList<Player> getDraftPool() {return draftPool;}
	public void setCompareGrades(GradeComparator compareGrades) {this.compareGrades = compareGrades;}
	public GradeComparator getCompareGrades() {return compareGrades;}
	public ArrayList<NFLTeams> getRoundOneOrder() {return roundOneOrder;}
	public ArrayList<DraftPick> getRoundOneSelections() {return roundOneSelections;}
	public ArrayList<NFLTeams> getRoundTwoOrder() {return roundTwoOrder;}
	public ArrayList<DraftPick> getRoundTwoSelections() {return roundTwoSelections;}
	public ArrayList<NFLTeams> getRoundThreeOrder() {return roundThreeOrder;}
	public ArrayList<DraftPick> getRoundThreeSelections() {return roundThreeSelections;}
	public ArrayList<NFLTeams> getRoundFourOrder() {return roundFourOrder;}
	public ArrayList<DraftPick> getRoundFourSelections() {return roundFourSelections;}
	public ArrayList<NFLTeams> getRoundFiveOrder() {return roundFiveOrder;}
	public ArrayList<DraftPick> getRoundFiveSelections() {return roundFiveSelections;}
	public ArrayList<NFLTeams> getRoundSixOrder() {return roundSixOrder;}
	public ArrayList<DraftPick> getRoundSixSelections() {return roundSixSelections;}
	public ArrayList<NFLTeams> getRoundSevenOrder() {return roundSevenOrder;}
	public ArrayList<DraftPick> getRoundSevenSelections() {return roundSevenSelections;}
		
	//Adding Players from Each Positions in CSVs to One Draft Pool 
	public void createDraftPool(int year)
	{
		Positions positions[] = Positions.values();
		//Loop Through Positions
		for (Positions position: positions)
		{
			Scanner csvScan;
			try 
			{
				//Access Each Position File
				csvScan = new Scanner(new File("./src/PreDraftData-NFL" + year + "/" + position + ".csv"));
				csvScan.nextLine(); //Skip the Header Portion
				//Run Through the CSV
				while (csvScan.hasNext())
				{
					//Separate the Data of Each Player
					String playerData = csvScan.nextLine();
					String values[] = playerData.split(",");
					String name = values[0];
					String college = values[1];
					boolean graduated;
					if (values[2].contains("No"))
						graduated = false;
					else
						graduated = true;
					double grade = Double.parseDouble(values[3]);
					Player player = new Player(name, position.position, college, graduated, grade); 
					draftPool.add(player); //Add Player to Draft Pool
				}
			} catch (FileNotFoundException e) {e.printStackTrace();}
		}
		Collections.sort(draftPool, compareGrades); //Sort the Draft Pool from Highest to Lowest Graded
	}
	
	//Perform Mock Draft of First Round
	public void roundOneOfDraft(int year)
	{
		NFLTeams teams[] = NFLTeams.values();
		Positions positions[] = Positions.values();
		try 
		{
			//Access the Order of Round 1
			Scanner txtScan = new Scanner(new File("./src/PreDraftData-NFL" + year + "/Round1.txt"));
			//Run Through the txt File
			while (txtScan.hasNextLine())
			{
				String name = txtScan.nextLine();
				for (NFLTeams team: teams)
				{
					if (name.contains(team.teamName))
						roundOneOrder.add(team); //Fill Array in Order
				}
			}
			//Loop Through the Order for Each Team to Select a Player
			for (int i = 0; i < roundOneOrder.size(); i++)
			{
				//Access the Needs File
				Scanner posScan = new Scanner(new File("./src/PreDraftData-NFL" + year + "/TeamNeeds/" + roundOneOrder.get(i).teamName + ".txt"));
				ArrayList<Positions> tempTeamNeeds = new ArrayList<Positions>(); //Temporary Array for Needs
				ArrayList<Player> tempPlayers = new ArrayList<Player>(); //Temporary Array for Possible Players to Select
				//Run Through Needs
				while (posScan.hasNextLine())
				{
					String pos = posScan.nextLine();
					for (Positions position: positions)
					{
						if (pos.contains(position.position))
							tempTeamNeeds.add(position); //Add to Team Needs
					}
				}
				//Add Possible Players Based on Needs
				for (int j = 0; j < tempTeamNeeds.size(); j++)
				{
					for (int k = 0; k < draftPool.size(); k++)
					{
						if (tempTeamNeeds.get(j).position == draftPool.get(k).getPosition())
							tempPlayers.add(draftPool.get(k)); 
					}
				}
				//If There are No Available Players Based on Position, Then the Highest Graded Player in Draft Pool is Selected
				if (tempPlayers.isEmpty())
					tempPlayers.add(draftPool.get(0));
				Collections.sort(tempPlayers, compareGrades); //Sort the Possible Players by Grade
				DraftPick pick = new DraftPick();
				//Set Info on Selected Pick
				pick.setName(tempPlayers.get(0).getName());
				pick.setCollege(tempPlayers.get(0).getCollege());
				pick.setPosition(tempPlayers.get(0).getPosition());
				pick.setStatus(tempPlayers.get(0).getStatus());
				pick.setGrade(tempPlayers.get(0).getGrade());
				pick.setTeam(roundOneOrder.get(i));
				//Remove the Need from Team Needs List of Player Selected
				for (int j = 0; j < tempTeamNeeds.size(); j++)
				{
					if (tempTeamNeeds.get(j).position == tempPlayers.get(0).getPosition())
						tempTeamNeeds.remove(tempTeamNeeds.get(j));
				}
				//Update the Team Needs Based on Selection
				try 
				{
					//Access the Updated Team Needs
					File needs = new File("./src/PostDraftData-NFL" + year + "/UpdatedTeamNeeds/" + roundOneOrder.get(i).teamName + ".txt");
					FileWriter fwNeeds = new FileWriter(needs);
					PrintWriter pwNeeds = new PrintWriter(fwNeeds);
					for (int j = 0; j < tempTeamNeeds.size(); j++)
					{
						pwNeeds.println(tempTeamNeeds.get(j).position); //Write to the File the Updated Needs
					}
					pwNeeds.close();
				} catch (IOException e) {e.printStackTrace();}
				draftPool.remove(tempPlayers.get(0)); //Remove Player Selected from Draft Pool
				roundOneSelections.add(pick); //Add to Round 1 Selections
			}
		} catch (FileNotFoundException e) {e.printStackTrace();}
		//Display Selections of the Round
		try 
		{
			//Access Selections txt File
			File file = new File("./src/PostDraftData-NFL" + year + "/Round1Selections.txt");
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);
			//Write to File the Important Information and Order of Selections of the Teams/Players
			for (int i = 0; i < roundOneSelections.size(); i++)
			{
				int pickNum = i + 1;
				if (pickNum < 10)
					pw.println(pickNum + ".  " + roundOneSelections.get(i).getTeam().teamName + "-> " + "(" + roundOneSelections.get(i).getPosition() + ") " + roundOneSelections.get(i).getName() + " (" + roundOneSelections.get(i).getCollege() + ")");
				else
					pw.println(pickNum + ". " + roundOneSelections.get(i).getTeam().teamName + "-> " + "(" + roundOneSelections.get(i).getPosition() + ") " + roundOneSelections.get(i).getName() + " (" + roundOneSelections.get(i).getCollege() + ")");
			}
			pw.close();
		} catch (IOException e) {e.printStackTrace();}
		
		System.out.println("ROUND 1 COMPLETE"); 
	}
	
	//Perform Mock Draft of Second Round
	public void roundTwoOfDraft(int year)
	{
		NFLTeams teams[] = NFLTeams.values();
		Positions positions[] = Positions.values();
		try 
		{
			Scanner txtScan = new Scanner(new File("./src/PreDraftData-NFL" + year + "/Round2.txt"));
			while (txtScan.hasNextLine())
			{
				String name = txtScan.nextLine();
				for (NFLTeams team: teams)
				{
					if (name.contains(team.teamName))
						roundTwoOrder.add(team);
				}
			}
			for (int i = 0; i < roundTwoOrder.size(); i++)
			{
				//Accessing the Updated Needs Because of the Changes from Previous Round(s)
				File needsFile = new File("./src/PostDraftData-NFL" + year + "/UpdatedTeamNeeds/" + roundTwoOrder.get(i).teamName + ".txt");
				Scanner posScan = new Scanner(needsFile);
				ArrayList<Positions> tempTeamNeeds = new ArrayList<Positions>();
				ArrayList<Player> tempPlayers = new ArrayList<Player>();
				//If All Needs are Filled and Team Still has Pick(s)
				if (needsFile.length() == 0)
				{
					//Get the Best Available Player Regardless of Position
					DraftPick pick = new DraftPick();
					pick.setName(draftPool.get(0).getName());
					pick.setCollege(draftPool.get(0).getCollege());
					pick.setPosition(draftPool.get(0).getPosition());
					pick.setStatus(draftPool.get(0).getStatus());
					pick.setGrade(draftPool.get(0).getGrade());
					pick.setTeam(roundTwoOrder.get(i));
					draftPool.remove(0); //Best Available is Removed From Draft Pool
					roundTwoSelections.add(pick); //Add Selection
				}
				//Still Needs Remain
				else 
				{
					while (posScan.hasNextLine())
					{
						String pos = posScan.nextLine();
						for (Positions position: positions)
						{
							if (pos.contains(position.position))
								tempTeamNeeds.add(position);
						}
					}
					for (int j = 0; j < tempTeamNeeds.size(); j++)
					{
						for (int k = 0; k < draftPool.size(); k++)
						{
							if (tempTeamNeeds.get(j).position == draftPool.get(k).getPosition())
								tempPlayers.add(draftPool.get(k));
						}
					}
					if (tempPlayers.isEmpty())
						tempPlayers.add(draftPool.get(0));
					Collections.sort(tempPlayers, compareGrades);
					DraftPick pick = new DraftPick();
					pick.setName(tempPlayers.get(0).getName());
					pick.setCollege(tempPlayers.get(0).getCollege());
					pick.setPosition(tempPlayers.get(0).getPosition());
					pick.setStatus(tempPlayers.get(0).getStatus());
					pick.setGrade(tempPlayers.get(0).getGrade());
					pick.setTeam(roundTwoOrder.get(i));
					for (int j = 0; j < tempTeamNeeds.size(); j++)
					{
						if (tempTeamNeeds.get(j).position == tempPlayers.get(0).getPosition())
							tempTeamNeeds.remove(tempTeamNeeds.get(j));
					}
					try 
					{
						File needs = new File("./src/PostDraftData-NFL" + year + "/UpdatedTeamNeeds/" + roundTwoOrder.get(i).teamName + ".txt");
						FileWriter fwNeeds = new FileWriter(needs);
						PrintWriter pwNeeds = new PrintWriter(fwNeeds);
						for (int j = 0; j < tempTeamNeeds.size(); j++)
						{
							pwNeeds.println(tempTeamNeeds.get(j).position);
						}
						pwNeeds.close();
					} catch (IOException e) {e.printStackTrace();}
					draftPool.remove(tempPlayers.get(0));
					roundTwoSelections.add(pick);
					posScan.close();
				  }
			}
		} catch (FileNotFoundException e) {e.printStackTrace();}
		try 
		{
			File file = new File("./src/PostDraftData-NFL" + year + "/Round2Selections.txt");
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);
			for (int i = 0; i < roundTwoSelections.size(); i++)
			{
				int pickNum = i + 1;
				if (pickNum < 10)
					pw.println(pickNum + ".  " + roundTwoSelections.get(i).getTeam().teamName + "-> " + "(" + roundTwoSelections.get(i).getPosition() + ") " + roundTwoSelections.get(i).getName() + " (" + roundTwoSelections.get(i).getCollege() + ")");
				else
					pw.println(pickNum + ". " + roundTwoSelections.get(i).getTeam().teamName + "-> " + "(" + roundTwoSelections.get(i).getPosition() + ") " + roundTwoSelections.get(i).getName() + " (" + roundTwoSelections.get(i).getCollege() + ")");
			}
			pw.close();
		} catch (IOException e) {e.printStackTrace();}
		
		System.out.println("ROUND 2 COMPLETE");
	}
	
	//Perform Mock Draft of Third Round
	public void roundThreeOfDraft(int year)
	{
		NFLTeams teams[] = NFLTeams.values();
		Positions positions[] = Positions.values();
		try 
		{
			Scanner txtScan = new Scanner(new File("./src/PreDraftData-NFL" + year + "/Round3.txt"));
			while (txtScan.hasNextLine())
			{
				String name = txtScan.nextLine();
				for (NFLTeams team: teams)
				{
					if (name.contains(team.teamName))
						roundThreeOrder.add(team);
				}
			}
			for (int i = 0; i < roundThreeOrder.size(); i++)
			{
				File needsFile = new File("./src/PostDraftData-NFL" + year + "/UpdatedTeamNeeds/" + roundThreeOrder.get(i).teamName + ".txt");
				Scanner posScan = new Scanner(needsFile);
				ArrayList<Positions> tempTeamNeeds = new ArrayList<Positions>();
				ArrayList<Player> tempPlayers = new ArrayList<Player>();
				if (needsFile.length() == 0)
				{
					DraftPick pick = new DraftPick();
					pick.setName(draftPool.get(0).getName());
					pick.setCollege(draftPool.get(0).getCollege());
					pick.setPosition(draftPool.get(0).getPosition());
					pick.setStatus(draftPool.get(0).getStatus());
					pick.setGrade(draftPool.get(0).getGrade());
					pick.setTeam(roundThreeOrder.get(i));
					draftPool.remove(0);
					roundThreeSelections.add(pick);
				}
				else 
				{
					while (posScan.hasNextLine())
					{
						String pos = posScan.nextLine();
						for (Positions position: positions)
						{
							if (pos.contains(position.position))
								tempTeamNeeds.add(position);
						}
					}
					for (int j = 0; j < tempTeamNeeds.size(); j++)
					{
						for (int k = 0; k < draftPool.size(); k++)
						{
							if (tempTeamNeeds.get(j).position == draftPool.get(k).getPosition())
								tempPlayers.add(draftPool.get(k));
						}
					}
					if (tempPlayers.isEmpty())
						tempPlayers.add(draftPool.get(0));
					Collections.sort(tempPlayers, compareGrades);
					DraftPick pick = new DraftPick();
					pick.setName(tempPlayers.get(0).getName());
					pick.setCollege(tempPlayers.get(0).getCollege());
					pick.setPosition(tempPlayers.get(0).getPosition());
					pick.setStatus(tempPlayers.get(0).getStatus());
					pick.setGrade(tempPlayers.get(0).getGrade());
					pick.setTeam(roundThreeOrder.get(i));
					for (int j = 0; j < tempTeamNeeds.size(); j++)
					{
						if (tempTeamNeeds.get(j).position == tempPlayers.get(0).getPosition())
							tempTeamNeeds.remove(tempTeamNeeds.get(j));
					}
					try 
					{
						File needs = new File("./src/PostDraftData-NFL" + year + "/UpdatedTeamNeeds/" + roundThreeOrder.get(i).teamName + ".txt");
						FileWriter fwNeeds = new FileWriter(needs);
						PrintWriter pwNeeds = new PrintWriter(fwNeeds);
						for (int j = 0; j < tempTeamNeeds.size(); j++)
						{
							pwNeeds.println(tempTeamNeeds.get(j).position);
						}
						pwNeeds.close();
					} catch (IOException e) {e.printStackTrace();}
					draftPool.remove(tempPlayers.get(0));
					roundThreeSelections.add(pick);
					posScan.close();
				}
			}
		} catch (FileNotFoundException e) {e.printStackTrace();}
		try 
		{
			File file = new File("./src/PostDraftData-NFL" + year + "/Round3Selections.txt");
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);
			for (int i = 0; i < roundThreeSelections.size(); i++)
			{
				int pickNum = i + 1;
				if (pickNum < 10)
					pw.println(pickNum + ".  " + roundThreeSelections.get(i).getTeam().teamName + "-> " + "(" + roundThreeSelections.get(i).getPosition() + ") " + roundThreeSelections.get(i).getName() + " (" + roundThreeSelections.get(i).getCollege() + ")");
				else
					pw.println(pickNum + ". " + roundThreeSelections.get(i).getTeam().teamName + "-> " + "(" + roundThreeSelections.get(i).getPosition() + ") " + roundThreeSelections.get(i).getName() + " (" + roundThreeSelections.get(i).getCollege() + ")");
			}
			pw.close();
		} catch (IOException e) {e.printStackTrace();}
		
		System.out.println("ROUND 3 COMPLETE");
	}
	
	//Perform Mock Draft of Fourth Round
	public void roundFourOfDraft(int year)
	{
		NFLTeams teams[] = NFLTeams.values();
		Positions positions[] = Positions.values();
		try 
		{
			Scanner txtScan = new Scanner(new File("./src/PreDraftData-NFL" + year + "/Round4.txt"));
			while (txtScan.hasNextLine())
			{
				String name = txtScan.nextLine();
				for (NFLTeams team: teams)
				{
					if (name.contains(team.teamName))
						roundFourOrder.add(team);
				}
			}
			for (int i = 0; i < roundFourOrder.size(); i++)
			{
				File needsFile = new File("./src/PostDraftData-NFL" + year + "/UpdatedTeamNeeds/" + roundFourOrder.get(i).teamName + ".txt");
				Scanner posScan = new Scanner(needsFile);
				ArrayList<Positions> tempTeamNeeds = new ArrayList<Positions>();
				ArrayList<Player> tempPlayers = new ArrayList<Player>();
				if (needsFile.length() == 0)
				{
					DraftPick pick = new DraftPick();
					pick.setName(draftPool.get(0).getName());
					pick.setCollege(draftPool.get(0).getCollege());
					pick.setPosition(draftPool.get(0).getPosition());
					pick.setStatus(draftPool.get(0).getStatus());
					pick.setGrade(draftPool.get(0).getGrade());
					pick.setTeam(roundFourOrder.get(i));
					draftPool.remove(0);
					roundFourSelections.add(pick);
				}
				else 
				{
					while (posScan.hasNextLine())
					{
						String pos = posScan.nextLine();
						for (Positions position: positions)
						{
							if (pos.contains(position.position))
								tempTeamNeeds.add(position);
						}
					}
					for (int j = 0; j < tempTeamNeeds.size(); j++)
					{
						for (int k = 0; k < draftPool.size(); k++)
						{
							if (tempTeamNeeds.get(j).position == draftPool.get(k).getPosition())
								tempPlayers.add(draftPool.get(k));
						}
					}
					if (tempPlayers.isEmpty())
						tempPlayers.add(draftPool.get(0));
					Collections.sort(tempPlayers, compareGrades);
					DraftPick pick = new DraftPick();
					pick.setName(tempPlayers.get(0).getName());
					pick.setCollege(tempPlayers.get(0).getCollege());
					pick.setPosition(tempPlayers.get(0).getPosition());
					pick.setStatus(tempPlayers.get(0).getStatus());
					pick.setGrade(tempPlayers.get(0).getGrade());
					pick.setTeam(roundFourOrder.get(i));
					for (int j = 0; j < tempTeamNeeds.size(); j++)
					{
						if (tempTeamNeeds.get(j).position == tempPlayers.get(0).getPosition())
							tempTeamNeeds.remove(tempTeamNeeds.get(j));
					}
					try 
					{
						File needs = new File("./src/PostDraftData-NFL" + year + "/UpdatedTeamNeeds/" + roundFourOrder.get(i).teamName + ".txt");
						FileWriter fwNeeds = new FileWriter(needs);
						PrintWriter pwNeeds = new PrintWriter(fwNeeds);
						for (int j = 0; j < tempTeamNeeds.size(); j++)
						{
							pwNeeds.println(tempTeamNeeds.get(j).position);
						}
						pwNeeds.close();
					} catch (IOException e) {e.printStackTrace();}
					draftPool.remove(tempPlayers.get(0));
					roundFourSelections.add(pick);
					posScan.close();
				}
			}
		} catch (FileNotFoundException e) {e.printStackTrace();}
		try 
		{
			File file = new File("./src/PostDraftData-NFL" + year + "/Round4Selections.txt");
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);
			for (int i = 0; i < roundFourSelections.size(); i++)
			{
				int pickNum = i + 1;
				if (pickNum < 10)
					pw.println(pickNum + ".  " + roundFourSelections.get(i).getTeam().teamName + "-> " + "(" + roundFourSelections.get(i).getPosition() + ") " + roundFourSelections.get(i).getName() + " (" + roundFourSelections.get(i).getCollege() + ")");
				else
					pw.println(pickNum + ". " + roundFourSelections.get(i).getTeam().teamName + "-> " + "(" + roundFourSelections.get(i).getPosition() + ") " + roundFourSelections.get(i).getName() + " (" + roundFourSelections.get(i).getCollege() + ")");
			}
			pw.close();
		} catch (IOException e) {e.printStackTrace();}
		
		System.out.println("ROUND 4 COMPLETE");
	}
	
	//Perform Mock Draft of Fifth Round
	public void roundFiveOfDraft(int year)
	{
		NFLTeams teams[] = NFLTeams.values();
		Positions positions[] = Positions.values();
		try 
		{
			Scanner txtScan = new Scanner(new File("./src/PreDraftData-NFL" + year + "/Round5.txt"));
			while (txtScan.hasNextLine())
			{
				String name = txtScan.nextLine();
				for (NFLTeams team: teams)
				{
					if (name.contains(team.teamName))
						roundFiveOrder.add(team);
				}
			}
			for (int i = 0; i < roundFiveOrder.size(); i++)
			{
				File needsFile = new File("./src/PostDraftData-NFL" + year + "/UpdatedTeamNeeds/" + roundFiveOrder.get(i).teamName + ".txt");
				Scanner posScan = new Scanner(needsFile);
				ArrayList<Positions> tempTeamNeeds = new ArrayList<Positions>();
				ArrayList<Player> tempPlayers = new ArrayList<Player>();
				if (needsFile.length() == 0)
				{
					DraftPick pick = new DraftPick();
					pick.setName(draftPool.get(0).getName());
					pick.setCollege(draftPool.get(0).getCollege());
					pick.setPosition(draftPool.get(0).getPosition());
					pick.setStatus(draftPool.get(0).getStatus());
					pick.setGrade(draftPool.get(0).getGrade());
					pick.setTeam(roundFiveOrder.get(i));
					draftPool.remove(0);
					roundFiveSelections.add(pick);
				}
				else 
				{
					while (posScan.hasNextLine())
					{
						String pos = posScan.nextLine();
						for (Positions position: positions)
						{
							if (pos.contains(position.position))
								tempTeamNeeds.add(position);
						}
					}
					for (int j = 0; j < tempTeamNeeds.size(); j++)
					{
						for (int k = 0; k < draftPool.size(); k++)
						{
							if (tempTeamNeeds.get(j).position == draftPool.get(k).getPosition())
								tempPlayers.add(draftPool.get(k));
						}
					}
					if (tempPlayers.isEmpty())
						tempPlayers.add(draftPool.get(0));
					Collections.sort(tempPlayers, compareGrades);
					DraftPick pick = new DraftPick();
					pick.setName(tempPlayers.get(0).getName());
					pick.setCollege(tempPlayers.get(0).getCollege());
					pick.setPosition(tempPlayers.get(0).getPosition());
					pick.setStatus(tempPlayers.get(0).getStatus());
					pick.setGrade(tempPlayers.get(0).getGrade());
					pick.setTeam(roundFiveOrder.get(i));
					for (int j = 0; j < tempTeamNeeds.size(); j++)
					{
						if (tempTeamNeeds.get(j).position == tempPlayers.get(0).getPosition())
							tempTeamNeeds.remove(tempTeamNeeds.get(j));
					}
					try 
					{
						File needs = new File("./src/PostDraftData-NFL" + year + "/UpdatedTeamNeeds/" + roundFiveOrder.get(i).teamName + ".txt");
						FileWriter fwNeeds = new FileWriter(needs);
						PrintWriter pwNeeds = new PrintWriter(fwNeeds);
						for (int j = 0; j < tempTeamNeeds.size(); j++)
						{
							pwNeeds.println(tempTeamNeeds.get(j).position);
						}
						pwNeeds.close();
					} catch (IOException e) {e.printStackTrace();}
					draftPool.remove(tempPlayers.get(0));
					roundFiveSelections.add(pick);
					posScan.close();
				}
			}
		} catch (FileNotFoundException e) {e.printStackTrace();}
		try 
		{
			File file = new File("./src/PostDraftData-NFL" + year + "/Round5Selections.txt");
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);
			for (int i = 0; i < roundFiveSelections.size(); i++)
			{
				int pickNum = i + 1;
				if (pickNum < 10)
					pw.println(pickNum + ".  " + roundFiveSelections.get(i).getTeam().teamName + "-> " + "(" + roundFiveSelections.get(i).getPosition() + ") " + roundFiveSelections.get(i).getName() + " (" + roundFiveSelections.get(i).getCollege() + ")");
				else
					pw.println(pickNum + ". " + roundFiveSelections.get(i).getTeam().teamName + "-> " + "(" + roundFiveSelections.get(i).getPosition() + ") " + roundFiveSelections.get(i).getName() + " (" + roundFiveSelections.get(i).getCollege() + ")");
			}
			pw.close();
		} catch (IOException e) {e.printStackTrace();}
		
		System.out.println("ROUND 5 COMPLETE");
	}
	
	//Perform Mock Draft of Sixth Round
	public void roundSixOfDraft(int year)
	{
		NFLTeams teams[] = NFLTeams.values();
		Positions positions[] = Positions.values();
		try 
		{
			Scanner txtScan = new Scanner(new File("./src/PreDraftData-NFL" + year + "/Round6.txt"));
			while (txtScan.hasNextLine())
			{
				String name = txtScan.nextLine();
				for (NFLTeams team: teams)
				{
					if (name.contains(team.teamName))
						roundSixOrder.add(team);
				}
			}
			for (int i = 0; i < roundSixOrder.size(); i++)
			{
				File needsFile = new File("./src/PostDraftData-NFL" + year + "/UpdatedTeamNeeds/" + roundSixOrder.get(i).teamName + ".txt");
				Scanner posScan = new Scanner(needsFile);
				ArrayList<Positions> tempTeamNeeds = new ArrayList<Positions>();
				ArrayList<Player> tempPlayers = new ArrayList<Player>();
				if (needsFile.length() == 0)
				{
					DraftPick pick = new DraftPick();
					pick.setName(draftPool.get(0).getName());
					pick.setCollege(draftPool.get(0).getCollege());
					pick.setPosition(draftPool.get(0).getPosition());
					pick.setStatus(draftPool.get(0).getStatus());
					pick.setGrade(draftPool.get(0).getGrade());
					pick.setTeam(roundSixOrder.get(i));
					draftPool.remove(0);
					roundSixSelections.add(pick);
				}
				else 
				{
					while (posScan.hasNextLine())
					{
						String pos = posScan.nextLine();
						for (Positions position: positions)
						{
							if (pos.contains(position.position))
								tempTeamNeeds.add(position);
						}
					}
					for (int j = 0; j < tempTeamNeeds.size(); j++)
					{
						for (int k = 0; k < draftPool.size(); k++)
						{
							if (tempTeamNeeds.get(j).position == draftPool.get(k).getPosition())
								tempPlayers.add(draftPool.get(k));
						}
					}
					if (tempPlayers.isEmpty())
						tempPlayers.add(draftPool.get(0));
					Collections.sort(tempPlayers, compareGrades);
					DraftPick pick = new DraftPick();
					pick.setName(tempPlayers.get(0).getName());
					pick.setCollege(tempPlayers.get(0).getCollege());
					pick.setPosition(tempPlayers.get(0).getPosition());
					pick.setStatus(tempPlayers.get(0).getStatus());
					pick.setGrade(tempPlayers.get(0).getGrade());
					pick.setTeam(roundSixOrder.get(i));
					for (int j = 0; j < tempTeamNeeds.size(); j++)
					{
						if (tempTeamNeeds.get(j).position == tempPlayers.get(0).getPosition())
							tempTeamNeeds.remove(tempTeamNeeds.get(j));
					}
					try 
					{
						File needs = new File("./src/PostDraftData-NFL" + year + "/UpdatedTeamNeeds/" + roundSixOrder.get(i).teamName + ".txt");
						FileWriter fwNeeds = new FileWriter(needs);
						PrintWriter pwNeeds = new PrintWriter(fwNeeds);
						for (int j = 0; j < tempTeamNeeds.size(); j++)
						{
							pwNeeds.println(tempTeamNeeds.get(j).position);
						}
						pwNeeds.close();
					} catch (IOException e) {e.printStackTrace();}
					draftPool.remove(tempPlayers.get(0));
					roundSixSelections.add(pick);
					posScan.close();
				}
			}
		} catch (FileNotFoundException e) {e.printStackTrace();}
		try 
		{
			File file = new File("./src/PostDraftData-NFL" + year + "/Round6Selections.txt");
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);
			for (int i = 0; i < roundSixSelections.size(); i++)
			{
				int pickNum = i + 1;
				if (pickNum < 10)
					pw.println(pickNum + ".  " + roundSixSelections.get(i).getTeam().teamName + "-> " + "(" + roundSixSelections.get(i).getPosition() + ") " + roundSixSelections.get(i).getName() + " (" + roundSixSelections.get(i).getCollege() + ")");
				else
					pw.println(pickNum + ". " + roundSixSelections.get(i).getTeam().teamName + "-> " + "(" + roundSixSelections.get(i).getPosition() + ") " + roundSixSelections.get(i).getName() + " (" + roundSixSelections.get(i).getCollege() + ")");
			}
			pw.close();
		} catch (IOException e) {e.printStackTrace();}
		
		System.out.println("ROUND 6 COMPLETE");
	}
	
	//Perform Mock Draft of Seventh Round
	public void roundSevenOfDraft(int year)
	{
		NFLTeams teams[] = NFLTeams.values();
		Positions positions[] = Positions.values();
		try 
		{
			Scanner txtScan = new Scanner(new File("./src/PreDraftData-NFL" + year + "/Round7.txt"));
			while (txtScan.hasNextLine())
			{
				String name = txtScan.nextLine();
				for (NFLTeams team: teams)
				{
					if (name.contains(team.teamName))
						roundSevenOrder.add(team);
				}
			}
			for (int i = 0; i < roundSevenOrder.size(); i++)
			{
				File needsFile = new File("./src/PostDraftData-NFL" + year + "/UpdatedTeamNeeds/" + roundSevenOrder.get(i).teamName + ".txt");
				Scanner posScan = new Scanner(needsFile);
				ArrayList<Positions> tempTeamNeeds = new ArrayList<Positions>();
				ArrayList<Player> tempPlayers = new ArrayList<Player>();
				if (needsFile.length() == 0)
				{
					DraftPick pick = new DraftPick();
					pick.setName(draftPool.get(0).getName());
					pick.setCollege(draftPool.get(0).getCollege());
					pick.setPosition(draftPool.get(0).getPosition());
					pick.setStatus(draftPool.get(0).getStatus());
					pick.setGrade(draftPool.get(0).getGrade());
					pick.setTeam(roundSevenOrder.get(i));
					draftPool.remove(0);
					roundSevenSelections.add(pick);
				}
				else 
				{
					while (posScan.hasNextLine())
					{
						String pos = posScan.nextLine();
						for (Positions position: positions)
						{
							if (pos.contains(position.position))
								tempTeamNeeds.add(position);
						}
					}
					for (int j = 0; j < tempTeamNeeds.size(); j++)
					{
						for (int k = 0; k < draftPool.size(); k++)
						{
							if (tempTeamNeeds.get(j).position == draftPool.get(k).getPosition())
								tempPlayers.add(draftPool.get(k));
						}
					}
					if (tempPlayers.isEmpty())
						tempPlayers.add(draftPool.get(0));
					Collections.sort(tempPlayers, compareGrades);
					DraftPick pick = new DraftPick();
					pick.setName(tempPlayers.get(0).getName());
					pick.setCollege(tempPlayers.get(0).getCollege());
					pick.setPosition(tempPlayers.get(0).getPosition());
					pick.setStatus(tempPlayers.get(0).getStatus());
					pick.setGrade(tempPlayers.get(0).getGrade());
					pick.setTeam(roundSevenOrder.get(i));
					for (int j = 0; j < tempTeamNeeds.size(); j++)
					{
						if (tempTeamNeeds.get(j).position == tempPlayers.get(0).getPosition())
							tempTeamNeeds.remove(tempTeamNeeds.get(j));
					}
					try 
					{
						File needs = new File("./src/PostDraftData-NFL" + year + "/UpdatedTeamNeeds/" + roundSevenOrder.get(i).teamName + ".txt");
						FileWriter fwNeeds = new FileWriter(needs);
						PrintWriter pwNeeds = new PrintWriter(fwNeeds);
						for (int j = 0; j < tempTeamNeeds.size(); j++)
						{
							pwNeeds.println(tempTeamNeeds.get(j).position);
						}
						pwNeeds.close();
					} catch (IOException e) {e.printStackTrace();}
					draftPool.remove(tempPlayers.get(0));
					roundSevenSelections.add(pick);
					posScan.close();
				}
			}
		} catch (FileNotFoundException e) {e.printStackTrace();}
		try 
		{
			File file = new File("./src/PostDraftData-NFL" + year + "/Round7Selections.txt");
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);
			for (int i = 0; i < roundSevenSelections.size(); i++)
			{
				int pickNum = i + 1;
				if (pickNum < 10)
					pw.println(pickNum + ".  " + roundSevenSelections.get(i).getTeam().teamName + "-> " + "(" + roundSevenSelections.get(i).getPosition() + ") " + roundSevenSelections.get(i).getName() + " (" + roundSevenSelections.get(i).getCollege() + ")");
				else
					pw.println(pickNum + ". " + roundSevenSelections.get(i).getTeam().teamName + "-> " + "(" + roundSevenSelections.get(i).getPosition() + ") " + roundSevenSelections.get(i).getName() + " (" + roundSevenSelections.get(i).getCollege() + ")");
			}
			pw.close();
		} catch (IOException e) {e.printStackTrace();}
		
		System.out.println("ROUND 7 COMPLETE");
	}
}

