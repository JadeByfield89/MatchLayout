package com.permoveo.matchlayout.application;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by byfieldj on 8/1/17.
 *
 *
 * Application class where we initialize our Volley RequestQueue singleton
 * This request queue will be used to process all network requests on a background thread
 */

public class MatchLayoutApplication extends Application {

    public static final String TAG = MatchLayoutApplication.class.getSimpleName();

    private RequestQueue mRequestQueue;

    private static MatchLayoutApplication mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;


    }

    public static MatchLayoutApplication getInstance() {

        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }



}
