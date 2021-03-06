import java.util.Comparator;


public class GameBoardNodeComparator implements Comparator<GameBoardNode> {
	private Comparator<GameBoardNode> yComparator;
	private Comparator<GameBoardNode> xComparator;
	
	static double spentTime = 0;
	static int counter = 0;
	
	boolean ignoreDepth = false;
	
	public GameBoardNodeComparator() {
		ignoreDepth = true; // BFS
	}
	
	public GameBoardNodeComparator(int Ycondition) {
		this(1, Ycondition);		
	}
	
	public GameBoardNodeComparator(int xCondition, int yCondition) {
		xComparator = new XComparator(xCondition);
		yComparator = new YComparator(yCondition);
	}
	
	@Override
    public int compare(GameBoardNode offeredNode, GameBoardNode queuedNode) {
		long start = System.currentTimeMillis();
		counter++;
		
		// Logic for combining results from X and Y comparators
		GameBoardNode cn = TreeSearcher.currentNode;
		
		int compareResult = 0;
		
		if(!ignoreDepth) {
			if(offeredNode.getDepth() - queuedNode.getDepth() < 0)
				return -1;
			else if(queuedNode.getDepth() - offeredNode.getDepth() < 0)
				return 1;
		}
		
		compareResult = xComparator.compare(offeredNode, queuedNode);
		
		if(compareResult == 0)
			compareResult = yComparator.compare(offeredNode, queuedNode);
		
		spentTime += (System.currentTimeMillis() - start);
		
		return compareResult;
	}
}
