package cn.bingoogolapple.powerassistant.ui.activity;

import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import cn.bingoogolapple.bgaannotation.BGAA;
import cn.bingoogolapple.powerassistant.App;

public abstract class BaseActivity extends ActionBarActivity implements View.OnClickListener {
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
        BGAA.injectView2Activity(this);
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