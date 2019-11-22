package com.example.studymore;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.studymore.Model.Facts;
import com.example.studymore.Multithreader.AsyncTaskDelegateString;
import com.example.studymore.Multithreader.InsertFlashCardsAsyncTask;
import com.example.studymore.ui.FlashCards.FlashCardsAdd;
import com.example.studymore.ui.FlashCards.FlashCardsDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

public class FactsActivity extends AppCompatActivity implements AsyncTaskDelegateString {

    private TextView fact;
    private Button factButton;
    private ImageView factImage;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private int catOrDog;
    //integer for application to know whether dog or cat is selected
    //cat = 0; dog = 1
    private int catOrDogNew = 0;
    String url;
    //database to store fact to clash cards
    private FlashCardsDatabase fcdb;
    //first time popup
    RecyclerView.LayoutManager layoutManager;
    SharedPreferences pref = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);
        //add sample flash cards if it is the first time opened
        pref = getApplicationContext().getSharedPreferences("com.example.studymore.facts", 0); // 0 - for private mode

        //if it is the first time, then add a generic flash card
        if (pref.getBoolean("firstrunFacts", true)) {
            // Do first run stuff here then set 'firstrunFacts' as false
            showCustomPopupMenu();
            // using the following line to edit/commit prefs (no longer first time, no longer add)
            pref.edit().putBoolean("firstrunFacts", false).commit();
        }
        //if it is isn't the first time, then continue below

        //cycle through images using Glide completed
        //set up the layout
        fact = findViewById(R.id.factView);
        factButton = findViewById(R.id.newFactButton);
        factImage = findViewById(R.id.factImage);
        radioGroup = findViewById(R.id.factRadioGroup);

        fact.setText("Loading Fact... Please Wait...");

        //run getNewFact() method when it is loaded
        //url: http://meowfacts.herokuapp.com/
        getNewFact();
        getImage(0);

        //onClickListener to get new Fact
        factButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View myView) {
                getNewFact();
            }
        });

    }

    //this method is to run the query again
    public void getNewFact() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //place response to a String of Jsons
                Gson gson = new Gson();
                final Facts jsonCatFact = gson.fromJson(response, Facts.class);
                final String factAsString;
                Snackbar.make(getWindow().getDecorView().getRootView(), "Loading...", Snackbar.LENGTH_SHORT)
                        .setAction("Search on Google!", null).show();

                if (catOrDogNew == 0) {
                    url = "https://meowfacts.herokuapp.com/";
                    fact.setText(jsonCatFact.getData());
                    factAsString = jsonCatFact.getData();
                    getImage(catOrDogNew);
                } else {
                    fact.setText(jsonCatFact.getFacts());
                    factAsString = jsonCatFact.getFacts();
                    getImage(catOrDogNew);
                }

                System.out.println("I AM BEING CLICKED");
                //let the user search the fact when they click on the FAB
                FloatingActionButton fab = findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar.make(view, factAsString, Snackbar.LENGTH_LONG)
                                .setAction("Search on Google!", null).show();
                        Intent textIntent = new Intent(Intent.ACTION_SEND);
                        textIntent.setType("text/plain");
                        //if it is a cat, then use data
                        if (catOrDogNew == 0){
                        textIntent.putExtra(Intent.EXTRA_TEXT, jsonCatFact.getData());}
                        //if it is a dog, then use facts
                        else {
                            textIntent.putExtra(Intent.EXTRA_TEXT, jsonCatFact.getFacts());
                        }
                        startActivity(textIntent);

                    }
                });

                //save fact as flash card when save is clicked
                FloatingActionButton fabSave = findViewById(R.id.fabSave);
                fabSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addToFlashCards(jsonCatFact);
                    }
                });
            }

        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("There was error" + error.toString());
                //have snackbar intent to let the user know
                Snackbar.make(getWindow().getDecorView().getRootView(), "Connection Failed!", Snackbar.LENGTH_LONG).show();
            }
        };

        //url to get back json of the api
        //testing radio button
        catOrDog = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(catOrDog);
        System.out.println("The Button text is " + radioButton.getText());
        if (radioButton.getText().equals("Cat")) {
            catOrDogNew = 0;
        } else {
            catOrDogNew = 1;
        }
        System.out.println("Cat Or Dog" + catOrDog);
        //if radio button is in cat, use cat api else use dog
        //source: https://cat-fact.herokuapp.com/ and https://dog-api.kinduff.com/ and https://some-random-api.ml
        if (catOrDogNew == 0) {
            url = "https://meowfacts.herokuapp.com/";
        } else {
            url = "https://dog-api.kinduff.com/api/facts";
        }
        //create Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest myStringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        //add string request to request queue
        requestQueue.add(myStringRequest);

    }

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
    }

    public void getImage(int catOrDog) {
        Response.Listener<String> responseListenerImg = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                final Facts jsonImage = gson.fromJson(response, Facts.class);
                final String imageUrl = jsonImage.getLink();
                Glide.with(getApplicationContext()).load(imageUrl).into(factImage);
            }
        };

        Response.ErrorListener errorListenerImg = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                factImage.setImageResource(R.drawable.catsdogsfacts);
            }
        };

        //create Request Queue
        System.out.println("Car or Dog is: " + catOrDog);
        if (this.catOrDogNew == 0) {
            url = "https://some-random-api.ml/img/cat";
        } else {
            url = "https://some-random-api.ml/img/dog";
        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest myStringRequest = new StringRequest(Request.Method.GET, url, responseListenerImg, errorListenerImg);
        //add string request to request queue
        requestQueue.add(myStringRequest);
    }

    //method is to add the fact to flash cards activity (as a save feature)
    public void addToFlashCards(Facts jsonCatFact) {
        FlashCardsDatabase fcdb = FlashCardsDatabase.getInstance(getApplicationContext());
        InsertFlashCardsAsyncTask insertFlashCardsAsyncTask = new InsertFlashCardsAsyncTask();
        insertFlashCardsAsyncTask.setDatabase(fcdb);
        insertFlashCardsAsyncTask.setDelegate(FactsActivity.this);

        String catDogFactFront;
        //if it is cat, then set the title as cat fact and add the fact as the back else, set the dog
        if (catOrDogNew == 0) {
            catDogFactFront = "Cat Fact";
            insertFlashCardsAsyncTask.setFront(catDogFactFront);
            insertFlashCardsAsyncTask.setBack(jsonCatFact.getData());

        } else {
            catDogFactFront = "Dog Fact";
            insertFlashCardsAsyncTask.setFront(catDogFactFront);
            insertFlashCardsAsyncTask.setBack(jsonCatFact.getFacts());
        }
        insertFlashCardsAsyncTask.setRandomUUID(UUID.randomUUID());
        insertFlashCardsAsyncTask.execute();
    }

    @Override
    public void handleTaskResult(String result) {
        result = "Added the fact to Flash Cards!";
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    }

    //for first time usage
    //source: https://stackoverflow.com/questions/27642093/how-to-create-a-do-you-want-to-continue-alert-box
    private void showCustomPopupMenu() {
        //setup the alert builder
        //show a dialog box the first time the user opens the application
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //set the title of the dialog as 'first time'
        builder.setTitle("First Time");
        //set the first time message to welcome the user to StudyMore
        builder.setMessage("Here are facts about cats and dogs. Please select one you like! " +
                "Press on the Save button to save as a flash card and Message button to share the fact to your friends!");
        //add a button to let the user continue, the button just closes the box
        builder.setPositiveButton("CONTINUE", null);
        //create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
