package com.example.studymore.Multithreader;

import android.os.AsyncTask;

import com.example.studymore.ui.FlashCards.FlashCards;
import com.example.studymore.ui.FlashCards.FlashCardsDatabase;

public class DeleteFlashCardsAsyncTask extends AsyncTask<FlashCards, Integer, String> {
    private AsyncTaskDelegateString delegate;
    private FlashCardsDatabase database;
    private String cardIdToPut;


    public void setDelegate(AsyncTaskDelegateString delegate) {
        this.delegate = delegate;
    }

    public void setDatabase(FlashCardsDatabase database) {
        this.database = database;
    }

    public void setCardIdToPut(String cardIdToPut) {
        this.cardIdToPut = cardIdToPut;
    }

    @Override
    protected String doInBackground(FlashCards... flashCards) {
        //delete the specific card by find card id
        //it is provider by the recycler view
        System.out.println("The card id is" + cardIdToPut);
        database.flashCardsDao().deleteByCardId(cardIdToPut);
        return "Deleted your flashcard!";
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.handleTaskResult(result);
    }
}
