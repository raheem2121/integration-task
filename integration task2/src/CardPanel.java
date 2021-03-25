import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.json.JSONException;


public class CardPanel extends JPanel {
	//creating JPanel to implement CardLayout
	public static JPanel deck= new JPanel(new CardLayout());;
	

	
//	protected static final JPanel cards = new JPanel(new CardLayout());
	public static Word word =  new Word(0,null, 0, null, false);
	public static WelcomePage welcomePage = new WelcomePage();
	
	//static DifficultyPage selectDifficulty = new DifficultyPage(word);
    
	private final String name;
	
    
    public CardPanel(String name) {
    	this.name = name;
        this.setPreferredSize(new Dimension(600, 600));
        

        
    }
    @Override
    public String toString() {
        return name;
    }
    
    public static void main(String[] args) throws IOException {
    	//print out which options are available to the user for selection 
    			//Hangman A -> press 1
    			//Hangman B -> press 2
    			//Snakes and Ladders A -> press 3
    			//Snakes and ladders B -> press 4
    			
    			//have a regular expression to check that the input can only be numbers 1-4 
    			//if outside of that range -> warning message asking to give a number between 1 and 4
    			
    			int userInput = options();
    			
    			
    		     if (String.valueOf(userInput).matches("[0-4]+")) {
    		    	 switch(userInput){
    		    	 case 0:
    		    		 System.exit(0);
    		    		 break;
    			     case 1:
    			    	 System.out.println("The option you selected is Hangman A");
    			    	 //call hangman A
    			    	 // this prints out the JSON from the url!!!!!!!!
    		    			//Task: move API KEY to env file or something 
    		    			String url ="https://api.wordnik.com/v4/words.json/randomWords?hasDictionaryDef=true&maxCorpusCount=-1&minDictionaryCount=1&maxDictionaryCount=-1&minLength=5&maxLength=-1&limit=10&api_key=d8yrlh91wo2cx01rrtltwysumgwg8er6pd3m7zm7cw7yqhgvc";
    		    			try {
    		    				Controller.decodeJSONRandomWord(url);
    		    			} catch (JSONException e) {
    		    			// TODO Auto-generated catch block
    		    				e.printStackTrace();
    		    			} catch (Exception e) {
    		    			// TODO Auto-generated catch block
    		    				e.printStackTrace();
    		    			}
//    		    			int frequency = Controller.decodeJSONFrequencyWord("hello");
//    		    			String print =Controller.rankWord(frequency, "hello");
//    		    			System.out.println("frequency "+ frequency + " print "+ print)
    		    			try {
    		    				ArrayList<String> words = Controller.decodeJSONRandomWord(url);
    		    				Queries.insertWord(words);
    		    			}  catch (Exception e) {
    		    				// TODO Auto-generated catch block
    		    				e.printStackTrace();
    		    			}
    		    			PostgresqlConnection app = new PostgresqlConnection();
    		    			app.connect();
    		    	
		    		        EventQueue.invokeLater(new Runnable() {
		
		    		            @Override
		    		            public void run() {
		    		                create();
		    		            }
		    		        });
    			    	 break; 
    			     case 2: 
    			    	 System.out.println("The option you selected is Hangman B");
    			    	
    			    	 Main.mainHangmanGame();
    			         break;
    			     case 3: 
    			    	 System.out.println("The option you selected is Snakes and Ladders A");
    			    	 //call snakes and ladders A
    			    	 Start.LaddersGame();
    			    	 break; 
    			    	 
    			    	 
    			    	 
    			     case 4:
    			    	 
    			    	System.out.println("THe option you selected is Snakes and Ladders B");
    			    	//call snakes and ladders B
    			    	System.out.println("WELCOME TO SNAKES AND LADDERS ");
    			 		System.out.println(" ");

    			 		
    			 		List<players> thePlayers = new ArrayList<>(); //create array list for players
    			 		System.out.println("how many players are playing (2-4 players allowed) ?");
    			 		
    			 		Scanner in = new Scanner(System.in); //create an input for players to type in the number of players playing
    			 		System.out.println("I am here");
    			 		int numOfPlayers = in.nextInt();
    			 		
    			 		System.out.println(numOfPlayers);
    			 		while (numOfPlayers > 4 || numOfPlayers < 2) {
    			 			System.out.println("input invalid, make sure 2-4 players are entered");
    			 			numOfPlayers = in.nextInt();
    			 		}
    			 		System.out.println("Type a name to represent your player");
    			 		for(int i = 1; i<numOfPlayers+1; ++i) { //scroll through the number of players
    			 			System.out.println("Enter name for player " + i);
    			 			String name = in.next();
    			 			players Player = new players(name);
    			 			thePlayers.add(Player);
    			 			
    			 			}
    			 		
    			 		
    			 			Board board = new Board();
    			 			Dice dice = new Dice();
    			 		//loop so it goes through each player
    			 			
    			 			System.out.println("Lets Play!");
    			 			int counter = 0;
    			 			
    			 			do {
    			 				players currPlayer = thePlayers.get(counter);
    			 				board.movePlayer(currPlayer);
    			 				counter++;
    			 				if (counter == numOfPlayers) {
    			 					counter = 0;
    			 				}
    			 				if (numOfPlayers == 1) {
    			 					System.exit(numOfPlayers);
    			 				}
    			 			}while(counter < numOfPlayers);
    			 	
    			 			
    			 			//Code sometimes crashes due to index outofbounds exception, incorporate try and catch 
    			 				
    			 			break;
    			 		}
    			    	 
    		    
    		     } else {
    		    	 
    		    	 System.out.println("Please check that the value you inputted is a number between 1 and 4");
    		    	 //wait 2 seconds before presenting the user again with the options 
    		    	 System.exit(0);
    		    	 
    		     }
    		    
   
    		  
    		     }    		     
    /*
 	 * options contains the text that gets printed in the console
 	 */
 	private static int options() {
 		Scanner in = new Scanner(System.in); 
		
 		System.out.println("Hello, please select which game you'd like to play");
 		//wait 1 seconds before presenting the user with the options to select
 		try{
 		    Thread.sleep(1000);//1000ms = 1s
 		}catch(InterruptedException ex){
 			System.out.println("This is the exception: "+ex);
 		}
 		System.out.println("To play Hangman A, please press 1");
 		System.out.println("To play Hangman B, please press 2");
 		System.out.println("To play Snakes and Ladders A, please press 3");
 		System.out.println("To play Snakes and Ladders B, please press 4");
 		System.out.println("To exit, please press 0");
 		int userInput = in.nextInt(); 
 		
 		return userInput;
 		
 	}	     	     
    public void addComponentToPane(Container pane) {    	
    	
    	
    	//create the first card
    	//create the panels that contain the cards
    	
    	 


    	//now I add onto the pane
    	pane.add(deck);
    }
    
    private static void create() {
    	//create adn set up the window
    	JFrame f = new JFrame();   
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    	CardPanel p = new CardPanel("");
//    	deck.add(p);

        deck.add(welcomePage, welcomePage.toString());
   	 	
   	 	//wordPage.repaint();
   	
       
        
   	 	f.add(deck, BorderLayout.CENTER);
   	 	f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
       
    }
    
    public void switchCard(String name){
    	CardLayout cl = (CardLayout) deck.getLayout();
        cl.show(deck, name );
        deck.updateUI();
    	}
	
   
}
