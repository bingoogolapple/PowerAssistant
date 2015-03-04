package cn.bingoogolapple.powerassistant.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.umeng.update.UmengUpdateAgent;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgaannotation.BGAALayout;
import cn.bingoogolapple.bgaannotation.BGAAView;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.powerassistant.R;
import cn.bingoogolapple.powerassistant.util.SpUtil;

@BGAALayout(R.layout.activity_splash)
public class SplashActivity extends BaseActivity {
    @BGAAView(R.id.banner_splash_pager)
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
                loadMainActivity();
            }
        });

        if(SpUtil.getFirstUse()) {
            SpUtil.setFirstUse(false);
        } else {
            loadMainActivity();
        }
    }

    private void loadMainActivity() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        mApp.exitWithDoubleClick();
    }
}