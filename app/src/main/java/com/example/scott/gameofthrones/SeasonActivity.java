package com.example.scott.gameofthrones;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        volleyRequest();
    }

    public void volleyRequest() {

        String url = "https://api.themoviedb.org/3/tv/1399/season/1?api_key=af660f1c1b43ce2121ab62786304e12f&language=en-US";


        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
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
                // TODO Auto-generated method stub

            }
        });
        AppController.getInstance().addToRequestQueue(jsObjRequest);
    }

    protected Fragment createFragment(){

        return SeasonFragment.newInstance(mJSONObject);
    }

}
