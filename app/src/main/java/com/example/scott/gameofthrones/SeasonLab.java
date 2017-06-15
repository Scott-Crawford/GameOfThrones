package com.example.scott.gameofthrones;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by scott on 6/8/2017.
 */

public class SeasonLab {
    private static final String TAG = "SeasonLab";
    private static SeasonLab sSeasonLab;
    private List<Episode> mEpisodes;
    private static JSONObject mJSONObject;


    public static SeasonLab get(Context context,JSONObject jsonObject){
        if(sSeasonLab==null){
            sSeasonLab=new SeasonLab(context,jsonObject);
        }
        return sSeasonLab;
    }

    public static SeasonLab get(Context context){
        if(sSeasonLab==null){
            sSeasonLab=new SeasonLab(context,null);
        }
        return sSeasonLab;
    }



    private SeasonLab(Context context,JSONObject jsonObject){

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("episodes");
            mEpisodes=new ArrayList<>();
            for (int i=0;i<jsonArray.length();i++) {
                JSONObject jsonEpisode = jsonArray.getJSONObject(i);
                String dtStart = jsonEpisode.getString("air_date");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date;
                try {
                    date = format.parse(dtStart);

                } catch (ParseException e) {
                    date = new Date();
                }
                int id = jsonEpisode.getInt("id");
                String title = jsonEpisode.getString("name");
                int episode_number = jsonEpisode.getInt("episode_number");
                int season_number = jsonEpisode.getInt("season_number");
                String overview = jsonEpisode.getString("overview");
                double vote_average = jsonEpisode.getDouble("vote_average");
                Episode episode = new Episode(id, title, date, episode_number, season_number, overview, vote_average);
                mEpisodes.add(episode);
            }


        }
        catch (JSONException e){
            Log.e(TAG,"JSON");
        }

    }

    public List<Episode> getEpisodes(){
        return mEpisodes;
    }

    public Episode getEpisode(int id){
        for(Episode episode : mEpisodes){
            if(episode.getId()==id){
                return episode;
            }
        }
        return null;
    }
}
