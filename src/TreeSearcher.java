import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class TreeSearcher {
	private GameBoardTree tree;
	
	GameBoard goal;
	
	Queue<GameBoardNode> bfsQueue;
	
	public TreeSearcher(GameBoard start, GameBoard goal) {
		tree = new GameBoardTree(start);
		this.goal = goal;
		
		bfsQueue = new LinkedList<GameBoardNode>();
	}
	
	public List<GameBoard> totalSearch() {
		bfsQueue.clear();
		
		bfsQueue.add(tree.getRoot());
		
		while(!bfsQueue.isEmpty()) {
			GameBoardNode node = bfsQueue.poll();
			
			if(node.equals(goal))
				return new LinkedList<GameBoard>();
			
			Set<GameBoardNode> children = node.getChildren();
			Iterator<GameBoardNode> it = children.iterator();
			
			while(it.hasNext()) {
				GameBoardNode child = it.next();
				if(!tree.nodeSet.contains(child))
					bfsQueue.add(child);
			}
		}
		
		return null;
	}
}
