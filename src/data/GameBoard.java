package data;
import java.util.*;

public class GameBoard {
	public static final int WALL = 0x23;	//	#
	public static final int PLAYER = 0x40;	//	@
	public static final int PLAYER_ON_GOAL_SQUARE = 0x2b;	//	+
	public static final int BOX = 0x24;	//	$
	public static final int BOX_ON_GOAL = 0x2a; // *
	public static final int GOAL_SQUARE = 0x2e; // .
	public static final int EMPTY_FLOOR = 0x20; // (space)
	public static final int START = 0x73; // s
	
	public int[] boxPositions;
	public int playerPosition;

	Random random;

	private static int[] boxHashTable;
	private static int[] playerHashTable;

	public GameBoard(GameBoard old){
		boxPositions = Arrays.copyOf(old.boxPositions,  old.boxPositions.length);
		playerPosition = old.playerPosition;
	}
	public GameBoard(String[] data) {
		random = new Random();

		// find out dimensions
        int width = 0;
		int height = data.length;
		
		for(int i = 0; i < height; i++) {
			width = width < data[i].length() ? data[i].length() : width;
		}

		Vector<Integer> goals = new Vector<Integer>();
		Vector<Integer> boxes = new Vector<Integer>();
		
		StaticBoard.init(width, height, 0);
		
		// parse string and create board
		// copy boxes and player into startBoard, mutate stuff and make endBoard
		// let startBoard and endBoard point to the same StaticBoard as this instance.

		playerHashTable =new int[StaticBoard.MAP_SIZE];
		boxHashTable =new int[StaticBoard.MAP_SIZE];

		for(int p = 0; p < StaticBoard.MAP_SIZE; p++) {
			boxHashTable[p] = random.nextInt();
			playerHashTable[p] = random.nextInt();
		}
		
		for(int y = 0; y < data.length; y++) {
			char[] lineData = data[y].toCharArray();
			for(int x = 0; x < lineData.length; x++) {
				
				int p = y*StaticBoard.MAP_WIDTH+x;
				
				switch(lineData[x]) {
				case PLAYER_ON_GOAL_SQUARE:
					goals.add(p);
				case PLAYER:
					playerPosition = p;
					StaticBoard.start = p;
				case EMPTY_FLOOR:
					StaticBoard.floor[p] = true;
					break;
				case BOX_ON_GOAL:
					boxes.add(p);
				case GOAL_SQUARE:
					goals.add(p);
					StaticBoard.floor[p] = true;
					break;
				case BOX:
					boxes.add(p);
					StaticBoard.floor[p] = true;
					break;
				}
			}
		}
		
		int numGoals = goals.size();
		if(numGoals != boxes.size()) {
			System.err.println("Num goals: " + numGoals + ", num boxes: " + boxes.size());
			System.exit(1); // Debug!!!
		}
		StaticBoard.goalPositions = new int[numGoals];
		boxPositions = new int[numGoals];
		for(int i = 0; i < numGoals; i++) {
			boxPositions[i] = boxes.get(i);
			StaticBoard.goalPositions[i] = goals.get(i);
		}
	}

	/**
	 * @return This board but with all boxes on the goals and the player at start.
	 */
	public GameBoard getEndBoard(){
		GameBoard newBoard = new GameBoard(this);
		
		System.arraycopy(StaticBoard.goalPositions, 0, newBoard.boxPositions, 0, StaticBoard.goalPositions.length);
		playerPosition = StaticBoard.start;

		return newBoard;
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
		int anchor = getAnchorPosition(playerPosition);

		int hashValue = playerHashTable[anchor];

		for(int b = 0; b < boxPositions.length; b++)
			hashValue ^= boxHashTable[boxPositions[b]];

		return hashValue;
	}

	public int getAnchorPosition(int fromPos) {
		boolean visited[] = new boolean[StaticBoard.MAP_SIZE];

		Queue<Integer> queue = new LinkedList<Integer>();

		queue.add(fromPos);

		int minX = fromPos % StaticBoard.MAP_WIDTH;
		int minY = fromPos / StaticBoard.MAP_WIDTH;

		int currentPos;
		// find minY
		while(!queue.isEmpty()) {
			currentPos = queue.poll();

			visited[currentPos] = true;
		
			for(int m = 0; m < StaticBoard.directions.length; m++) {
				int newp = currentPos + StaticBoard.directions[m];
				if(newp >= 0 && newp < StaticBoard.MAP_SIZE)
					if(!visited[newp])
						if(isWalkable(newp)) {
							queue.add(newp);
							if(newp / StaticBoard.MAP_WIDTH < minY) {
								minX = newp % StaticBoard.MAP_WIDTH;
								minY = newp / StaticBoard.MAP_WIDTH;
							} else if (newp / StaticBoard.MAP_WIDTH == minY &&
									   newp % StaticBoard.MAP_WIDTH < minX) {
								minX = newp % StaticBoard.MAP_WIDTH;
								minY = newp / StaticBoard.MAP_WIDTH;
							}
						}
			}
		}

		// find min x
		currentPos = minY*StaticBoard.MAP_WIDTH + minX;
		while(StaticBoard.floor[currentPos + StaticBoard.LEFT]) {
			currentPos = currentPos + StaticBoard.LEFT;
		}

		return currentPos;
	}

	// TODO check and simplify with StaticBoard directions
	//returns true if we can go from start point to goal point
	public boolean hasFreePath(int from, int to){
		int fromX = from % StaticBoard.MAP_WIDTH;
		int fromY = from / StaticBoard.MAP_WIDTH;
		
		int toX = to % StaticBoard.MAP_WIDTH;
		int toY = to / StaticBoard.MAP_WIDTH;
		
		int start = fromY*StaticBoard.MAP_WIDTH + fromX;
		int goal = toY*StaticBoard.MAP_WIDTH+toX;
		ArrayList<ArrayList<Integer>> neighbour = new ArrayList<ArrayList<Integer>>();
		char[][] map=new char[StaticBoard.MAP_WIDTH][StaticBoard.MAP_HEIGHT];
		for (int i = 0; i < StaticBoard.MAP_WIDTH*StaticBoard.MAP_HEIGHT; i++) {
			neighbour.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < StaticBoard.MAP_WIDTH - 1; i++) {
			for (int j = 0; j < StaticBoard.MAP_HEIGHT - 1; j++) {
				char current = map[i][j];
				//check if neighbours to the right
				if(current == ' ' || current == '.' || current == '+'|| current == '@') {
					char next = map[i][j + 1];
					if (next == ' ' || next == '.' || next == '+'|| next == '@') {
						neighbour.get(i * StaticBoard.MAP_WIDTH + j).add(i * StaticBoard.MAP_WIDTH + j + 1);
						//add next as neighbour to current
						neighbour.get(i * StaticBoard.MAP_WIDTH + j + 1).add(i * StaticBoard.MAP_WIDTH + j);
						//add current as neighbour to next
					}
				}
				//check if neighbours to under
				if(current == ' ' || current == '.' || current == '+'|| current == '@') {
					char down = map[i+1][j];
					if (down == ' ' || down == '.' || down == '+'|| down == '@') {
						neighbour.get(i * StaticBoard.MAP_WIDTH + j).add((i+1)*StaticBoard.MAP_WIDTH + j );
						//add next as neighbour to current
						neighbour.get((i+1) * StaticBoard.MAP_WIDTH + j).add(i * StaticBoard.MAP_WIDTH + j );
						//add next as neighbour to current
					}
				}

			}
		}
		//BFS
		if(start==goal){
			return true;
		}
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		boolean[] visited = new boolean[StaticBoard.MAP_WIDTH*StaticBoard.MAP_HEIGHT];
		visited[start]=true;
		ArrayList<Integer> adjacent;
		int current;
		while(!queue.isEmpty()){
			current = queue.poll();
			adjacent = neighbour.get(current);
			for(int i:adjacent){
				if(!visited[i]){
					queue.add(i);
					visited[i]=true;
					if(i == goal){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * @param p
	 * @param direction
	 * @return true if the player can walk from where it currently is, to the required position in order to perform the move.<br />false if not.
	 */
	public boolean canPull(int boxPos, int direction) {
		if(hasFreePath(playerPosition, boxPos + direction)) {
			if (direction == StaticBoard.UP) {
				if (boxPos < StaticBoard.MAP_SIZE && boxPos >= StaticBoard.MAP_WIDTH*2) {
					if (isWalkable(boxPos + 2*StaticBoard.UP))
						return true;
				}
			}
			else if (direction == StaticBoard.RIGHT) {
				if (boxPos % StaticBoard.MAP_WIDTH < (StaticBoard.MAP_WIDTH - 2)) {
					if (isWalkable(boxPos + 2*StaticBoard.RIGHT))
						return true;
				}
			}
			else if (direction == StaticBoard.DOWN) {
				if (boxPos < StaticBoard.MAP_SIZE - 2*StaticBoard.MAP_WIDTH) {
					if (isWalkable(boxPos + 2*StaticBoard.DOWN))
						return true;
				}
			}
			else if (direction == StaticBoard.LEFT) {
				if (boxPos % StaticBoard.MAP_WIDTH > 1) {
					if (isWalkable(boxPos + 2*StaticBoard.LEFT))
						return true;
				}
			}
		}

		return false;
	}

	public boolean isWalkable(int p) {
		return StaticBoard.floor[p] && !hasBox(p);
	}

	public boolean hasBox(int p) {
		for(int b = 0; b < boxPositions.length; b++) {
			if(boxPositions[b] == p)
				return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return Renderer.draw(this);
	}
}
