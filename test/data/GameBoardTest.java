package data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameBoardTest {
	GameBoard miniBoard, smallBoard;
	
	@Test
	public void testGetAnchorPosition() {
		miniBoard = new GameBoard(new String[] {
				 "#####",
				 "# $.#",
				 "# * #",
				 "#@  #",
				 "#####"
		 });
		assertTrue(miniBoard.getAnchorPosition(pos(2,3)) == pos(1,1));
		
		smallBoard = new GameBoard(new String[] {
				" ######### ",
				" #   #   # ",
				" # @ #   # ",
				"## ** ** # ",
				"#  #* *# ##",
				"#  ** **  #",
				"#         #",
				"###########",
		});
		assertTrue(smallBoard.getAnchorPosition(pos(3,2)) == pos(2,1));
		int anchor = smallBoard.getAnchorPosition(pos(7,1));
		System.out.println(pos(anchor));
		assertTrue(anchor == pos(2,1));
	}
	
	private int pos(int x, int y) {
		return y*StaticBoard.MAP_WIDTH + x;
	}
	
	private String pos(int p) {
		return (p % StaticBoard.MAP_WIDTH) + "," + (p / StaticBoard.MAP_WIDTH);
	}
}
