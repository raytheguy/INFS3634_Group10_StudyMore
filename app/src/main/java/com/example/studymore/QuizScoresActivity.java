package com.example.studymore;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studymore.Multithreader.AsyncTaskDelegateListScore;
import com.example.studymore.Multithreader.GetScoreAsyncTask;
import com.example.studymore.ui.Quiz.Score;
import com.example.studymore.ui.Quiz.ScoreRecycleViewAdapter;
import com.example.studymore.ui.Quiz.ScoreDatabase;

import java.util.ArrayList;


public class QuizScoresActivity extends AppCompatActivity implements AsyncTaskDelegateListScore {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<Score> scores = new ArrayList<>();
    private ScoreDatabase database;
    SharedPreferences prefMain = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_scores);

        //open an information box if it is the first time opened
        prefMain = getApplicationContext().getSharedPreferences("com.example.studymore.scores", 0); // 0 - for private mode
        //if it is the first time, then add a generic flash card
        if (prefMain.getBoolean("firstRunScores", true)) {
            // Do first run stuff here then set 'firstrun' as false
            //Open the Information Box
            showCustomPopupMenu();
            // using the following line to edit/commit prefs (no longer first time, no longer add)
            prefMain.edit().putBoolean("firstRunScores", false).commit();
        }
        //if it is isn't the first time, then continue below (i.e. no popup box)

        recyclerView = findViewById(R.id.quizScore_rv);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Context context = getApplicationContext();
        database = ScoreDatabase.getInstance(context);

        GetScoreAsyncTask getFlashCardsAsyncTask = new GetScoreAsyncTask();
        getFlashCardsAsyncTask.setDatabase(database);
        getFlashCardsAsyncTask.setDelegate(QuizScoresActivity.this);
        getFlashCardsAsyncTask.execute();

    }

    public void handleTaskResult(ArrayList<Score> result) {
        scores = result;

        //set recycle view after results returned
        ScoreRecycleViewAdapter scoreAdapter = new ScoreRecycleViewAdapter();
        scoreAdapter.setData(scores);
        recyclerView.setAdapter(scoreAdapter);
    }

    private void showCustomPopupMenu() {
        //setup the alert builder
        //show a dialog box the first time the user opens the application
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //set the title of the dialog as 'first time'
        builder.setTitle("First Time");
        //set the first time message to welcome the user to StudyMore
        builder.setMessage("Your scores for quizzes will appear here after you have done a quiz!");
        //add a button to let the user continue, the button just closes the box
        builder.setPositiveButton("CONTINUE", null);
        //create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
