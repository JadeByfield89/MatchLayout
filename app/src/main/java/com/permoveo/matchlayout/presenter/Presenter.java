package com.permoveo.matchlayout.presenter;

/**
 * Created by byfieldj on 8/1/17.
 *
 * This interface establishes a relationship between our View(GiphyListViewActivity)'s lifecycle and our Presenter implementation
 */

public interface Presenter {

    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();
}
