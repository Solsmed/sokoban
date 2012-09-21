import java.util.*;

public class GameBoard {
	StaticBoard board;
	ArrayList<Point> boxes;
	Point player;
	
	Random random;
	
	private static int[][] boxHashTable;
	private static int[][] playerHashTable;
	
	GameBoard startBoard;
	GameBoard endBoard;
	
	public GameBoard(String text) {
		random = new Random();
		
		// parse string and create board
		// copy boxes and player into startBoard, mutate stuff and make endBoard
		// let startBoard and endBoard point to the same StaticBoard as this instance.
		
		int xSize = 0;
		int ySize = 0;
		
		for(int y = 0; y < ySize; y++)
		for(int x = 0; x < xSize; x++) {
			boxHashTable[x][y] = random.nextInt();
			playerHashTable[x][y] = random.nextInt();
		}
	}
	
	@Override
	public int hashCode() {
		/*
		position of all the boxes
		position of the player
			position is not actual position, but rather
			the position it would have if the player moved
			to the topmost row reachable from its current
			position, then the leftmost column from there.
		*/
		
		// get the position the player would have if it moved top, then left
		Point anchor = getAnchorPoint(player.getX(), player.getY());
		int hashValue = playerHashTable[anchor.getX()][anchor.getY()];
		
		for(int b = 0; b < boxes.size(); b++)
			hashValue ^= boxHashTable[boxes.get(b).getX()][boxes.get(b).getY()];

		return hashValue;
	}

	private Point getAnchorPoint(int fromX, int fromY) {
		int anchorX = 0;
		int anchorY = 0;
		
		
		boolean visited[][] = new boolean[board.getSize().getX()][board.getSize().getY()];
		
		Queue<Point> queue = new LinkedList<Point>();
		
		queue.add(new Point(fromX, fromY));
		
		int minX = fromX;
		int minY = fromY;
		
		while(!queue.isEmpty()) {
			
		}
		
		// find northernmost reachable position through bfs
		// bfs()
		// go left as far as possible
		return new Point(anchorX, anchorY);
	}
	
	private GameBoard update(Move m){
		//use the makeMove-function in Move to change a move's direction
		return null;
	}
	//returns true if we can go from start point to goal point
	public boolean goToPoint(int fromX, int fromY, int toX, int toY){
		int from = fromY * board.getSize().getX() + fromX;
		int goal = toY*board.getSize().getX() + toX;
		boolean found = false;
		int currPos = from;
		int nextPos;
		ArrayList<ArrayList<Integer>> neighbour = new ArrayList<ArrayList<Integer>>();
		Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(from);
		boolean[] visited = new boolean[board.getSize().getX()*board.getSize().getY()];
        ArrayList<Integer> adjacent;
        while(!queue.isEmpty() && !found){
         currPos = queue.poll();
            adjacent = neighbour.get(currPos);
            for(int i:adjacent){
             if(!visited[i] && !found){
              queue.add(i);                                       
                    visited[i]=true;
                    if(i == goal){
                     found=true;  
                    }
             }
            }
        }
        return false;
	}
}
