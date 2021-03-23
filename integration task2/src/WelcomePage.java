import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class WelcomePage extends CardPanel{
	
	public WelcomePage() {
		super("WELCOME");
		DifficultyPage selectDifficulty = new DifficultyPage(word);
		setVisible(true);
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		JLabel welcomeLabel = new JLabel("Welcome");
		add(welcomeLabel);
		JButton newGame = new JButton("New Game");
		add(newGame);
		//Anonymous inner calss to add an ActionListener to the newGame button
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deck.add(selectDifficulty, selectDifficulty.toString());
				switchCard(selectDifficulty.toString());
			}
		});
		JButton quitGame = new JButton("Quit Game");
		add(quitGame);
		
		quitGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){  
				System.exit(0);
	        }    
	     });
		
	}



}
