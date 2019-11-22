package com.example.studymore;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studymore.ActivityDB.ActivityAdapter;
import com.example.studymore.ActivityDB.ActivityDatabase;
import com.example.studymore.ui.Quiz.Score;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ImageButton settingsButton;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    SharedPreferences prefMain = null;
    //to create score recording
//    public static ArrayList<Score> scoreArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        scoreArrayList = new ArrayList<Score>();
        //get the view to be used by the dialog box
        View view = findViewById(R.id.mainConstraint);

        //open an information box if it is the first time opened
        prefMain = getApplicationContext().getSharedPreferences("com.example.studymore.main", 0); // 0 - for private mode
        //if it is the first time, then add a generic flash card
        if (prefMain.getBoolean("firstRunMain", true)) {
            // Do first run stuff here then set 'firstrun' as false
            //Open the Information Box
            showCustomPopupMenu();
            // using the following line to edit/commit prefs (no longer first time, no longer add)
            prefMain.edit().putBoolean("firstRunMain", false).commit();
        }
        //if it is isn't the first time, then continue below (i.e. no popup box)

        //set the settings button
        settingsButton = findViewById(R.id.settingsClog);

        //onClick Listener for settings button
        //onClickListener to get new Fact
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View myView) {
                Intent intentBtn = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intentBtn);
            }
        });

        //set the recycler view and associated data to list the functions of the application
        recyclerView = findViewById(R.id.rv_activity);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ActivityAdapter activityAdapter = new ActivityAdapter();

        activityAdapter.setData(ActivityDatabase.getAllActivity());
        recyclerView.setAdapter(activityAdapter);


    }

    private void showCustomPopupMenu() {
        //setup the alert builder
        //show a dialog box the first time the user opens the application
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //set the title of the dialog as 'first time'
        builder.setTitle("First Time");
        //set the first time message to welcome the user to StudyMore
        builder.setMessage("Welcome to StudyMore - Cats and Dogs! Learn about cats and dogs then test yourself!");
        //add a button to let the user continue, the button just closes the box
        builder.setPositiveButton("CONTINUE", null);
        //create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
