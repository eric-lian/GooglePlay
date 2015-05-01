package com.example.googleplay;

import com.example.googleplay.application.utils.UIUtils;
import com.example.googleplay.factory.FragmentFactory;
import com.example.googleplay.widget.ui.PagerTab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.ViewGroup;


public class MainActivity extends BaseActivity implements OnPageChangeListener{
	private PagerTab pagerTab;
	private ViewPager viewPager;
	
	private String[] title;
	
	//初始化视图
	@Override
	public void initView() {
		setContentView(R.layout.activity_main);
		DrawerLayout layout = new DrawerLayout(this);
		//初始化视图
		pagerTab=(PagerTab)findViewById(R.id.tabs);
		
		//pagerTab.setHasTransientState(true);
		
		viewPager=(ViewPager)findViewById(R.id.pager);
		//初始化适配器
		//初始化tab
		BaseFragmentAdapter baseFragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager());
		//设置适配器
		viewPager.setAdapter(baseFragmentAdapter);
		//还要给tab设置viewpager,绑定viewpager和滚动的title
		pagerTab.setViewPager(viewPager);
		//给这个tab设置当页面改变的时候，这个里面重写了viewpager的onPagerChangeListener()方法
		pagerTab.setOnPageChangeListener(this);

		
		
		
	}

	@Override
	public void initTitleBar() {
		ActionBar actionBar = getSupportActionBar();
		
	}
	
	//这个类是专门针对fragment指定的，一般fragment适配器用这个，视图的那个用ViewPagerAdapter
	private class BaseFragmentAdapter extends FragmentStatePagerAdapter{
		private FragmentManager fm;
		
		private String[] title;

		public BaseFragmentAdapter(FragmentManager fm) {
			super(fm);
			this.fm=fm;
			this.title=UIUtils.getStringArray(R.array.tab_names);;
		}

		@Override
		public Fragment getItem(int position) {
		
			return FragmentFactory.createFragment(position);
		}

		@Override
		public int getCount() {
			return title.length;
		}
	

		@Override
		public CharSequence getPageTitle(int position) {
			return title[position];
		}
		
	}

	@Override
	public void onPageScrollStateChanged(int position) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int position) {
		
	}
	//当页面被选中的时候显示
	@Override
	public void onPageSelected(int position) {
		//当页面改变的时候，我需要视图给显示出来，定义一个加载的工具，但是工具只负责加载，显示需要自己，因为别人也不知道你想要显示什么
		BaseFragment fragment = FragmentFactory.createFragment(position);
		fragment.show();
	}

	
}
