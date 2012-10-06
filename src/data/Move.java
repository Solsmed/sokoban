package data;


public class Move {
	private final int destination;
	private final int originDirection;
	
	/**
	 * Final position is (position + direction)
	 * 
	 * @param p Origin position
	 * @param dir Direction of movement
	 */
	
	public Move(int destination, int originDirection){
		this.destination = destination;
		this.originDirection = originDirection;
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
}
