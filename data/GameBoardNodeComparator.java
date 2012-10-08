import java.util.Comparator;

public class GameBoardNodeComparator implements Comparator<GameBoardNode> {
	private final int Y_CONDITION = 2;
	
	public GameBoardNodeComparator() {
		
	}
	
	@Override
    public int compare(GameBoardNode a, GameBoardNode b) {
		GameBoardNode cn = TreeSearcher.currentNode;
		
		switch(Y_CONDITION) {

		// Every box
		case 1: 
			return 0;
			
		// Every unplaced box
		case 2:
			int aDone = 0;
			int bDone = 0;
			
	        for(int startBox = 0; startBox < a.gameBoard.boxPositions.length; startBox++) {
	        	for(int box = 0; box < a.gameBoard.boxPositions.length; box++) {
	        		if(a.gameBoard.boxPositions[box] == StaticBoard.startBoard.boxPositions[startBox])
	        			aDone++;
	        		if(b.gameBoard.boxPositions[box] == StaticBoard.startBoard.boxPositions[startBox])
		        		bDone++;
	        	}
	        }
	        
			if (aDone > bDone)
	        {
	            return -1;
	        }
	        if (aDone < bDone)
	        {
	            return 1;
	        }
	        
	        return 0;
	        
		// Lexicographical order
		case 3:
			
		// Sum of shortest Manhattan distances to goals
		case 4:
			int sumDistA = 0;
			int sumDistB = 0;
			
			int numBoxes = a.gameBoard.boxPositions.length;

			int[][] distances = new int[numBoxes][numBoxes];
			for(int box = 0; box < numBoxes; box++) {
				for(int goal = 0; goal < numBoxes; goal++) {
					// TODO fill distances
				}
			}
			
			// TODO Let closest pair bind, then the next free pair, etc
			// Make a list of matching boxes and goals
			
			// Sum the list's pairwise distances and return

		// Box closest to target
		case 5:
			// TODO must know which box is going to be moved, look at GameBoardNode
			
		// Random box
		case 6:
			int yusd = 1;
			return 0;
		}
	
		return 0;
		}
}
