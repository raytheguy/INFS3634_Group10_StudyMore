package com.example.studymore.ui.Quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class QuestionDatabase {

    public static Question getQuestionById(int questionID) {
        return questions.get(questionID);
    }

    /***
     * Return an ArrayList containing all the articles in the database.
     */
    public static ArrayList<Question> getAllActivity() {
        return new ArrayList<Question>((List) Arrays.asList(questions.values().toArray()));
    }


    // You can ignore everything below this for now.
    private static final HashMap<Integer, Question> questions = new HashMap<>();

    static {
        questions.put(1, new Question(
                1,
                "Why is a dog's nose wet?",
                "To help them smell",
                "Because they lick them",
                "From their tears",
                "To help them smell"
        ));
        questions.put(2, new Question(
                2,
                "There is one dog that doesn't bark, but yodels. What is the breed?",
                "Chow Chow",
                "Dobermann",
                "Basenji",
                "Basenji"
        ));questions.put(3, new Question(
                3,
                "What percentage of dalmations are deaf?",
                "20%",
                "25%",
                "30%",
                "30%"
        ));questions.put(4, new Question(
                4,
                "How many breeds of dogs have a black tongue?",
                "1",
                "2",
                "3",
                "2"
        ));questions.put(5, new Question(
                5,
                "How many eyelids do dogs have?",
                "1",
                "2",
                "3",
                "3"
        ));questions.put(6, new Question(
                6,
                "What taste can't cats detect?",
                "Bitterness",
                "Sweetness",
                "Sourness",
                "Sweetness"
        ));questions.put(7, new Question(
                7,
                "How old was the longest living cat?",
                "36",
                "37",
                "38",
                "38"
        ));questions.put(8, new Question(
                8,
                "How high can the average cat jump?",
                "7 feet",
                "8 feet",
                "9 feet",
                "9 feet"
        ));questions.put(9, new Question(
                9,
                "Where is the only body part cats sweat?",
                "Ears",
                "Paws",
                "Legs",
                "Paws"
        ));questions.put(10, new Question(
                10,
                "What is a group of cats called?",
                "Caravan",
                "Charm",
                "Clowder",
                "Clowder"
        ));
    }
}

