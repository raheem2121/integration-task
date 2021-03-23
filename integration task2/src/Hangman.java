import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Hangman {

    private ArrayList<Character> unknownWord = new ArrayList<Character>();
    private char[] arr;
    private int number;


    public String chooseWord() throws IOException {//This method chooses the word from a file

        FileReader fileR = new FileReader("C:\\Users\\SLL014\\IdeaProjects\\Actual Game\\random words.txt");
        BufferedReader buffReader = new BufferedReader(fileR);
        String line;
        ArrayList<String> arr = new ArrayList<String>();

        while ((line = buffReader.readLine()) != null) {
            arr.add(line);
        }
        fileR.close();
        buffReader.close();

        Random randomWord = new Random();
        number = randomWord.nextInt(arr.size());

        String word = arr.get(number);
        return word;

    }
    public char[] stringToChar(String word) {
        return word.toCharArray();
    }

    public ArrayList<Character> symbolsForLetters(String word) {
        for (int i = 0; i < word.length(); i++) {
            unknownWord.add('_');
        }
        return unknownWord;
    }

    public void setArr(char[] arr) throws IOException {
        this.arr = stringToChar(chooseWord());

    }

    public char[] getArr() {
        return arr;
    }

}


