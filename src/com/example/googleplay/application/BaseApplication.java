package com.example.googleplay.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class BaseApplication extends Application {
	// 在整个应许程序里面都能够拿到，拿到主线程的context上下文
	private static Context mContext = null;
	// 获取主线程的handler
	private static Handler mMainThreadHandler = null;
	// 获取到主线程的Looper
	private static Looper mMainThreadLooper = null;
	// 得到主线程
	private static Thread mMainThread = null;
	// 得到主线程的id
	private static int mMainThreadId;

	@Override
	public void onCreate() {
		super.onCreate();
		this.mContext = this;
		// 因为我只需要new一个handler就行了，所有的只要用这一个就行了，提升性能
		this.mMainThreadHandler = new Handler();
		this.mMainThreadLooper = getMainLooper();
		this.mMainThread = Thread.currentThread();
		// 获取调用者得线程id
		this.mMainThreadId = android.os.Process.myTid();
	}

	public static Context getmContext() {
		return mContext;
	}

	public static Handler getmMainThreadHandler() {
		return mMainThreadHandler;
	}

	public static Looper getmMainThreadLooper() {
		return mMainThreadLooper;
	}

	public static Thread getmMainThread() {
		return mMainThread;
	}

	public static int getmMainThreadId() {
		return mMainThreadId;
	}

}
