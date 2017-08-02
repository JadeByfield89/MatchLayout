package com.permoveo.matchlayout.view;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.permoveo.matchlayout.R;
import com.permoveo.matchlayout.adapter.MatchLayoutViewAdapter;
import com.permoveo.matchlayout.model.Match;
import com.permoveo.matchlayout.presenter.MatchLayoutPresenter;
import com.permoveo.matchlayout.util.GridItemDecoration;
import com.permoveo.matchlayout.util.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MatchLayoutActivity extends AppCompatActivity implements MatchLayoutView {

    @Bind(R.id.rv_recylcer)
    RecyclerView mMatchList;

    @Bind(R.id.pbProgress)
    ProgressBar mProgress;

    @Bind(R.id.rl_container)
    RelativeLayout mParent;

    private MatchLayoutViewAdapter mAdapter;


    private MatchLayoutPresenter mPresenter = new MatchLayoutPresenter(this);
    private GridLayoutManager mGridLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mAdapter = new MatchLayoutViewAdapter();
        int spanCount = 2;
        mGridLayoutManager = new GridLayoutManager(this, spanCount, GridLayoutManager.VERTICAL, false);
        mMatchList.setLayoutManager(mGridLayoutManager);
        mMatchList.addItemDecoration(new GridItemDecoration(12));

        loadMatches();

    }

    @Override
    public void loadMatches() {
        mPresenter.fetchResults();
    }

    @Override
    public void displayResults(ArrayList<Match> results) {

        // Let's hide our progress spinner and display the results
        mProgress.setVisibility(View.INVISIBLE);
        mMatchList.setVisibility(View.VISIBLE);

        mMatchList.setAdapter(mAdapter);
        mAdapter.appendResults(results);
    }

    @Override
    public void reportError(VolleyError error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onDestroy();
    }
}
