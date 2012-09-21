import java.util.ArrayList;


public class TempGameBoard {
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
}
