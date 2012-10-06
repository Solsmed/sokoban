package data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameBoardTest {
	String[] map;
	GameBoard board;
	
	@Before
	public void setUp() throws Exception {
		map = new String[] {
				 "#####",
				 "# $.#",
				 "# * #",
				 "#@  #",
				 "#####"};
		
		board = new GameBoard(map);
	}

	@Test
	public void testGetAnchorPosition() {
		assertTrue(board.getAnchorPosition(16) == 6);
	}
}
