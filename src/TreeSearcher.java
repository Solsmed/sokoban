import java.util.LinkedList;
import java.util.Queue;

public class TreeSearcher {
	private GameBoardTree tree;
	
	Queue<GameBoardNode> bfsQueue;
	
	public TreeSearcher(GameBoard start) {
		tree = new GameBoardTree(start);
		
		bfsQueue = new LinkedList<GameBoardNode>();
	}
	
	public void totalSearch() {
		bfsQueue.clear();
		
		bfsQueue.add(tree.getRoot());
		
		while(!bfsQueue.isEmpty()) {
			GameBoardNode node = bfsQueue.poll();
			node.expandNode();
		}
	}
}
