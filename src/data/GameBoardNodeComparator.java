package data;

import java.util.Comparator;

public class GameBoardNodeComparator implements Comparator<GameBoardNode> {
	public GameBoardNodeComparator(String mode) {
		
	}
	
	@Override
    public int compare(GameBoardNode a, GameBoardNode b)
    {
        /*if (x.length() < y.length())
        {
            return -1;
        }
        if (x.length() > y.length())
        {
            return 1;
        }*/
        return 0;
    }

}
