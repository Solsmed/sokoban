package data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
	private ArrayList<HashSet<Integer>> vertices = new ArrayList<HashSet<Integer>>();
	private HashSet<Integer> goals = new HashSet<Integer>();
	private HashSet<Character> movable = new HashSet<Character>();
	int vertex = 0;
	int start = 0;
	String lastRow = "";
	ArrayList<Character> allowed = new ArrayList<Character>();
	int capacity = 0;
	char[][] board;
	int height;
	int width;

	public Graph(int capacity) {
		for (int i = 0; i < capacity; i++)
			vertices.add(new HashSet<Integer>());
		allowed.add(' ');
		allowed.add('@');
		allowed.add('.');
		allowed.add('+');
		movable.add('.');
		movable.add(' ');
		this.capacity = capacity;
	}

	public boolean canPull(int x, int y, char direction) {
		switch (direction) {
		case 'U':
			if (y < height && y > 1) {
				if (movable.contains(board[x][y - 1])
						&& movable.contains(board[x][y - 2]))
					return true;
			}
			break;
		case 'R':
			if (x < (width - 2) && x >= 0) {
				if (movable.contains(board[x + 1][y])
						&& movable.contains(board[x + 2][y]))
					return true;
			}
			break;
		case 'D':
			if (y < (height - 2) && y >= 0) {
				if (movable.contains(board[x][y + 1])
						&& movable.contains(board[x][y + 2]))
					return true;
			}
			break;
		case 'L':
			if (x < width && x > 1) {
				if (movable.contains(board[x - 1][y])
						&& movable.contains(board[x - 2][y]))
					return true;
			}
			break;
		}

		return false;
	}
	
	public boolean canPush(int x, int y, char direction) {
		switch (direction) {
		case 'U':
			if (y < (height-1) && y > 0) {
				if (movable.contains(board[x][y - 1])
						&& movable.contains(board[x][y + 1]))
					return true;
			}
			break;
		case 'R':
			if (x < (width - 1) && x > 0) {
				if (movable.contains(board[x - 1][y])
						&& movable.contains(board[x + 1][y]))
					return true;
			}
			break;
		case 'D':
			if (y < (height - 1) && y > 0) {
				if (movable.contains(board[x][y - 1])
						&& movable.contains(board[x][y + 1]))
					return true;
			}
			break;
		case 'L':
			if (x < (width -1) && x > 0) {
				if (movable.contains(board[x - 1][y])
						&& movable.contains(board[x - 1][y]))
					return true;
			}
			break;
		}

		return false;
	}

	public void checkCorner(int x, int y) {
		int[] dx = { -1, 0, 1, 0 };
		int[] dy = { 0, -1, 0, 1 };
		int[] dx2 = { 0, 1, 0, -1 };
		int[] dy2 = { -1, 0, 1, 0 };
		for (int i = 0; i < 4; i++) {
			if (x + dx[i] >= 0 && x + dx[i] < width && y + dy[i] >= 0
					&& y + dy[i] < height && x + dx2[i] >= 0
					&& x + dx2[i] < width && y + dy2[i] >= 0
					&& y + dy2[i] < height) {
				if (board[x + dx[i]][y + dy[i]] == '#'
						&& board[x + dx2[i]][y + dy2[i]] == '#') {
					// This is corner. Corner is evil, corner will prevail! ..If not goal
				}
			}
		}
	}
	
	public void readFromFile(String fileName){
		BufferedReader br = null;
		 
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader("Maps/"+fileName));
 
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				//Here we process the lines as before
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void processRow(String row) {
		for (int i = 0; i < (row.length() - 1); i++) {
			char current = row.charAt(i);
			char next = row.charAt(i + 1);
			if (allowed.contains(current)) {

				// The vertex is a goal
				if (current == '.' || current == '+')
					goals.add(vertex);

				// The vertex is the player
				if (current == '@' || current == '+')
					start = vertex;

				// This vertex and the next is adjacent
				if (allowed.contains(next)) {
					vertices.get(vertex).add(vertex + 1);
					vertices.get(vertex + 1).add(vertex);
				}
				// This vertex and the one above is adjacent
				if (lastRow.length() > i && allowed.contains(lastRow.charAt(i))) {
					vertices.get(vertex).add(vertex - lastRow.length());
					vertices.get(vertex - lastRow.length()).add(vertex);
				}

			}
			vertex++;
		}
		vertex++;
		lastRow = row;
	}

	public void printGraph() {
		System.out.print(vertices.toString());
	}

	public String getPath() {
		return bfs();
	}

	public String bfs() {
		if (goals.contains(start)) {
			return "";
		}
		int tracer = 0;
		int[] trace = new int[capacity];
		boolean found = false;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		HashSet<Integer> adjacent;
		boolean[] visited = new boolean[capacity];
		visited[start] = true;
		int current;
		while (!queue.isEmpty() && !found) {
			current = queue.poll();
			adjacent = vertices.get(current);
			for (int i : adjacent) {
				if (!visited[i] && !found) {
					queue.add(i);
					visited[i] = true;
					if (goals.contains(i)) {
						tracer = i;
						found = true;
					}
					trace[i] = current;
				}
			}
		}
		String path = "";
		int last = 0;
		int compare;
		while (tracer != start) {
			last = trace[tracer];
			compare = tracer - last;
			if (compare == 1) {
				path += "R";
			} else if (compare == (-1)) {
				path += "L";
			} else if (compare > 1) {
				path += "D";
			} else {
				path += "U";
			}
			tracer = last;
		}
		String reverse = new StringBuffer(path).reverse().toString();
		return reverse;
	}
}
