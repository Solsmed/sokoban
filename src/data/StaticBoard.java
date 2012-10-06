package data;

public class StaticBoard {
	public static boolean[] floor;
	public static int[] goalPositions;
	public static int start;
	
	public static int MAP_WIDTH;
	public static int MAP_HEIGHT;
	public static int MAP_SIZE;
	
	public static int[] directions;
	public static int UP;
	public static int DOWN;
	public static int LEFT;
	public static int RIGHT;
	
	public static int NULL = -1;
	
	public static void init(int width, int height, int numGoals){
		StaticBoard.MAP_WIDTH = width;
		StaticBoard.MAP_HEIGHT = height;
		MAP_SIZE = width * height;
		floor = new boolean[width*height];
		goalPositions = new int[numGoals];
		start = NULL;
		UP = -width;
		DOWN = width;
		LEFT = -1;
		RIGHT = 1;
		directions = new int[] {UP, RIGHT, DOWN, LEFT};
	}
}
