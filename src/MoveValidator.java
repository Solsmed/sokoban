import java.util.*;

public class MoveValidator {
	static char[] directions ={'U','R','D','L'};
	public static Set<GameBoard> getValidPermutations(GameBoard b) {
		HashSet<GameBoard> validPermutations = new HashSet<GameBoard>();
		ArrayList<Point> boxes = b.boxes;
		for(Point box : boxes){
			for(char direction: directions){
				if(b.canPull(box,direction)){
					GameBoard newboard=new GameBoard(b);
					Renderer.draw(newboard);
					System.out.println("BOARD B4");
					validPermutations.add(newboard.makeMove(box, direction));
					System.out.println("BOARD AFTER");
					Renderer.draw(newboard);
					
				}
			}
		}
		
		return validPermutations;
	}
	
	public static Set<GameBoard> getPossibleEndBoards(GameBoard board) {
		Set<GameBoard> possibleEndBoards = new HashSet<GameBoard>();
		
		GameBoard endBoard = board.getEndBoard();
		
		int dx[] = new int[] {0,1,0,-1};
		int dy[] = new int[] {-1,0,1,0};
		
		for(int b = 0; b < endBoard.boxes.size(); b++) {
			Point boxPoint = endBoard.boxes.get(b);
			int bx = boxPoint.getX();
			int by = boxPoint.getY();
			
			for(int d = 0; d < dx.length; d++) {
				if(board.isWalkable(bx+dx[d],by+dy[d])) {
					GameBoard newBoard = new GameBoard(board);
					Renderer.draw(newBoard);
					System.out.println("BOARD B4");
					newBoard.player = new Point(bx+dx[d],by+dy[d]);
					possibleEndBoards.add(newBoard);
				}
			}	
		}
		
		return possibleEndBoards;
	}
}
