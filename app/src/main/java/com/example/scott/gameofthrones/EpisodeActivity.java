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

    //on creation of the activity, get the episode id from the intent in order to set the title of the activity. Then start the fragment manager and call createFragment, then starts the fragment.
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

    //Create a fragment, passing it the episodeID from the intent
    protected Fragment createFragment(){
        int episodeId = (int) getIntent().getSerializableExtra(EXTRA_EPISODE_ID);
        return EpisodeFragment.newInstance(episodeId);
    }

    //Creates a new intent when called, putting the episodeID as an extra to later be used by the episode fragment.
    public static Intent newIntent(Context packageContext, int episodeID){
        Intent intent = new Intent(packageContext,EpisodeActivity.class);
        intent.putExtra(EXTRA_EPISODE_ID,episodeID);
        return intent;
    }
}