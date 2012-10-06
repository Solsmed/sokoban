package search;

import java.util.*;

import data.*;

public class TreeSearcher {
	private List<GameBoardTree> treeList;
	private Set<GameBoard> roots;
	private GameBoard start;
	
	GameBoard goal;
	
	PriorityQueue<GameBoardNode> bfsQueue;
	Set<GameBoard> nodeSet;
	
	public TreeSearcher(GameBoard start) {
		this.start = start;
		roots = MoveValidator.getPossibleEndBoards(start.getEndBoard());
		
		treeList = new ArrayList<GameBoardTree>();
		nodeSet = new HashSet<GameBoard>();
		
		for(GameBoard root : roots) {
			treeList.add(new GameBoardTree(root, nodeSet));
			System.out.println("Created a root");
		}
		
		Comparator<GameBoardNode> comparator = new GameBoardNodeComparator("Y3"); 
		bfsQueue = new PriorityQueue<GameBoardNode>(64*1024, comparator);
	}
	
	public List<GameBoard> totalSearch() {
		bfsQueue.clear();
		
		for(GameBoardTree tree : treeList)
			bfsQueue.add(tree.getRoot());
		
		while(!bfsQueue.isEmpty()) {
			GameBoardNode node = bfsQueue.poll();
			System.out.println(nodeSet.size());
			if(node.gameBoard.equals(start)){
				List<GameBoard> goalPath = new LinkedList<GameBoard>();
				GameBoardNode currentNode = node;
				while(currentNode != null) {
					System.out.println(Renderer.draw(currentNode.gameBoard));
					goalPath.add(currentNode.gameBoard);
					currentNode = currentNode.parent;
				}
				return goalPath;
			}
			
			
			node.spawnChildren();
			System.out.println("children: " + node.getChildren().size());
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
