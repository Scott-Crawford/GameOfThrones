package com.example.scott.gameofthrones;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by scott on 6/7/2017.
 */

public class EpisodeFragment extends Fragment{

    private static final String ARG_EPISODE_ID = "episode_id";

    private Episode mEpisode;
    private TextView mAirDate;
    private TextView mEpisodeNum;
    private TextView mSeasonNum;
    private TextView mVoteAverage;
    private TextView mOverview;

    //Loads the episodeID into the arguments of the fragment for later use.
    public static EpisodeFragment newInstance(int episodeId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_EPISODE_ID,episodeId);
        EpisodeFragment fragment = new EpisodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //Loads the episodeID from the arguments, then calls get in SeasonLab to retrieve the episode with the corresponding ID
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int episodeID = (int) getArguments().getSerializable(ARG_EPISODE_ID);
        mEpisode = SeasonLab.get(getActivity()).getEpisode(episodeID);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_episode,container,false);
        mAirDate=(TextView)v.findViewById(R.id.air_date_text);
        mAirDate.setText(DateFormat.format("dd MMM yyyy",mEpisode.getReleaseDate()).toString());
        mEpisodeNum=(TextView)v.findViewById(R.id.episode_num_text);
        mEpisodeNum.setText(""+mEpisode.getEpisodeNum());
        mSeasonNum=(TextView)v.findViewById(R.id.season_num_text);
        mSeasonNum.setText(""+mEpisode.getSeasonNum());
        mVoteAverage=(TextView)v.findViewById(R.id.vote_average_text);
        mVoteAverage.setText(""+mEpisode.getVoteAverage());
        mOverview=(TextView)v.findViewById(R.id.overview_text);
        mOverview.setText(mEpisode.getOverview());

        return v;
    }
}
