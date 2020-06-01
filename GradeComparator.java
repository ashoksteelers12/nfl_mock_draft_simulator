import java.util.*;

public class GradeComparator implements Comparator<Player>
{
	//For Sorting the Players in Terms of Their Given Grade
	public int compare(Player p1, Player p2)
	{
		//Using Double Since Grades are Given in Decimal Format
		return Double.compare(p2.getGrade(), p1.getGrade());
	}
}
