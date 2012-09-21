
public class Renderer {
	public static void draw(GameBoard b) {
		StringBuffer sb = new StringBuffer();
		
		Point boardSize = b.board.getSize();
		
		for(int y=0;y<boardSize.getY();y++){
			for(int x=0;x<boardSize.getX();x++){
				if(b.board.isFloor(x, y))
					sb.append(' ');
				else
					sb.append('#');
			}
			sb.append('\n');
		}
		
		System.out.println(sb.toString());
	}

}
