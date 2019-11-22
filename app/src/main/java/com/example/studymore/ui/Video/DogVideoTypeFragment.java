package com.example.studymore.ui.Video;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.studymore.R;

import java.util.Vector;

public class DogVideoTypeFragment extends Fragment {
    Vector<YoutubeVideos> youtubeVideos = new Vector<>();
    Context mContext;
    ConstraintLayout videoBg;
    ConstraintLayout videoBox1;
    ConstraintLayout videoBox2;
    ConstraintLayout videoBox3;
    ConstraintLayout videoBox4;
    ImageView videoImage1;
    ImageView videoImage2;
    ImageView videoImage3;
    ImageView videoImage4;

    public DogVideoTypeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v2 = inflater.inflate(R.layout.fragment_videocategory, container, false);
        //match Constraint Layout to the boxes
        videoBox1 = v2.findViewById(R.id.videoConstraintLayout1);
        videoBox2 = v2.findViewById(R.id.videoConstraintLayout2);
        videoBox3 = v2.findViewById(R.id.videoConstraintLayout3);
        videoBox4 = v2.findViewById(R.id.videoConstraintLayout4);

        //set on click listeners for each
        //match Constraint Layout to the boxes
        videoBox1 = v2.findViewById(R.id.videoConstraintLayout1);
        videoBox2 = v2.findViewById(R.id.videoConstraintLayout2);
        videoBox3 = v2.findViewById(R.id.videoConstraintLayout3);
        videoBox4 = v2.findViewById(R.id.videoConstraintLayout4);
        //set the images
        videoImage1 = v2.findViewById(R.id.videoBox1ImageView);
        videoImage1.setImageResource(R.drawable.video_petcare2_smaller);
        videoImage2 = v2.findViewById(R.id.videoBox2ImageView);
        videoImage2.setImageResource(R.drawable.video_health);
        videoImage3 = v2.findViewById(R.id.videoBox3ImageView);
        videoImage3.setImageResource(R.drawable.video_training);
        videoImage4 = v2.findViewById(R.id.videoBox4ImageView);
        videoImage4.setImageResource(R.drawable.video_socialise_smaller);

        //get context
        mContext = getActivity();
        //set background
        videoBg = v2.findViewById(R.id.videoBgConstraintLayout);
        videoBg.setBackgroundResource(R.drawable.video_dogfragment);


        //when box 1 is clicked
        videoBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View myView) {
                videoBox1Click();

            }
        });

        //when box 2 is clicked
        videoBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View myView) {
                videoBox2Click();

            }
        });

        //when box 3 is clicked
        videoBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View myView) {
                videoBox3Click();

            }
        });

        //when box 4 is clicked
        videoBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View myView) {
                videoBox4Click();

            }
        });
        return v2;
    }

    //box 1 method
    public void videoBox1Click() {
        //remove existing items in list
        youtubeVideos.clear();
        //put the videos in
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ngl1QsdTrqc\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/mCqYeh25x2E\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/pxzU-mtx8og\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/MCvrxJgy8r0\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/peUVLEUj-AM\" frameborder=\"0\" allowfullscreen></iframe>"));

        //get the dog id to pass over
        CatVideoFragment catVideoFragment = new CatVideoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("catVideos", youtubeVideos);
        catVideoFragment.setArguments(bundle);

        AppCompatActivity activity = (AppCompatActivity) mContext;
        //check if it is the right container z5161354
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont_main_video, catVideoFragment).addToBackStack(null).commit();
    }

    //box 2 method
    public void videoBox2Click() {
        //remove existing items in list
        youtubeVideos.clear();
        //put the videos in
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/XZ8M6o46KO4\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/1j4ZGfAJ2Jk\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/kU3Q5kVKRDE\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/lAB1ewmGWHo\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/pcpL2KSwCa0\" frameborder=\"0\" allowfullscreen></iframe>"));

        //get the dog id to pass over
        CatVideoFragment catVideoFragment = new CatVideoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("catVideos", youtubeVideos);
        catVideoFragment.setArguments(bundle);

        AppCompatActivity activity = (AppCompatActivity) mContext;
        //check if it is the right container z5161354
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont_main_video, catVideoFragment).addToBackStack(null).commit();
    }

    //box 3 method
    public void videoBox3Click() {
        //remove existing items in list
        youtubeVideos.clear();
        //put the videos in
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/pJ7MpH5PHJI\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/soY1HbsJDz8\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/dgsy9OzOYcI\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/pLtszjL2zcg\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/5_3ceZ9mSrk\" frameborder=\"0\" allowfullscreen></iframe>"));

        //get the dog id to pass over
        CatVideoFragment catVideoFragment = new CatVideoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("catVideos", youtubeVideos);
        catVideoFragment.setArguments(bundle);

        AppCompatActivity activity = (AppCompatActivity) mContext;
        //check if it is the right container z5161354
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont_main_video, catVideoFragment).addToBackStack(null).commit();
    }

    //box 4 method
    public void videoBox4Click() {
        //remove existing items in list
        youtubeVideos.clear();
        //put the videos in
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/XQqp_8mVlGM\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/vHrHBZIA5h4\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/EWCeD8X3IZg\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/NWh4zc8BVAQ\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/4uBihrYJrlg\" frameborder=\"0\" allowfullscreen></iframe>"));

        //get the dog id to pass over
        CatVideoFragment catVideoFragment = new CatVideoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("catVideos", youtubeVideos);
        catVideoFragment.setArguments(bundle);

        AppCompatActivity activity = (AppCompatActivity) mContext;
        //check if it is the right container z5161354
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont_main_video, catVideoFragment).addToBackStack(null).commit();
    }

}
