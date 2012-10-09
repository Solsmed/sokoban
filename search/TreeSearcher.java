import java.util.*;

public class TreeSearcher {
	private List<GameBoardTree> treeList;
	private Set<GameBoard> roots;

	//private GameBoard start;
	
	public static GameBoardNode currentNode;
	
	GameBoard goal;
	
	PriorityQueue<GameBoardNode> bfsQueue;
	Set<GameBoard> nodeSet;
	
	public TreeSearcher(int xcond, int ycond) {
		roots = MoveValidator.getPossibleEndBoards(StaticBoard.startBoard.getEndBoard());
		
		treeList = new ArrayList<GameBoardTree>();
		nodeSet = new HashSet<GameBoard>();
		
		for(GameBoard root : roots) {
			treeList.add(new GameBoardTree(root, nodeSet));
			System.out.println("Created a root");
			
		}
		
		Comparator<GameBoardNode> comparator = new GameBoardNodeComparator(xcond, ycond); 
		bfsQueue = new PriorityQueue<GameBoardNode>(64*1024, comparator);
	}
	
	public String totalSearch() {
		bfsQueue.clear();
		
		for(GameBoardTree tree : treeList){
			bfsQueue.add(tree.getRoot());
//			System.out.println(Renderer.draw((tree.getRoot().gameBoard)));
		}
		
		while(!bfsQueue.isEmpty()) {
			GameBoardNode node = bfsQueue.poll();
			currentNode = node;
//			System.out.println(nodeSet.size());
			if(node.gameBoard.equals(StaticBoard.startBoard)){
				LinkedList<GameBoardNode> goalPath = new LinkedList<GameBoardNode>();
				GameBoardNode currentNode = node;
				while(currentNode != null) {
//					System.out.println(Renderer.draw(currentNode.gameBoard));
//					System.out.println(currentNode.priorMove);
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
	String getPathLOL(LinkedList<GameBoardNode> transitionTables){
		String path="";
//		System.out.println(StaticBoard.startPosition+" "+transitionTables.peekFirst().gameBoard.playerPosition);
		path=path+MoveValidator.findPath(transitionTables.get(0).gameBoard.playerPosition,StaticBoard.startPosition , StaticBoard.startBoard);
//		System.out.println("START PATH "+path);
//		System.out.println("transptable size "+transitionTables.size());
		for(GameBoardNode node: transitionTables){
			if(node.priorMove!=null){
			path=path+boxMove(-node.priorMove.getDirection());
			path=path+node.priorMove.getPath();
			}
			
		}
//		System.out.println("OUTPUT STRING SIZE "+path.length());
//		System.out.println("Spent time comparing: " + GameBoardNodeComparator.spentTime + " seconds.");
//		System.out.println("Comparisons made: " + GameBoardNodeComparator.counter + ".");
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
