import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONException;


public class Queries {
	private static final String url = "jdbc:postgresql://localhost/hangman";
    private static final String user = "postgres";
    private static final String password = "traecy";

	
	
	public static void insertWord(ArrayList<String> words) throws SQLException{
		Word wordToInsert = new Word(0, null, 0, null, false);
		for (int i = 0; i<words.size(); ++i) {
			String word = words.get(i);
			System.out.println("The word I am considering is: "+ word);
			wordToInsert.setWordValue(word);
			int length = Controller.calculateWordLength(word);
			System.out.println("the lenght of "+ word+" is: "+ length);
			wordToInsert.setLetterCount(length);
			try {
				int wordFrequency = Controller.decodeJSONFrequencyWord(word);
				String difficulty = Controller.rankWord(wordFrequency, word);
				System.out.println("The difficulty of "+ word+" is: "+ difficulty);
				wordToInsert.setDifficulty(difficulty);
			} catch(Exception e){
				System.out.println(e);
			} 
			
			try (Connection connection = DriverManager.getConnection(url, user, password);){
				
				PreparedStatement stmt = connection.prepareStatement("INSERT INTO public.words (word, length, difficulty, guessed) VALUES(?,?,?,?);");
				//the first ? is the word itself
				stmt.setString(1, wordToInsert.getWordValue());
				//the second ? is the number of letters that word contains
				stmt.setInt(2, wordToInsert.getLetterCount());
				//then there is the difficulty calculated on the basis of frequency and letter count
				stmt.setString(3, wordToInsert.getDifficulty());
				//guessed is always false at insert by default.
				stmt.setBoolean(4,  false);
				//executeUpdate when there is an insert
				stmt.executeUpdate();
				//release resources
				stmt.close();
				connection.close();	
			} catch (SQLException e) {
				System.out.println("ERROR INSERTING RECORDS! "+ e);
				
			}

			
			
		}
		System.out.println("Finished all insertions");
	}
	
	/**
	 * 
	 * @param difficulty
	 * @return an ArrayList of ints
	 */
	public static ArrayList<Integer> getWordIds(String difficulty){
		ArrayList<Integer> ids = new ArrayList<>();
		
		try (Connection connection = DriverManager.getConnection(url, user, password);){
			
			PreparedStatement stmt = connection.prepareStatement("SELECT id FROM public.words WHERE difficulty = ? AND guessed = ?");
			//the first ? is difficulty
			stmt.setString(1, difficulty);
			//the second ? is guessed
			stmt.setBoolean(2, false);
			//execute query
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("id"));
				//I am adding the id to the ArrayList
				ids.add(rs.getInt("id"));
			}
			//release resources
			stmt.close();
			connection.close();
			//System.out.println("This is the array that will be returned"+ ids);
		} catch (SQLException e) {
			System.out.println("ERROR IN SELECTING IDs!!! "+ e);
			
		}
		return ids;
		
	}
	/**
	* selectWord is responsible returning a word object from the database given its corresponding id
 	* @param difficulty
 	* @return Word object
 	* @throws ClassNotFoundException
	 * @throws SQLException 
 	*/
	public static  Word selectWord(int wordId) throws ClassNotFoundException, SQLException{
		//those initial null values will then be changed into the actual values I get from the db later on
		Word wordToGuess = new Word(wordId, null, wordId, null, false);
		
		System.out.println("I AM BEING CALLED!!!! "+ wordId);
		
		try(Connection connection = DriverManager.getConnection(url, user, password)) {
			//prepared statements are better when there are variables inside the query (id in this case)
			PreparedStatement stmt = connection.prepareStatement("SELECT id, word, length, difficulty FROM public.words WHERE id = ?");
			stmt.setInt(1, wordId);
			//execute the prepared statement
			ResultSet rs = stmt.executeQuery();
			while ( rs.next() ) {
				//'populate' word object with values gotten from the db
				//I do this by calling the setter methods of the word object
				int id = rs.getInt("id");
				wordToGuess.setId(id);
				String word = rs.getString("word");
				wordToGuess.setWordValue(word);
				int length = rs.getInt("length");
				wordToGuess.setLetterCount(length);
				String difficulty = rs.getString("difficulty");
				wordToGuess.setDifficulty(difficulty);
//				System.out.println(id+" "+word+" "+length);
			}
			System.out.println(wordToGuess.getId()+ wordToGuess.getDifficulty()+ wordToGuess.getWordValue() );
			//release resources
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("ERROR IN SELECT!!! "+ e);
		}
		
		return wordToGuess;
	}
	
	/**
	 * To keep track of whether a user has guessed a word, once a word has been selected, the guessed value is 
	 * changed from the deafult false to true. This prevents the word from being guessed twice.
	 * @param wordToUpdate
	 * @return
	 */
	public static int updateWordGuessed(Word wordToUpdate) {
		wordToUpdate.setGuessed(true);
		int updatedRows = 0;
		System.out.println(wordToUpdate.getGuessed());
		try(Connection connection = DriverManager.getConnection(url, user, password)) {
			//prepared statements are better when there are variables inside the query (id in this case)
			PreparedStatement stmt = connection.prepareStatement("UPDATE public.words SET guessed =? WHERE id = ?");
			stmt.setBoolean(1, wordToUpdate.getGuessed());
			stmt.setInt(2, wordToUpdate.getId());
			//execute the prepared statement
			updatedRows = stmt.executeUpdate();
			
			//release resources
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("ERROR UPDATING THE GUESSED VALUE!!! "+ e.getMessage());
		}
		return updatedRows;
	}
	
	
}
 

