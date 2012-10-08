import java.util.*;

public class GameBoardTree {
	// A tree with all the permutations of boards, used in the BFS
	GameBoardNode root;
	
	Set<GameBoard> nodeSet;
	
	public GameBoardTree(GameBoard rootGameBoard, Set<GameBoard> visitedBoardsSet){
		this.root = new GameBoardNode(this, null, rootGameBoard, null);
				
		nodeSet = visitedBoardsSet;
		
		nodeSet.add(root.gameBoard);
	}
		
	public GameBoardNode getRoot() {
		return root;
	}
	
	public void addNode(GameBoardNode node) {
		nodeSet.add(node.gameBoard);
	}
}
