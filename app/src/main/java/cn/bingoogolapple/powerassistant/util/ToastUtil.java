package cn.bingoogolapple.powerassistant.util;

import android.widget.Toast;

import cn.bingoogolapple.powerassistant.App;

public class ToastUtil {

    private ToastUtil() {
    }

    public static void makeText(CharSequence text) {
        if (text.length() > 10) {
            Toast.makeText(App.getInstance(), text, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(App.getInstance(), text, Toast.LENGTH_SHORT).show();
        }
    }

    public static void makeText(int resId) {
        makeText(App.getInstance().getResources().getString(resId));
    }
}