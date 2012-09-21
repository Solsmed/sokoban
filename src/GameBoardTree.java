import java.util.*;

public class GameBoardTree {
	// A tree with all the permutations of boards, used in the BFS
	GameBoardNode root;
	Queue<GameBoardNode> bfsQueue;
	
	public GameBoardTree(GameBoard root){
		this.root = new GameBoardNode(null, root);
		
		bfsQueue = new LinkedList<GameBoardNode>();
	}
	
	public void totalSearch() {
		bfsQueue.clear();
		
		bfsQueue.add(root);
		
		while(!bfsQueue.isEmpty()) {
			expandNext();
		}
	}

	public void expandNext() {
		GameBoardNode expansionNode = bfsQueue.poll();
		
		Set<GameBoard> valids = MoveValidator.getValidPermutations(expansionNode.gameBoard);
		
		Iterator<GameBoard> it = valids.iterator();
		
		while(it.hasNext()) {
			GameBoardNode newNode = new GameBoardNode(expansionNode, it.next());
			expansionNode.addChild(newNode);
			bfsQueue.add(newNode);
		}
	}
	/*
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
*/
}
