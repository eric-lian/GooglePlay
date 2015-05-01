package com.example.googleplay.factory;

import java.util.HashMap;

import com.example.googleplay.BaseFragment;
import com.example.googleplay.fragment.AppFragment;
import com.example.googleplay.fragment.CategoryFragment;
import com.example.googleplay.fragment.GameFragment;
import com.example.googleplay.fragment.HomeFragment;
import com.example.googleplay.fragment.HotFragment;
import com.example.googleplay.fragment.RecommnetFragment;
import com.example.googleplay.fragment.SubjectFragment;

/**
 *=====================版权：郑州大学西亚斯国际学院 起点工作室 =================
 *=====================技术支持：起点工作室==============================
 *=====================日期：2015-4-30 下午5:13:58====================
 *=====================作者：LJW=====================================
 *这是一个Fragment工厂类，为主页提供fragment,每个项目都很有可能会用到这个工厂
 */
public class FragmentFactory {
	//分别初始化标题 首页，程序，游戏 ，专题，推荐，分类，排行
	private static final int TITLE_HOME=0;
	private static final int TITLE_APP=1;
	private static final int TITLE_GAME=2;
	private static final int TITLE_SUBJECT=3;
	private static final int TITLE_RECOMMENT=4;
	private static final int TITLE_CATEGORY=5;
	private static final int TITLE_HOT=6;
 	
	//缓存fragment
	private static HashMap<Integer, BaseFragment> fragments=new HashMap<Integer, BaseFragment>();
	//创建一个工厂类
	public static BaseFragment createFragment(Integer position){
		
		BaseFragment fragment=null;
		//查看当前缓存是否有所需要的fragment
		fragment=fragments.get(position);
		if(null!=fragment){
			return fragment;
		}
		//switch case 语句只能接受一个常量值
		switch (position) {
		case TITLE_HOME:
			fragment=new HomeFragment();
			break;
		case TITLE_APP:
			fragment=new AppFragment();
			break;
		case TITLE_GAME:
			fragment=new GameFragment();
			break;
		case TITLE_SUBJECT:
			fragment=new SubjectFragment();
			break;
		case TITLE_RECOMMENT:
			fragment=new RecommnetFragment();
			break;
		case TITLE_CATEGORY:
			fragment=new CategoryFragment();
			break;
		case TITLE_HOT:
			fragment=new HotFragment();
			break;
		default:
			break;
		}
		fragments.put(position, fragment);
		
		return fragment;
	};
}
