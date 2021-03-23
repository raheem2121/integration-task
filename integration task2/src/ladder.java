
public class ladder extends snakes {
	
	//super inherits from main class
	public ladder(int bottom, int top) {
		super(bottom, top);
		
	}
	
	public String toString() {
		return "this is a ladder going from " + super.gettrigger() + super.gettail();
	}

}
