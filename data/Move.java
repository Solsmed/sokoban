public class Move {
	private final int destination;
	private final int originDirection;
	private String path;
	private int boxIndex;
	
	/**
	 * Final position is (position + direction)
	 * 
	 * @param p Origin position
	 * @param dir Direction of movement
	 */
	
	public Move(int destination, int originDirection,String path,int boxIndex){
		this.destination = destination;
		this.originDirection = originDirection;
		this.path=path;
		this.boxIndex=boxIndex;
	}
	
	public int getDestination() {
		return destination;
	}
	public int getBoxIndex() {
		return boxIndex;
	}
	
	
	public int getSource() {
		return destination - originDirection;
	}
	
	public int getDirection() {
		return originDirection;
	}
	public String getPath(){
		return path;
	}
	public String toString(){
		return "("+destination+","+originDirection+","+path+")";
	}
}
