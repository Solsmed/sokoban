import java.awt.Point;
import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.ArrayList;


public class Board {
	MapNode[][] map;
	int xrange;
	int yrange;
	MapNode start;
	Point path[][];
	Point playerPos;
	MapNode[][] startboard;
	MapNode[][] endboard;
	Board previousBoard;

	public Board(){
		map=null;
		start=null;
		path=null;
		//initialize();
	}
	//used when wanting to create a new board with a new move applied on it
	//will be heavily used in our search for the target board
	// it will save the previous Board, this we can use later to see which order the player went around the map
	public Board(MapNode[][] startboard,MapNode[][] endboard,MapNode[][] map,MapNode Move,Board oldBoard){
		this.startboard=startboard;
		this.endboard=endboard;
		this.map=map;
		previousBoard=oldBoard;
		Walk(Move);

	}
	//creating copies of the board to remmember what state we want as endcondition, and where to start
	public void initialize(){
		startboard=MatrixCopy(map);


		endboard=createEndboard();
		//	previousBoard=new Board(startboard,endboard,map,new MapNode(1,1,'c'),this);

	}
	public MapNode[][] MatrixCopy(MapNode[][] data) {
		int M = data.length;
		int N = data[0].length;
		MapNode[][] newMap = new MapNode[M][N];
		for (int i = 0; i < M; i++){
			for (int j = 0; j < N; j++){
				newMap[i][j] = new MapNode(i,j,data[i][j].data);
			}
		}
		return newMap;
	}



	public MapNode[][] createEndboard(){
		endboard=MatrixCopy(map);
		for(int i=0; i<yrange;i++){
			for(int k=0;k<xrange;k++){
				if(endboard[k][i].data=='.'){
					endboard[k][i].data='*';

				}
				if(endboard[k][i].data=='$'){
					endboard[k][i].data=' ';

				}

			}

		}
		return endboard;
	}
	// checking if player can walk backwards i.e there is a empty square behind player in any direction
	//return is an array of all possible direction one can walk
	public ArrayList<MapNode> canWalk(){
		ArrayList<MapNode> walkablepos= new ArrayList<MapNode>();
		if(map[playerPos.x-1][playerPos.y].data==' '||map[playerPos.x-1][playerPos.y].data=='.'){
			walkablepos.add(map[playerPos.x-1][playerPos.y]);
		}
		if(map[playerPos.x+1][playerPos.y].data==' '||map[playerPos.x+1][playerPos.y].data=='.'){
			walkablepos.add(map[playerPos.x-1][playerPos.y]);

		}
		if(map[playerPos.x][playerPos.y-1].data==' '||map[playerPos.x][playerPos.y-1].data=='.'){
			walkablepos.add(map[playerPos.x-1][playerPos.y]);

		}
		if(map[playerPos.x][playerPos.y+1].data==' '||map[playerPos.x][playerPos.y+1].data=='.'){
			walkablepos.add(map[playerPos.x-1][playerPos.y]);

		}
		return walkablepos;
	}

	// walks "backwards" or rather walks, but if a box is opposite direction of the walk then it pulls the box
	public void Walk(MapNode Movepos){

		//Pull box if box is in the square in just infront of the player when the player moves opposite direction
		if(playerPos.x-Movepos.x==1){
			if(hasBox(map[playerPos.x+1][playerPos.y])){
				pullBox(map[playerPos.x][playerPos.y],map[playerPos.x+1][playerPos.y]);
			}else{
				updateRoad(map[playerPos.x][playerPos.y]);
			}

		}
		if(playerPos.x-Movepos.x==-1){
			if(hasBox(map[playerPos.x-1][playerPos.y])){
				pullBox(map[playerPos.x][playerPos.y],map[playerPos.x-1][playerPos.y]);
			}else{
				updateRoad(map[playerPos.x][playerPos.y]);
			}

		}
		if(playerPos.y-Movepos.y==1){
			if(hasBox(map[playerPos.x][playerPos.y+1])){
				pullBox(map[playerPos.x][playerPos.y],map[playerPos.x][playerPos.y+1]);
			}else{
				updateRoad(map[playerPos.x][playerPos.y]);
			}


		}
		if(playerPos.y-Movepos.y==-1){
			if(hasBox(map[playerPos.x][playerPos.y-1])){
				pullBox(map[playerPos.x][playerPos.y],map[playerPos.x][playerPos.y-1]);
			}else{
				updateRoad(map[playerPos.x][playerPos.y]);
			}

		}
		// update player pos
		setPlayerPos(new Point(Movepos.x,Movepos.y));




	}
	// if box is in opposite direction of a player walk, then move the box to playerpos(pull)
	public void pullBox(MapNode playerPos,MapNode BoxPos){
		if(playerPos.data=='@'&&BoxPos.data=='$'){
			playerPos.data=BoxPos.data;
			BoxPos.data=' ';
		}
		if(playerPos.data=='@'&&BoxPos.data=='*'){
			playerPos.data='$';
			BoxPos.data='.';
		}
		if(playerPos.data=='+'&&BoxPos.data=='$'){
			playerPos.data='*';
			BoxPos.data=' ';
		}
		if(playerPos.data=='+'&&BoxPos.data=='*'){
			playerPos.data=BoxPos.data;
			BoxPos.data='.';
		}

	}

	public void setPlayerPos(Point pPos){
		this.playerPos=pPos;
		if(map[pPos.x][pPos.y].data=='.'){
			map[pPos.x][pPos.y].data='+';
		}else{
			map[pPos.x][pPos.y].data='@';
		}

	}
	public boolean hasBox(MapNode m){
		if(m.data=='$'||m.data=='*'){
			return true;
		}
		return false;
	}
	// if player walks without pulling box
	public void updateRoad(MapNode m){
		if(m.data=='@'){
			m.data=' ';
		}else{
			m.data='.';
		}
	}

	// will be used when we got transposition tables
	public void savestate(){

	}
	//initilizing game, computing start and endboard




	// BELOW HERE OLD FUNCTIONS WILL Hinitialize()AVE TO BE REPLACED
	public ArrayList<MapNode> getNeighbours(int x,int y){
		ArrayList<MapNode> neighbours= new ArrayList<MapNode>();

		if(x-1>=0 && x-1<=xrange){
			neighbours.add(map[x-1][y]);
		}
		if(x+1>=0 && x+1<=xrange){
			neighbours.add(map[x+1][y]);
		}
		if(y-1>=0 && y-1<=yrange){
			neighbours.add(map[x][y-1]);
		}
		if(y+1>=0 && y+1<=yrange){
			neighbours.add(map[x][y+1]);
		}
		return neighbours;
	}
	public void addMapNode(int x,int y, MapNode Node){
		map[x][y]=Node;

	}
	public String path(Point p){
		if(p.x==start.x && p.y==start.y){
			return "";
		}
		int diffx=p.x-path[p.x][p.y].x;
		int diffy=p.y-path[p.x][p.y].y;
		if(diffx==1)
			return path(path[p.x][p.y])+"R";
		if(diffx==-1)
			return path(path[p.x][p.y])+"L";
		if(diffy==1)
			return path(path[p.x][p.y])+"D";
		if(diffy==-1)
			return path(path[p.x][p.y])+"U";
		else
			return "";
	}
	public void printBoard(){
		System.out.println("BOARD PRINT");
		for(int i=0;i<yrange;i++){
			for(int k=0;k<xrange;k++){
				System.out.print(map[k][i].data);
			}
			System.out.println();
		}
	}

	public void printEndBoard(){
		System.out.println("ENDBOARD PRINT");
		for(int i=0;i<yrange;i++){
			for(int k=0;k<xrange;k++){
				System.out.print(endboard[k][i].data);
			}
			System.out.println();
		}
	}
	public void printStartBoard(){
		System.out.println("STARTBOARD PRINT");
		for(int i=0;i<yrange;i++){
			for(int k=0;k<xrange;k++){
				System.out.print(startboard[k][i].data);
			}
			System.out.println();
		}
	}

	public String BFS() {
		boolean visited[][]= new boolean[xrange][yrange];
		path= new Point[xrange][yrange];

		visited[start.x][start.y]=true;
		path[start.x][start.y]=new Point(start.x,start.y);
		if(start.data=='+'){
			//done
			return path(new Point(start.x,start.y));
		}
		ArrayDeque<MapNode> q= new ArrayDeque<MapNode>();
		q.add(start);
		MapNode Node;
		MapNode closeNode;
		while(!q.isEmpty()){
			//System.out.println("WTF");
			Node=q.poll();
			// get x-1 x+1 y-1 y+1  //lägg till grannar
			ArrayList<MapNode> adj= getNeighbours(Node.x,Node.y);                        

			//
			for(int i=0; i<adj.size(); i++){
				closeNode=adj.get(i);


				if(closeNode!=null&&!visited[closeNode.x][closeNode.y]){
					//System.out.println("WTF");

					// reached target    
					if(closeNode.data=='.'){
						System.out.println("REACHED GOAL"+closeNode.x+" "+closeNode.y);
						visited[closeNode.x][closeNode.y]=true;
						path[closeNode.x][closeNode.y]=new Point(Node.x,Node.y);
						return path(new Point(closeNode.x,closeNode.y));
					}
					// otherwise add target and continue
					if(closeNode.isRoad()){

						visited[closeNode.x][closeNode.y]=true;
						path[closeNode.x][closeNode.y]=new Point(Node.x,Node.y);
						q.add(closeNode);
					}


				}
			}
		}

		return null;

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

		int boxes = boxHash();

		// get the position the player would have if it moved top, then left
		Point anchorPosition = getAnchorPosition(0, 0);

		return boxes + anchorPosition.hashCode();
	}

	private int boxHash() {
		return 0;
	}

	private Point getAnchorPosition(int fromX, int fromY) {
		int anchorX = 0;
		int anchorY = 0;
		// find northernmost reachable position through bfs
		// bfs()
		// go left as far as possible
		return new Point(anchorX, anchorY);
	}
}
