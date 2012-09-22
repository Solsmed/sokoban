package data;
public class StaticBoard {
	boolean[][] floor;
	boolean[][] goal;
	int xSize;
	int ySize;
	Point start;
	
	public StaticBoard(int xSize, int ySize){
		this.xSize = xSize;
		this.ySize = ySize;
		this.floor = new boolean[xSize][ySize];
		this.goal = new boolean[xSize][ySize];
		this.start=null;
	}
	
	public boolean isFloor(int x, int y) {
		return isFloor(new Point(x, y));
	}
	
	public boolean isFloor(Point p) {
		try {
			return floor[p.getX()][p.getY()];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("isFloor(): no such position. " + p + ", board size: " + xSize + "," + ySize);
			return false;
		}
	}
	
	public boolean isGoal(int x,int y) {
		try {
			return goal[x][y];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("isGoal(): no such position. " + new Point (x,y) + ", board size: " + xSize + "," + ySize);
			return false;
		}
	}
	
	@Deprecated
	public Point getSize() {
		return new Point(xSize, ySize);
	}
}
