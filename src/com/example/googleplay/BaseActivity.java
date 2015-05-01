package com.example.googleplay;

import com.example.googleplay.application.utils.LogUtils;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public abstract class BaseActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		LogUtils.i(this.getClass().getSimpleName());
		super.onCreate(savedInstanceState);
		initView();
		initTitleBar();
	}

	public abstract void initTitleBar();
	public abstract void initView();

}
