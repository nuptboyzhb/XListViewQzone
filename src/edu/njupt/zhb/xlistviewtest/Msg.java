/*
 * $filename: Msg.java,v $
 * $Date: 2014-4-27  $
 * Copyright (C) ZhengHaibo, Inc. All rights reserved.
 * This software is Made by Zhenghaibo.
 */
package edu.njupt.zhb.xlistviewtest;

import android.R.integer;

/*
 *@author: ZhengHaibo  
 *web:     http://blog.csdn.net/nuptboyzhb
 *mail:    zhb931706659@126.com
 *2014-4-27  Nanjing,njupt,China
 */
public class Msg {
	private int imgHead;
	private boolean self;
	public boolean isSelf() {
		return self;
	}
	public void setSelf(boolean self) {
		this.self = self;
	}
	private String content;
	public int getImgHead() {
		return imgHead;
	}
	public void setImgHead(int imgHead) {
		this.imgHead = imgHead;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
