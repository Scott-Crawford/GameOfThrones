package com.example.scott.gameofthrones;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.UUID;


public class EpisodeActivity extends AppCompatActivity {

    private static final String EXTRA_EPISODE_ID="com.example.scott.gameofthrones";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int episodeID = (int) getIntent().getSerializableExtra(EXTRA_EPISODE_ID);
        Episode mEpisode = SeasonLab.get(this).getEpisode(episodeID);
        setTitle(mEpisode.getTitle());
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment==null){
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }


    protected Fragment createFragment(){
        int episodeId = (int) getIntent().getSerializableExtra(EXTRA_EPISODE_ID);
        return EpisodeFragment.newInstance(episodeId);
    }

    public static Intent newIntent(Context packageContext, int episodeID){
        Intent intent = new Intent(packageContext,EpisodeActivity.class);
        intent.putExtra(EXTRA_EPISODE_ID,episodeID);
        return intent;
    }
}