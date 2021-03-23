import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class WordPage extends CardPanel implements PropertyChangeListener {
	private final String[] keyboard = {"Q","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M"};
	private JLabel label = new JLabel();
	private JLabel guessesLeft = new JLabel();
	public WordPage(Word word) {
		super("GUESSWORD");
		//listens for changes to the Word object I passed in
		word.addPropertyChangeListener(this);
		
		CardLayout cLayout=new CardLayout();
		JPanel mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(600, 200));
		JPanel changingPanel = new JPanel(cLayout);
		changingPanel.setPreferredSize(new Dimension(600, 400));
		
		
		
		//Requirement 4: word selection
		ArrayList<Integer> ids = Queries.getWordIds(word.getDifficulty());
		int id = Controller.pickRandomId(ids);
		try {
			word = Queries.selectWord(id);
			Queries.updateWordGuessed(word);
			
		
		} catch (ClassNotFoundException | SQLException e1) {
			
			e1.printStackTrace();
		} 
		
		//Requirement 5: word display
		JLabel toGuess = new JLabel();
		List<Pair<Character, Boolean>> letters = Controller.createList(word.getWordValue());
		toGuess.setText(Controller.displayValue(letters));
		mainPanel.add(toGuess);
		
		
		
		//
		
		//Using the value for the word difficulty, I set the number of guesses left;
		//guessesLeft depends on the difficulty of the word. 
		//Unless specified, the guesses left is always letterCount + 2
		//If very Easy -> letterCount + 4
		//if Very Hard -> letterCount - 4
		int numberOfGuesses = word.getLetterCount();
		if(word.getDifficulty().equals("very easy")) {
			numberOfGuesses+=4;
		} else if(word.getDifficulty().equals("very hard")) {
			numberOfGuesses-=4;
		} else {
			numberOfGuesses +=2;			
		}
		guessesLeft.setText(String.valueOf(numberOfGuesses));
		JLabel guessesLeftText = new JLabel("GUESSES LEFT:");
		mainPanel.add(guessesLeftText);
		mainPanel.add(guessesLeft);
		
		//set initial warnings to 0;
		int warnings = 0;
		JLabel warningsLabel = new JLabel(String.valueOf(warnings));
		JLabel warningsLabelText = new JLabel("WARNINGS:");
		mainPanel.add(warningsLabelText);
		mainPanel.add(warningsLabel);
		
		
		//There are 3 buttons: 
		//button 1: guess letter -> this will change lower section of the JPanel to display a virtual keyboard
		JButton guessLetter = new JButton("Guess Letter");
		mainPanel.add(guessLetter);
		guessLetter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cLayout.show(changingPanel, "word");
			}
		});
		//button 2: guess word -> this will change lower secton of the  JPanel to dislay a textfield where the user can input whole word.
		JButton guessWord = new JButton("Guess Word");
		mainPanel.add(guessWord);
		guessWord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cLayout.show(changingPanel, "letter");
			}
		});
		//button 3: X -> direct to welcomePage
		JButton x = new JButton("X");
		mainPanel.add(x);
		x.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchCard("WELCOME");
			}
		});
		
		//building guessLetterPanel
		JPanel guessLetterPanel = new JPanel();
		JTextField textLetterInput = new JTextField(2);
		guessLetterPanel.add(textLetterInput);
		guessLetterPanel.setPreferredSize(new Dimension(600, 400));
		JButton submitLetter = new JButton("Submit");
		submitLetter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// on submit, If the letter is present I make a new JDialog pop up and I reveal the letter (if the letter is correct) 
				String letterToBeCompared = textLetterInput.getText();
				if(letterToBeCompared.matches("[a-zA-Z]+")) {
					//this compares the letter to the ones in the List 
					List<Pair<Character, Boolean>>newLetters =Controller.compareLetter(letters, letterToBeCompared.charAt(0) );
					if(isLetterPresent(newLetters, letterToBeCompared.charAt(0))) {
						//THIS DOESN"T do anything atm, find something else 
							System.out.println("letter exists!!!!!!");
							if(haveLettersBeenGuessed(newLetters)) {
								hasPlayerWon();
							}
					} else {
							//DECREASE N. OF GUESSES BY 1
					
						int currentNGuessesLeft = Integer.parseInt(guessesLeft.getText());
						guessesLeft.setText(String.valueOf(currentNGuessesLeft-1));
						hasPlayerLost(currentNGuessesLeft-1);
					}
					//this displays the letter guessed.
					toGuess.setText(Controller.displayValue(newLetters));
					
				
				}else {
					// CALL WARNING JDIALOG WITH INVALID USER INPUT
					JOptionPane.showMessageDialog( getParent(),
						    "The value you submitted is not a letter\n"+"Please check your value again",
						    "Inane warning",
						    JOptionPane.WARNING_MESSAGE);
					int currentNWarnings = Integer.parseInt(warningsLabel.getText());
					warningsLabel.setText(String.valueOf(currentNWarnings+1));
					//for 3 consecutive warnings, penalty is added
					if(threeWarnings(currentNWarnings+1)) {
						//Penalty of 1
						int currentNGuessesLeft = Integer.parseInt(guessesLeft.getText());
						guessesLeft.setText(String.valueOf(currentNGuessesLeft-1)); 
						hasPlayerLost(currentNGuessesLeft-1);
					}
					
					}
				textLetterInput.setText("");
				}
	
		});
		guessLetterPanel.add(submitLetter, BorderLayout.SOUTH);
		
		
		//I create a JPanel of buttons that I can then add to the guessLetterPanel
		JPanel buttons = new JPanel();
		buttons.setPreferredSize(new Dimension(600, 100));
		buttons.setLayout(new GridLayout(3,10));
		GridBagConstraints c = new GridBagConstraints();
		c.fill=GridBagConstraints.CENTER;
		
		for (int i = 0; i<keyboard.length; ++i) {
			JButton letterButton =new JButton(keyboard[i]);
			letterButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String buttonText = letterButton.getText();
				//text is set to whichever letter the user presses
				textLetterInput.setText(textLetterInput.getText().concat(letterButton.getText()));
				//disable the button so user can't click on it twice
				letterButton.setEnabled(false);
				
				
			}});
			buttons.add(letterButton);
			System.out.println(keyboard[i]);
		}
		
		guessLetterPanel.add(buttons, c);
		
		
		// building guessWordPanel ->
		JPanel guessWordPanel = new JPanel();
		guessWordPanel.add(new JLabel("the whole word is..."), BorderLayout.NORTH);
		JTextField wordTextField = new JTextField(12);
		guessWordPanel.add(wordTextField, BorderLayout.CENTER);
		JButton submit = new JButton("Submit");
		//Here I get the wordValue to use in the anonymous class bc the word.getWordVal() is out of scope there
		String wordToBeGuessed = word.getWordValue().toUpperCase();
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String wordToBeCompared = wordTextField.getText().toUpperCase();
				//check if the user has inputted ivalid characters. 
				
				if (wordToBeCompared.matches("[a-zA-Z]+")) {
					System.out.println("Word matches");
					System.out.println("tobecomp "+ wordToBeCompared+" wordtobeguess "+ wordToBeGuessed);
					if (wordToBeCompared.equals(wordToBeGuessed)) {
						
						//JDIALOG FOR WON GAME
						hasPlayerWon();
						System.out.println("you have won the game");
					} else {
						//PENALTY -3
						int currentNGuessesLeft = Integer.parseInt(guessesLeft.getText());
						guessesLeft.setText(String.valueOf(currentNGuessesLeft-3));
						//check whether the number of guesses left <=0!!! 
						hasPlayerLost(currentNGuessesLeft-3);
					}
				} else {
					//WARNING MESSAGE DIALOG FOR INVALID WORD
					JOptionPane.showMessageDialog( getParent(),
						    "The word you submitted contains invalid characters. Please check whether the word contained numeric characters or symbols",
						    "Inane warning",
						    JOptionPane.WARNING_MESSAGE);
					System.out.println("String doesn't match REGEX");
					
					int currentNWarnings = Integer.parseInt(warningsLabel.getText());
					warningsLabel.setText(String.valueOf(currentNWarnings+1));
					//for 3 consecutive warnings, penalty is added
					if(threeWarnings(currentNWarnings+1)) {
						//Penalty of 1
						int currentNGuessesLeft = Integer.parseInt(guessesLeft.getText());
						guessesLeft.setText(String.valueOf(currentNGuessesLeft-1)); 
						hasPlayerLost(currentNGuessesLeft-1);
					}
					
				}
				
				wordTextField.setText("");
			}
		});
		guessWordPanel.add(submit, BorderLayout.SOUTH);
		
		
		//adding both panels to the changing panel
		changingPanel.add(guessWordPanel, "letter");
		changingPanel.add(guessLetterPanel, "word");
		//adding the panels 
		add(mainPanel,BorderLayout.NORTH);
		add(changingPanel, BorderLayout.SOUTH);
		
		setVisible(true);
		repaint();
	}
	//checks whether the number of guesses left is equal or less than 0. That means the player has lost
	public boolean hasPlayerLost(int guessesLeft) {
		if (guessesLeft<=0) {
			Object[] options = {"NEW GAME",
            "QUIT GAME"};
			int n = JOptionPane.showOptionDialog(getParent(), "You have no guesses left\n"+" you have lost the game!","", JOptionPane.YES_NO_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    null,     //do not use a custom Icon
				    options,  //the titles of buttons
				    options[0]); 
			if (n == JOptionPane.NO_OPTION){
				System.exit(0);
				
			} else {
				switchCard("WELCOME");
			}
//			JOptionPane.showMessageDialog(getParent(), "You have lost the game!");
			return true;
		} else {
			return false;
		}
	}
	
	public void hasPlayerWon() {
		Object[] options = {"NEW GAME",
        "QUIT GAME"};
		int n = JOptionPane.showOptionDialog(getParent(), "You have won the game\n"+"Congratulations!!","", JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,     //do not use a custom Icon
			    options,  //the titles of buttons
			    options[0]); 
		if (n == JOptionPane.NO_OPTION){
			System.exit(0);
			
		} else {
			switchCard("WELCOME");
		}
	}
	
	//checks whether the letter the player has inputted is present
	public boolean isLetterPresent(List<Pair<Character, Boolean>> list, char c) {
		for (Pair<Character, Boolean> pair : list) {
			 if(pair.key == c) {
				 return true;
			 }
		}
		return false;
	}
	//checks whether ALL the letters have been guessed
	public boolean haveLettersBeenGuessed(List<Pair<Character, Boolean>> list) {
		for (Pair<Character, Boolean> pair : list) {
			 if(pair.value == false) {
				 return false;
			 }
		}
		return true;
	}
	
	//checks whether the player has received three consecutive warnings
	public boolean threeWarnings(int warnings) {
		if (warnings %3 ==0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("I am in event of WodPage. Updated difficulty in WordPage is: "+evt.getNewValue());
		System.out.println("I am in event of WodPage. Previous difficulty in WordPage is: "+evt.getOldValue());
		
	
	}
	
	

	
}

