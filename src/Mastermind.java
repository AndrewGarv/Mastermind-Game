/*
 * Program: Mastermind.java
 * Author: Andrew Garvey
 * Date: 11/27/2011
 * Description: Mastemind game.
 * The application will prompt the players to decide how many games they want to play
 * must be an even number
 * You will then be prompted to decide how many guesses per game.
 * Players 1 and 2 will have a turn being the Code Maker.
 * Scoring points is done by the Code Maker only. A point is given to code maker
 * for every worng guess by the code breaker. An extra point is given if the max number
 * of guesses is reached without solving the code maker.
 * Code Maker will give feedback to the code Breaker by issuing coloured pegs; 1 black peg
 * for each correct colour and position and a white peg for every correct colour but
 * wrong position.
 * After all the games are played the points are tallied and the winner is declared.  
 */

import java.io.*;
import java.util.Arrays;

// class has all necessary methods to play mastermind
public class Mastermind {
 // initialize variables
 InputStreamReader isr = new InputStreamReader(System.in);
 BufferedReader br = new BufferedReader(isr);
 String userInput1 = "";
 String userInput2 = "";
 static int numberOfGames = 0;
 static int numberOfGuesses = 0;
 int pointsForPlayer1 = 0;
 int pointsForPlayer2 = 0;
 int gameNumber = 0;
 final int COLS = 4;
 final int PEGCOLS = 2;
 char[] charCodeMakerArray = new char[COLS];
 int numberOfGuesses3 = 0;

 
 // help screen
 public void helpScreen() {

  System.out
    .println("1. You must decide how many games per match to play - Must be an even number.");
  System.out
    .println("2. You must also decide how many guesses per game - Must be 8, 10, or 12.");
  System.out
    .println("3. Code maker will will choose 4 colours out of 6.");
  System.out
    .println("4. Code breaker will try to guess the code maker's 4 colours by entering the first");
  System.out
    .println("letter of each colour on the same line from the following list.");
  System.out
    .println("5. R - Red, B - Blue, G - Green, O - Orange, Y - Yellow, P - Purple.");
  System.out
    .println("6. Code Maker will give feedback to the code breaker by issuing");
  System.out
    .println("a black peg for guessing the correct position of the colour");
  System.out
    .println("and a white peg if the colour is part of the code maker's colours but in the wrong position.");

  System.out
    .println("7. Code maker gets 1 point for every guess that is incorrect by the code breaker.");
  System.out
    .println("8. Code maker gets an extra point if the last guess by the code breaker of the game is incorrect.");
  System.out
    .println("9. The player with the most points after all the games are played is declared the Winner!");

 }

 public void getGameRulesInput() {
  boolean goodInput = true;

  // do loop required to ensure correct entry is made by user
  // and the number of games chosen
  do {
   System.out
     .println("How many games would you like to play, this must be an even number");
   goodInput = true;
   try {
    userInput1 = br.readLine();
    numberOfGames = Integer.parseInt(userInput1);
   } catch (IOException ex) {
    goodInput = false;
   } catch (NumberFormatException ez) {
    goodInput = false;

   }
  } while (!(numberOfGames % 2 == 0) || goodInput == false);

  
  // do loop will continue until the number of guesses chosen is reached
  // and the correct number of guesses is chosen
  do {
   System.out
     .println("How many guesses would you like to play per game? Must be 8, 10, 12");
   goodInput = true;
   try {
    userInput2 = br.readLine();
    numberOfGuesses = Integer.parseInt(userInput2);
   } catch (IOException ex) {
    goodInput = false;
   } catch (NumberFormatException ez) {
    goodInput = false;
   }
  } while (!(numberOfGuesses == 8 || numberOfGuesses == 10 || numberOfGuesses == 12)
    || goodInput == false);
 }

 // Code maker - generate colours based on random numbers
 public void getCodeMakerInput() {
  // initialize variables
  int x = 0;
  int[] intCodeMakerArray;
  // array to hold the random numbers
  intCodeMakerArray = new int[COLS];
  gameNumber = gameNumber + 1;
  String whichPlayer = "";
  numberOfGuesses3 = 0;

  if (gameNumber % 2 == 0) {
   whichPlayer = "Player # 2";
  } else {
   whichPlayer = "Player # 1";
  }
  System.out.println("\nNEW GAME - " + whichPlayer);
  System.out
    .println("***** " + "I AM NOW THE CODE MAKER - Good Luck Guessing *****");

  // generate random numbers for the code maker
  // and map them to pre determined colours
  do {
   intCodeMakerArray[x] = (int) ((Math.random() * 6) + 1);
   // map the colours to the numbers
   charCodeMakerArray[x] = getColours(intCodeMakerArray[x]);
   x++;
  } while (x < COLS);

 }

 public void getCodeBreakerInput() {

  // initialize variables
  final int PEGCOLS = 2;
  int numberOfGuesses2 = 0;
  int numberOfGuessesRemaining = numberOfGuesses;
  final int COLS = 4;
  boolean goodInput = true;
  String colourInput = "";
  char[] colourInputArray = new char[COLS];
  int[] pegsArray = new int[PEGCOLS];

  // outer do loop necessary to continue until the number
  // of guesses equals the rule set at the beginning of the game
  // inner do loop is necessry to avoid any typing errors
  do {
   do {
    System.out
      .println("TYPE in the first letter of only 4 COLOURS listed below:");
    System.out
      .println("R - Red, B - Blue, G - Green, O - Orange, Y -Yellow, P - Purple");
    goodInput = true;
    numberOfGuessesRemaining = numberOfGuessesRemaining - 1;
    try {
     colourInput = br.readLine().replaceAll(" ", "")
       .toUpperCase(); // trim white space
     // convert to char array
     colourInputArray = colourInput.toCharArray();
     // exception handling - will not end game but code breaker
     // will have to re-enter
    } catch (IOException ex) {
     goodInput = false;
    } catch (NumberFormatException ez) {
     goodInput = false;
    }
    if (!(colourInput.length() == 4)) {
     System.out
       .println("You did not type in 4 letters - Try Again");
    } else if (goodInput == false) {
     System.out
       .println("An Invalid entry has occurred - Try Again");
    } else if (goodInput == true) {
     // check if codebreaker's guess matches code makers colours
     boolean isGuessCorrect = Arrays.equals(colourInputArray,
       charCodeMakerArray);
     if (isGuessCorrect == true) {
      System.out.println("Code Makers Colours: "
        + charCodeMakerArray[0] + " "
        + charCodeMakerArray[1] + " "
        + charCodeMakerArray[2] + " "
        + charCodeMakerArray[3]);
      System.out.println("\n");
      System.out.println("Code Breakers Guess: "
        + colourInputArray[0] + " "
        + colourInputArray[1] + " "
        + colourInputArray[2] + " "
        + colourInputArray[3]);
      // correct guess - game however is won on points and not
      // by single games
      System.out
        .println("YOU WIN THE GAME - MAYBE NOT THE MATCH");
      // set number of guesses to max to end game if player
      // guessed correctly
      numberOfGuesses2 = numberOfGuesses;
     }
     // determine how many cloured pegs to give and calculate
     // players points
     else {
      pegsArray = determinePlayerPoints(colourInputArray,
        charCodeMakerArray);

      // code maker's feedback
      System.out.println("--------------------------------");
      System.out.println("Code Maker's Feedback:      PEGS");
      System.out.println("Code Breaker's Guesses      B  W");
      System.out.println("--------------------------------");
      System.out.print(colourInputArray[0] + " ");
      System.out.print(colourInputArray[1] + " ");
      System.out.print(colourInputArray[2] + " ");
      System.out.print(colourInputArray[3]
        + "                     ");
      System.out.print(pegsArray[0] + "  ");
      System.out.print(pegsArray[1]);
      // show code breaker the number og guesses remaining
      System.out.println("      No. of Guesses Remaining - "
        + numberOfGuessesRemaining);
      System.out.println("--------------------------------");
     }
    }

   } while (!(goodInput == true) || !(colourInput.length() == 4));

   numberOfGuesses2 = numberOfGuesses2 + 1;

  } while (numberOfGuesses2 < numberOfGuesses);

  // reveal code maker's colours at end of all guesses by a player
  System.out.print("\nCode Maker's Colours Were:  ");
  for (int y = 0; y < COLS; y++) {
   System.out.print(charCodeMakerArray[y] + " ");
  }
  System.out.println("\n");
 }

 // method to determine number of black and white pegs
 // calculate players points
 public int[] determinePlayerPoints(char[] colourInputArray,
   char[] charCodeMakerArray) {

  // initialize variables this method will be using
  final int PEGCOLS = 2;
  final int COLS = 4;
  int[] pegsArray = new int[PEGCOLS];
  boolean[] breakerHit = new boolean[COLS];
  boolean[] makerHit = new boolean[COLS];
  int blackPegs = 0;
  int whitePegs = 0;

  // initialize boolean values for code breaker
  for (int i = 0; i < COLS; i++) {
   breakerHit[i] = false;
  }

  // initialize boolean values for code maker
  for (int i = 0; i < COLS; i++) {
   makerHit[i] = false;
  }

  // code to calculate black pegs
  for (int i = 0; i < COLS; i++) {
   if (colourInputArray[i] == charCodeMakerArray[i]) {
    breakerHit[i] = true;
    makerHit[i] = true;
    blackPegs = blackPegs + 1;
   }
  }

  // code to calculate white pegs
  for (int i = 0; i < COLS; i++) {
   for (int j = 0; j < COLS; j++) {
    if ((colourInputArray[i] == charCodeMakerArray[j])
      && (breakerHit[i] == false) && (makerHit[j] == false)) {
     breakerHit[i] = true;
     makerHit[j] = true;
     whitePegs = whitePegs + 1;
     j = 4;
    }
   }
  }

  // tally up player's points and return in array
  // to be displayed by codemaker
  // to code breaker
  numberOfGuesses3 = numberOfGuesses3 + 1;
  if (gameNumber % 2 == 0 && numberOfGuesses3 == numberOfGuesses) {
   pointsForPlayer2 = pointsForPlayer2 + 2;
  } else if (gameNumber % 2 == 0) {
   pointsForPlayer2 = pointsForPlayer2 + 1;
  } else if (numberOfGuesses3 == numberOfGuesses) {
   pointsForPlayer1 = pointsForPlayer1 + 2;
  } else
   pointsForPlayer1 = pointsForPlayer1 + 1;

  pegsArray[0] = blackPegs;
  pegsArray[1] = whitePegs;

  return pegsArray;
 }

 // method to map colours to the numbers
 // 1 = R (Red)
 // 2 = B (Blue)
 // 3 = G (Green)
 // 4 = O (Orange)
 // 5 = Y (Yellow)
 // 6 = P (Purple)
 public char getColours(int intColour) {
  char colour = 0;
  if (intColour == 1) {
   colour = 'R';
  } else if (intColour == 2) {
   colour = 'B';
  } else if (intColour == 3) {
   colour = 'G';
  } else if (intColour == 4) {
   colour = 'O';
  } else if (intColour == 5) {
   colour = 'Y';
  } else if (intColour == 6) {
   colour = 'P';
  }
  return colour;
 }

 public void quit() {
  System.out.println("Exiting - Good Bye!"); // quit game
  System.exit(0);
 }
}
