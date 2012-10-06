package search;

import java.util.*;

import data.GameBoard;
import data.GameBoardNode;
import data.GameBoardTree;

public class TreeSearcher {
	private List<GameBoardTree> treeList;
	private Set<GameBoard> roots;
	private GameBoard start;
	
	GameBoard goal;
	
	Queue<GameBoardNode> bfsQueue;
	
	public TreeSearcher(GameBoard start) {
		this.start = start;
		roots = MoveValidator.getPossibleEndBoards(start.getEndBoard());
		
		treeList = new ArrayList<GameBoardTree>();
		
		for(GameBoard root : roots) {
			treeList.add(new GameBoardTree(root));
		}
		
		bfsQueue = new LinkedList<GameBoardNode>();
	}
	
	public List<GameBoard> totalSearch() {
		bfsQueue.clear();
		
		for(GameBoardTree tree : treeList)
			bfsQueue.add(tree.getRoot());
		
		while(!bfsQueue.isEmpty()) {
			GameBoardNode node = bfsQueue.poll();
			System.out.println(node.gameBoard.boxes);
			System.out.println(node.gameBoard.player);
			
			if(node.equals(start)){
				System.out.println("YES VI VINNER");
				return new LinkedList<GameBoard>();
				
			}
				
			
			node.spawnChildren();
			Set<GameBoardNode> children = node.getChildren();
			Iterator<GameBoardNode> it = children.iterator();
			
			while(it.hasNext()) {
				
				GameBoardNode child = it.next();
			
				bfsQueue.add(child);
			}
		}
		
		return null;
	}
}
