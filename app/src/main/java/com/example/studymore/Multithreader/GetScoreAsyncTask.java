package com.example.studymore.Multithreader;

import android.os.AsyncTask;

import com.example.studymore.ui.Quiz.Score;
import com.example.studymore.ui.Quiz.ScoreDatabase;

import java.util.ArrayList;

public class GetScoreAsyncTask extends AsyncTask<Score, Integer, ArrayList<Score>> {
    private AsyncTaskDelegateListScore delegate;
    private ScoreDatabase database;
    private ArrayList<Score> scoreList;

    public void setDelegate(AsyncTaskDelegateListScore delegate) {
        this.delegate = delegate;
    }

    public void setDatabase(ScoreDatabase database) {
        this.database = database;
    }


    @Override
    protected ArrayList<Score> doInBackground(Score... scores) {
        //creates a new arraylist with all scores from database
        scoreList = new ArrayList<Score>(database.scoreDao().getScores());
        return scoreList;
    }

    @Override
    protected void onPostExecute(ArrayList<Score> result) {
        //returns the arraylist of cards back to the activity
        delegate.handleTaskResult(result);
    }
}
