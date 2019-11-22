package com.example.studymore.ui.Video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studymore.R;

import java.util.Vector;

public class CatVideoFragment extends Fragment {
    RecyclerView recyclerView;
    Vector<YoutubeVideos> youtubeVideos = new Vector<>();

    public CatVideoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //create the recycling things
        final View v2 = inflater.inflate(R.layout.fragment_video, container, false);
        recyclerView = (RecyclerView) v2.findViewById(R.id.videoRecyclerNew);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //get back the videos
        Bundle bundle = getArguments();
        youtubeVideos = (Vector<YoutubeVideos>) bundle.getSerializable("catVideos");

        //set the video adapter
        VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);

        recyclerView.setAdapter(videoAdapter);
        return v2;
    }


}
