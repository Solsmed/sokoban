import java.util.*;

public class MoveValidator {
	static char[] directions ={'U','R','D','L'};
	public static Set<GameBoard> getValidPermutations(GameBoard b) {
		HashSet<GameBoard> validPermutations = new HashSet<GameBoard>();
		ArrayList<Point> boxes=b.getBoxes();
		for(Point box : boxes){
			for(char direction: directions){
				if(b.canPull(box,direction)){
					
				}
			}
		}
		
		return validPermutations;
	}
}
