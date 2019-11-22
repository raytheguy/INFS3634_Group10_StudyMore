package com.example.studymore;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Vector;
import com.example.studymore.ui.Video.CatVideoTypeFragment;
import com.example.studymore.ui.Video.DogVideoTypeFragment;
import com.example.studymore.ui.Video.YoutubeVideos;
import com.google.android.material.bottomnavigation.BottomNavigationView;

//method is to switch between cats and dogs video fragments
public class VideoActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Vector<YoutubeVideos> youtubeVideos = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_new);

        //create the bottom navigation bar
        BottomNavigationView bottomNavView = findViewById(R.id.nav_view_video);
        bottomNavView.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont_main_video,
                    new CatVideoTypeFragment()).commit();
        }

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.navigation_cats:
                            selectedFragment = new CatVideoTypeFragment();
                            break;
                        case R.id.navigation_dogs:
                            selectedFragment = new DogVideoTypeFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont_main_video,
                            selectedFragment).commit();
                    return true;
                }
            };
}
