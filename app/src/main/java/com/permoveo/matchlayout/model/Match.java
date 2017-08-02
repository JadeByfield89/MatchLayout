package com.permoveo.matchlayout.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by byfieldj on 8/1/17.
 * <p>
 * <p>
 * This simple POJO class represents a single result from our list of JSON objects returned by the Match endpoint call.
 * Here we parse the JSON and extract the necessary fields(username, age, location, and match percentage).
 */

public class Match {

    private String mUsername;
    private int mAge;
    private String mCityName;
    private String mState;
    private int mMatchPercentage;
    private String mPhotoUrl;

    private static final String KEY_USERNAME = "username";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_CITY_NAME = "city_name";
    private static final String KEY_STATE_CODE = "state_code";
    private static final String KEY_AGE = "age";
    private static final String KEY_THUMB_PATHS = "thumb_paths";
    private static final String KEY_LARGE = "large";
    private static final String KEY_MATCH_PERCENTAGE = "match";
    private static final String KEY_PHOTO = "photo";


    public Match() {

    }

    public void parseJson(JSONObject object) {

        if (object == null) {
            Log.d(TAG, "No JSON found at this location!");
        } else {

            try {
                if (object.has(KEY_USERNAME)) {

                    // Get user name
                    String username = object.getString(KEY_USERNAME);
                    setUsername(username);

                    // Get age
                    int age = object.getInt(KEY_AGE);
                    setAge(age);


                    // Get the city name
                    String cityName = object.getString(KEY_CITY_NAME);
                    setCityName(cityName);


                    // Get the state code
                    String stateCode = object.getString(KEY_STATE_CODE);
                    setState(stateCode);

                    // Get the match %
                    int matchPercentage = object.getInt(KEY_MATCH_PERCENTAGE);
                    setMatchPercentage(matchPercentage);

                    // Get the photo url
                    JSONObject photoObject = object.getJSONObject(KEY_PHOTO);
                    JSONObject thumbnailObject = photoObject.getJSONObject(KEY_THUMB_PATHS);

                    if(thumbnailObject.has(KEY_LARGE)){
                        String medThumbnail = thumbnailObject.getString(KEY_LARGE);
                        setPhotoUrl(medThumbnail);
                    }

                }

            } catch (JSONException e) {

                e.printStackTrace();
            }
        }

    }


    private void setUsername(String username) {
        this.mUsername = username;
    }

    public String getUsername() {

        return mUsername;
    }


    private void setAge(int age) {
        this.mAge = age;
    }

    public int getAge() {

        return mAge;
    }

    private void setCityName(String city) {
        this.mCityName = city;
    }

    public String getCityName() {

        return mCityName;
    }

    private void setState(String state) {
        this.mState = state;
    }

    public String getState() {

        return mState;
    }

    private void setMatchPercentage(int match) {
        this.mMatchPercentage = match;
    }

    public int getMatchPercentage() {

        return mMatchPercentage;
    }

    private void setPhotoUrl(String url) {
        this.mPhotoUrl = url;
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }

}
