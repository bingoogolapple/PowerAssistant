package cn.bingoogolapple.powerassistant.receiver;

import android.app.admin.DeviceAdminReceiver;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import cn.bingoogolapple.powerassistant.util.ToastUtil;

public class DeviceKeeperReceiver extends DeviceAdminReceiver {
    public static final String ADD_DEVICE_ADMIN = "cn.bingoogol.screenexpert.ADD_DEVICE_ADMIN";
    public static final String REMOVE_DEVICE_ADMIN = "cn.bingoogol.screenexpert.REMOVE_DEVICE_ADMIN";

    @Override
    public void onEnabled(Context context, Intent intent) {
        super.onEnabled(context, intent);
        context.sendBroadcast(new Intent(ADD_DEVICE_ADMIN));
        ToastUtil.makeText("已激活屏幕专家为设备管理器");
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        super.onDisabled(context, intent);
        context.sendBroadcast(new Intent(REMOVE_DEVICE_ADMIN));
        ToastUtil.makeText("已取消激活屏幕专家为设备管理器");
    }
}