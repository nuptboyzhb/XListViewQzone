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
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*
 *@author: ZhengHaibo  
 *web:     http://blog.csdn.net/nuptboyzhb
 *mail:    zhb931706659@126.com
 *2014-4-27  Nanjing,njupt,China
 */
public class BaseAdapter1 extends BaseAdapter {
	
	private Context context;
	
	private List<Model> listViewData;
	
	private int layoutResId;//ListView每个Item的布局文件
	
	public BaseAdapter1(Context context,int layoutResId) {
		this.context = context;
		this.layoutResId = layoutResId;
		listViewData = new ArrayList<Model>();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(layoutResId,null);
        Model model = listViewData.get(position);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.imgHead);
        imageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), model.getImgHead()));
        TextView tvName = (TextView)convertView.findViewById(R.id.tvName);
        tvName.setText(model.getName());
        TextView tvDate = (TextView)convertView.findViewById(R.id.tvDate);
        tvDate.setText(model.getDate());
        TextView tvContent = (TextView)convertView.findViewById(R.id.tvContent);
        tvContent.setText(model.getContent());
        TextView tvPhonemodel = (TextView)convertView.findViewById(R.id.tvPhonemodel);
        tvPhonemodel.setText(model.getPhonemodel());
        ImageView ivAgree = (ImageView)convertView.findViewById(R.id.ivAgree);
        ivAgree.setOnClickListener(new ListViewButtonOnClickListener(position) );
        ImageView ivComment = (ImageView)convertView.findViewById(R.id.ivComment);
        ivComment.setOnClickListener(new ListViewButtonOnClickListener(position));
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
		if(null == listViewData){
			return 0;
		}
		return listViewData.size();
	}
	
	/**
	 * 添加一条记录
	 * @param model
	 */
	public void addModel(Model model){
		listViewData.add(model);
	}
	
	/**
	 * 获取一条记录
	 * @param i
	 * @return
	 */
	public Model getModel(int i){
		if(i<0||i>listViewData.size()-1){
			return null;
		}
		return listViewData.get(i);
	}
	/**
	 * 清除所有数据
	 */
	public void clear(){
		listViewData.clear();
	}
	
	class ListViewButtonOnClickListener implements OnClickListener{
		private int position;//记录ListView中Button所在的Item的位置
		public ListViewButtonOnClickListener(int position) {
			this.position = position;
		}
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ivAgree:
				ImageView ivAgree = (ImageView)v;
				ivAgree.setImageResource(R.drawable.qzone_picviewer_bottom_praise_icon);
				Toast.makeText(context,"你点了赞", Toast.LENGTH_SHORT).show();
				break;
			case R.id.ivComment:
				Toast.makeText(context,"你点了评论", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		}
	}
}
