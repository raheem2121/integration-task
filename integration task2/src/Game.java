import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Game {
    Hangman theComputer;
    private ArrayList<Character> dashesForSecretWord = new ArrayList<>();
    private String guess;
    private static int numberOfWarnings ;
    private static int numberOfGuesses ;
    private char[] secretWord;
    private int wrongDataType;
    private String word;
    private boolean hasUserWonGame;
    private char letter;
    private MainClass game;
    private int score;
    private int lengthOfWord;
    private ArrayList<Integer> scores;
    private ArrayList<Character> usedLetters;
    private boolean isWordUsed;
    private int uniqueLetters;
    private ArrayList<Character>specialLetters;




    public void newGame() throws IOException { //this method initialises the variables and outlines the steps that are repeated within every game
        theComputer = new Hangman();
        word = theComputer.chooseWord();
        uniqueLetters=0;
        scores=new ArrayList<>();
        secretWord = theComputer.stringToChar(word);
        dashesForSecretWord = theComputer.symbolsForLetters(word);
        lengthOfWord = dashesForSecretWord.size();
        usedLetters = new ArrayList<>();
        numberOfGuesses=6;
        numberOfWarnings=0;
        score = 0;
        wrongDataType = 0;
        ArrayList<Character>specialLetters=new ArrayList<>();
        System.out.println(dashesForSecretWord);

        System.out.println("The number of guesses are" + " " + numberOfGuesses);
        System.out.println("The number of warnings are" + " " + numberOfWarnings);
        //arr= theComputer.getArr();
        theComputer.setArr(theComputer.stringToChar(theComputer.chooseWord()));
        //arr= theComputer.stringToChar(theComputer.chooseWord());
        while (!hasUserWonGame && numberOfGuesses >= 1) { //numberOfGuesses>1
            guessedLetter();


        }
        endOfGame();
    }

    public void guessedLetter() {   //this method allows the user to guess a letter and ensures that the letter written is of the correct data type and has not been repeated.

        if (!knowsWholeWordOrNot()) {

            System.out.println("Please enter a letter");
            Scanner scanner = new Scanner(System.in);
            guess = scanner.nextLine();
            letter = guess.toLowerCase().charAt(0);
            int count = dashesForSecretWord.size();


            if (correctDataType(letter)) {

                if (hasLetterBeenUsed(letter)) {
                    //System.out.println("I should exit now");
                }

                boolean correctGuess = false;
                for (int i = 0; i < count; ++i) {
                    if (secretWord[i] == letter) {
                        //dashesForSecretWord.set((i-1),letter);
                        dashesForSecretWord.set(i, letter);
                        correctGuess = true;
                    }
                }

                if (!correctGuess) {
                    System.out.println("This is incorrect");
                    numberOfGuesses--;
                    System.out.println("The number of guesses left are " + numberOfGuesses);

                }

            }
        }


        System.out.println(dashesForSecretWord);
    }


    public void endOfGame() throws IOException {// this method shows the output displayed at the end of the game

        if (hasUserWonGame) {
            setScore(numberOfGuesses, uniqueLetters);
            System.out.println("Well done, you have won the game!, the score is" + " " + getScore());


        } else {
            System.out.println("You have lost the game, the word was" + " " + word);
        }

        if (playAnotherGame()) {
            anotherGame();
        }


    }


    public boolean correctDataType(char letter) {// This method ensures that the user enters the correct data type
        if (Character.toString(letter).matches("^[a-zA-Z]*$")) {
            return true;
        } else {


            if (wrongDataType != 3) {
                System.out.println("You have entered an incorrect data type,please enter data type string");
                wrongDataType++;
            } else {
                numberOfGuesses--;
                wrongDataType = 0;
                numberOfWarnings++;
                System.out.println("You have entered the wrong data type three times therefore you recieve a warning");
                System.out.println("the number of warnings is" + " " + numberOfWarnings + "and the number of guesses are" + " " + numberOfGuesses);

            }
            return false;
        }

    }



    public boolean knowsWholeWordOrNot() {//this method allows the user to guess the entire word
        String question = "Do you know the whole word";
        boolean doYouKnowTheWord = false;
        System.out.println(question);
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine().toLowerCase();
        if (answer.equals("yes")) {
            System.out.println("please enter the word");
            guess = scanner.nextLine();
            if (guess.equals(word)) {
                hasUserWonGame = true;
                doYouKnowTheWord = true;
            } else {
                numberOfGuesses -= 2;
                System.out.println("This is incorrect, please try again");
                System.out.println("the number of guesses are " + " " + numberOfGuesses);

            }
        }
        return doYouKnowTheWord;
    }


    private boolean playAnotherGame() {//This method allows the user to play another game
        boolean anotherNewGame = true;
        System.out.println("Would you like to play again? yes or no");
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine().toLowerCase();
        if (word.equals("yes")) {
            anotherNewGame = true;

        } else {
            readFile();
            System.out.println("Thank you for playing!");
            anotherNewGame = false;


        }
        return anotherNewGame;
    }

    public void anotherGame() throws IOException {//This is the method to start a new game
        MainClass game2 = new MainClass();
        game2.newGame();
    }

    public void setScore(int numberOfGuesses, int uniqueLetters) {//This is a method for intialising the score
        score = (numberOfGuesses *uniqueCharacters(word));
        writeFile();

    }

    public int getScore() { //this method returns the score
        System.out.println(score);
        return this.score;
    }


    public void writeFile() {//This method reads the file containing the score
        String ScoreValue = String.valueOf(score);
        File newFile = new File("Scores.txt");
        try {
            FileWriter write = new FileWriter(newFile, true);
            BufferedWriter buffer = new BufferedWriter(write);
            buffer.write(ScoreValue);
            buffer.newLine();
            buffer.close();
            write.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void readFile() {//this method reads the file and sorts the scores
        try {
            FileReader fileRD = new FileReader("C:\\Users\\SLL014\\IdeaProjects\\Actual Game\\Scores.txt");
            BufferedReader buffReader = new BufferedReader(fileRD);
            String number;
            int currentScore;

            while((number = buffReader.readLine()) != null) {
                currentScore = Integer.parseInt(number);
                scores.add(currentScore);

            }
            fileRD.close();
            buffReader.close();
            System.out.println("LeaderBoard:");
            sortArray(scores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean sortArray(ArrayList<Integer> scores) {
        boolean sortedArray = false;
        int temp;
        while (!sortedArray) {
            sortedArray = true;
            for (int i = 0; i < (scores.size() - 1); i++) {
                if (scores.get(i).compareTo(scores.get(i + 1)) < 0) {
                    temp = scores.get(i);
                    scores.set(i, scores.get(i + 1));
                    scores.set(i + 1, temp);
                    sortedArray = false;
                }
            }
        }
        System.out.print(scores);
        return true;
    }



    public boolean hasLetterBeenUsed(char letter) {//this method checks whether the the letter has been inputed before
        isWordUsed = false;

        if (usedLetters.contains(letter)) {
            System.out.println("You have already entered this letter,Please enter another letter");
            isWordUsed = true;
        } else {
            usedLetters.add(letter);
            isWordUsed = false;

        }
        return isWordUsed;
    }



    public int uniqueCharacters(String word){//This method calculates the number of unique letters in the word
        String uniqueLetters;
        int count;
        if(word==null || word.length()==0){
            return 0;
        }
        Set<Character> alphabetSet=new HashSet<>();

        for(int i=0;i<word.length();i++){
            alphabetSet.add(word.charAt(i));
        }

        System.out.println(alphabetSet.size());
        return alphabetSet.size();

    }

}
