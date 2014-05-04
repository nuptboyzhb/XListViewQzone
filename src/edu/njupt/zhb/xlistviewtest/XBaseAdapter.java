/*
 * $filename: BaseAdapter1.java,v $
 * $Date: 2014-4-27  $
 * Copyright (C) ZhengHaibo, Inc. All rights reserved.
 * This software is Made by Zhenghaibo.
 */
package edu.njupt.zhb.xlistviewtest;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*
 *@author: ZhengHaibo  
 *web:     http://blog.csdn.net/nuptboyzhb
 *mail:    zhb931706659@126.com
 *2014-4-27  Nanjing,njupt,China
 */
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class XBaseAdapter extends BaseAdapter {

	private Context context;

	private List<Model> listViewData;

	private int layoutResId;// ListView每个Item的布局文件

	public XBaseAdapter(Context context, int layoutResId) {
		this.context = context;
		this.layoutResId = layoutResId;
		listViewData = new ArrayList<Model>();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(context).inflate(layoutResId, null);
		Model model = listViewData.get(position);
		ImageView imageView = (ImageView) convertView
				.findViewById(R.id.imgHead);
		imageView.setImageBitmap(BitmapFactory.decodeResource(
				context.getResources(), model.getImgHead()));
		TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
		tvName.setText(model.getName());
		TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
		tvDate.setText(model.getDate());
		TextView tvContent = (TextView) convertView
				.findViewById(R.id.tvContent);
		tvContent.setText(model.getContent());
		if (model.getType() == 1) {// 图片资源
			GridView gridView = (GridView) convertView
					.findViewById(R.id.gridview);
			gridView.setVisibility(View.VISIBLE);
			GridAdapter gridAdapter = new GridAdapter(context,
					R.layout.gridview_item, model.getImageUrls());
			gridView.setAdapter(gridAdapter);
		}
		if (!model.getAddress().isEmpty()) {
			ImageView ivAddress = (ImageView) convertView
					.findViewById(R.id.ivAddress);
			ivAddress.setVisibility(View.VISIBLE);
			TextView tvAddress = (TextView) convertView
					.findViewById(R.id.tvAddress);
			tvAddress.setVisibility(View.VISIBLE);
			tvAddress.setText(model.getAddress());
		}
		TextView tvPhonemodel = (TextView) convertView
				.findViewById(R.id.tvPhonemodel);
		tvPhonemodel.setText(model.getPhonemodel());
		ImageView ivAgree = (ImageView) convertView.findViewById(R.id.ivAgree);
		if (model.isAgree()) {
			ivAgree.setImageResource(R.drawable.qzone_picviewer_bottom_praise_icon);
		} else {
			ivAgree.setImageResource(R.drawable.qzone_picviewer_bottom_unpraise_icon);
		}
		ivAgree.setOnClickListener(new ListViewButtonOnClickListener(position));
		ivAgree.setFocusable(false);
		ImageView ivComment = (ImageView) convertView
				.findViewById(R.id.ivComment);
		ivComment
				.setOnClickListener(new ListViewButtonOnClickListener(position));
		ivComment.setFocusable(false);
		if (null != model.getAgreeShow() && model.getAgreeShow().size() > 0) {
			ImageView ivAgreeShow = (ImageView) convertView
					.findViewById(R.id.ivAgreeShow);
			ivAgreeShow.setVisibility(View.VISIBLE);
			TextView tvAgreeShow = (TextView) convertView
					.findViewById(R.id.tvAgreeShow);
			tvAgreeShow.setVisibility(View.VISIBLE);
			tvAgreeShow.setText(model.getAgreeShow().toString() + "觉得很赞！");
		}
		Button btnSendComment = (Button) convertView
				.findViewById(R.id.btnSendComment);
		btnSendComment.setOnClickListener(new ListViewButtonOnClickListener(
				position));
		btnSendComment.setFocusable(false);
		EditText etComment = (EditText)convertView.findViewById(R.id.etComment);
		etComment.setOnKeyListener(new ListViewOnKeyListener(position));
		etComment.setFocusable(false);
		if(null != model.getComments() && model.getComments().size()>0){
			TextView tvComments = (TextView)convertView.findViewById(R.id.tvComments);
			tvComments.setVisibility(View.VISIBLE);
			String string = "";
			for(String comment:model.getComments()){
				string+=comment;
			}
			tvComments.setText(string);
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
	 * @param model
	 */
	public void addModel(Model model) {
		listViewData.add(model);
	}

	/**
	 * 添加一条记录
	 * 
	 * @param model
	 * @param insertHead
	 *            true:插入在头部
	 */
	public void addModel(Model model, boolean insertHead) {
		if (insertHead) {
			listViewData.add(0, model);
		} else {
			listViewData.add(model);
		}
	}

	/**
	 * 获取一条记录
	 * 
	 * @param i
	 * @return
	 */
	public Model getModel(int i) {
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

	class ListViewButtonOnClickListener implements OnClickListener {
		private int position;// 记录ListView中Button所在的Item的位置

		public ListViewButtonOnClickListener(int position) {
			this.position = position;
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ivAgree:
				ImageView ivAgree = (ImageView) v;
				Model model = listViewData.get(position);
				List<String> agreeShow = model.getAgreeShow();
				if (null == agreeShow || agreeShow.size() <= 0) {
					agreeShow = new ArrayList<String>();
				}
				if (model.isAgree()) {
					agreeShow.remove("我");
					ivAgree.setImageResource(R.drawable.qzone_picviewer_bottom_unpraise_icon);
				} else {
					agreeShow.add("我");
					ivAgree.setImageResource(R.drawable.qzone_picviewer_bottom_praise_icon);
				}
				model.setAgree(!model.isAgree());
				model.setAgreeShow(agreeShow);
				notifyDataSetChanged();
				// Toast.makeText(context, "你点了赞", Toast.LENGTH_SHORT).show();
				break;
			case R.id.ivComment:
				Toast.makeText(context, "你点了评论", Toast.LENGTH_SHORT).show();
				break;
			case R.id.btnSendComment:

				break;
			default:
				break;
			}
		}
	}

	class ListViewOnKeyListener implements OnKeyListener {
		private int position;
		
		public ListViewOnKeyListener(int position){
			this.position = position;
		}
		
		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_ENTER
					&& event.getAction() == KeyEvent.ACTION_DOWN) {
				EditText editView = (EditText)v;
				String comment = editView.getEditableText().toString();
				Model model = listViewData.get(position);
				List<String> comments = model.getComments();
				if(null == comments){
					comments = new ArrayList<String>();
				}
				comment = "[me]:"+comment+"\n";
				comments.add(comment);
				model.setComments(comments);
				notifyDataSetChanged();
			}
			return false;
		}

	}
}
