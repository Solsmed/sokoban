package data;

import java.util.Comparator;

public class GameBoardNodeComparator implements Comparator<GameBoardNode> {
	public GameBoardNodeComparator(String mode) {
		
	}
	
	@Override
    public int compare(GameBoardNode a, GameBoardNode b)
    {
		int aDone = 0;
		int bDone = 0;
		
        // TODO Boxes must not have identities, a box is a box, no matter what its index is
        for(int i = 0; i < a.gameBoard.boxPositions.length; i++) {
        	if(a.gameBoard.boxPositions[i] == StaticBoard.startBoard.boxPositions[i])
        		aDone++;
        	if(b.gameBoard.boxPositions[i] == StaticBoard.startBoard.boxPositions[i])
        		bDone++;
        }

        System.out.println(aDone + " vs " + bDone);
        
		if (aDone > bDone)
        {
            return -1;
        }
        if (aDone < bDone)
        {
            return 1;
        }
        
        return 0;
    }

}
