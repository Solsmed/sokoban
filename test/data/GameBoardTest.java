package data;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameBoardTest {	
	@Test
	public void testGetAnchorPosition() {
		GameBoard miniBoard = new GameBoard(new String[] {
				 "#####",
				 "# $.#",
				 "# * #",
				 "#@  #",
				 "#####"
		 });
		assertTrue(miniBoard.getAnchorPosition(pos(2,3)) == pos(1,1));
		
		GameBoard smallBoard = new GameBoard(new String[] {
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
		assertTrue(anchor == pos(2,1));
	}
	
	@Test
	public void testHasFreePath() {
		GameBoard denseBoard = new GameBoard(new String[] {
				"#######",
				"##   ##",
				"#  #  #",
				"# ### #",
				"# *#@ #",
				"#######",
		});
		
		assertTrue(denseBoard.hasFreePath(pos(4,4), pos(1,4)));

		GameBoard smallBoard = new GameBoard(new String[] {
				" ######### ",
				" #   #   # ",
				" # @ #   # ",
				"## ** ** # ",
				"#  #* *# ##",
				"#  ** **  #",
				"#         #",
				"###########",
		});
		
		assertTrue(smallBoard.hasFreePath(pos(6,2), pos(8,5)));
	}
	
	private int pos(int x, int y) {
		return y*StaticBoard.MAP_WIDTH + x;
	}
	
	private String pos(int p) {
		return (p % StaticBoard.MAP_WIDTH) + "," + (p / StaticBoard.MAP_WIDTH);
	}
}
