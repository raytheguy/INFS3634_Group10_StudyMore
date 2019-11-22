package com.example.studymore.ui.Dog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studymore.R;
import com.example.studymore.ui.Cat.Cat;
import com.example.studymore.ui.Cat.CatDetailFragment;
import com.example.studymore.ui.Cat.CatRecycleViewAdapter;

import java.util.List;

public class DogRecycleViewAdapter extends RecyclerView.Adapter<DogRecycleViewAdapter.ViewHolder>{
    public Context mContext;
    public List<Dog> arrayToPut;

    //Take in context from the search, the list of cats in an Array
    public DogRecycleViewAdapter(Context mContext, List<Dog> arrayToPut) {
        this.mContext = mContext;
        this.arrayToPut = arrayToPut;
    }

    @NonNull
    @Override
    //inflate the view; same as any recycle adapter, just need to change the id
    public DogRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.encyclo_recycle_layout, parent, false);
        DogRecycleViewAdapter.ViewHolder holder = new DogRecycleViewAdapter.ViewHolder(myView);
        return holder;
    }

    //changes based on layout
    @Override
    public void onBindViewHolder(@NonNull DogRecycleViewAdapter.ViewHolder holder, final int position) {
        holder.dogTextView.setText(arrayToPut.get(position).getName());

        //onClick Listener when the 'holder' or constraintLayout is clicked
        holder.layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View myView) {

                //get the dog id to pass over
                String dogId = arrayToPut.get(position).getId();
                DogDetailFragment dogDetailFragment = new DogDetailFragment();
                Bundle bundle = new Bundle();
                //put dog ID over
                bundle.putString("dogId", arrayToPut.get(position).getId());
                bundle.putSerializable("dogThings", arrayToPut.get(position));
                dogDetailFragment.setArguments(bundle);

                AppCompatActivity activity = (AppCompatActivity) mContext;
                //check if it is the right container z5161354
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont_main, dogDetailFragment).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        //get the amount of article Ids that exists
        return arrayToPut.size();
    }

    //purpose: holds widgets in memory
    public class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout layoutMain;
        TextView dogTextView;

        public ViewHolder(@NonNull View v){
            super(v);

            //attach to TextView and constraint layout to be clickable
            layoutMain = v.findViewById(R.id.animalClick);
            dogTextView = v.findViewById(R.id.animalName);
        }

    }
}
