package com.example.studymore.Multithreader;

import com.example.studymore.ui.Quiz.Score;

import java.util.ArrayList;

//AsyncTask Delegate for ArrayList of Score
public interface AsyncTaskDelegateListScore {
    void handleTaskResult(ArrayList<Score> result);
}