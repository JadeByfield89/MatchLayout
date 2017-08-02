package com.permoveo.matchlayout.view;

import com.android.volley.VolleyError;
import com.permoveo.matchlayout.model.Match;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by byfieldj on 8/1/17.
 *
 *
 * This is the interface that will act as the main communication layer between our Presenter and our View
 * This way each can be easily tested in isolation because they are not tightly coupled together.
 */


public interface MatchLayoutView {

    void loadMatches();
    void displayResults(ArrayList<Match> results);
    void reportError(VolleyError error);
}
