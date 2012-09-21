import java.util.HashSet;
import java.util.Set;

public class GameBoardNode {
	GameBoardNode parent;
	Set<GameBoardNode> children;
	
	GameBoard gameBoard;
	
	public GameBoardNode(GameBoardNode parent, GameBoard gb) {
		gameBoard = gb;
		
		children = new HashSet<GameBoardNode>();
	}
	
	public boolean addChild(GameBoardNode b) {
		return children.add(b);
	}
	
	@Override
	public int hashCode() {
		return gameBoard.hashCode();
	}
}
