package com.permoveo.matchlayout;

import com.android.volley.VolleyError;
import com.permoveo.matchlayout.presenter.MatchLayoutPresenter;
import com.permoveo.matchlayout.view.MatchLayoutActivity;
import com.permoveo.matchlayout.view.MatchLayoutView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by byfieldj on 8/1/17.
 */


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MatchLayoutPresenterTest {

    private MatchLayoutPresenter mPresenter;

    private MatchLayoutView mView;



    @Before
    public void setup() throws Exception{

        mView = mock(MatchLayoutView.class);
        mPresenter = new MatchLayoutPresenter(mView);

    }

    @Test
    public void viewShouldCallReportError(){

        mPresenter.notifyViewOfError();

        verify(mView, atLeastOnce()).reportError(new VolleyError("Test Error: Something went wrong!"));
    }
}
