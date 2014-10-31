package cn.bingoogolapple.powerassistant.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;

import cn.bingoogolapple.loon.library.Loon;
import cn.bingoogolapple.powerassistant.App;

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {
    protected App mApp;
    protected LocalBroadcastManager mLocalBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp = App.getInstance();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mApp.addActivity(this);
        injectView();
        setListener();
        processLogic(savedInstanceState);
    }

    protected void injectView() {
        Loon.injectView2Activity(this);
    }

    protected void setListener() {
    }

    protected abstract void processLogic(Bundle savedInstanceState);

    @Override
    public void onClick(View v) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mApp.removeActivity(this);
    }
}