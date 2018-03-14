package com.example.silence.my_bottomnavigation;

import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

/**
 * Created by Silence on 2018/3/12.
 */

public abstract class BaseActivity extends AppCompatActivity implements BottomNavigation.OnMenuItemSelectionListener {
    private ViewPager mViewPager;
    private SystemBarTintManager mSystemBarTint;
    private boolean mTranslucentStatus;
    private boolean mTranslucentStatusSet;
    private boolean mTranslucentNavigation;
    private boolean mTranslucentNavigationSet;
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
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        //mViewPager = (ViewPager) findViewById(R.id.ViewPager01);
        mBottomNavigation = (BottomNavigation) findViewById(R.id.BottomNavigation);
        if (null != mBottomNavigation) {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf");
            mBottomNavigation.setOnMenuItemClickListener(this);
            mBottomNavigation.setDefaultTypeface(typeface);
        }
    }
    public boolean hasManagedToolbarScroll() {
        return hasAppBarLayout() && findViewById(R.id.CoordinatorLayout01) instanceof CoordinatorLayout;
    }
    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }
    public boolean hasAppBarLayout() {
        return getToolbar().getParent() instanceof AppBarLayout;
    }
    public boolean hasTranslucentStatusBar() {
        if (!mTranslucentStatusSet) {
            if (Build.VERSION.SDK_INT >= 19) {
                mTranslucentStatus =
                        ((getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                                == WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            } else {
                mTranslucentStatus = false;
            }
            mTranslucentStatusSet = true;
        }
        return mTranslucentStatus;
    }

    public int getStatusBarHeight() {
        return getSystemBarTint().getConfig().getStatusBarHeight();
    }

    public boolean hasTranslucentNavigation() {
        if (!mTranslucentNavigationSet) {
            final SystemBarTintManager.SystemBarConfig config = getSystemBarTint().getConfig();
            if (Build.VERSION.SDK_INT >= 19) {
                boolean themeConfig =
                        ((getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                                == WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

                mTranslucentNavigation = themeConfig && config.hasNavigtionBar() && config.isNavigationAtBottom()
                        && config.getNavigationBarHeight() > 0;
            }
            mTranslucentNavigationSet = true;
        }
        return mTranslucentNavigation;
    }
    public SystemBarTintManager getSystemBarTint() {
        if (null == mSystemBarTint) {
            mSystemBarTint = new SystemBarTintManager(this);
        }
        return mSystemBarTint;
    }
    public int getNavigationBarHeight() {
        return getSystemBarTint().getConfig().getNavigationBarHeight();
    }
}
