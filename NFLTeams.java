
public enum NFLTeams 
{
	//List of All the NFL Teams
	CARDINALS("Arizona Cardinals"), FALCONS("Atlanta Falcons"),
	RAVENS("Baltimore Ravens"), BILLS("Buffalo Bills"),
	PANTHERS("Carolina Panthers"), BEARS("Chicago Bears"),
	BENGALS("Cincinnati Bengals"), BROWNS("Cleveland Browns"),
	COWBOYS("Dallas Cowboys"), BRONCOS("Denver Broncos"),
	LIONS("Detroit Lions"), PACKERS("Green Bay Packers"),
	TEXANS("Houston Texans"), COLTS("Indianapolis Colts"),
	JAGUARS("Jacksonville Jaguars"), CHIEFS("Kansas City Chiefs"),
	RAIDERS("Las Vegas Raiders"), CHARGERS("Los Angeles Chargers"),
	RAMS("Los Angeles Rams"), DOLPHINS("Miami Dolphins"),
	VIKINGS("Minnesota Vikings"), PATRIOTS("New England Patriots"),
	SAINTS("New Orleans Saints"), GIANTS("New York Giants"),
	JETS("New York Jets"), EAGLES("Philadelphia Eagles"),
	STEELERS("Pittsburgh Steelers"), FORTYNINERS("San Francisco 49ers"),
	SEAHAWKS("Seattle Seahawks"), BUCCANEERS("Tampa Bay Buccaneers"),
	TITANS("Tennessee Titans"), REDSKINS("Washington Redskins");

	//Following Functions Used to Access the String Value of the Enum Values
	public final String teamName;
	private NFLTeams(String teamName) {this.teamName = teamName;}
}
