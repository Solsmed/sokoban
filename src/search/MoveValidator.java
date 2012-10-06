package search;

import data.*;

import java.util.*;

import data.StaticBoard;

public class MoveValidator {
	
	public static Set<GameBoardNode> getValidAndNewPermutations(GameBoardNode bn, Set<GameBoard> existingNodes) {
		HashSet<GameBoardNode> validPermutations = new HashSet<GameBoardNode>();
		
		for(int b = 0; b < bn.gameBoard.boxPositions.length; b++) {
			int boxPos = bn.gameBoard.boxPositions[b];
			for(int dir = 0; dir < StaticBoard.directions.length; dir++) {
				int direction = StaticBoard.directions[dir];
				if(bn.gameBoard.canPull(boxPos, direction)) {
					
					GameBoard newBoard = new GameBoard(bn.gameBoard);
					newBoard.boxPositions[b] += direction;
					int newPlayerPosition = boxPos + 2*direction;
					newBoard.playerPosition = newPlayerPosition;
					if(!existingNodes.contains(newBoard)) {
						Move move = new Move(newPlayerPosition, direction);
						GameBoardNode newNode = new GameBoardNode(bn.getTree(), bn, newBoard, move);
						validPermutations.add(newNode);
						existingNodes.add(newNode.gameBoard);
					}
				}
			}
		}
		
		return validPermutations;
	}
	
	/**
	 * 
	 * @param board
	 * @return A set of GameBoards, one element for each possible (hash-unique) end GameBoard. If, for example, the parameter GameBoard's end GameBoard presents two distinct rooms in which the player can end up in, they generate two elements in the set.
	 */
	public static Set<GameBoard> getPossibleEndBoards(GameBoard board) {
		Set<GameBoard> possibleEndBoards = new HashSet<GameBoard>();
		
		GameBoard endBoard = board.getEndBoard();
		
		for(int b = 0; b < endBoard.boxPositions.length; b++) {
			int boxPos = endBoard.boxPositions[b];
			
			for(int dir = 0; dir < StaticBoard.directions.length; dir++) {
				if(board.isWalkable(boxPos + StaticBoard.directions[dir])) {
					GameBoard newBoard = new GameBoard(board);
					newBoard.playerPosition = boxPos + StaticBoard.directions[dir];
					possibleEndBoards.add(newBoard);
				}
			}	
		}
		
		return possibleEndBoards;
	}
	
	/**
	 * Calculates the shortest forward (push) movement pattern of the player that makes the board transform from the <code>from</code> parameter to the <code>to</code> parameter.
	 * 
	 * Assumes the boards are adjacent and will presume that the first difference found is the only one existing.
	 * 
	 * @param from The board at t_n.
	 * @param to The board at t_(n+1).
	 * @return A sequence of movements of the player: x_0, x_1, ..., x_m where x_i € {U,R,D,L}. <br/><code>null</code> if no difference found (should never happen if this method is used as intended).
	 */
	public static char[] getPlayerMovementFor(GameBoard from, GameBoard to) {
		// Get last movement
		
		
		return null;
	}
}
