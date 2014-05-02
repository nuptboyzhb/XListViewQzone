/*
 * $filename: BaseAdapter1.java,v $
 * $Date: 2014-4-27  $
 * Copyright (C) ZhengHaibo, Inc. All rights reserved.
 * This software is Made by Zhenghaibo.
 */
package edu.njupt.zhb.xlistviewtest;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/*
 *@author: ZhengHaibo  
 *web:     http://blog.csdn.net/nuptboyzhb
 *mail:    zhb931706659@126.com
 *2014-4-27  Nanjing,njupt,China
 */
public class ChatBaseAdapter extends BaseAdapter {

	private Context context;

	private List<Msg> listViewData;

	public ChatBaseAdapter(Context context) {
		this.context = context;
		listViewData = new ArrayList<Msg>();
	}

	/**
	 * 根据发送消息的类型进行分类，不同的消息类型不同的布局
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Msg msg = listViewData.get(position);
		if (msg.isSelf()) {// 自己发送的消息
			convertView = LayoutInflater.from(context).inflate(
					R.layout.list_item_right_text, null);
			ImageView imgHead = (ImageView) convertView
					.findViewById(R.id.imgHead);
			imgHead.setImageBitmap(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.ic_launcher));
			Button btn = (Button) convertView.findViewById(R.id.btn_right_text);
			btn.setText(msg.getContent());
			btn.setOnClickListener(new ListViewButtonOnClickListener(position));
		} else {// 对方发送的消息
			convertView = LayoutInflater.from(context).inflate(
					R.layout.list_item_left_text, null);
			ImageView imgHead = (ImageView) convertView
					.findViewById(R.id.imgHead);
			imgHead.setImageBitmap(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.ic_launcher));
			Button btn = (Button) convertView.findViewById(R.id.btn_left_text);
			btn.setText(msg.getContent());
			btn.setOnClickListener(new ListViewButtonOnClickListener(position));
		}
		return convertView;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listViewData.get(position);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (null == listViewData) {
			return 0;
		}
		return listViewData.size();
	}

	/**
	 * 添加一条记录
	 * 
	 * @param Msg
	 */
	public void addMsg(Msg Msg) {
		listViewData.add(Msg);
	}

	/**
	 * 获取一条记录
	 * 
	 * @param i
	 * @return
	 */
	public Msg getMsg(int i) {
		if (i < 0 || i > listViewData.size() - 1) {
			return null;
		}
		return listViewData.get(i);
	}

	/**
	 * 清除所有数据
	 */
	public void clear() {
		listViewData.clear();
	}

	/**
	 * 添加一组数据
	 * 
	 * @param list
	 *            数据源
	 * @param isHeader
	 *            添加位置：true 添加在原有数据的前方 false 添加在原有数据的后面
	 */
	public void addAll(List<Msg> list, boolean isHeader) {
		if (isHeader) {
			list.addAll(listViewData);
			listViewData = list;
		} else {
			listViewData.addAll(list);
		}
	}

	class ListViewButtonOnClickListener implements OnClickListener {
		private int position;// 记录ListView中Button所在的Item的位置

		public ListViewButtonOnClickListener(int position) {
			this.position = position;
		}

		@Override
		public void onClick(View v) {
			Toast.makeText(context, listViewData.get(position).getContent(),
					Toast.LENGTH_SHORT).show();
		}
	}
}
