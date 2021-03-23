import java.util.Random;


public class snakes {

	private int trigger;
	private int tail;
	
	//create heads and tails of snake. Create use for players to fall down head to tail
	
	
	public snakes(int trigger, int tail) {
		this.trigger = trigger;
		this.tail = tail;
		
	}
		
	public int gettrigger() {
		return this.trigger;
	}
	
	public void use(players player ) {
		player.setposition(tail);
	}
	
	public int gettail() {
		return this.tail;
	}
	
	public void settrigger(int trigger) {
		this.trigger = trigger;
	}
	
	public void settail(int tail) {
		this.tail = tail;
	}
	
}
