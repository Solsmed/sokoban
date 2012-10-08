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
		
		
		GameBoard hardBoardQuestionmark = new GameBoard(new String[] {
			" ######### ", 
			" ######### ",
			" #.@ $   # ",
			"####   * # ",
			"# ######## ",
			"#         #",
			"#         #",
			"###########"
		});
		
		assertTrue(hardBoardQuestionmark.getAnchorPosition(pos(6,2)) == pos(2,2));
		assertTrue(hardBoardQuestionmark.getAnchorPosition(pos(3,2)) == pos(2,2));
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
	
	@Test
	public void testHashCode() {
		GameBoard board = new GameBoard(new String[] {
				" ######### ", 
				" ######### ",
				" #.@ $   # ",
				"####   * # ",
				"# ######## ",
				"#         #",
				"#         #",
				"###########"
			});
		/*GameBoard board3 = new GameBoard(new String[] {
				"#######",
				"#  $. #",
				"#.  $ #",
				"#######"});*/
		int hash1 = board.hashCode();		
		
		GameBoard board2 = new GameBoard(board);
		board2.playerPosition = pos(6,2);
		
		int hash2 = board2.hashCode();
		
		System.out.println(hash1 + " " + hash2 + " " + (hash1 == hash2));
		
		System.out.println(board2.equals(board));
	}
	
	private int pos(int x, int y) {
		return y*StaticBoard.MAP_WIDTH + x;
	}
	
	private String pos(int p) {
		return (p % StaticBoard.MAP_WIDTH) + "," + (p / StaticBoard.MAP_WIDTH);
	}
}
