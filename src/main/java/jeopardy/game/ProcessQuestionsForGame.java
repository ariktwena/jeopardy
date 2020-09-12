package jeopardy.game;

import jeopardy.classes.Question;
import jeopardy.classes.Question_board;

import java.util.ArrayList;
import java.util.Random;

public class ProcessQuestionsForGame {

    private final ArrayList<Question> questionList;
    private final ArrayList<Question_board> questionBoardList;
    private final boolean easyGame;


    public ProcessQuestionsForGame(ArrayList<Question> questionList, boolean easyGame) {
        this.questionList = questionList;
        this.easyGame = easyGame;
        this.questionBoardList = new ArrayList<>();
    }

    public ArrayList<Question_board> theProcessor(){

        ArrayList<Question> workingQuestionList;
        ArrayList<Integer> workingNumberList;

        if(easyGame){

            workingQuestionList = matchEasyCategories();

        } else {

            workingQuestionList = matchHardCategories();

        }

        workingNumberList = generateRandomNumbers(workingQuestionList);
        return extractBoardQuestions(workingQuestionList, workingNumberList);

    }


    //We make an array list of the easy questions
    public ArrayList<Question> matchEasyCategories() {

        //We make a altered list to put the values in
        ArrayList<Question> alteredList = new ArrayList<Question>();

        for( int i = 0 ; i < questionList.size()-4 ; i++ ) {

            if(questionList.get(i).getScore() == 100
                    &&
                    questionList.get(i + 1).getScore() == 200
                    &&
                    questionList.get(i + 2).getScore() == 300
                    &&
                    questionList.get(i + 3).getScore() == 400
                    &&
                    questionList.get(i + 4).getScore() == 500){

                //We add the category entries to a new array
                alteredList.add(questionList.get(i));
                alteredList.add(questionList.get(i + 1));
                alteredList.add(questionList.get(i + 2));
                alteredList.add(questionList.get(i + 3));
                alteredList.add(questionList.get(i + 4));

                //We skip the entries and continue
                i += 4;

            }

        }

        return alteredList;

    }

    //We make an array list of the hard questions
    public ArrayList<Question> matchHardCategories() {

        //We make a altered list to put the values in
        ArrayList<Question> alteredList = new ArrayList<Question>();

        for( int i = 0 ; i < questionList.size()-4 ; i++ ) {

            if(questionList.get(i).getScore() == 200
                    &&
                    questionList.get(i + 1).getScore() == 400
                    &&
                    questionList.get(i + 2).getScore() == 600
                    &&
                    questionList.get(i + 3).getScore() == 800
                    &&
                    questionList.get(i + 4).getScore() == 1000){

                //We add the category entries to a new array
                alteredList.add(questionList.get(i));
                alteredList.add(questionList.get(i + 1));
                alteredList.add(questionList.get(i + 2));
                alteredList.add(questionList.get(i + 3));
                alteredList.add(questionList.get(i + 4));

                //We skip the entries and continue
                i += 4;

            }

        }

        return alteredList;

    }


    public ArrayList<Integer> generateRandomNumbers(ArrayList<Question> list){

        //Create a Integer list
        ArrayList<Integer> randomNumberList = new ArrayList<>();

        // create instance of Random class
        Random rand = new Random();

        //Make random number variable
        int randomNumber;

        for(int i = 0 ; i < 6 ; i++){

            // Generate random integers in range 0 to hardQuestionsModList.size() - 5
            randomNumber = rand.nextInt(list.size() - 5);

            //If the list does not contains the number the result will be -1, and % 5 gives numbers that are divided in 5
            while(randomNumberList.lastIndexOf(randomNumber) != -1 || randomNumber % 5 != 0){

                // Generate new random number
                randomNumber = rand.nextInt(list.size() - 5);
            }

            //Add the number to the array
            randomNumberList.add(randomNumber);
        }

//        for(int i = 0 ; i < randomNumberList.size() ; i++){
//            System.out.println(randomNumberList.get(i).toString());
//        }

        return randomNumberList;
    }


    public ArrayList<Question_board> extractBoardQuestions(ArrayList<Question> list, ArrayList<Integer> randomNumbers){

        //We go to the random number that is the index
        for(int i = 0 ; i < randomNumbers.size() ; i++){

            //We extract the index and the next 4 questions and make a Question_board from them
            for(int j = 0 ; j < 5 ; j++){
                questionBoardList.add(new Question_board(
                        list.get(randomNumbers.get(i) + j).getId(),
                        list.get(randomNumbers.get(i) + j).getScore(),
                        list.get(randomNumbers.get(i) + j).getCategory(),
                        list.get(randomNumbers.get(i) + j).getQuestion(),
                        list.get(randomNumbers.get(i) + j).getAnswer()));
            }

        }

//        for( int i = 0 ; i < questionBoardList.size() ; i++ ){
//            if(questionBoardList.get(i).getAnswered() == null){
//
//                System.out.println(questionBoardList.get(i).toString());
//
//            }
//        }

        return questionBoardList;

    }

}
