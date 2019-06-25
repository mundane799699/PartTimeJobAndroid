package com.example.adapter;

import com.example.Bean;
import com.example.xstrategy.R;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class XAdapter extends BaseAdapter{

	private Context context;
	private List<Bean> list;
	
	public XAdapter(Context context,List<Bean> list){
		this.context = context;
		this.list = list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if(arg1 == null){
			arg1 = LayoutInflater.from(context).inflate(R.layout.item_information, arg2,false);
		}
		
		Bean bean = list.get(arg0);
		ImageView iv = (ImageView)arg1.findViewById(R.id.iv_item);
		TextView tv_title = (TextView)arg1.findViewById(R.id.tv_item_title);
		TextView tv_content = (TextView)arg1.findViewById(R.id.tv_item_content);
		
		iv.setImageResource(bean.getImage());
		tv_title.setText(bean.getTitle());
		tv_content.setText(bean.getContent());
		
		return arg1;
	}

}
