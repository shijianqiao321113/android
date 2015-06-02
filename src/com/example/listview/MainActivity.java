package com.example.listview;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnScrollListener{

	ListView listview;
	SimpleAdapter listAdapter;
	ArrayList<HashMap<String, String>> list;
	int con=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listview = (ListView) findViewById(R.id.list);
		
		list = new ArrayList<HashMap<String, String>>();
		for(int i=0;i<20;i++){
			con++;
			// 创建表
			HashMap<String, String> map1 = new HashMap<String, String>();
			// 初始化表数据
			map1.put("cc", con+"user");
			map1.put("aa", con+"addr");
			// 将表添加进list
			list.add(map1);
			// 新建容器adapter
		}
		listAdapter = new SimpleAdapter(
				this,
				list,
				R.layout.user,
				new String[] { "cc", "aa" }, 
				new int[] { R.id.textUsrName, R.id.textUsrAddr }
			);
		//listview 绑定数据
		listview.setAdapter(listAdapter);
		
		TextView textView=new TextView(this);
		textView.setText("底部加载中，，，");
		textView.setGravity(BIND_AUTO_CREATE);
		listview.addFooterView(textView);
		
		TextView textv=new TextView(this);
		textv.setText("头部加载中，，，");
		textv.setGravity(BIND_AUTO_CREATE);
		listview.addHeaderView(textv);
		
		//listview 添加监听
		listview.setOnScrollListener(this);

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		switch (scrollState) {
        // 当不滚动时
        case OnScrollListener.SCROLL_STATE_IDLE:// 是当屏幕停止滚动时
        	if(listview.getCount()-1==listview.getLastVisiblePosition()){
        		try {
					Thread.sleep(2*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        		for(int i=0;i<20;i++){
        			con++;
        			// 创建表
        			HashMap<String, String> map1 = new HashMap<String, String>();
        			// 初始化表数据
        			map1.put("cc", con+"user");
        			map1.put("aa", con+"addr");
        			// 将表添加进list
        			list.add(map1);
        			// 新建容器adapter
        		}
        		listAdapter.notifyDataSetChanged();
        		return ;
        	}else if(listview.getFirstVisiblePosition()==0){
        		try {
					Thread.sleep(2*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        		//listview 在顶部状态
        		listAdapter.notifyDataSetInvalidated();
        		return ;
        	}
            break;
        case OnScrollListener.SCROLL_STATE_FLING://抬起手列表滚动状态  
            break;
        case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL://触摸不间断滚动  
            break;
        }

		
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		
	}

}
