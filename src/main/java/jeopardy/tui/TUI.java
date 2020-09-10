package jeopardy.tui;

import jeopardy.classes.Board;
import jeopardy.classes.Player;
import jeopardy.classes.Question_board;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
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

    /**
     *
     * Play Board - Category
     *
     */

    //Choose category
    public String playerCategoryInput(){
        writer.println("");
        writer.print("Choose a category > ");
        writer.flush();
        String input = scanner.nextLine();
        return input;
    }


    //Show chosen category
    public void getCategoryTitle(int index_start, ArrayList<Question_board> list){
        writer.println("");
        writer.println("You have chosen the Category '" + list.get(index_start).getCategory().getCategoryName() + "', here are the the available questions and possible score prize!");
        writer.flush();
    }


    /**
     *
     * Play Board - Questions
     *
     */

    //Show available questions
    public void availableQuestionsInCategory(int index_start, int index_end, ArrayList<Question_board> list){

        String[] choiseSpots = {"A", "B", "C", "D", "E"};
        int choiseCount = 0;

        for( int i = index_start ; i < index_end ; i++ ){
            if(list.get(i).getAnswered() == null){

                writer.println(choiseSpots[choiseCount] + ": " + list.get(i).getScore());
                writer.flush();

            } else {
                writer.println(choiseSpots[choiseCount] + ": Question has already been answered.");
                writer.flush();
            }
            choiseCount ++;
        }

    }

    //Choose question choice
    public String playerQuestionInputChoice(){
        writer.println("");
        writer.print("Choose a question > ");
        writer.flush();
        String input = scanner.nextLine();
        return input;
    }

    //Get the question
    public boolean getTheQuestion(ArrayList<Question_board> list, int index_start, int question_index){
        if(list.get(index_start + question_index).getAnswered() != null){
            writer.print("The question has already been played\n");
            writer.flush();
            return false;
        } else {
            writer.print(list.get(index_start + question_index).getQuestion());
            writer.flush();
            return true;
        }
    }

    //Choose question answer
    public String playerQuestionInputAnswer(){
        writer.println("");
        writer.print("What is you answer? > ");
        writer.flush();
        String input = scanner.nextLine();
        return input;
    }

    //Validate answer
    public void validateAnswer(ArrayList<Question_board> list, int index_start, int question_index, String answer, Player player){
        if(list.get(index_start + question_index).getAnswer().equalsIgnoreCase(answer)){

            //Message to the player
            writer.print("Correct " + player.getName() + "!!! You have earned " + list.get(index_start + question_index).getScore() + " points :)\n");
            writer.flush();

            //We add the score to the player
            player.setScore(player.getScore() + list.get(index_start + question_index).getScore());

            //We deactivet the question
            list.get(index_start + question_index).setAnswered("---");

        } else {
            //Message to the player
            writer.print("Incorrect " + player.getName() + "! The right answer is: " + list.get(index_start + question_index).getAnswer() + "\n");
            writer.flush();

            //We deactivet the question
            list.get(index_start + question_index).setAnswered("---");

        }
    }

    /**
     *
     * Play Board - Hard
     *
     */

    //Get help
    public void getHardBoardMessage(){
        writer.println("");
        writer.println("You have reached the hard part of the game. You will now be tested on the hardest questions for maximum prizes :)");
        writer.flush();
    }


    //public void createBoard(ArrayList<Question> list, int cat1, int cat2, int cat3, int cat4, int cat5, int cat6){
    public void createBoard(ArrayList<Question_board> list) {

        //We print the header of the board (The list has 30 spots. Every 5 spot is a now line of questions)
        writer.println(board.getHeader());
        writer.format(board.getCategoryLeftAlignFormat(),
                "A: " + list.get(0).getCategory().getCategoryName(),
                "B: " +list.get(5).getCategory().getCategoryName(),
                "C: " +list.get(10).getCategory().getCategoryName(),
                "D: " +list.get(15).getCategory().getCategoryName(),
                "E: " +list.get(20).getCategory().getCategoryName(),
                "F: " +list.get(25).getCategory().getCategoryName());

        //We print 5 rows of point on the board
        for( int i = 0 ; i < 5 ; i++ ){
            writer.println(board.getSeparator());
            writer.format(board.getScoreLeftAlignFormatRow(),
                    list.get(0 + i).getAnswered() == null ? list.get(0 + i).getScore() : list.get(0 + i).getAnswered(),
                    list.get(5 + i).getAnswered() == null ? list.get(5 + i).getScore() : list.get(5 + i).getAnswered(),
                    list.get(10 + i).getAnswered() == null ? list.get(10 + i).getScore() : list.get(10 + i).getAnswered(),
                    list.get(15 + i).getAnswered() == null ? list.get(15 + i).getScore() : list.get(15 + i).getAnswered(),
                    list.get(20 + i).getAnswered() == null ? list.get(20 + i).getScore() : list.get(20 + i).getAnswered(),
                    list.get(25 + i).getAnswered() == null ? list.get(25 + i).getScore() : list.get(25 + i).getAnswered());
        }
        writer.println(board.getFooter());
        writer.flush();



    }


}
