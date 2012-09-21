import java.util.*;

public class GameBoardTree {
	// A tree with all the permutations of boards, used in the BFS
	GameBoardNode root;
	Queue<GameBoardNode> bfsQueue;
	
	public GameBoardTree(GameBoard root){
		this.root = new GameBoardNode(null, root);
		
		bfsQueue = new LinkedList<GameBoardNode>();
	}
	
	public void totalSearch() {
		bfsQueue.clear();
		
		bfsQueue.add(root);
		
		while(!bfsQueue.isEmpty()) {
			expandNext();
		}
	}

	public void expandNext() {
		GameBoardNode expansionNode = bfsQueue.poll();
		
		Set<GameBoard> valids = MoveValidator.getValidPermutations(expansionNode.gameBoard);
		
		Iterator<GameBoard> it = valids.iterator();
		
		while(it.hasNext()) {
			GameBoardNode newNode = new GameBoardNode(expansionNode, it.next());
			expansionNode.addChild(newNode);
			bfsQueue.add(newNode);
		}
	}
}
