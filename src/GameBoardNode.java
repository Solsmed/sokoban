import java.util.Iterator;
import java.util.Set;

public class GameBoardNode {
	GameBoardTree tree;
	GameBoardNode parent;
	Set<GameBoardNode> children;
	
	GameBoard gameBoard;
	
	public GameBoardNode(GameBoardTree tree, GameBoardNode parent, GameBoard gb) {
		this.tree = tree;
		this.tree.addNode(this);
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
			GameBoardNode newNode = new GameBoardNode(tree, this, it.next());
			addChild(newNode);
		}
	}
	
	@Override
	public int hashCode() {
		return gameBoard.hashCode();
	}
	
	public boolean equals(Object obj) {
		return hashCode() == obj.hashCode();
		/*
		if(this == obj)
			return true;
		if((obj == null) || (obj.getClass() != this.getClass()))
			return false;
		// object must be GameBoardNode at this point
		GameBoardNode test = (GameBoardNode)obj;
		return num == test.num && (data == test.data || (data != null && data.equals(test.data)));
		*/
	}
}
