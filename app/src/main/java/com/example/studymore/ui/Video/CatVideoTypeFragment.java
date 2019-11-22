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

public class CatVideoTypeFragment extends Fragment {
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

    public CatVideoTypeFragment() {

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
        //set the images
        videoImage1 = v2.findViewById(R.id.videoBox1ImageView);
        videoImage1.setImageResource(R.drawable.video_petcare2_smaller);
        videoImage2 = v2.findViewById(R.id.videoBox2ImageView);
        videoImage2.setImageResource(R.drawable.video_health);
        videoImage3 = v2.findViewById(R.id.videoBox3ImageView);
        videoImage3.setImageResource(R.drawable.video_training);
        videoImage4 = v2.findViewById(R.id.videoBox4ImageView);
        videoImage4.setImageResource(R.drawable.video_socialise_smaller);
        //set background
        videoBg = v2.findViewById(R.id.videoBgConstraintLayout);
        videoBg.setBackgroundResource(R.drawable.video_catfragment);

        //get context
        mContext = getActivity();

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
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/s6p4FYveKKk\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/AalAw9Fqhyo\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/itZDZZpcQlc\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/LhgynG941D8\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/pdpXsSjfvFo\" frameborder=\"0\" allowfullscreen></iframe>"));

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
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/SjmRPWYnoJw\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/SMsFm_nECoA\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/STg1IX4VbAs\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/X21_UXXK4B8\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/3KnL4cf_iyU\" frameborder=\"0\" allowfullscreen></iframe>"));

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
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/NND_64dSxd0\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/7m7xijjJS88\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/WJcWoksdlOM\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/EKe0otXH3Ck\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/2Iz9woMsMdQ\" frameborder=\"0\" allowfullscreen></iframe>"));

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
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ZaqrTb54Xmc\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/BH4rlk0NLkc\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/fO_T6wXIrqM\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/M54WzptXM_Y\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/XTRjCFm2tUE\" frameborder=\"0\" allowfullscreen></iframe>"));

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
