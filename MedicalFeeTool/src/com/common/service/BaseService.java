/**
 * sunrise, Inc. All rights reserved. Copyright (C): 2015
 */
package com.common.service;

import android.util.Log;

public abstract class BaseService<T> {

	/**
	 * 远程数据请求回调方法
	 * 
	 * @param <T>
	 * @param strJson
	 * @return
	 */
	public abstract void callBackJsonData(T object);

	/**
	 * 远程数据请求回调失败方法
	 * 
	 * @param msg
	 *            错误信息
	 */
	public void callBackFail(String msg) {
		Log.e("接口数据回调出错：", msg);
	}

}
