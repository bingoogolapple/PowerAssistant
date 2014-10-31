package cn.bingoogolapple.powerassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.bingoogolapple.powerassistant.service.PowerAssistantCoreService;
import cn.bingoogolapple.powerassistant.util.Logger;

public class BootCompleteReceiver extends BroadcastReceiver {
    private static final String TAG = BootCompleteReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.i(TAG, "手机重启完毕");
        context.startService(new Intent(context, PowerAssistantCoreService.class));
    }
}
