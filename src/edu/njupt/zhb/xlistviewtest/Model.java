/*
 * $filename: Model.java,v $
 * $Date: 2014-4-27  $
 * Copyright (C) ZhengHaibo, Inc. All rights reserved.
 * This software is Made by Zhenghaibo.
 */
package edu.njupt.zhb.xlistviewtest;

import java.util.List;

import org.androidannotations.annotations.EBean;

/*
 *@author: ZhengHaibo  
 *web:     http://blog.csdn.net/nuptboyzhb
 *mail:    zhb931706659@126.com
 *2014-4-27  Nanjing,njupt,China
 */
@EBean
public class Model {
	private int imgHead;// 头像资源ID
	private String name;// 姓名
	private String date;// 日期
	private String phonemodel;// 手机型号
	private int type;// 消息类型
	private boolean agree;//是否点过赞
	private String address;//位置信息
	private List<String> agreeShow;//获得“赞”的姓名列表
	private List<String> comment;//用户评论列表
	public List<String> getAgreeShow() {
		return agreeShow;
	}
	public void setAgreeShow(List<String> agreeShow) {
		this.agreeShow = agreeShow;
	}
	public List<String> getComment() {
		return comment;
	}
	public void setComment(List<String> comment) {
		this.comment = comment;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isAgree() {
		return agree;
	}
	public void setAgree(boolean agree) {
		this.agree = agree;
	}
	private String content;// 消息内容
	private List<String> imageUrls;// 图片的Url地址
	public int getImgHead() {
		return imgHead;
	}
	public void setImgHead(int imgHead) {
		this.imgHead = imgHead;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPhonemodel() {
		return phonemodel;
	}
	public void setPhonemodel(String phonemodel) {
		this.phonemodel = phonemodel;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}
}
