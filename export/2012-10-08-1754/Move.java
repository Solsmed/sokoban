public class Move {
	private final int destination;
	private final int originDirection;
	private String path;
	
	/**
	 * Final position is (position + direction)
	 * 
	 * @param p Origin position
	 * @param dir Direction of movement
	 */
	
	public Move(int destination, int originDirection,String path){
		this.destination = destination;
		this.originDirection = originDirection;
		this.path=path;
	}
	
	public int getDestination() {
		return destination;
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
