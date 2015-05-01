package com.example.googleplay.fragment;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.googleplay.BaseFragment;
import com.example.googleplay.application.utils.UIUtils;
import com.example.googleplay.widget.ui.LoadingPager.ResultType;

public class HomeFragment extends BaseFragment {
	private ArrayList<String> list;

	//应该返回数据加载的结果，对数据进行检查
	@Override
	protected ResultType load() {
		list = new ArrayList<String>();
		for (int i=0;i<100;i++) {
			list.add("小苹果"+i);
		};
		return checkLoadResult(list);
	}
	
	//这个是用来显示当数据加载成功的时候，显示的视图
	@Override
	protected View createSuccessView() {
		ListView view = new ListView(UIUtils.getContext());
		HomeAdapter adapter = new HomeAdapter(list);
		view.setAdapter(adapter);
		return view;
	}
	
	class HomeAdapter extends BaseAdapter{

		private List<String> listData;
		public HomeAdapter(List<String> list) {
			this.listData=list;
		}
		@Override
		public int getCount() {
			return listData.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView textView = new TextView(UIUtils.getContext());
			textView.setText(listData.get(position));
			return textView;
		}
		
	}

}
