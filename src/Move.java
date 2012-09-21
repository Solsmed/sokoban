
public class Move {
	private Point position;
	private char direction;
	
	public Move(Point p, char dir){
		direction = dir;
		position = p;
	}
	
	public Point makeMove(){
		int xdiff = 0;
		int ydiff = 0;
		
		switch(direction){
		case 'U':
			ydiff = -1;
			break;
		case 'R':
			xdiff = 1;
			break;
		case 'D':
			ydiff = 1;
			break;
		case 'L':
			xdiff = -1;
			break;
			
		}
		return new Point(position.getX() + xdiff, position.getY() + ydiff);
	}
}
