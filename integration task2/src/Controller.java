import java.io.*;

import java.util.List;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.net.*;
import org.json.JSONArray;
import org.json.JSONException;
//import org.json.JSONException;
import org.json.JSONObject;


//import org.json.JSONString;


public class Controller {
	/**
	 * generic method that, given an API/url, retrieves the JSON at that url
	 * @param urlToRead
	 * @return
	 * @throws Exception
	 */
	public static String getJSONFromAPI(String urlToRead) throws Exception {
	      StringBuilder result = new StringBuilder();
	      URL url = new URL(urlToRead);
	      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	      connection.setRequestMethod("GET");
	      
	      BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	      String line;
	      while ((line = read.readLine()) != null) {
	         result.append(line);
	      }
	     
	      read.close();
	      connection.disconnect();
	      return result.toString();
	      
	   }
	/**
	 * decodeJSONRandomWord is responsible for getting random words from the relative Wordnik API 
	 * @param url
	 * @return String that is the word decoded from the JSON 
	 * @throws JSONException
	 * @throws Exception
	 */
	public static ArrayList<String> decodeJSONRandomWord(String url) throws JSONException, Exception {
		String word = "";
		ArrayList<String> randomWords = new ArrayList<String>();
		 //I have a JSON array (i.e. array of many json objects)
		JSONArray arr = new JSONArray(getJSONFromAPI(url));
	   //Now i get into the jsonArray to read the JSON objects
	    for (int i = 0; i < arr.length(); ++i) {
	    	System.out.println("I am in FOR LOOP! Index: "+i);
	    	System.out.println("this: "+ arr.get(i));
	    	//create the JSON object to get the word 
	    	JSONObject wordObj = (JSONObject)arr.get(i);
	    	System.out.println("I want to know the word!");
	    	//This is the word in the JSON object
	    	
			try {
				word = (String)wordObj.get("word");
				randomWords.add(word);
				//System.out.println(word);
			} catch (JSONException e) {
				e.printStackTrace();
			
			}
	    	
	    }
	    
	    return randomWords;
	}

	
	/**
	 * decode JSONFrequencyWord is responsible for getting the corpus count of a given word from the corresponding Wordnik API
	 * @param word
	 * @return int that is the frequency
	 */
	public static int decodeJSONFrequencyWord(String word)throws JSONException, Exception {
		int frequency = 0;
		String url = "https://api.wordnik.com/v4/word.json/"+word+"/frequency?useCanonical=false&startYear=2000&endYear=2012&api_key=d8yrlh91wo2cx01rrtltwysumgwg8er6pd3m7zm7cw7yqhgvc";
		try {
			
			JSONObject wordObj = new JSONObject(getJSONFromAPI(url));
	    	System.out.println("I want to know the word!");
	    	//This is the word in the JSON object
	    	
			try {
				frequency = (int) wordObj.getInt("totalCount");
				System.out.println(frequency);
			} catch(JSONException e){
				System.out.println("There was an error decoding the JSON :"+ e);
			}
			
	
		} catch (Exception e) {
			System.out.println("Error retrieving word frequency: "+ e);
		}
		
		return frequency;
	}
	
	
	
	
	
	

	
	/**
	 * 
	 * @param totalCount
	 * @param id
	 * @param word
	 * @return
	 */
	public static String rankWord(long frequency, String word) {
		//ul = upper limit
		//ll = lower limit 

		//based on the frequency of the word 'the' which accounts for roughly 4% of all word occurrences in English
		//final int veryEasyul= 300000000;
	
		final int veryEasyll= 150000000;
		final long easyll = 50000000;
		final int normalll= 80000;
		final long hardll= 1000;
	
		
		try {
			decodeJSONFrequencyWord(word);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		int frequencyRank = 0;
		int newFrequencyRank = 0;
		if(frequency > veryEasyll) {
			frequencyRank = 1;
			newFrequencyRank =rankByLetterCount(word, frequencyRank);
			
		} else if (frequency < veryEasyll && frequency >= easyll) {
			frequencyRank = 2;
			newFrequencyRank = rankByLetterCount(word, frequencyRank);
		} else if (frequency < easyll && frequency >= normalll) {
			frequencyRank = 3;
			newFrequencyRank = rankByLetterCount(word,frequencyRank);
		} else if (frequency < normalll && frequency >= hardll) {
			frequencyRank = 4;
			newFrequencyRank = rankByLetterCount(word, frequencyRank);
		} else if (frequency < hardll) {
			frequencyRank = 5;
			newFrequencyRank = rankByLetterCount(word, frequencyRank);
		}
		String difficulty = "";
		switch(newFrequencyRank) {
			case 1: difficulty= "very easy";
					break;
			case 2: difficulty= "easy";
					break;
			case 3: difficulty="normal";
					break;
			case 4: difficulty= "hard";
					break;
			case 5: difficulty= "very hard";
					break;
		
		}
		
		return difficulty;
	}
	
	/**
	 * 
	 * @param word
	 * @param id
	 * @param frequencyRank
	 * @return
	 */
	public static int rankByLetterCount(String word, int frequencyRank) {
		int letters = calculateWordLength(word);
		if (letters<=5) {
			frequencyRank--;
		} else if (letters >8) {
			frequencyRank++;
			
		} else if (letters<=8 && letters >5) {
			return frequencyRank;
		}
		return frequencyRank;
		
	}
	
	/**
	 * 
	 * @param word
	 * @return
	 */
	public static int calculateWordLength(String word) {
		return word.toCharArray().length;
		
	}
	
	
	/**
	 * 
	 * @param ids
	 * @return int
	 */
	public static int pickRandomId(ArrayList<Integer> ids) {
		
		SecureRandom randGen = new SecureRandom();
		int randomIndex = randGen.nextInt(ids.size());
		try {
			//now select the word corresponding to the random id that was picked
			Queries.selectWord(ids.get(randomIndex));
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error in select query "+e);
		}
		return ids.get(randomIndex);
	}
	
	

	
	public static List<Pair<Character, Boolean>> createList (String word) {
		String uppercaseWord =word.toUpperCase();
		char[] arrayOfLetters = uppercaseWord.toCharArray();
	
		List<Pair<Character, Boolean>> charList = new ArrayList<Pair<Character, Boolean>>();
		for(int i= 0; i<(arrayOfLetters.length);++i) {
			charList.add(new Pair(arrayOfLetters[i], false));
		}
        
        for (Pair<Character, Boolean> pair : charList) {
            System.out.println(pair.key + " -> " + pair.value);
        }
        return charList;
    }
	
	public static List<Pair<Character, Boolean>> compareLetter(List<Pair<Character, Boolean>> list, char l) {
		String label = "";
		 for (Pair<Character, Boolean> pair : list) {
			 if(pair.key == l) {
				 pair.value = true;
			 }
			 System.out.println(pair.key);
			 System.out.println(pair.value);
	      }
		return list;
	}
	
	public static String displayValue(List<Pair<Character, Boolean>> list) {
		String label = "";
		//Display Elements
		for (Pair<Character, Boolean> pair : list) {
			 if(pair.value == false) {
				 label += "_ ";
			 } else {
				 label += pair.key.toString()+" ";
			 }
	      }
		
		return label;
	}
}
