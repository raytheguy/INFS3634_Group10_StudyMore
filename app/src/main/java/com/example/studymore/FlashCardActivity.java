package com.example.studymore;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.studymore.Multithreader.AsyncTaskDelegateListFC;
import com.example.studymore.Multithreader.GetFlashCardsAsyncTask;
import com.example.studymore.Multithreader.InsertFlashCardsAsyncTask;
import com.example.studymore.ui.FlashCards.FlashCards;
import com.example.studymore.ui.FlashCards.FlashCardsAdd;
import com.example.studymore.ui.FlashCards.FlashCardsDatabase;
import com.example.studymore.ui.FlashCards.FlashCardsRecycleViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.UUID;

public class FlashCardActivity extends AppCompatActivity implements AsyncTaskDelegateListFC {

    ArrayList<FlashCards> newFlash = new ArrayList<>();
    private FlashCardsDatabase database;
    private TextView noOfFlashCards;
    private RecyclerView recyclerView;
    private ImageView flashCardRating;
    private ImageButton flashCardQuestionButton;
    //layout manager
    RecyclerView.LayoutManager layoutManager;
    SharedPreferences pref = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card);

        //horizontal recycle view source: https://www.youtube.com/watch?v=94rCjYxvzEE&t=1102s
        //create part of RecyclerView
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView = findViewById(R.id.recyclerViewFlash);
        recyclerView.setLayoutManager(layoutManager);
        flashCardRating = findViewById(R.id.flashCardRatingImageView);
        flashCardQuestionButton = findViewById(R.id.flashCardQuestionButton);

        //create Database
        Context context = getApplicationContext();
        database = FlashCardsDatabase.getInstance(context);

        //add sample flash cards if it is the first time opened
        pref = getApplicationContext().getSharedPreferences("com.example.studymore", 0); // 0 - for private mode

        //if it is the first time, then add a generic flash card
        if (pref.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            //Do the AsyncTask Thing
            addSample();
            showCustomPopupMenu();
            // using the following line to edit/commit prefs (no longer first time, no longer add)
            pref.edit().putBoolean("firstrun", false).commit();
        }
        //if it is isn't the first time, then continue below

        //do the Multithreader thing
        GetFlashCardsAsyncTask getFlashCardsAsyncTask = new GetFlashCardsAsyncTask();
        getFlashCardsAsyncTask.setDatabase(database);
        getFlashCardsAsyncTask.setDelegate(FlashCardActivity.this);
        getFlashCardsAsyncTask.execute();

        //no longer used as no longer running on main thread!!!!!!
//        newFlash = new ArrayList<FlashCards>(database.flashCardsDao().getCards());

        //set size of FlashCard size and set to textView
        noOfFlashCards = findViewById(R.id.noOfFlashCardsTextView);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPlusButton = new Intent(view.getContext(), FlashCardsAdd.class);
                startActivity(intentPlusButton);
            }
        });

        //onClick listener for question mark button
        flashCardQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Bronze = Less than 5 Flash Cards | Silver = 5-19 Flash Cards | Gold = Over 20 Flash Cards!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    //add sample flash cards on first run
    //these flash cards can be deleted by the user permanently
    public void addSample() {
        InsertFlashCardsAsyncTask insertGenericFlashCardsAsyncTask = new InsertFlashCardsAsyncTask();
        insertGenericFlashCardsAsyncTask.setDatabase(database);
//            insertFlashCardsAsyncTask.setDelegate(FlashCardsAdd.this);
        ArrayList<FlashCards> firstTimeAdd = new ArrayList<>();
        firstTimeAdd.add(new FlashCards(UUID.randomUUID().toString(),
                "How To Use", "Scroll Sideways to view flash cards. You can add your own using the plus button!"));
        firstTimeAdd.add(new FlashCards(UUID.randomUUID().toString(),
                "Dogs and Chocolate", "Giving dogs chocolate is actually poisonous! Never do it!"));
        firstTimeAdd.add(new FlashCards(UUID.randomUUID().toString(),
                "Cat Cleaning", "Cats hate when you wash them! Consider using a wet cloth instead!"));
        firstTimeAdd.add(new FlashCards(UUID.randomUUID().toString(),
                "Catnip and Cats", "Most cats do not respond to catnip!"));
        firstTimeAdd.add(new FlashCards(UUID.randomUUID().toString(), "Playing music to cats",
                "Cats do not like music according to a University Research. Try not to play them music!"));
        //use the AsyncTask (add in another thread)
        insertGenericFlashCardsAsyncTask.setFirstTimeAdd(firstTimeAdd);
        insertGenericFlashCardsAsyncTask.execute();
    }

    @Override
    public void handleTaskResult(ArrayList<FlashCards> result) {
        newFlash = result;
        //set the size of flash cards after size is gotten back
        noOfFlashCards.setText("Number of Flash Cards: " + newFlash.size());

        //if less than 5 then bronze
        if (newFlash.size() < 5) {
            flashCardRating.setImageResource(R.drawable.flashcard_bronze);
        }
        //if over 5 then silver
        else if (newFlash.size() >= 5 && newFlash.size() < 20) {
            flashCardRating.setImageResource(R.drawable.flashcard_silver);
        }
        //if over 20 then gold
        else if (newFlash.size() >= 20) {
            flashCardRating.setImageResource(R.drawable.flashcard_gold);
        }
        //if for some reason, size cannot be get or there is error with getting amount then:
        else {
            flashCardRating.setImageResource(R.drawable.flashcard_bronze);
        }

        //set recycle view after results returned
        FlashCardsRecycleViewAdapter adapter = new FlashCardsRecycleViewAdapter(this, newFlash);
        recyclerView.setAdapter(adapter);
    }

    //for first time usage
    private void showCustomPopupMenu() {
        //setup the alert builder
        //show a dialog box the first time the user opens the application
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //set the title of the dialog as 'first time'
        builder.setTitle("First Time");
        //set the first time message to welcome the user to StudyMore
        builder.setMessage("Here are the Flash Cards! Please tap on one to show the other side! You can add your own or add them from the Facts feature!");
        //add a button to let the user continue, the button just closes the box
        builder.setPositiveButton("CONTINUE", null);
        //create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
