package data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameBoardTest {
	String[] map;
	
	@Before
	public void setUp() throws Exception {
		map = new String[] {
				 "#####",
				 "#  .#",
				 "# * #",
				 "#@  #",
				 "#####"};
		
		GameBoard board = new GameBoard(map);
	}

	@Test
	

}
