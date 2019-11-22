package com.example.studymore.ui.Cat;

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

public class CatDetailFragment extends Fragment {
    //call the View things from the fragment
    TextView catTitle;
    ImageView catImage;
    TextView catDescription;
    TextView catWeight;
    TextView catTemp;
    TextView catOrigin;
    TextView catLife;
    TextView catDog;
    TextView catWikipedia;
    String catId;
    Cat catReceived;

    public CatDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //note this fragment need to use the Gson thing again because of the images it needs to obtain
        //it also needs to use the glide to get the associated images
        //detailView Fragment
        final View rootView = inflater.inflate(R.layout.fragment_cat_detail, container, false);

        //link the Views to their Resource Ids
        catTitle = rootView.findViewById(R.id.catTitleTextView);
        catDescription = rootView.findViewById(R.id.catDescTextView);
        catWeight = rootView.findViewById(R.id.catWeightTextView);
        catTemp = rootView.findViewById(R.id.catTempTextView);
        catOrigin = rootView.findViewById(R.id.catTempTextView);
        catLife = rootView.findViewById(R.id.catLifeTextView);
        catDog = rootView.findViewById(R.id.catDogTextView);
        catWikipedia = rootView.findViewById(R.id.catWikiLinkTextView);

        //have intent to let marker know that you need to scroll down for more information
        View view2 = getActivity().findViewById(R.id.fragment_cont_main);
        Snackbar.make(view2, "Scroll down for more information. Please wait for image to load", Snackbar.LENGTH_SHORT).show();

        //inflate the layout for this fragment
        //get the bundle of a cat's information
        Bundle bundle = getArguments();
        catId = bundle.getString("catId");

            //do the Gson Conversion to get the images by calling method below
            getImage(catId, rootView);
            catReceived = (Cat) bundle.getSerializable("catThings");
            //set cat information
            catTitle.setText(catReceived.getName());
            catDescription.setText(catReceived.getDescription());

            //some don't have weight, so ignore it
            if (catReceived.getWeight() != null) {
                catWeight.setText("Cat Weight: " + catReceived.getWeight().getMetric() + " kg");
            }
            catTemp.setText("Temperament: " + catReceived.getTemperament());
            catOrigin.setText("Origin: " + catReceived.getOrigin());
            catLife.setText("Cat Life: " + catReceived.getLife_span() + " years");
            catDog.setText("Dog Friendly Level: " + String.valueOf(catReceived.getDog_friendly()) + "/5");
            catWikipedia.setText(catReceived.getWikipedia_url());
            catWikipedia.setMovementMethod(LinkMovementMethod.getInstance());
            catWikipedia.setOnClickListener(new View.OnClickListener() {

                //set on click listener on the wikipedia url to open browser (intent)
                @Override
                public void onClick(View view)
                {
                    Intent browser= new Intent(Intent.ACTION_VIEW, Uri.parse(catReceived.getWikipedia_url()));
                    startActivity(browser);
                }

            });

        return rootView;
        }


    public void getImage(String catId, final View view){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String getBackFromApi = response;
                Gson gson = new Gson();
                CatBreedsImage[] breedsImage = gson.fromJson(getBackFromApi, CatBreedsImage[].class);
                catImage = view.findViewById(R.id.catImage);

                //only glide is url is not null
                if (breedsImage.length != 0) {
                    Glide.with(view.getContext()).load(breedsImage[0].getUrl()).into(catImage);
                }
            }


        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error! No image is available");
                //a message in case there was an error getting the images
                Snackbar.make(view, "Note! Cannot obtain image for this cat!", Snackbar.LENGTH_LONG).show();
            }
        };

        //get cat id for image
        String url = "https://api.thecatapi.com/v1/images/search?breed_id="+catId;
        RequestQueue requestQueueDetail = Volley.newRequestQueue(getActivity());
        StringRequest myRequestDetail = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueueDetail.add(myRequestDetail);

    }

}
