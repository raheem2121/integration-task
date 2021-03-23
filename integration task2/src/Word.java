

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Word  {
	private static int id;
	private static String wordValue;
	private static int letterCount;
	private static String difficulty;
	private static boolean guessed;
	private final PropertyChangeSupport wordPcs = new PropertyChangeSupport(this);

	public Word(int id, String wordValue, int letterCount, String difficulty, boolean guessed) {
		super();
		this.id = id;
		this.wordValue= wordValue;
		this.letterCount= letterCount;
		this.difficulty=difficulty;
		this.guessed = guessed;
	}
	
    
	//getters
	public int getId() {
		return id;
	}
	
	public String getWordValue() {
		return wordValue;
	}
	
	public int getLetterCount() {
		return letterCount;
	}
	public void setDifficulty(String wordDifficulty) {
		String oldDifficulty = difficulty;
		difficulty= wordDifficulty;
		wordPcs.firePropertyChange("difficulty", oldDifficulty, difficulty);
	}
	public String getDifficulty() {
		return difficulty;
	}
	
	public boolean getGuessed() {
		return this.guessed;
	}
	//setters
	public void setId(int wordId) {
		int oldId = id;
		id = wordId;
		wordPcs.firePropertyChange("id", oldId, wordId);
	}
	public void setWordValue(String word) {
		String oldWordValue = wordValue;
		wordValue = word;
		wordPcs.firePropertyChange("wordValue", oldWordValue, wordValue);
	}
	public void setLetterCount(int length) {
		int oldLength = letterCount;
		letterCount = length;
		wordPcs.firePropertyChange("wordValue", oldLength, letterCount);
	}

	public void setGuessed(boolean isGuessed) {
		boolean oldGuessed = guessed;
		guessed = isGuessed;
		wordPcs.firePropertyChange("wordValue", oldGuessed, guessed);
	}
	 

	public void addPropertyChangeListener(PropertyChangeListener listener) {
        wordPcs.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        wordPcs.removePropertyChangeListener(listener);
    }
}