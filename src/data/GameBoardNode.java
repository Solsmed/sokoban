package data;

import java.util.HashSet;
import java.util.Set;

import search.MoveValidator;

public class GameBoardNode {
	private GameBoardTree tree;
	public GameBoardNode parent;
	Set<GameBoardNode> children;
	
	public Move priorMove;
	
	public GameBoard gameBoard;
	
	public GameBoardNode(GameBoardTree tree, GameBoardNode parent, GameBoard gb, Move move) {
		gameBoard = gb;
		this.parent = parent;
		
		priorMove = move;
		
		children = new HashSet<GameBoardNode>();
		
		this.tree = tree;
	}
	
	public GameBoardTree getTree() {
		return tree;
	}
	
	public Set<GameBoardNode> getChildren() {
		return children;
	}
	
	public Set<GameBoardNode> spawnChildren() {
		children = MoveValidator.getValidAndNewPermutations(this, tree.nodeSet);

		return getChildren();
	}
	
	/*
	private GameBoardNode getRoot() {
		GameBoardNode root = null;
		
		while(parent != null)
			root = root.parent;
		
		return root;
	}
	*/
	
	public boolean equals(Object obj) {
		if (obj instanceof GameBoardNode)
			return gameBoard.equals(((GameBoardNode)obj).gameBoard);
		
		return false;
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
