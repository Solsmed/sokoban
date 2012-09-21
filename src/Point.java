
public class Point {
	private int x;
	private int y; 
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() { return x; }
	
	public int getY() { return y; }
	
	public void translate(int dx, int dy) {
		x += dx;
		y += dy;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	public Point makeMove(char direction){
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
		return new Point(x + xdiff, y + ydiff);
	}
}
