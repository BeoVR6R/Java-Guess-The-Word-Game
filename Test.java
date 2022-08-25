import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Test {

    public static void main(String arg[]) {
        Scanner in = new Scanner(System.in);
        // --------------------------------------------------------------------------------------------------------------------------------
        // Create a new array list with default word.
        MyArrayList<Character> defaultWord = new MyArrayList<Character>();
        MyArrayList<Character> enteredWord = new MyArrayList<Character>();

        // Create a new array list to store returned list.
        MyArrayList<Character> returnList = new MyArrayList<Character>();
        // --------------------------------------------------------------------------------------------------------------------------------

        String wordE, wordFF; // Verables to store the word entered and the word from the list.
        File f1 = new File("input.txt");
        Boolean valid = false;

        // --------------------------------------------------------------------------------------------------------------------------------
        // Print instructions to user
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------INSTRUCTIONS-------------------------------------");
        System.out.println(
                "You have 5 rounds to guess the word.\nIt is a five-letter word\nResponse from the system is as follows:\nA = Valid character and in it's correct position");
        System.out.println("B = Valid character but not in it's correct position\nC = Not a valid character");
        System.out.println("------------------------------------------------------------------------------------\n");
        // --------------------------------------------------------------------------------------------------------------------------------

        for (int x = 0; x < 5; x++)// run 5 time user get 5 guesses.
        {

            System.out.println("Guess the five-letter word.");
            wordE = in.nextLine();
            if (wordE.length() == 5) // Check if word entered is a 5 letter word.
            {
                try { // Open the file to read the words in the list.
                    FileReader fr = new FileReader(f1);
                    BufferedReader br = new BufferedReader(fr);
                    while ((wordFF = br.readLine()) != null) {
                        // ----------------------------------------------------------------------------------------------------------------
                        // for loop to populate defaultWord array
                        for (int i = 0; i < wordFF.length(); i++) {
                            char c = wordFF.charAt(i);
                            defaultWord.add(i, c);
                        }
                        // ----------------------------------------------------------------------------------------------------------------
                        // ----------------------------------------------------------------------------------------------------------------
                        // for loop to populate enteredWord array
                        for (int i = 0; i < wordE.length(); i++) {
                            char c = wordE.charAt(i);
                            enteredWord.add(i, c);
                        }
                        // ----------------------------------------------------------------------------------------------------------------
                        // if (wordFF.equals(wordE))// check if the word entered is in the list
                        // {
                        valid = true;
                        // }
                    }

                    if (valid) {
                        // --------------------------------------------------------------------------------------------------------------------------------
                        // Call checkChar to check the validity of the chars and store the info.
                        returnList = defaultWord.checkChar(enteredWord);
                        // --------------------------------------------------------------------------------------------------------------------------------
                    }

                    fr.close();
                } catch (IOException e) {
                    System.out.println("File Read Error");
                }
            } else
                valid = false;

            System.out.println("The entered word is a valid 5 letter word: " + valid);

            if (valid) {
                // --------------------------------------------------------------------------------------------------------------------------------
                // display the feedback of the entered word
                System.out.println("[" + returnList);
                // display the result of the feedback (Winner, Try again, Looser)
                boolean checkWin = returnList.checkWin();
                if (checkWin) {
                    x = 4;
                    System.out.println("Congratulations! You won this round.");
                } else if (!checkWin && x == 4) {
                    System.out.println("You lost the battle, game over!");
                } else {
                    System.out.println("Oops! Try again.");
                }
                // --------------------------------------------------------------------------------------------------------------------------------
            }
            valid = false;
            // --------------------------------------------------------------------------------------------------------
            // Clear both arrays, input from txt and input from user
            enteredWord.clear();
            defaultWord.clear();
            // --------------------------------------------------------------------------------------------------------
        }
        // --------------------------------------------------------------------------------------------------------
        // Close Scanner.in after runtime of the program
        in.close();
        // --------------------------------------------------------------------------------------------------------
    }

}