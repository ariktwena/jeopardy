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

        // Generate random integers in range 0 to hardQuestionsModList.size() - 5
        int random1 = rand.nextInt(list.size() - 5);
        int random2 = rand.nextInt(list.size() - 5);
        int random3 = rand.nextInt(list.size() - 5);
        int random4 = rand.nextInt(list.size() - 5);
        int random5 = rand.nextInt(list.size() - 5);
        int random6 = rand.nextInt(list.size() - 5);


        while( random1 % 5 != 0
                ||
                random2 % 5 != 0
                ||
                random3 % 5 != 0
                ||
                random4 % 5 != 0
                ||
                random5 % 5 != 0
                ||
                random6 % 5 != 0
                ||
                random1 == random2
                ||
                random1 == random3
                ||
                random1 == random4
                ||
                random1 == random5
                ||
                random1 == random6
                ||
                random2 == random3
                ||
                random2 == random4
                ||
                random2 == random5
                ||
                random2 == random6
                ||
                random3 == random4
                ||
                random3 == random5
                ||
                random3 == random6
                ||
                random4 == random5
                ||
                random4 == random6
                ||
                random5 == random6){

            random1 = rand.nextInt(list.size() - 5);
            random2 = rand.nextInt(list.size() - 5);
            random3 = rand.nextInt(list.size() - 5);
            random4 = rand.nextInt(list.size() - 5);
            random5 = rand.nextInt(list.size() - 5);
            random6 = rand.nextInt(list.size() - 5);

        }

//        System.out.println(random1);
//        System.out.println(random2);
//        System.out.println(random3);
//        System.out.println(random4);
//        System.out.println(random5);
//        System.out.println(random6);

        randomNumberList.add(random1);
        randomNumberList.add(random2);
        randomNumberList.add(random3);
        randomNumberList.add(random4);
        randomNumberList.add(random5);
        randomNumberList.add(random6);

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
