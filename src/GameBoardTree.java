import java.util.*;

public class GameBoardTree {
	// A tree with all the permutations of boards, used in the BFS
	GameBoardNode root;
	
	Set<GameBoardNode> nodeSet;
	
	public GameBoardTree(GameBoard root){
		this.root = new GameBoardNode(null, root);
		this.root.setTree(this);
		
		nodeSet = new HashSet<GameBoardNode>();
	}
		
	public GameBoardNode getRoot() {
		return root;
	}
	
	public void addNode(GameBoardNode node) {
		nodeSet.add(node);
	}
}
