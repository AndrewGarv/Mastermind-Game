/*
 * Program: MastermindRunner.java
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

// main runner class
public class MastermindRunner {
 public static void main(String[] args) {

  // initializ variables
  int selection = 0;
  String userInput = "";
  boolean goodInput = true; // used to ensure good input
  int numberOfGames = 0;

  // create object for mastermind class
  Mastermind mstm = new Mastermind();

  // create object to be able to read user input
  InputStreamReader isr = new InputStreamReader(System.in);
  BufferedReader br = new BufferedReader(isr);
  
  // 1. Outer loop allows the players to keep playing until you enter 3 to Quit
        // 2. Inner do loop is to
  //    make a selection using a menu.
  //    Valid selection is required or will display message
  //    to enter correct value
  do {
   do {
    goodInput = true;
    System.out.println("\nLets Play Mastermind");
    System.out.println("Please Make a Selection:");
    System.out.println("[1] Start game");
    System.out.println("[2] Online Help");
    System.out.println("[3] Quit");

    try {
     userInput = br.readLine();
     selection = Integer.parseInt(userInput);
    } catch (IOException ex) {
     System.out.println("Incorrect Value - Enter 1 or 2 or 3");
     goodInput = false;
     break;
    } catch (NumberFormatException ez) {
     System.out.println("Incorrect Value - Enter 1 or 2 or 3");
     goodInput = false;
     break;
    }
    
    // method chosen based on user input
    if (selection == 1) {
     // create code maker's input

     mstm.getGameRulesInput();
     System.out.println("-------------------------------------");
     System.out.println("We are playing "
       + Mastermind.numberOfGames + " games per match");
     System.out.println("with " + Mastermind.numberOfGuesses
       + " guesses per game");
     System.out.println("-------------------------------------");
     // do loop for the number of games allowed for the match
     do {
      mstm.getCodeMakerInput(); // generate code makers
             // colours
      mstm.getCodeBreakerInput(); // generate code breakers
             // colours
      numberOfGames = numberOfGames + 1;
     } while (numberOfGames < Mastermind.numberOfGames);

     // help screen chosen
    } else if (selection == 2) {
     mstm.helpScreen();
     // quit game
    } else if (selection == 3) {
     mstm.quit();
     // incorrect value chosen - re-enter
    } else {
     System.out.println("Incorrect Value - Enter 1 or 2 or 3");
    }
   } while (!(selection == 1 || selection == 2 || selection == 3)
     || goodInput == false);

   // display who the winner is
   if (selection == 1) {
    System.out.println("\n\nTHE WINNER IS?");
    System.out.println("Player # 1 points: "
      + mstm.pointsForPlayer1);
    System.out.println("Player # 2 points: "
      + mstm.pointsForPlayer2);
    if (mstm.pointsForPlayer1 > mstm.pointsForPlayer2) {
     System.out.println("Player # 1 is the WINNER!");
    } else if (mstm.pointsForPlayer2 > mstm.pointsForPlayer1) {
     System.out.println("Player # 2 is the WINNER!");
    } else {
     System.out.println("TIE MATCH - NOBODY WINS");
    }
   }
  } while (!(selection == 3));
 }
}
