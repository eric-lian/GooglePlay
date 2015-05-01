package com.example.googleplay;

import java.util.List;

import com.example.googleplay.application.utils.UIUtils;
import com.example.googleplay.widget.ui.LoadingPager;
import com.example.googleplay.widget.ui.LoadingPager.ResultType;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/**
 *==========版权：郑州大学西亚斯国际学院 起点工作室 =====================
 *==========技术支持：起点工作室==================================
 *==========日期：2015-4-30 下午5:41:02============================
 *==========作者：LJW========================================
 *描述：为各个fragment构造一个父类，把所有公共的可抽取的代码抽取出来，要掌握一种思想，当有很多地方用到重复性的东西的时候，就要利用
 *合理的手段抽取出来
 *
 *ctrl+t 显示当前类的结构
 *ctrl+k 参照当前选中的快速定位到下一个
 *ctrl+j 增量查找
 *
 */
public abstract class BaseFragment extends Fragment{

	private LoadingPager loadingPager=null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(null==loadingPager){
			loadingPager = new LoadingPager(UIUtils.getContext()) {
				@Override
				public ResultType load() {
					return BaseFragment.this.load();
				}
				
				@Override
				public View createSuccessView() {
					return BaseFragment.this.createSuccessView();
				}
			};
		};
		
		return loadingPager;
		
	}

	protected abstract ResultType load() ;

	protected abstract View createSuccessView();
	
	public void show(){
		if(null!=loadingPager){
			loadingPager.show();
		}
	}
	//这个方法是对数据返回的结果进行检查
	public  ResultType checkLoadResult(Object obj) {
		//数据加载失败
		if(null==obj){
			return ResultType.ERROR;
		}
		
		if(obj instanceof List){
			List list=(List)obj;
			return list.size()>0?ResultType.SUCCESS :ResultType.EMPTY;
		}
		return ResultType.SUCCESS;
	}
}


