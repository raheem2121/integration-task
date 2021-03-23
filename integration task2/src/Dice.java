
public class Dice {
		
		private int randomNum;
		
		public Dice () {
			this.randomNum = 0;
	    }
		
		public void roll () {
			this.randomNum = (int) (Math.random() * 6); //random function for dice roll
			this.randomNum++;
	   
	        
		}
		
		public int getrandomNum () {
			return this.randomNum;
		}
		
		public void setrandomNum(int num) {
			this.randomNum = num;
		}
		
		
		public String toString() {
			return " has rolled " + this.randomNum;
		}
}
