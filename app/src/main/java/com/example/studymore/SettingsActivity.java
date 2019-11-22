package com.example.studymore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studymore.Multithreader.AsyncTaskDelegateString;
import com.example.studymore.Multithreader.DeleteAllFlashCardsAsyncTask;
import com.example.studymore.Multithreader.DeleteAllScoresAsyncTask;
import com.example.studymore.Multithreader.DeleteFlashCardsAsyncTask;
import com.example.studymore.ui.FlashCards.FlashCardsDatabase;
import com.example.studymore.ui.Quiz.ScoreDatabase;
import com.google.android.material.snackbar.Snackbar;

//activity is for settings such as reset quiz scores and delete flash cards
public class SettingsActivity extends AppCompatActivity implements AsyncTaskDelegateString {
    Button deleteFlashCards;
    Button deleteScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //link deleteFlashCards to layout
        deleteFlashCards = findViewById(R.id.flashCardDeleteBtn);
        deleteScores = findViewById(R.id.scoreDeleteBtn);

        //ToDO Delete Functions
        //onCLick listener for delete flashcards
        deleteFlashCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View myView) {
                Snackbar.make(myView, "Deleting Flash Cards. Please Wait...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                //create instance of database
                FlashCardsDatabase fcdb = FlashCardsDatabase.getInstance(getApplicationContext());
                //do the delegate thing to delete all flash cards from the Room Database
                DeleteAllFlashCardsAsyncTask deleteAllFlashCardsAsyncTask = new DeleteAllFlashCardsAsyncTask();
                deleteAllFlashCardsAsyncTask.setDatabase(fcdb);
                deleteAllFlashCardsAsyncTask.setDelegate(SettingsActivity.this);
                deleteAllFlashCardsAsyncTask.execute();
            }
        });

        //onClick listener for delete scores
        //:ToDo ON CLICK LISTENER TO DELETE SCORES
        deleteScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View myView) {
                Snackbar.make(myView, "Deleting Score. Please Wait...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                //create instance of database
                ScoreDatabase scoreDB = ScoreDatabase.getInstance(getApplicationContext());
                //do the delegate thing to delete all flash cards from the Room Database
                DeleteAllScoresAsyncTask deleteAllScoresAsyncTask = new DeleteAllScoresAsyncTask();
                deleteAllScoresAsyncTask.setDatabase(scoreDB);
                deleteAllScoresAsyncTask.setDelegate(SettingsActivity.this);
                deleteAllScoresAsyncTask.execute();
            }
        });
    }

    @Override
    public void handleTaskResult(String result) {
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    }
}
