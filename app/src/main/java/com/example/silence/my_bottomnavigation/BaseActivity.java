package com.example.silence.my_bottomnavigation;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

/**
 * Created by Silence on 2018/3/12.
 */

public class BaseActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private BottomNavigation mBottomNavigation;
    public ViewPager getViewPager() {
        return mViewPager;
    }
    public BottomNavigation getBottomNavigation() {
        if (null == mBottomNavigation) {
            mBottomNavigation = (BottomNavigation) findViewById(R.id.BottomNavigation);
        }
        return mBottomNavigation;
    }
}
