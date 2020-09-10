package jeopardy.game;

import jeopardy.classes.Player;
import jeopardy.classes.Question_board;
import jeopardy.tui.TUI;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private final ArrayList<Question_board> easyQuestionsList;
    private final ArrayList<Question_board> hardQuestionsList;
    private final TUI tui;
    private Player player;
    private boolean gameStart;
    private boolean exitGame;
    private int numberOfAnswers;


    public Game(ArrayList<Question_board> easyQuestionsList, ArrayList<Question_board> hardQuestionsList, Scanner scanner, PrintWriter writer) {
        this.easyQuestionsList = easyQuestionsList;
        this.hardQuestionsList = hardQuestionsList;
        this.tui = new TUI(scanner, writer);
        //this.tui = new TUI(new Scanner(System.in), new PrintWriter(System.out));
        this.player = null;
        this.gameStart = false;
        this.exitGame = false;
        numberOfAnswers = 0;
    }

    public void processor(){

        createPlayer();

        tui.weelcomeMessage(player.getName());

        startGame();

    }


    //Create player
    public Player createPlayer(){
        //Create player name
        player = new Player(tui.getPlayerName());
        //Return player
        return player;
    }

    public void startGame(){

        //Player start game choice
        String playerStartChoice = tui.playerInput().toLowerCase();

        //If the player write "play" the game starts
        if(playerStartChoice.equalsIgnoreCase("play")){

            //Game starts and the right board is chosen
            whatBoardToPlay();

        } else {

            //If the player answer doesn't match the answerIndex, we got to the switch statements
            getSwitch(playerStartChoice);

            if(!exitGame){
                //Go back to category choice via "redirect
                redirectAfterSwitch();
            }

        }
    }

    public void whatBoardToPlay(){
        if (numberOfAnswers < 3) {
            playTheGame(easyQuestionsList);
        } else {

            if(numberOfAnswers == 3){
                tui.getHardBoardMessage();
            }

            playTheGame(hardQuestionsList);
        }
    }


    //Show the board categories and selector to the player
    public void playTheGame(ArrayList<Question_board> list){

        int index_start, index_end;

        //Set the gameStart to true so the player can be redirected
        gameStart = true;

        //Load the current status of the board
        tui.createBoard(list);

        //A list of the answers possibilities for the categories/questions
        List<String> answerIndex = List.of("a", "b", "c", "d", "e", "f");

        //Player category choice
        String playerCategoryChoice = tui.playerCategoryInput().toLowerCase();

        //We check if the players input is on the list and return the index number. Else it returns -1
        int input_index_categoty = answerIndex.lastIndexOf(playerCategoryChoice);

        //If the input_index is greater den -1, list contains the player answer/input
        if(input_index_categoty >= 0){

            //We calculate the index_start: 0, 5, 10, 15, 20, 25. Example third element with index 2 => 10
            index_start = 5 * input_index_categoty;

            //We calculate the index_start: 5, 10, 15, 20, 25, 30. Example third element with index 2 => 15
            index_end = 5 + (5 * input_index_categoty);

            //Get the category title to display
            tui.getCategoryTitle(index_start, list);

            //Get the available questions and non if they are answered
            tui.availableQuestionsInCategory(index_start, index_end, list);

            //Player Question choice
            String playerQuestionChoice = tui.playerQuestionInputChoice().toLowerCase();

            //We check if the players input is on the list and return the index number. Else it returns -1
            int input_index_question = answerIndex.lastIndexOf(playerQuestionChoice);

            //If the input_index is greater den -1, list contains the player answer/input
            if(input_index_question >= 0){

                //input_index_question will be 0, 1, 2, 3 or 4
                int question_index = input_index_question;

                //We check if the question is NOT answered og else the methode will display a message to the player
                if(tui.getTheQuestion(list, index_start, question_index)){

                    //Player answer
                    String answer = tui.playerQuestionInputAnswer();

                    //We check if the players answer matches the right answer
                    tui.validateAnswer(list, index_start, question_index, answer, player);

                    //We update the number of questions that have been played
                    numberOfAnswers += 1;
                }

                //We restart the categories by showing the "loader"
                tui.loaderLong();

                //Go back to category choice via "redirect
                redirectAfterSwitch();

            } else {

                //If the player answer doesn't match the answerIndex, we got to the switch statements
                getSwitch(playerQuestionChoice);

                //Go back to category choice via "redirect
                redirectAfterSwitch();

            }
        } else {

            //If the player answer doesn't match the answerIndex, we got to the switch statements
            getSwitch(playerCategoryChoice);

            //Go back to category choice via "redirect
            redirectAfterSwitch();

        }


    }


    public void getSwitch(String playerInput){
        switch (playerInput) {
            case "help":
                tui.loader();
                tui.getHelpGame();
                break;
            case "score":
                tui.getScore(player.getName(), player.getScore());
                break;
            case "board":
                tui.getBoardStatus(numberOfAnswers);
                break;
            case "back":
                tui.loader();
                whatBoardToPlay();
                break;
            case "exit":
                tui.exitGame(player.getName());
                exitGame = true;
                break;
            default:
                tui.gameDefaultMessage();
        }
    }

    public void redirectAfterSwitch(){
        //Checks if the game has started though the gameStart boolean
        if(gameStart && !exitGame){

            //Redirects to the category selector
            whatBoardToPlay();

        } else if(!gameStart && !exitGame){

            //Redirects to the start game screen where you can write "play"
            startGame();

        }
    }








}
