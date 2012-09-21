import java.util.ArrayDeque;
import java.util.ArrayList;


public class GameBoardTree {
	// A tree with all the permutations of boards, used in the BFS
	private int xrange;
	private int yrange;
	private MapNode start;
	
	public GameBoardTree(int x, int y){
		xrange = x;
		yrange = y;
	}


	public String BFS() {
		boolean visited[][]= new boolean[xrange][yrange];
		Point[][] path= new Point[xrange][yrange];

		visited[start.getX()][start.getY()]=true;
		path[start.getX()][start.getY()]=new Point(start.getX(),start.getY());
		if(start.data=='+'){
			//done
			return path(new Point(start.getX(),start.getX()));
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
	
	public String path(Point p){
		return "";
	}

}
