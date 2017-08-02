package com.permoveo.matchlayout.network;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.permoveo.matchlayout.application.MatchLayoutApplication;
import com.permoveo.matchlayout.constants.APIConstants;
import com.permoveo.matchlayout.model.Match;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by byfieldj on 8/1/17.
 * <p>
 * This class makes the network call to the sample Match API endpoint and returns to us a list of match results.
 */

public class MatchRequest {

    private static final String TAG = "MatchRequest";
    private static final String KEY_DATA = "data";
    private MatchRequestListener mListener;
    private ArrayList<Match> mMatchResults = new ArrayList<>();

    /*

     /*
    This callback will be responsible for delivering the results(or an error) back to the component that made the call
     */

    public interface MatchRequestListener {

        void onMatchRequestCompleted(ArrayList<Match> results);

        void onMatchRequestError(VolleyError error);

    }


    public void requestMatches(MatchRequestListener listener) {

        this.mListener = listener;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, APIConstants.SAMPLE_MATCH_ENDPOINT, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray dataArray = response.getJSONArray(KEY_DATA);

                    Log.d(TAG, "JSON Array size -> " + dataArray.length());

                    if(dataArray.length() > 0){

                        for(int i = 0; i < dataArray.length(); i++){

                            JSONObject matchObject = dataArray.getJSONObject(i);

                            Log.d(TAG, "Match object -> " + matchObject.toString());

                            Match match = new Match();
                            match.parseJson(matchObject);
                            mMatchResults.add(match);
                        }
                    }

                    mListener.onMatchRequestCompleted(mMatchResults);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                mListener.onMatchRequestError(error);
            }
        });

        MatchLayoutApplication.getInstance().addToRequestQueue(request);
    }


}
