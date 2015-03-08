package cn.bingoogolapple.powerassistant.util;

import android.util.Log;

import cn.bingoogolapple.powerassistant.BuildConfig;

public class Logger {
    private static final boolean IS_DEVELOP_MODE = BuildConfig.IS_DEVELOP_MODE;
    
	private Logger() {
	}

	// 日志记录级别，开发阶段根据需求设置成大于0的数，项目正式发布后设置成0
	private static int LOGLEVEL = 6;
	private static int ERROR = 1;
	private static int WARN = 2;
	private static int INFO = 3;
	private static int DEBUG = 4;
	private static int VERBOSE = 5;

	public static void v(String tag, String msg) {
		if (IS_DEVELOP_MODE && LOGLEVEL > VERBOSE) {
			Log.v(tag, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (IS_DEVELOP_MODE && LOGLEVEL > DEBUG) {
			Log.d(tag, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (IS_DEVELOP_MODE && LOGLEVEL > INFO) {
			Log.i(tag, msg);
		}
	}

	public static void w(String tag, String msg) {
		if (IS_DEVELOP_MODE && LOGLEVEL > WARN) {
			Log.w(tag, msg);
		}
	}

	public static void e(String tag, String msg) {
		if (IS_DEVELOP_MODE && LOGLEVEL > ERROR) {
			Log.e(tag, msg);
		}
	}
}