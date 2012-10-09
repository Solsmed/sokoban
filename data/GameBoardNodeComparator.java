import java.util.Comparator;


public class GameBoardNodeComparator implements Comparator<GameBoardNode> {
	private Comparator<GameBoardNode> yComparator;
	private Comparator<GameBoardNode> xComparator;
	
	static double spentTime = 0;
	static int counter = 0;
	
	public GameBoardNodeComparator(int Ycondition) {
		this(1, Ycondition);		
	}
	
	public GameBoardNodeComparator(int xCondition, int yCondition) {
		xComparator = new XComparator(xCondition);
		yComparator = new YComparator(yCondition);
	}
	
	@Override
    public int compare(GameBoardNode a, GameBoardNode b) {
		long start = System.currentTimeMillis();
		counter++;
		
		// Logic for combining results from X and Y comparators
		GameBoardNode cn = TreeSearcher.currentNode;
		
		int compareResult = 0;
		
		if(xComparator.compare(a, b) >= 0)
			compareResult = yComparator.compare(a, b);
		
		compareResult = xComparator.compare(a, b);
		
		spentTime += (System.currentTimeMillis() - start);
		
		return compareResult;
	}
}
