package cn.bingoogolapple.powerassistant.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.umeng.update.UmengUpdateAgent;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.library.BGABanner;
import cn.bingoogolapple.loon.library.LoonLayout;
import cn.bingoogolapple.loon.library.LoonView;
import cn.bingoogolapple.powerassistant.R;

@LoonLayout(id = R.layout.activity_splash)
public class SplashActivity extends BaseActivity {
    @LoonView(id = R.id.banner_splash_pager)
    private BGABanner mPagerBanner;


    @Override
    protected void processLogic(Bundle savedInstanceState) {
        UmengUpdateAgent.update(this);
        List<View> views = new ArrayList<View>();
        views.add(getLayoutInflater().inflate(R.layout.view_one, null));
        views.add(getLayoutInflater().inflate(R.layout.view_two, null));
        View lastView = getLayoutInflater().inflate(R.layout.view_last, null);
        views.add(lastView);
        mPagerBanner.setViewPagerViews(views);

        lastView.findViewById(R.id.btn_last_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        mApp.exitWithDoubleClick();
    }
}