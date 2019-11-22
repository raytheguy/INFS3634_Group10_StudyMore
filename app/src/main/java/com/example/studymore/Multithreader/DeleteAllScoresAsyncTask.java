package com.example.studymore.Multithreader;

import android.os.AsyncTask;

import com.example.studymore.ui.FlashCards.FlashCards;
import com.example.studymore.ui.FlashCards.FlashCardsDatabase;
import com.example.studymore.ui.Quiz.Score;
import com.example.studymore.ui.Quiz.ScoreDatabase;

public class DeleteAllScoresAsyncTask extends AsyncTask<Score, Integer, String> {
    private AsyncTaskDelegateString delegate;
    private ScoreDatabase database;

    public void setDelegate(AsyncTaskDelegateString delegate) {
        this.delegate = delegate;
    }

    public void setDatabase(ScoreDatabase database) {
        this.database = database;
    }

    @Override
    protected String doInBackground(Score... scores) {
        // delete all scores in the room database
        //it is provided by the recyclerView
        database.scoreDao().nukeTable();
        return "Deleted all Scores!";
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.handleTaskResult(result);
    }
}
