public class GameBoardTree {
	// A tree with all the permutations of boards, used in the BFS
	GameBoardNode root;
	
	public GameBoardTree(GameBoard root){
		this.root = new GameBoardNode(null, root);
	}
		
	public GameBoardNode getRoot() {
		return root;
	}
}
