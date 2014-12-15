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
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
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

	private Activity activity;

	private List<Model> listViewData;

	private int layoutResId;// ListView每个Item的布局文件

	public XBaseAdapter(Context context, int layoutResId, Activity activity) {
		this.context = context;
		this.layoutResId = layoutResId;
		listViewData = new ArrayList<Model>();
		this.activity = activity;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Model model = listViewData.get(position);
		ViewItemHolder viewItemHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(layoutResId,
					null);
			viewItemHolder = new ViewItemHolder();
			viewItemHolder.imgHead = (ImageView) convertView
					.findViewById(R.id.imgHead);
			viewItemHolder.tvName = (TextView) convertView
					.findViewById(R.id.tvName);
			viewItemHolder.tvDate = (TextView) convertView
					.findViewById(R.id.tvDate);
			viewItemHolder.tvContent = (TextView) convertView
					.findViewById(R.id.tvContent);
			viewItemHolder.ivPhoto = (ImageView) convertView
					.findViewById(R.id.ivPhoto);
			viewItemHolder.ivAddress = (ImageView) convertView
					.findViewById(R.id.ivAddress);
			viewItemHolder.tvAddress = (TextView) convertView
					.findViewById(R.id.tvAddress);
			viewItemHolder.tvPhonemodel = (TextView) convertView
					.findViewById(R.id.tvPhonemodel);
			viewItemHolder.ivAgree = (ImageView) convertView
					.findViewById(R.id.ivAgree);
			viewItemHolder.ivComment = (ImageView) convertView
					.findViewById(R.id.ivComment);
			viewItemHolder.tvComment = (TextView) convertView
					.findViewById(R.id.tvComment);
			viewItemHolder.ivAgreeShow = (ImageView) convertView
					.findViewById(R.id.ivAgreeShow);
			viewItemHolder.tvAgreeShow = (TextView) convertView
					.findViewById(R.id.tvAgreeShow);
			viewItemHolder.btnComment = (Button) convertView
					.findViewById(R.id.btnComment);
			viewItemHolder.tvComments = (TextView) convertView
					.findViewById(R.id.tvComments);
			convertView.setTag(viewItemHolder);
		} else {
			viewItemHolder = (ViewItemHolder) convertView.getTag();
		}
		viewItemHolder.imgHead.setImageBitmap(BitmapFactory.decodeResource(
				context.getResources(), model.getImgHead()));
		viewItemHolder.tvName.setText(model.getName());
		viewItemHolder.tvDate.setText(model.getDate());
		viewItemHolder.tvContent.setText(model.getContent());
		if (model.getType() == FinalVar.MSG_IMAGE) {// 图片资源
			viewItemHolder.ivPhoto.setImageResource(R.drawable.pic_screen);
			viewItemHolder.ivPhoto.setVisibility(View.VISIBLE);
		} else {
			viewItemHolder.ivPhoto.setVisibility(View.GONE);
		}
		if (!model.getAddress().isEmpty()) {
			viewItemHolder.ivAddress.setVisibility(View.VISIBLE);
			viewItemHolder.tvAddress.setVisibility(View.VISIBLE);
			viewItemHolder.tvAddress.setText(model.getAddress());
		} else {
			viewItemHolder.ivAddress.setVisibility(View.GONE);
			viewItemHolder.tvAddress.setVisibility(View.GONE);
		}
		viewItemHolder.tvPhonemodel.setText(model.getPhonemodel());
		viewItemHolder.ivAgree
				.setOnClickListener(new ListViewButtonOnClickListener(position));
		if (model.isAgree()) {
			viewItemHolder.ivAgree
					.setImageResource(R.drawable.qzone_picviewer_bottom_praise_icon);
		} else {
			viewItemHolder.ivAgree
					.setImageResource(R.drawable.qzone_picviewer_bottom_unpraise_icon);
		}
		viewItemHolder.ivAgree.setFocusable(false);
		if (null != model.getAgreeShow() && model.getAgreeShow().size() > 0) {
			viewItemHolder.ivAgreeShow.setVisibility(View.VISIBLE);
			viewItemHolder.tvAgreeShow.setVisibility(View.VISIBLE);
			viewItemHolder.tvAgreeShow.setText(model.getAgreeShow().toString()
					+ "觉得很赞！");
		} else {
			viewItemHolder.ivAgreeShow.setVisibility(View.GONE);
			viewItemHolder.tvAgreeShow.setVisibility(View.GONE);
		}
		viewItemHolder.ivComment
				.setOnClickListener(new ListViewButtonOnClickListener(position));
		viewItemHolder.ivComment.setFocusable(false);
		viewItemHolder.tvComment
				.setOnClickListener(new ListViewButtonOnClickListener(position));
		viewItemHolder.btnComment
				.setOnClickListener(new ListViewButtonOnClickListener(position));
		viewItemHolder.btnComment.setFocusable(false);
		if (null != model.getComments() && model.getComments().size() > 0) {
			viewItemHolder.tvComments.setVisibility(View.VISIBLE);
			String string = "";
			for (String comment : model.getComments()) {
				string += comment + "\n";
			}
			viewItemHolder.tvComments.setText(string);
		} else {
			viewItemHolder.tvComments.setVisibility(View.GONE);
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

	class ViewItemHolder {
		ImageView imgHead;
		TextView tvName;
		TextView tvDate;
		TextView tvContent;
		ImageView ivPhoto;
		ImageView ivAddress;
		TextView tvAddress;
		ImageView ivAgree;
		TextView tvPhonemodel;
		ImageView ivComment;
		TextView tvComment;
		ImageView ivAgreeShow;
		TextView tvAgreeShow;
		Button btnComment;
		TextView tvComments;
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
				listViewData.set(position, model);
				notifyDataSetChanged();
				Toast.makeText(context, "你点了赞", Toast.LENGTH_SHORT).show();
				break;
			case R.id.ivComment:
			case R.id.tvComment:
			case R.id.btnComment:
				InputMethodManager imm = (InputMethodManager) v.getContext()
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
				Model model1 = listViewData.get(position);
				String nikename = model1.getName();
				activity.findViewById(R.id.etComment).setVisibility(
						View.VISIBLE);
				activity.findViewById(R.id.btnSendComment).setVisibility(
						View.VISIBLE);
				((EditText) activity.findViewById(R.id.etComment)).setHint("@"
						+ nikename);
				activity.findViewById(R.id.etComment).setFocusable(true);
				activity.findViewById(R.id.btnSendComment).setOnClickListener(
						new ListViewButtonOnClickListener(position));
				break;
			case R.id.btnSendComment:
				Model mdl = listViewData.get(position);
				List<String> commentsList = mdl.getComments();
				String commentString = ((EditText) activity
						.findViewById(R.id.etComment)).getEditableText()
						.toString();
				if (null == commentsList || commentsList.size() <= 0) {
					commentsList = new ArrayList<String>();
				}
				commentsList.add(commentString);
				mdl.setComments(commentsList);
				listViewData.set(position, mdl);
				notifyDataSetChanged();
				((EditText) activity.findViewById(R.id.etComment)).setText("");
				activity.findViewById(R.id.etComment).setVisibility(View.GONE);
				activity.findViewById(R.id.btnSendComment).setVisibility(
						View.GONE);
				InputMethodManager imm2 = (InputMethodManager) v.getContext()
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm2.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
				break;
			default:
				break;
			}
		}
	}
}
