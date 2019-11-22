package com.example.studymore.ui.FlashCards;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studymore.FlashCardActivity;
import com.example.studymore.Multithreader.AsyncTaskDelegateString;
import com.example.studymore.Multithreader.InsertFlashCardsAsyncTask;
import com.example.studymore.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.UUID;

public class FlashCardsAdd extends AppCompatActivity implements AsyncTaskDelegateString {
    private List<FlashCards> bookToPut;
    private EditText frontText;
    private EditText backText;
    private Button addFlashBtn;
    private FlashCardsDatabase fcdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashcard_addnew_layout);

        //link layout to variables
        frontText = findViewById(R.id.flash_front_editText);
        backText = findViewById(R.id.flash_back_editText);
        addFlashBtn = findViewById(R.id.addFlashButton);

        //onClickListener
        addFlashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToDatabase(view);
            }
        });

    }

    //method to add to database
    public void addToDatabase(View view){
        //get what the user has entered
        fcdb = FlashCardsDatabase.getInstance(getApplicationContext());
        String front = frontText.getText().toString();
        String back = backText.getText().toString();
        System.out.println("The thing is" + back);
        UUID randomUUID = UUID.randomUUID();

        if (front.equals("") && back.equals("") || back.equals("") || front.equals("")){
            Snackbar.make(view, "Error! Make sure all fields are filled in!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else{
            //need to assign delegate
            //the task of adding flash cards will be given to another thread
            //z5161354
            InsertFlashCardsAsyncTask insertFlashCardsAsyncTask = new InsertFlashCardsAsyncTask();
            insertFlashCardsAsyncTask.setDatabase(fcdb);
            insertFlashCardsAsyncTask.setDelegate(FlashCardsAdd.this);
            insertFlashCardsAsyncTask.setFront(front);
            insertFlashCardsAsyncTask.setBack(back);
            insertFlashCardsAsyncTask.setRandomUUID(randomUUID);
            insertFlashCardsAsyncTask.execute();
            
            //if successful, display this message
            Snackbar.make(view, "Added Flashcard Successfully! Going back to Flashcards!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            //if successful, go back to flashcards  recycle view activity
            Intent intentPlusButton = new Intent(view.getContext(), FlashCardActivity.class);
            startActivity(intentPlusButton);
        }
    }
    @Override
    public void handleTaskResult(String result){
        result += "Deleted all flash cards!";
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    }
}
