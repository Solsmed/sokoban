import java.util.Comparator;

public class XComparator implements Comparator<GameBoardNode> {

	private int condition;
	private int n;
	
	public XComparator(int condition) {
		this.condition = condition;
	}
	
	public XComparator(int condition, int n) {
		this.condition = condition;
		this.n = n;
	}
	
	public int compare(GameBoardNode a, GameBoardNode b) {
		return 0;
	}

}
