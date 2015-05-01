package com.example.googleplay.fragment;

import android.view.View;

import com.example.googleplay.BaseFragment;
import com.example.googleplay.widget.ui.LoadingPager.ResultType;

public class RecommnetFragment extends BaseFragment{

	@Override
	protected ResultType load() {
			return checkLoadResult(null);
	}

	@Override
	protected View createSuccessView() {
		return  null;
	}

}
