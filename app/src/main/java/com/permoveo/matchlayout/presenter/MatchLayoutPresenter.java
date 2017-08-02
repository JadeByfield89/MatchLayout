package com.permoveo.matchlayout.presenter;

import com.android.volley.VolleyError;
import com.permoveo.matchlayout.model.Match;
import com.permoveo.matchlayout.network.MatchRequest;
import com.permoveo.matchlayout.view.MatchLayoutView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by byfieldj on 8/1/17.
 *
 * This class will be responsible for a couple things
 * <p>
 * 1) Providing our MatchLayoutActivity(View) with data from our model
 * 2) Instructing the View how to respond to user input events, in the case we decided to add any in the future.
 * Like tapping on a cell, for example.
 */

public class MatchLayoutPresenter implements Presenter {

    private MatchLayoutView mView;
    private MatchRequest mRequest;


    public MatchLayoutPresenter(MatchLayoutView view){
        mRequest = new MatchRequest();
        this.mView = view;
    }


    public void fetchResults(){

        mRequest.requestMatches(new MatchRequest.MatchRequestListener() {
            @Override
            public void onMatchRequestCompleted(ArrayList<Match> results) {
                mView.displayResults(results);
            }

            @Override
            public void onMatchRequestError(VolleyError error) {
                mView.reportError(error);

            }
        });

    }



    public void notifyViewOfError(){
        mView.reportError(new VolleyError("Test Error: Something went wrong!"));
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }
}
