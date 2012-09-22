package data;

import static org.junit.Assert.*;

import org.junit.Test;


/*
String[] map = new String[] {"#####",
									 "#  .#",
									 "# * #",
									 "#@  #",
									 "#####"};
		
		GameBoard gb1 = new GameBoard(map);
		
		GameBoardNode parent = new GameBoardNode(null, gb1);
		GameBoardNode child1 = new GameBoardNode(parent, gb1);
		GameBoardNode child2 = new GameBoardNode(parent, gb1);
		GameBoardNode grandChild11 = new GameBoardNode(child1, gb1);
		
		assert(parent.getChildren().size() == 2);
 */
public class GameBoardNodeTest {

	@Test
	public void testHashCode() {
		String[] map = new String[] {"#####",
				 "#  .#",
				 "# * #",
				 "#@  #",
				 "#####"};

		GameBoard gb1 = new GameBoard(map);
		
		GameBoardNode parent = new GameBoardNode(null, gb1);
		GameBoardNode child1 = new GameBoardNode(parent, gb1);
		GameBoardNode child2 = new GameBoardNode(parent, gb1);
		GameBoardNode grandChild11 = new GameBoardNode(child1, gb1);
		
		assertEquals("Resultat",parent.hashCode(),child1.hashCode());
	}

	@Test
	public void testGameBoardNode() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTree() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetChildren() {
		fail("Not yet implemented");
	}

	@Test
	public void testSpawnChildren() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

}
