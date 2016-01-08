package com.common;

import java.lang.reflect.Field;

import android.util.Log;

public class Constants {
	public static final String MY_PROCESS_NAME = "com.sunrise.iccs";// 自己的进程名字

	public static final int REQUEST_MODE_POST = 0;// post请求方式
	public static final int REQUEST_MODE_GET = 1;// get请求方式

	public static final int DATA_COMPRESS_YES = 0;// 数据是压缩
	/** 数据没有压缩 */
	public static final int DATA_COMPRESS_NO = 1;

	public static final int CAPTURE_RESULT_CODE = 1000;// 扫描结果返回状态

	@SuppressWarnings("rawtypes")
	public static Object getConstantsValue(String key) {
		try {
			Class clazz = Class.forName(Constants.class.getName());
			Field field = clazz.getField(key.toUpperCase());
			return field.get(Constants.class);
		} catch (Exception e) {
			Log.e("获取资源存储目录发生异常：", e.toString());
			return null;
		}
	}
}
