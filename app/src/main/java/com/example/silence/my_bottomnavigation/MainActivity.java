package com.example.silence.my_bottomnavigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);// 设置布局文件
        final ViewGroup root = (ViewGroup) findViewById(R.id.CoordinatorLayout01);// 获取协调布局
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);// 获取工具栏控件
        setSupportActionBar(toolbar);// 用ToolBar替代原本的ActionBar

        initializeBottomNavigation(savedInstanceState);// 初始化底部导航栏
        initializeUI(savedInstanceState);// 初始化界面

    }

    private void initializeUI(Bundle savedInstanceState) {
    }

    private void initializeBottomNavigation(Bundle savedInstanceState) {
    }
}
