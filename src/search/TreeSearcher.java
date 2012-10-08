package search;

import java.util.*;

import data.*;

public class TreeSearcher {
	private List<GameBoardTree> treeList;
	private Set<GameBoard> roots;
	//private GameBoard start;
	
	GameBoard goal;
	
	PriorityQueue<GameBoardNode> bfsQueue;
	Set<GameBoard> nodeSet;
	
	public TreeSearcher() {
		//this.start = start;
		roots = MoveValidator.getPossibleEndBoards(StaticBoard.startBoard.getEndBoard());
		
		treeList = new ArrayList<GameBoardTree>();
		nodeSet = new HashSet<GameBoard>();
		
		for(GameBoard root : roots) {
			treeList.add(new GameBoardTree(root, nodeSet));
			System.out.println("Created a root");
		}
		
		Comparator<GameBoardNode> comparator = new GameBoardNodeComparator("Y3"); 
		bfsQueue = new PriorityQueue<GameBoardNode>(64*1024, comparator);
	}
	
	public String totalSearch() {
		bfsQueue.clear();
		
		for(GameBoardTree tree : treeList)
			bfsQueue.add(tree.getRoot());
		
		while(!bfsQueue.isEmpty()) {
			GameBoardNode node = bfsQueue.poll();
//			System.out.println(nodeSet.size());
			if(node.gameBoard.equals(StaticBoard.startBoard)){
				List<GameBoardNode> goalPath = new LinkedList<GameBoardNode>();
				GameBoardNode currentNode = node;
				while(currentNode != null) {
					System.out.println(Renderer.draw(currentNode.gameBoard));
					System.out.println(currentNode.priorMove);
					goalPath.add(currentNode);
					currentNode = currentNode.parent;
				}
				return getPathLOL(goalPath);
			}
			
			
			node.spawnChildren();
//			System.out.println("children: " + node.getChildren().size());
			Set<GameBoardNode> children = node.getChildren();
			Iterator<GameBoardNode> it = children.iterator();
			while(it.hasNext()) {
				
				GameBoardNode child = it.next();
			
				bfsQueue.add(child);
			}
		}

		
		return "";
	}
	String getPathLOL(List<GameBoardNode> transitionTables){
		String path="";
		System.out.println(StaticBoard.startPosition+" "+transitionTables.get(0).gameBoard.playerPosition);
		path=path+MoveValidator.findPath(StaticBoard.startPosition, transitionTables.get(0).gameBoard.playerPosition, StaticBoard.startBoard);
		for(GameBoardNode node: transitionTables){
			if(node.priorMove!=null){
			path=path+boxMove(-node.priorMove.getDirection());
			path=path+node.priorMove.getPath();
			}
			
		}
		return path;
	}
	public String boxMove(int direction){
		String move="";
		if(StaticBoard.UP==direction){
			move="U";

		}
		if(StaticBoard.RIGHT==direction){
			move="R";

		}
		if(StaticBoard.DOWN==direction){
			move="D";

		}
		if(StaticBoard.LEFT==direction){
			move="L";

		}
		return move;


	}

}
