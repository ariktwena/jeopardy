package jeopardy.tui;

import jeopardy.classes.Board;

import java.io.PrintWriter;
import java.util.Scanner;

public class TUI {

    private final Scanner scanner;
    private final PrintWriter writer;
    private final Board board;


    public TUI(Scanner scanner, PrintWriter writer) {
        this.scanner = scanner;
        this.writer = writer;
        this.board = new Board();

    }

    //Get player name
    public String getPlayerName() {
        System.out.println("");
        writer.print("Enter your name > ");
        writer.flush();
        String name = scanner.nextLine();
        loader();
        return name;
    }

    //Get player name
    public void weelcomeMessage(String name) {
        writer.println("");
        writer.println("Welcome to the Quiz show " + name + "!!");
        writer.println("");
        writer.println("Here are the roles of the game: ");
        writer.println("You start by choosing a category A, B, C etc. Then you choose a question for points.");
        writer.println("If you answer correctly, you get the points.");
        writer.println("");
        writer.println("If you ever need help, then just write [help].");
        writer.println("");
        writer.println("Write [Play] to start the game. Have a Happy Quizzy :)");
        writer.flush();
    }

    //Get input from player
    public String playerInput(){
        writer.println("");
        writer.print("What do you want to do? > ");
        writer.flush();
        String input = scanner.nextLine();
        return input;
    }

    //Loading text
    public void loader(){
        try{
            writer.println("Loading.....");
            writer.flush();
            Thread.sleep(1500);
        } catch (InterruptedException i){
            throw new UnsupportedOperationException("You got an InterruptedException: " + i.getMessage());
        }
    }

    //Loading text
    public void loaderLong(){
        try{
            writer.println("Loading.....");
            writer.flush();
            Thread.sleep(3000);
        } catch (InterruptedException i){
            throw new UnsupportedOperationException("You got an InterruptedException: " + i.getMessage());
        }
    }


    //Get help in the game
    public void getHelpGame(){
        writer.println("");
        writer.println("You have to choose a category or question by typing: A, B, C, D, E or F.");
        writer.println("");
        writer.println("Here are the help commands:");
        writer.println("[Help]" + "\t" + "Get help and options");
        writer.println("[Score]" + "\t" + "See your current score");
        writer.println("[Board]" + "\t" + "See the question status");
        writer.println("[Exit]" + "\t" + "Exit the game");
        writer.flush();
        loaderLong();
    }

    //Exit the game
    public void exitGame(String name){
        writer.println("");
        writer.println("I have never met a successful person that was a quitter. Successful people never, ever, give up! " + name);
        writer.flush();
    }

    //Get player score
    public void getScore(String name, int score){
        writer.println("");
        writer.println(name + " you have " + score + " points.");
        writer.flush();
    }

    //Get player score
    public void getBoardStatus(int boardStatus){
        writer.println("");
        writer.println("You have answered " + boardStatus + " questions. You still have " + (60-boardStatus) + " to go :)");
        writer.flush();
    }

    //Game default message
    public void gameDefaultMessage(){
        writer.println("");
        writer.println("Wrong input....");
        writer.println("Do you need help? Then just write [help], or [exit] to end the game.");
        writer.flush();
    }

    //Choose category
    public String playerCategoryInput(){
        writer.println("");
        writer.print("Choose a category > ");
        writer.flush();
        String input = scanner.nextLine();
        return input;
    }


    //Show chosen category
    public void getCategoryTitle(String name){
        writer.println("");
        writer.println("You have chosen the Category '" + name + "', here are the the available questions and possible score prize!");
        writer.flush();
    }

    //Available question and points
    public void availableQuestionsInCategoryAndPoint(String option, int score){
        writer.println(option + ": " + score);
        writer.flush();
    }

    //Non available question and points
    public void nonAvailableQuestionsInCategoryAndPoint(String option){
        writer.println(option + ": Question has already been answered.");
        writer.flush();
    }

    //Choose question choice
    public String playerQuestionInputChoice(){
        writer.println("");
        writer.print("Choose a question > ");
        writer.flush();
        String input = scanner.nextLine();
        return input;
    }

    //Show the question is taken
    public void questionHasAlreadyBeenPlayed(){
        writer.print("The question has already been played\n");
        writer.flush();
    }

    //Get the question
    public void getQuestion(String question){
        writer.print(question);
        writer.flush();
    }

    //Choose question answer
    public String playerQuestionInputAnswer(){
        writer.println("");
        writer.print("What is you answer? > ");
        writer.flush();
        String input = scanner.nextLine();
        return input;
    }

    //Correct answer
    public void correctAnswer(String name, int score){
        //Message to the player
        writer.print("Correct " + name + "!!! You have earned " + score + " points :)\n");
        writer.flush();
    }

    //Incorrect answer
    public void incorrectAnswer(String name, String answer){
        //Message to the player
        writer.print("Incorrect " + name + "! The right answer is: " + answer + "\n");
        writer.flush();
    }

    //Get help
    public void getHardBoardMessage(){
        writer.println("");
        writer.println("You have reached the hard part of the game. You will now be tested on the hardest questions for maximum prizes :)");
        writer.flush();
    }

    //Board header
    public void getBoardHeader(){
        writer.println(board.getHeader());
    }
    //Board Category Left Align
    public void getBoardCategoryLeftAlignFormat(String cat1, String cat2, String cat3, String cat4, String cat5, String cat6){
        writer.format(board.getCategoryLeftAlignFormat(), cat1, cat2, cat3, cat4, cat5, cat6);
    }
    //Board Separator
    public void getBoardSeparator(){
        writer.println(board.getSeparator());
    }
    //Board Score Left
    public void getBoardScoreLeftAlignFormatRow(String score1, String score2, String score3, String score4, String score5, String score6){
        writer.format(board.getScoreLeftAlignFormatRow(), score1, score2, score3, score4, score5, score6);
    }
    //Board Footer
    public void getBoardFooter(){
        writer.println(board.getFooter());
        writer.flush();
    }


}
