package com.example.scott.gameofthrones;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by scott on 6/8/2017.
 */

public class SeasonActivity extends AppCompatActivity {

    private static final String TAG = "SeasonActivity";
    private JSONObject mJSONObject;

    //sets the content view, then calls volleyRequest.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        Log.d(TAG,"Reach1");
        volleyRequest();
    }

    //Calls the the movie database with a volley request, adding the request to the queue. In order to avoid errors with populating the recyclerview, the request must be completed before the fragment is created.
    public void volleyRequest() {

        String url = "http://api.themoviedb.org/3/tv/1399/season/1?api_key=af660f1c1b43ce2121ab62786304e12f&language=en-US";

        Log.d(TAG,"Reach2");
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                Log.d(TAG,"Reach4");
                mJSONObject=response;
                FragmentManager fm = getSupportFragmentManager();
                Fragment fragment = fm.findFragmentById(R.id.fragment_container);

                if(fragment==null){
                    fragment = createFragment();
                    fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error", error.toString());

                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null) {
                    Log.e("Status code", String.valueOf(networkResponse.statusCode));
                }

            }
        });
        Log.d(TAG,"Reach3");
        AppController.getInstance().addToRequestQueue(jsObjRequest);
    }

    protected Fragment createFragment(){

        return SeasonFragment.newInstance(mJSONObject);
    }

}
