
public class Renderer {
	public static void draw(GameBoard b) {
		StringBuffer sb = new StringBuffer();
		
		for(int y=0;y<b.board.ySize;y++){
			for(int x=0;x<b.board.ySize;x++){
				if(b.board.floor[x][y])
					sb.append('#');
				else
					sb.append(' ');
			}
		}
		
		System.out.println(sb.toString());
	}

}
