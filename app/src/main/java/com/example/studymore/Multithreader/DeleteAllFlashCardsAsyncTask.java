package com.example.studymore.Multithreader;

import android.os.AsyncTask;

import com.example.studymore.ui.FlashCards.FlashCards;
import com.example.studymore.ui.FlashCards.FlashCardsDatabase;

public class DeleteAllFlashCardsAsyncTask extends AsyncTask<FlashCards, Integer, String> {
    private AsyncTaskDelegateString delegate;
    private FlashCardsDatabase database;

    public void setDelegate(AsyncTaskDelegateString delegate) {
        this.delegate = delegate;
    }

    public void setDatabase(FlashCardsDatabase database) {
        this.database = database;
    }

    @Override
    protected String doInBackground(FlashCards... flashCards) {
        // delete all flash cards in the room database
        //it is provided by the recyclerView
        database.flashCardsDao().nukeTable();
        return "Deleted all flashcards!";
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.handleTaskResult(result);
    }
}
