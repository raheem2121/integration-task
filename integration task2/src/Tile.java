
public class Tile {
	//tracks users positions 
	private int position;
	private int destination;
	
	
	public Tile() {
		
	}
	
	public Tile(int destination, int position) {
		this.destination = destination;
		this.position = position;
	}
	
	public void setdestination(int destination) {
		this.destination = destination;
	}
	
	public int getdestination() {
		return destination;
	}
	
	public void setposition(int position) {
		this.position = position;
	}
	
	public int getposition() {
		return position;
	}

}
