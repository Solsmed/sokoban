public class StaticBoard {
	boolean[][] floor;
	int xSize;
	int ySize;
	
	public StaticBoard(boolean floor[][], int xSize, int ySize){
		this.xSize = xSize;
		this.ySize = ySize;
		this.floor = floor;
	}
	
	public boolean isFloor(Point p) {
		try {
			return floor[p.getX()][p.getY()];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("isFloor(): no such position. " + p + ", board size: " + xSize + "," + ySize);
			return false;
		}
	}
}
