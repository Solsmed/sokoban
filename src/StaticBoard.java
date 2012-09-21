public class StaticBoard {
	private boolean[][] floor;
	private boolean[][] goal;
	int xSize;
	int ySize;
	Point start;
	
	public StaticBoard(boolean floor[][], boolean goal[][], int xSize, int ySize,Point start){
		this.xSize = xSize;
		this.ySize = ySize;
		this.floor = floor;
		this.goal = goal;
		this.start=start;
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
	
	public boolean isGoal(Point p) {
		try {
			return goal[p.getX()][p.getY()];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("isGoal(): no such position. " + p + ", board size: " + xSize + "," + ySize);
			return false;
		}
	}
	
	@Deprecated
	public Point getSize() {
		return new Point(xSize, ySize);
	}
}
