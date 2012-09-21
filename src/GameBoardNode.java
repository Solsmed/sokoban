import java.util.Iterator;
import java.util.Set;

public class GameBoardNode {
	GameBoardNode parent;
	Set<GameBoardNode> children;
	
	GameBoard gameBoard;
	
	public GameBoardNode(GameBoardNode parent, GameBoard gb) {
		gameBoard = gb;
		
		children = null;
	}
	
	public boolean addChild(GameBoardNode b) {
		return children.add(b);
	}
	
	public Set<GameBoardNode> getChildren() {
		if(children != null) {
			return children;
		}
		
		makeChildren();
		
		return children;
	}
	
	private void makeChildren() {
		Set<GameBoard> newChildren = MoveValidator.getValidPermutations(gameBoard);
		
		Iterator<GameBoard> it = newChildren.iterator();
		
		while(it.hasNext()) {
			GameBoardNode newNode = new GameBoardNode(this, it.next());
			addChild(newNode);
		}
	}
	
	@Override
	public int hashCode() {
		return gameBoard.hashCode();
	}
}
