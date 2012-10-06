package search;

import data.*;
import java.util.*;

import data.GameBoard;
import data.Point;

public class MoveValidator {
	static char[] directions ={'U','R','D','L'};
	public static Set<GameBoard> getValidPermutations(GameBoard b) {
		HashSet<GameBoard> validPermutations = new HashSet<GameBoard>();
		GameBoard newboard=new GameBoard(b);
		ArrayList<Point> boxes = newboard.boxes;
		for(int i=0;i<boxes.size();i++){
			for(char direction: directions){
				if(newboard.canPull(boxes.get(i),direction)){
				
					newboard.makeMove(boxes.get(i),i, direction);
					validPermutations.add(newboard);					
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
					newBoard.player = new Point(bx+dx[d],by+dy[d]);
					possibleEndBoards.add(newBoard);
				}
			}	
		}
		
		return possibleEndBoards;
	}
}
