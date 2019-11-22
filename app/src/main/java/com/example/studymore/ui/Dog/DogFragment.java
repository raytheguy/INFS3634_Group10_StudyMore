package com.example.studymore.ui.Dog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.studymore.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class DogFragment extends Fragment {
    //create new arrayList to hold cat objects
    public List<Dog> dogToPut;
    //new layout manager
    RecyclerView.LayoutManager layoutManager;
    //String to receive the JSON
    String getBackFromApi;
    //RecyclerView to be used by all classes
    public RecyclerView recyclerView;
    //search button
    public Button search;
    //search textbox
    public EditText searchText;
    //default loader
    String initialLoad = "";

    //default constructor
    public DogFragment() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //create the recycling things
        //new instance of layout manager and setting layout manager
        final View v2 = inflater.inflate(R.layout.fragment_encyclo, container, false);
        recyclerView = v2.findViewById(R.id.encyclo_recycle);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //attach button to search layout
        search = v2.findViewById(R.id.searchButton);

        //link search box
        searchText = v2.findViewById(R.id.searchBox);

        //when fragment is open, load searches
        doSearch(initialLoad);

        //onClick listener for when the user clicks search
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View myView) {
                //to obtain what the user has entered
                String userSearchText = searchText.getText().toString();
                System.out.println("The user has entered: " + userSearchText);
                doSearch(userSearchText);
            }
        });

        return v2;

    }

    //when onclick of search is clicked, do the method below:
    public void doSearch(String userSearchText) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Your response has succeeded");
                //place response to a String of Jsons
                getBackFromApi = response;
                Gson gson = new Gson();
                Dog[] jsonDog = gson.fromJson(getBackFromApi, Dog[].class);
                //convert arrays to ArrayList
                dogToPut = Arrays.asList(jsonDog);

                if (dogToPut.size() == 0){
                    //have snackbar intent to let the user know no results found
                    Snackbar.make(getView(), "No Results Found!", Snackbar.LENGTH_LONG).show();
                }

                //pass the List to the RecyclerViewFragment
                DogRecycleViewAdapter adapterSearch = new DogRecycleViewAdapter(getActivity(), dogToPut);
                recyclerView.setAdapter(adapterSearch);
            }

        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("There was error" + error.toString());
                //have snackbar intent to let the user know
                Snackbar.make(getView(), "Connection Failed or No Animal Founds on Your Search!", Snackbar.LENGTH_LONG).show();
            }
        };

        //API Url
        String url;
        //if the user entered nothing, then use the search all url
        if (userSearchText.equals("")) {
            url = "https://api.thedogapi.com/v1/breeds?limit=1000";
        }
        else {
            url = "https://api.thedogapi.com/v1/breeds/search?q=" + userSearchText;
        }
        //Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        //String Request
        StringRequest myStringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        //Add String Request to Queue
        requestQueue.add(myStringRequest);

    }}