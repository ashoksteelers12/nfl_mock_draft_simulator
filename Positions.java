
public enum Positions 
{
	//List of All the Possible NFL Positions
	QB("QB"), RB("RB"), FB("FB"), WR("WR"), 
	TE("TE"), OT("OT"), OG("OG"),
	C("Center"), CB("CB"), FS("FS"), SS("SS"), 
	ILB("ILB"), DT("DT"), DE("DE"),
	OLB("OLB"), K("K"), P("P"), LS("LS");
	
	//Following Functions are for the Accessing of the String Position Values
	public final String position;
	private Positions(String position) {this.position = position;}
}
