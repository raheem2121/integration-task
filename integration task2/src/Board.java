import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Board {
	
	
	List<players> thePlayers = new ArrayList<>();
	players Player = new players();

	
	public void setPlayers(List<players> theplayers) {
		this.thePlayers = thePlayers;
	}
	
	public List<players> getplayers(){
		return thePlayers;
	}
	
	public void addPlayers( players Player) {
		this.thePlayers.add(Player);
	}
	
	public void initialize() {
		int[] position = IntStream.range(1, 100).toArray();
	}
	

	public void movePlayer(players Player){
	//create instances of objects 
	Dice dice = new Dice();
	ladder Ladder1 = new ladder(12,34);
	ladder Ladder2 = new ladder(23,45);
	ladder Ladder3 = new ladder(48,62);
	ladder Ladder4 = new ladder(72,84);
	snakes Snake1 = new snakes(86, 23);
	snakes Snake2 = new snakes(68, 41);
	snakes Snake3 = new snakes(51, 33);
	snakes Snake4 = new snakes(49, 32);
	BigSticks stick = new BigSticks((int) (Math.random() * 89));
	Biscuit biscuit = new Biscuit((int) (Math.random() * 89));
	BigSticks stick2 = new BigSticks((int) (Math.random() * 89));
	Biscuit biscuit2 = new Biscuit((int) (Math.random() * 89));
	
	//set of if statements to scroll through the different tiles a user can land on
		Scanner scan = new Scanner(System.in);
		do {
		if (Player.getposition()<100) {
			int position = Player.getposition();
			System.out.println(Player.getname() + " Press d to roll dice");
			String input = scan.next();
			if(input.equalsIgnoreCase("d")) {
				dice.roll();
			    //dice.setrandomNum(41);
				Player.setposition(position + dice.getrandomNum());
				
				if(Player.getposition()== biscuit.getbiscuit()){ //biscuit 1 
					Player.hasBiscuit();
					System.out.println(Player.getname() + dice + " and moved from position " +  position + " to " + Player.getposition() + " and has collected a biscuit. "
							+ " This can be used to feed a snake and avoid falling down");
				}
				else if(Player.getposition()== biscuit2.getbiscuit()){ //biscuit 2
						Player.hasBiscuit();
						System.out.println(Player.getname() + dice + " and moved from position " +  position + " to " + Player.getposition() + " and has collected a biscuit. "
								+ " This can be used to feed a snake and avoid falling down");	
				}
				else if(Player.getposition()== stick.getstick()) { //stick 1
					Player.hasStick();
					System.out.println(Player.getname() + " has collected a Big stick. This can be used to avoid falling down a snake. However if this is used the snake will move 1 row up");
				}
				else if(Player.getposition()== stick2.getstick()) { //stick 2
					Player.hasStick();
					System.out.println(Player.getname() + " has collected a Big stick. This can be used to avoid falling down a snake. However if this is used the snake will move 1 row up");
				}
				else if(Player.getposition()== Ladder1.gettrigger() ) { //ladder 1
					Ladder1.use(Player);
					System.out.println(Player.getname() + " " + dice + " and landed on a ladder."
							+ " you have moved from position " + Ladder1.gettrigger() + " to " + Ladder1.gettail() );
				}
				else if (Player.getposition()== Snake1.gettrigger()) { // snake 1
					if(Player.getStickstatus() == true) {
						System.out.println(Player.getname() + " you have used the stick and not fallen down the snake");
						
					}
					else if(Player.getBiscuitStatus()== true){
						System.out.println(Player.getname()+ " you have used a biscuit and not fallen down the snake");
					}
					else
					{
					Snake1.use(Player);
					System.out.println(Player.getname() + " " + dice + " and landed on a snake."
							+ " you have moved from position " + Snake1.gettrigger() + " to " + Snake1.gettail() );
					}
				}
				else if(Player.getposition()== Ladder2.gettrigger() ) { //ladder 2
					Ladder2.use(Player);
					System.out.println(Player.getname() + " " + dice + " and landed on a ladder."
							+ " you have moved from position " + Ladder2.gettrigger() + " to " + Ladder2.gettail() );
				}
				else if (Player.getposition()== Snake2.gettrigger()) { // snake 2
					if(Player.getStickstatus() == true) {
						System.out.println(Player.getname() + " you have used the stick and not fallen down the snake");
						Snake2.settrigger(+10);
						Snake2.settail(+10);
					}	
					else if(Player.getBiscuitStatus()== true){
						System.out.println(Player.getname()+ " you have used a biscuit and not fallen down the snake");
					}
					Snake2.use(Player);
					System.out.println(Player.getname() + " " + dice + " and landed on a snake."
							+ " you have moved from position " + Snake2.gettrigger() + " to " + Snake2.gettail() );
				}
				else if(Player.getposition()== Ladder3.gettrigger() ) { //ladder 3
					Ladder3.use(Player);
					System.out.println(Player.getname() + " " + dice + " and landed on a ladder."
							+ " you have moved from position " + Ladder3.gettrigger() + " to " + Ladder3.gettail() );
				}
				else if (Player.getposition()== Snake3.gettrigger()) { // snake 3
					if(Player.getStickstatus() == true) {
						System.out.println(Player.getname()+ " you have used the stick and not fallen down the snake");
						Snake3.settrigger(+10);
						Snake3.settail(+10);
					}
					else if(Player.getBiscuitStatus()== true){
						System.out.println(Player.getname()+ " you have used a biscuit and not fallen down the snake");
					}
					Snake3.use(Player);
					System.out.println(Player.getname() + " " + dice + " and landed on a snake."
							+ " you have moved from position " + Snake3.gettrigger() + " to " + Snake3.gettail() );
				}
				else if(Player.getposition()== Ladder4.gettrigger() ) { //ladder 4
					Ladder4.use(Player);
					System.out.println(Player.getname() + " " + dice + " and landed on a ladder."
							+ " you have moved from position " + Ladder4.gettrigger() + " to " + Ladder4.gettail() );
				}
				else if (Player.getposition()== Snake4.gettrigger()) { // snake 4
					if(Player.getStickstatus() == true) {
						System.out.println(Player.getname() + " you have used the stick and not fallen down the snake");
						Snake4.settrigger(+10);
						Snake4.settail(+10);
					}
					else if(Player.getBiscuitStatus()== true){
						System.out.println(Player.getname() + " you have used a biscuit and not fallen down the snake");
					}
					Snake4.use(Player);
					System.out.println(Player.getname() + " " + dice + " and landed on a snake."
							+ " you have moved from position " + Snake4.gettrigger() + " to " + Snake4.gettail() );
				}
				else if (Player.getposition() == 100) { //tile 100
					System.out.println(Player.getname() + " " + dice + " Congratulations" + Player.getname() + " you are the winner!");
					
				}
				else if (Player.getposition() > 100) { //puts player to previous position, stopping them from exceeding 100
					Player.setposition(position);
					System.out.println(Player.getname() + dice + " you must land directly on 100, try again next turn");
				} //player lands on empty tile
				else {
					System.out.println(Player.getname() + dice + " and have moved from position " + position + " to " + Player.getposition());
				}	
				
				
			
			}
				
				
			}
		
	
		
		}while (dice.getrandomNum() == 6);
		
	
}
}





