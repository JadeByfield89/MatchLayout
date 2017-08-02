package com.permoveo.matchlayout;

import com.permoveo.matchlayout.view.MatchLayoutActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by byfieldj on 8/1/17.
 */

@RunWith(MockitoJUnitRunner.class)
@Config(constants = BuildConfig.class)
public class MatchLayoutActivityTest {


    private MatchLayoutActivity mActivity;

    @Before
    public void setup() throws Exception {

        mActivity = Robolectric.buildActivity(MatchLayoutActivity.class).create().resume().get();

    }

    @Test
    public void activityShouldNotBeNull() {

        assertNotNull(mActivity);
    }


}
