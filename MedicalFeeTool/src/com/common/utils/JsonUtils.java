package com.common.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
public class JsonUtils
{
	public static <T> T json2Object(String json, TypeToken<T> type)
	{
		try{
			Gson gson = new Gson();
			return gson.fromJson(json, type.getType());
		}catch (Exception e){
			Log.e("Json字符串转成对象解析出错：", e.getMessage(), e);
		}
		return null;
	}

	public static String object2Json(Object obj)
	{	
		try{
			Gson gson = new Gson();
			return gson.toJson(obj);
		}catch (Exception e){
			Log.e("对象转成Json字符串出错：", e.getMessage(), e);
		}
		return null;
	}
}
