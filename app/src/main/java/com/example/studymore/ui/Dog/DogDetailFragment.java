package com.example.studymore.ui.Dog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.studymore.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

public class DogDetailFragment extends Fragment {
    //call the View things from the fragment
    TextView dogTitle;
    ImageView dogImage;
    TextView dogDescription;
    TextView dogWeight;
    TextView dogTemp;
    TextView dogOrigin;
    TextView dogLife;
    TextView dogHeight;
    TextView dogWikipedia;
    String dogId;
    Dog dogReceived;

    public DogDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //note this fragment need to use the Gson thing again because of the images it needs to obtain
        //it also needs to use the glide
        //detailView Fragment
        final View rootView = inflater.inflate(R.layout.fragment_dog_detail, container, false);

        //link the Views to their Resource Ids
        dogTitle = rootView.findViewById(R.id.dogTitleTextView);
        dogDescription = rootView.findViewById(R.id.dogBredForTextView);
        dogWeight = rootView.findViewById(R.id.dogWeightTextView);
        dogTemp = rootView.findViewById(R.id.dogTempTextView);
        dogOrigin = rootView.findViewById(R.id.dogOriginTextView);
        dogLife = rootView.findViewById(R.id.dogLifeTextView);
        dogHeight = rootView.findViewById(R.id.dogHeightTextView);
        dogWikipedia = rootView.findViewById(R.id.dogWikiLinkTextView);

        //have intent to let marker know that you need to scroll down for more information
        View view2 = getActivity().findViewById(R.id.fragment_cont_main);
        Snackbar.make(view2, "Scroll down for more information. Please wait for image to load", Snackbar.LENGTH_SHORT).show();

        //inflate the layout for this fragment
        //get the bundle of a dog's information
        Bundle bundle = getArguments();
        dogId = bundle.getString("dogId");

        //do the Gson Conversion to get the images by calling method below
        getImage(dogId, rootView);
        dogReceived = (Dog) bundle.getSerializable("dogThings");
        //set dog information
        dogTitle.setText(dogReceived.getName());
        dogDescription.setText(dogReceived.getBred_for());

        //some don't have weight, so ignore it
        if (dogReceived.getWeight() != null) {
            dogWeight.setText("Dog Weight: " + dogReceived.getWeight().getMetric() + " kg");
        }
        //some don't have height so ignore it
        if (dogReceived.getHeight() != null){
            dogHeight.setText("Dog Height: " + String.valueOf(dogReceived.getHeight().getMetric()) + " cm");
        }
        dogTemp.setText("Temperament: " + dogReceived.getTemperament());
        //some don't have height so set it as "Unknown"
        if (dogReceived.getOrigin() != null){
            dogOrigin.setText("Origin: " + dogReceived.getOrigin());
        }
        else {
            dogOrigin.setText("Origin: Unknown");
        }

        dogLife.setText("Dog Life: " + dogReceived.getLife_span());
        dogWikipedia.setText("Wikipedia Link: API is currently missing Wikipedia Links");
        dogWikipedia.setMovementMethod(LinkMovementMethod.getInstance());
        dogWikipedia.setOnClickListener(new View.OnClickListener() {

            //set on click listener on the wikipedia url to open browser (intent)
            @Override
            public void onClick(View view)
            {
                Intent browser= new Intent(Intent.ACTION_VIEW, Uri.parse(dogReceived.getName()));
                startActivity(browser);
            }

        });

        return rootView;
    }


    public void getImage(String dogId, final View view){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String getBackFromApi = response;
                Gson gson = new Gson();
                DogBreedsImage[] breedsImage = gson.fromJson(getBackFromApi, DogBreedsImage[].class);
                dogImage = view.findViewById(R.id.dogImage);

                //only glide is url is not null
                if (breedsImage.length != 0) {
                    Glide.with(view.getContext()).load(breedsImage[0].getUrl()).into(dogImage);
                }
            }


        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error! No image is available");
                //snackbar in case there is an error where the image could not be obtained
                Snackbar.make(view, "Note! Cannot obtain image for this dog!", Snackbar.LENGTH_LONG).show();
            }
        };

        //get dog id for image
        String url = "https://api.thedogapi.com/v1/images/search?breed_id="+dogId;
        RequestQueue requestQueueDetail = Volley.newRequestQueue(getActivity());
        StringRequest myRequestDetail = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueueDetail.add(myRequestDetail);

    }
}
