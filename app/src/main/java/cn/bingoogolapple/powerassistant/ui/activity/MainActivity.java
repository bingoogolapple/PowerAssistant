package cn.bingoogolapple.powerassistant.ui.activity;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.mobisage.android.MobiSageAdBanner;

import cn.bingoogolapple.loon.library.LoonLayout;
import cn.bingoogolapple.loon.library.LoonView;
import cn.bingoogolapple.powerassistant.R;
import cn.bingoogolapple.powerassistant.receiver.DeviceKeeperReceiver;
import cn.bingoogolapple.powerassistant.service.PowerAssistantCoreService;
import cn.bingoogolapple.powerassistant.util.Logger;
import cn.bingoogolapple.powerassistant.util.SpUtil;


@LoonLayout(id = R.layout.activity_main)
public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @LoonView(id = R.id.tb_main_onekey)
    private ToggleButton mOnekeyTb;
    @LoonView(id = R.id.tb_main_shake)
    private ToggleButton mShakeTb;
    @LoonView(id = R.id.tb_main_showad)
    private ToggleButton mShowAdTb;
    @LoonView(id = R.id.ad_container)
    private LinearLayout mAdContainer;

    private MobiSageAdBanner mAdView;

    private DevicePolicyManager mDevicePolicyManager;
    private ComponentName mComponentName;
    private PowerAssistantCoreService.ScreenAction mScreenAction;
    private ServiceConnection mScreenServiceConn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mScreenAction = (PowerAssistantCoreService.ScreenAction) service;
        }
    };

    @Override
    protected void setListener() {
        mOnekeyTb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mScreenAction != null) {
                    if (mDevicePolicyManager.isAdminActive(mComponentName)) {
                        if (isChecked) {
                            Logger.i(TAG, "开启一键锁屏");
                            mScreenAction.openOnekeyLockScreen();
                        } else {
                            mScreenAction.closeOnekeyLockScreen();
                        }
                    } else {
                        activeDeviceAdmin();
                    }
                }
            }
        });
        mShakeTb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mScreenAction != null) {
                    if (isChecked) {
                        mScreenAction.openShakeUnlockScreen();
                    } else {
                        mScreenAction.closeShakeUnlockScreen();
                    }
                }
            }
        });
        mShowAdTb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    showAd();
                } else {
                    hiddenAd();
                }
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        startService(new Intent(this, PowerAssistantCoreService.class));
        bindService(new Intent(this, PowerAssistantCoreService.class), mScreenServiceConn, BIND_AUTO_CREATE);
        mDevicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        mComponentName = new ComponentName(this, DeviceKeeperReceiver.class);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mOnekeyTb.setChecked(SpUtil.getOnekeyLockScreen());
        mShakeTb.setChecked(SpUtil.getShakeUnlockScreen());
        mShowAdTb.setChecked(SpUtil.getShowAd());
    }

    @Override
    protected void onDestroy() {
        unbindService(mScreenServiceConn);
        super.onDestroy();
    }

    private void showAd() {
        SpUtil.setShowAd(true);
        if (mAdView == null) {
            mAdView = new MobiSageAdBanner(this, "TE1MnyPbwVZ83mUWy9+XccBq");
        }
        mAdContainer.addView(mAdView.getAdView());
    }

    private void hiddenAd() {
        if (mAdView != null) {
            mAdView.destroyAdView();
            mAdView = null;
        }
        SpUtil.setShowAd(false);
    }

    private void activeDeviceAdmin() {
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mComponentName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, getString(R.string.active_tips));
        startActivity(intent);
    }

    private void removeDeviceAdmin() {
        boolean active = mDevicePolicyManager.isAdminActive(mComponentName);
        if (active) {
            mDevicePolicyManager.removeActiveAdmin(mComponentName);
        }
    }

    @Override
    public void onBackPressed() {
        mApp.exitWithDoubleClick();
    }
}