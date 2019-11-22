package com.example.studymore.Multithreader;

import android.os.AsyncTask;

import com.example.studymore.ui.Quiz.Score;
import com.example.studymore.ui.Quiz.ScoreDatabase;

import java.util.ArrayList;

public class InsertScoreAsyncTask extends AsyncTask<Score, Integer, String> {
    private AsyncTaskDelegateString delegate;
    private ScoreDatabase database;
    private int attemptNumber;
    private int score;

    public void setDelegate(AsyncTaskDelegateString delegate)  {
        this.delegate = delegate;
    }

    public void setDatabase(ScoreDatabase database) {
        this.database = database;
    }

    public void setAttemptNumber(int attemptNumber) {
        this.attemptNumber = attemptNumber;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    protected String doInBackground(Score... scores) {
        //get the attempt number
        attemptNumber = database.scoreDao().getScores().size() + 1;
        //insert score of what the user has entered
        //get from activity
        database.scoreDao().insert(new Score(attemptNumber, score));
        //return message to the user of what it is doing in the background
        return "Inserting Score ...";
    }

    @Override
    protected void onPostExecute(String result) {
        //tell the activity that the task has completed
        if (delegate != null) {
            delegate.handleTaskResult(result);
        }
    }
}
