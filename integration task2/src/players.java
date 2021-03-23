
public class players {
	//create class for players and create setters and getters
	private String name = null;
	private int position = 0;
	private boolean hasStick = false;
	private boolean hasBiscuit = false;
	
	
	public players() {
		
	}
	
	public players(String name) {
		this.name = name;
		
		
	}
	
	public void setname(String name) {
		this.name = name;
	}
	
	public String getname() {
		return name;
	}
	
	public void setposition(int currentPosition) {
		this.position = currentPosition;
	}
	
	public int getposition() {
		return position;
	}
	//store stick and biscuit if user lands on them
	public boolean getStickstatus() {
		return this.hasStick;
	}
	
	public void hasStick() {
		this.hasStick = true;
	}
	
	public void useStick() {
		this.hasStick = false;
	}
	
	public boolean getBiscuitStatus() {
		return this.hasBiscuit;
	}
	
	public void hasBiscuit() {
		this.hasBiscuit = true;
	}
	
	public void useBiscuit() {
		this.hasBiscuit = false;
	}
	

}
