import java.util.Comparator;

public class XComparator implements Comparator<GameBoardNode> {

	public static final int STOPMOVE_AFTER_EACH_STEP = 1;
	public static final int STOPMOVE_AFTER_N_STEPS = 2;
	public static final int STOPMOVE_WHEN_BOX_IS_DONE = 3;
	public static final int STOPMOVE_WHEN_BOX_IS_WITHIN_K_STEPS = 4;
	public static final int STOPMOVE_AFTER_RANDOM_NUMBER_OF_MOVES = 5;
	
	private static final int STOP = 0;
	private static final int BOOST = -1;
	private static final int DELAY = 1;
	
	private int condition;
	private int n;
	
	public XComparator(int condition) {
		this.condition = condition;
	}
	
	public XComparator(int condition, int n) {
		this.condition = condition;
		this.n = n;
	}
	
	public int compare(GameBoardNode offeredNode, GameBoardNode queuedNode) {
		int currentBox = getMovingBoxIndex(TreeSearcher.currentNode);
		int aBox = getMovingBoxIndex(offeredNode);
		int bBox = getMovingBoxIndex(queuedNode);
		
		if(aBox == -1 || bBox == -1)
			return 0;
		
		switch(condition) {
		case STOPMOVE_AFTER_EACH_STEP:
			return STOP;
		case STOPMOVE_AFTER_N_STEPS:
			/*
			stepCounter++;
			if(stepCounter >= n)
				return STOP; // TODO
			STOP
			*/
			return STOP;
		case STOPMOVE_WHEN_BOX_IS_DONE:
			if(isDone(offeredNode.gameBoard, aBox))
				return STOP;
			if(aBox == currentBox) {
				return BOOST;
			}
			else if(bBox == currentBox) {
				return DELAY;
			}
			return STOP;
		case STOPMOVE_WHEN_BOX_IS_WITHIN_K_STEPS:
			return 0; // TODO
		case STOPMOVE_AFTER_RANDOM_NUMBER_OF_MOVES:
			return 0; // TODO
		}
		
		return 0;
	}
	
	private int getMovingBoxIndex(GameBoardNode node) {
		if(node == null)
			return -1;
		int currentMovingBox;
		Move m = node.priorMove;
		if(m != null)
			currentMovingBox = m.getBoxIndex();
		else
			currentMovingBox = -1;
		
		return currentMovingBox;
	}
	
	private boolean isDone(GameBoard board, int boxIndex) {
		for(int box = 0; box < StaticBoard.startBoard.boxPositions.length; box++) {
			if(board.boxPositions[boxIndex] == StaticBoard.startBoard.boxPositions[box])
				return true;
		}
		return false;
	}
}
