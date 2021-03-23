
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class DifficultyPage extends CardPanel{
	
	public  DifficultyPage(Word wordToGuess){
		super("DIFFICULTY");
		
		setVisible(true);
		WordListener listen = new WordListener(this);
		wordToGuess.addPropertyChangeListener(listen);
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		JLabel selectMessage = new JLabel("Please select game difficulty");
		add(selectMessage);
		//adding difficulty mode buttons
		JButton veryEasy = new JButton("Very Easy");
		add(veryEasy);
		veryEasy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//The method 'next panel' can be reused for other buttons as well.
				//one of the benefits of anonyomous inner classes!
				try {
					difficultySelected(wordToGuess,"very easy");
					System.out.println("New Word DIfficulty "+wordToGuess.getDifficulty());
					WordPage wordPage = new WordPage(wordToGuess);
					deck.add(wordPage, wordPage.toString());
					switchCard(wordPage.toString());
					
					
				} catch (ClassNotFoundException | SQLException e1) {
					System.out.println(e1);
					System.out.println("cannot do select");
				}
			}
		});
		JButton easy = new JButton("Easy");
		add(easy);
		easy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					difficultySelected(wordToGuess, "easy");
					WordPage wordPage = new WordPage(wordToGuess);
					deck.add(wordPage, wordPage.toString());
					switchCard(wordPage.toString());
					
				} catch(ClassNotFoundException | SQLException e1){
					System.out.println(e1);
					System.out.println("cannot do select");
				}
			}
		});
		
		JButton normal = new JButton("Normal");
		add(normal);
		normal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					difficultySelected(wordToGuess,"normal");
					WordPage wordPage = new WordPage(wordToGuess);
					deck.add(wordPage, wordPage.toString());
					switchCard(wordPage.toString());
					
				} catch(ClassNotFoundException | SQLException e1) {
					System.out.println(e1);
					System.out.println("cannot do select");
				}
			}
		});
		
		JButton hard = new JButton("Hard");
		add(hard);
		hard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					difficultySelected(wordToGuess,"hard");
					WordPage wordPage = new WordPage(wordToGuess);
					deck.add(wordPage, wordPage.toString());
					switchCard(wordPage.toString());
					
				} catch(ClassNotFoundException | SQLException e1) {
					System.out.println(e1);
					System.out.println("cannot do select");
				}
			}
		});
		
		JButton veryHard = new JButton("Very Hard");
		add(veryHard);
		veryHard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					difficultySelected(wordToGuess,"very hard");
					WordPage wordPage = new WordPage(wordToGuess);
					deck.add(wordPage, wordPage.toString());
					switchCard(wordPage.toString());
					
				} catch(ClassNotFoundException | SQLException e1) {
					System.out.println(e1);
					System.out.println("cannot do select");
				}
			}
		});
		
	}
	/**
	 * this function calls the select statement based on the word selected and passes the 
	 * difficulty option selected by the player
	 * @param option which will later be used to query the word based on the button
	 * selected
	 * @throws ClassNotFoundException
	 * @throws SQLException 
	 */
	private String difficultySelected(Word word, String option) throws ClassNotFoundException, SQLException {
		word.setDifficulty(option);
		System.out.println("WORD Difficulty!!!! "+word.getDifficulty());
		return word.getDifficulty();
	}

	
	
}
