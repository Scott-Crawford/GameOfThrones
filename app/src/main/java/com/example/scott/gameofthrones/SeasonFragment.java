package com.example.scott.gameofthrones;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SeasonFragment extends Fragment {

    private RecyclerView mSeasonRecyclerView;
    private SeasonAdapter mAdapter;
    private JSONObject mJSONObject;
    private static final String ARG_JSONObject = "JSONObject";


    //Inflate the view, and update the UI
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_episode_list,container,false);
        mSeasonRecyclerView = (RecyclerView) view.findViewById(R.id.episode_recycler_view);
        mSeasonRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    //Create a SeasonLab singleton, loading it with the data from the volley request
    //Get the list of episodes from seasonLab, load them into an adapter for the RecyclerView
    private void updateUI(){
        SeasonLab seasonLab = SeasonLab.get(getActivity(),mJSONObject);
        List<Episode> episodes = seasonLab.getEpisodes();
        if(mAdapter==null) {
            mAdapter = new SeasonAdapter(episodes);
            mSeasonRecyclerView.setAdapter(mAdapter);
        }
        else{
            mAdapter.notifyDataSetChanged();
        }
    }

    //Class for seasonHolder,which is each item in the RecyclerView, handling their text and give them a onClickListener that starts a new activity for that episode
    private class SeasonHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private Episode mEpisode;
        public SeasonHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.textView);

        }
        public void bindEpisode(Episode episode){
            mEpisode=episode;
            mTitleTextView.setText(mEpisode.getTitle());
        }
        public void onClick(View v){
            Intent intent = EpisodeActivity.newIntent(getActivity(), mEpisode.getId());
            startActivity(intent);
        }
    }

    //Class for the adapter for the RecyclerView. Takes a list of episodes, creates season holders for each one, and binds an episode to them
    private class SeasonAdapter extends RecyclerView.Adapter<SeasonHolder>{
        private List<Episode> mEpisodes;
        public SeasonAdapter(List<Episode> episodes){
            mEpisodes=episodes;
        }
        @Override
        public SeasonHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_episode,parent,false);
            return new SeasonHolder(view);
        }
        @Override
        public void onBindViewHolder(SeasonHolder holder,int position){
            Episode episode = mEpisodes.get(position);
            holder.bindEpisode(episode);
        }
        @Override
        public int getItemCount(){
            return mEpisodes.size();
        }
    }

    //Takes the JSONobject from the activity's intent, loads them into the args.
    public static SeasonFragment newInstance(JSONObject jsonObject){
        Bundle args = new Bundle();
        String jsonObjectString = jsonObject.toString();
        args.putSerializable(ARG_JSONObject,jsonObjectString);
        SeasonFragment fragment = new SeasonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //Gets the JSONObject from the args for use in setting up the fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String jsonString = (String) getArguments().getSerializable(ARG_JSONObject);
        try {
            mJSONObject = new JSONObject(jsonString);
        }
        catch (JSONException e){

        }
    }

}