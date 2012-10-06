package search;

import java.util.*;

import data.GameBoard;
import data.GameBoardNode;
import data.GameBoardTree;
import data.Renderer;

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
			
			if(node.equals(start)){
				List<GameBoard> goalPath = new LinkedList<GameBoard>();
				GameBoardNode currentNode = node;
				while(currentNode != null) {
					Renderer.draw(currentNode.gameBoard);
					goalPath.add(currentNode.gameBoard);
					currentNode = currentNode.parent;
				}
				return goalPath;
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
