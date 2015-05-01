package com.example.googleplay.widget.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.example.googleplay.R;
import com.example.googleplay.application.utils.UIUtils;
import com.example.googleplay.manager.ThreadManager;
/**
 *==========版权：郑州大学西亚斯国际学院 起点工作室 =====================
 *==========技术支持：起点工作室==================================
 *==========日期：2015-5-1 上午11:02:27============================
 *==========作者：LJW========================================
 *描述：所有页面的加载都要用到这个加载页面
 */
public abstract class LoadingPager extends FrameLayout {
	// 各种状态显示得变量
	private static final int STATE_UNLOAD = 1;
	private static final int STATE_LOAD = 2;
	private static final int STATE_ERROR = 3;
	private static final int STATE_EMPTY = 4;
	private static final int STATE_SUCCESS = 5;

	private View loadingView;

	private View errorView;

	private View emptyView;

	private View successView;

	private static int mState;

	public LoadingPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public LoadingPager(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		// 初始化状态的值应该是未加载状态的
		mState = STATE_UNLOAD;
		// 把各种情况的布局添加进来，进行初始化
		// 先初始化加载数据的页面,尽可能的组建化，把各个小功能进行封装
		loadingView = createLoadingView();
		// 添加布局,这里一般是不会为空的，也是代码的健壮性
		if (null != loadingView) {
			addViewToCurrentView(loadingView);
		}
		emptyView = createEmptyView();
		if (null != emptyView) {
			addViewToCurrentView(emptyView);
		}
		errorView = createErrorView();
		if (null != errorView) {
			addViewToCurrentView(errorView);
		}

		// 下面是要显示布局，并且要在主线程里面显示
		showSafePageView();

	}

	private void showSafePageView() {
		UIUtils.runInMainThread(new Runnable() {

			@Override
			public void run() {
				// 根据不同的状态显示不同的视图
				showPageView();
			}
		});
	}

	// 这一部分主要任务就是显示视图，还要把这些方法都暴露出去，可以供别人调用
	protected void showPageView() {
		if (null != loadingView) {
			loadingView.setVisibility(mState == STATE_LOAD
					| mState == STATE_UNLOAD ? View.VISIBLE : View.INVISIBLE);
		}

		if (null != errorView) {
			errorView.setVisibility(mState == STATE_ERROR ? View.VISIBLE
					: View.INVISIBLE);
		}

		if (null != emptyView) {
			emptyView.setVisibility(mState == STATE_EMPTY ? View.VISIBLE
					: View.INVISIBLE);
		}
		if (null == successView && mState == STATE_SUCCESS) {
			successView = createSuccessView();
			if (null != successView) {
				addViewToCurrentView(successView);
			}

		}

		if (null != successView) {
			successView.setVisibility(mState == STATE_SUCCESS ? View.VISIBLE
					: View.INVISIBLE);
		}

	}

	// 将这个方法暴漏出去
	public synchronized void show() {
		if (mState == STATE_EMPTY || mState == STATE_ERROR) {
			mState = STATE_UNLOAD;
		}
		if (mState == STATE_UNLOAD) {
			mState = STATE_LOAD;
			// 这个时候需要去加载数据，使用线程池，AsyncTask不是线程安全的
			ThreadManager.getLongPool().execute(new LoadRunner());
		}
	}

	public abstract View createSuccessView();

	private void addViewToCurrentView(View view) {
		addView(view, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
	}

	private View createLoadingView() {
		return UIUtils.inflate(R.layout.loading_page_loading);
	}

	private View createErrorView() {
		return UIUtils.inflate(R.layout.loading_page_error);
	}

	private View createEmptyView() {
		return UIUtils.inflate(R.layout.loading_page_empty);
	}

	public LoadingPager(Context context) {
		super(context);
		init();
	}

	protected class LoadRunner implements Runnable {

		@Override
		public void run() {
			// 加载数据，然后给我一个返回值，可以让我 清除当前的状态,这个时候返回用了一个枚举类型，能够保证返回状态时固定并且是唯一的
			final ResultType result = load();
			// 这主线程里面进行UI显示
			UIUtils.runInMainThread(new Runnable() {

				@Override
				public void run() {
					mState = result.getValue();
					showPageView();
				}
			});
		}

	}

	// 这里面的都是一个一个的对象，默认调用的是下面的构造方法
	public enum ResultType {
		ERROR(3), EMPTY(4), SUCCESS(5);
		private int value;

		ResultType(int value) {
			this.value = value;
		};

		public int getValue() {
			return value;
		}

	}

	public abstract ResultType load();

}
