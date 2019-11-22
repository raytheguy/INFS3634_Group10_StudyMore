package com.example.studymore.ui.Quiz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studymore.R;

import java.util.ArrayList;

public class ScoreRecycleViewAdapter extends RecyclerView.Adapter<ScoreRecycleViewAdapter.ScoreViewHolder>{
        private ArrayList<Score> quizResultsToAdapt;

        public void setData(ArrayList<Score> quizResultsToAdapt) {
            this.quizResultsToAdapt = quizResultsToAdapt;
        }

        @NonNull
        @Override
        public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view =
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.score_display, parent, false);

            ScoreViewHolder scoreViewHolder = new ScoreViewHolder(view);
            return scoreViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ScoreRecycleViewAdapter.ScoreViewHolder holder, int position) {

            final Score scoreAtPosition = quizResultsToAdapt.get(position);

            holder.attemptNumber.setText("Attempt "+scoreAtPosition.getAttemptNumber());

            int attemptScore = scoreAtPosition.getScore();

            holder.score.setText(": " + attemptScore+"/10");

            //trophy will depend on how much score they get
            if(attemptScore<5)
                holder.trophy.setImageResource(R.drawable.flashcard_bronze);
            else if(attemptScore<8)
                holder.trophy.setImageResource(R.drawable.flashcard_silver);
            else
                holder.trophy.setImageResource(R.drawable.flashcard_gold);

        }

        @Override
        public int getItemCount() {
            return quizResultsToAdapt.size();
        }

        // ViewHolder represents one item, but doesn't have data when it's first constructed.
        // We assign the data in onBindViewHolder.
        public static class ScoreViewHolder extends RecyclerView.ViewHolder {
            public View view;
            public TextView attemptNumber;
            public TextView score;
            public ImageView trophy;

            // This constructor is used in onCreateViewHolder
            public ScoreViewHolder(View v) {
                super(v);  // runs the constructor for the ViewHolder superclass
                view = v;
                attemptNumber = v.findViewById(R.id.attemptNumber);
                score = v.findViewById(R.id.score);
                trophy = v.findViewById(R.id.resultTrophy);
            }
        }
    }
