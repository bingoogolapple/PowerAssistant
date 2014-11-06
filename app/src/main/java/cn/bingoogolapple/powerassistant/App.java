package cn.bingoogolapple.powerassistant;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;

import java.util.LinkedList;

import cn.bingoogolapple.powerassistant.util.SpUtil;
import cn.bingoogolapple.powerassistant.util.ToastUtil;

/**
 * Created by bingoogolapple on 14-10-30.
 */
public class App extends Application {
    private static final String TAG = App.class.getSimpleName();
    private static App sInstance;
    private ActivityManager mActivityManager;
    private LinkedList<Activity> mActivities = new LinkedList<Activity>();
    private long mLastPressBackKeyTime;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mActivityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        SpUtil.init(this);
    }

    public static App getInstance() {
        return sInstance;
    }

    public void addActivity(Activity activity) {
        mActivities.add(activity);
    }

    public void removeActivity(Activity activity) {
        mActivities.remove(activity);
    }

    public void exitWithDoubleClick() {
        if (System.currentTimeMillis() - mLastPressBackKeyTime <= 1500) {
            exit();
        } else {
            mLastPressBackKeyTime = System.currentTimeMillis();
            ToastUtil.makeText(R.string.exit_tips);
        }
    }

    public void exit() {
        Activity activity;
        while (mActivities.size() != 0) {
            activity = mActivities.poll();
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}
