package data;


public class Renderer {
	public static String draw(GameBoard b) {
		char[] map = new char[StaticBoard.MAP_SIZE];
		
		/*
		
		Wall					#	0x23
		Player					@	0x40
		Player on goal square	+	0x2b
		Box						$	0x24
		Box on goal				*	0x2a
		Goal square				.	0x2e
		Empty floor			(space)	0x20
		Start					s	0x73
		
		 */
		
		// Static stuff
		
		for(int p=0; p < StaticBoard.MAP_SIZE; p++){
			if(StaticBoard.floor[p])
				map[p] = ' ';
			else
				map[p] = '#';
		}
		
		for(int g = 0; g < StaticBoard.goalPositions.length; g++)
			map[StaticBoard.goalPositions[g]] = '.';
		
		map[StaticBoard.startPosition] = 's';
		
		
		// Dynamic stuff
		
		if(map[b.playerPosition] == '.')
			map[b.playerPosition] = '+';
		else
			map[b.playerPosition] = '@';
		
		for(int i = 0; i < b.boxPositions.length; i++) {
			int boxPos = b.boxPositions[i];
			
			switch(map[boxPos]) {
			case ' ':
				map[boxPos] = '$';
				break;
			case '.':
				map[boxPos] = '*';
				break;
			}
		}
		
		StringBuffer sb = new StringBuffer();
		for(int p = 0; p < map.length; p++) {
			sb.append(map[p]);
			if(p % StaticBoard.MAP_WIDTH == StaticBoard.MAP_WIDTH - 1)
				sb.append('\n');
		}
		return sb.toString();
	}
}
