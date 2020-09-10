package jeopardy.questionReader;


import jeopardy.classes.Question;
import jeopardy.classes.Question_board;
import jeopardy.game.Game;
import jeopardy.game.ProcessQuestionsForGame;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputStreamFromFile {

    public void injectInputStreamToReader(Scanner scanner, PrintWriter writer) throws IOException, ParseException {

        //We set the InputStream to our file in the resource folder
        java.io.InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("master_season1-35clean.tsv");

        // If the InputStream is null we display a message
        if(inputStream == null){

            System.out.println("The files InputStream is empty/null");


        } else {

            //We convert the inputStream through a converter "new InputStreamReader(inputStream)" and paas it to the constructor
            QuestionReader questionReader = new QuestionReader(new InputStreamReader(inputStream));

            ProcessQuestionsToArray processQuestionsToArray = new ProcessQuestionsToArray(questionReader);

            ArrayList<Question> processedList = processQuestionsToArray.theProcessor();

            ProcessQuestionsForGame processQuestionsForGameEasy = new ProcessQuestionsForGame(processedList, true);
            ArrayList<Question_board> gameQuestionsForEasyGame = processQuestionsForGameEasy.theProcessor();

            ProcessQuestionsForGame processQuestionsForGameHard = new ProcessQuestionsForGame(processedList, false);
            ArrayList<Question_board> gameQuestionsForHardGame = processQuestionsForGameHard.theProcessor();

            Game game = new Game(gameQuestionsForEasyGame, gameQuestionsForHardGame, scanner, writer);

            game.processor();
        }


    }

    public static void main(String[] args) throws IOException, ParseException {
        new InputStreamFromFile().injectInputStreamToReader(new Scanner(System.in), new PrintWriter(System.out));
    }

}
