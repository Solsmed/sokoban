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
					String path=findPath(bn.gameBoard.playerPosition,boxPos+direction,bn.gameBoard);
//					System.out.println(path);
					GameBoard newBoard = new GameBoard(bn.gameBoard);
					newBoard.boxPositions[b] += direction;
					int newPlayerPosition = boxPos + 2*direction;
					newBoard.playerPosition = newPlayerPosition;
					if(!existingNodes.contains(newBoard)) {
						Move move = new Move(newPlayerPosition,direction,path);
						GameBoardNode newNode = new GameBoardNode(bn.getTree(), bn, newBoard, move);
						validPermutations.add(newNode);
						existingNodes.add(newBoard);
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
//					System.out.println("@ MOVEVALIDATOR ANCHOR POSITON" +newBoard.getAnchorPosition(newBoard.playerPosition)+" HASH VALUE "+newBoard.hashCode());
//					System.out.println("@ MOVEVALIDATOR\n"+Renderer.draw(newBoard));
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
	
	public static String findPath(int from,int to,GameBoard b){
		int start=from;
		
		
	
		int goal=to;
		
		if(start==goal){
			return "";
		}
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		boolean[] visited = new boolean[StaticBoard.MAP_SIZE];
		int[] path = new int[StaticBoard.MAP_SIZE];
		path[start]=1000;
		visited[start]=true;
		int current;
		while(!queue.isEmpty()){
			current = queue.poll();
			
//			adjacent = neighbour.get(current);
			for(int dir=0;dir<StaticBoard.directions.length;dir++){
				int neighbour=current+StaticBoard.directions[dir];
				if(b.isWalkable(neighbour)){
				if(!visited[neighbour]){
					queue.add(neighbour);
					visited[neighbour]=true;
					path[neighbour]=StaticBoard.directions[dir];
					if(neighbour == goal){
						return getPath(goal,path);
					}

				}
				}
			}
		}
		return "";
		
	}

	private static String getPath(int goal, int[] path) {
		int direction=-path[goal];
		if(direction==-1000){
			return "";
		}
		if(StaticBoard.UP==direction){
			return"U"+getPath(goal+direction,path);
		}
		if(StaticBoard.RIGHT==direction){
			return "R"+getPath(goal+direction,path);
		}
		if(StaticBoard.DOWN==direction){
			return "D"+getPath(goal+direction,path);
		}
		if(StaticBoard.LEFT==direction){
			return "L"+getPath(goal+direction,path);
		}
		return "";
	}
}
