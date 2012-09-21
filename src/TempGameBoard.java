import java.util.ArrayList;
import java.util.HashSet;


public class TempGameBoard {
	HashSet<Character> moveable;
	
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
	
	public TempGameBoard(){
		moveable=new HashSet<Character>();
		moveable.add(' ');
		moveable.add('.');
		moveable.add('@');
		moveable.add('+');
		
	}
	
	// x,y is the coordinates of a box
	// checks wether its possible to move the box in a given direction
	public boolean canPull(int x, int y, char direction) {
		switch (direction) {
		case 'U':
			if (y < height && y > 1) {
				if (board.isWalkable(x,y-1))&&(board.isWalkable(x,y-2)))
					return true;
			}
			break;
		case 'R':
			if (x < (width - 2) && x >= 0) {
				if (board.isWalkable(x+1,y))&&(board.isWalkable(x+2,y)))
					return true;
			}
			break;
		case 'D':
			if (y < (height - 2) && y >= 0) {
				if (board.isWalkable(x,y+1))&&(board.isWalkable(x,y+2)))
					return true;
			}
			break;
		case 'L':
			if (x < width && x > 1) {
				if (board.isWalkable(x-1,y))&&(board.isWalkable(x-2,y)))
					return true;
			}
			break;
		}

		return false;
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
}
