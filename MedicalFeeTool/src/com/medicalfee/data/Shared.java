package com.medicalfee.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Shared {
	public static String APPINFO = "shared";
	public static final String SHARED_NAME = "gson";

	/**
	 * 存放数据
	 * 
	 * @param context
	 * @param shareName
	 * @param key
	 * @param value
	 */
	public static void saveValue(Context context, String shareName, String key, String value) {
		getSharePreferences(context, shareName).edit().putString(key, value).commit();
	}

	public static SharedPreferences getSharePreferences(Context context, String shareName) {
		return context.getSharedPreferences(shareName, Activity.MODE_PRIVATE);
	}

	/**
	 * 缓存map集合
	 * 
	 * @param context
	 * @param key
	 * @param data
	 */

	public static void save(Context context, String key, Map<String, String> data) {
		Editor editor = context.getSharedPreferences(key, Activity.MODE_PRIVATE).edit();
		Iterator<String> keys = data.keySet().iterator();
		while (keys.hasNext()) {
			String string = (String) keys.next();
			editor.putString(string, data.get(string));
		}
		editor.commit();
	}

	public static void put(Context context, String key, String value) {
		SharedPreferences share = context.getSharedPreferences(APPINFO, Context.MODE_PRIVATE);
		if (value != null)
			share.edit().putString(key, value).commit();
		else
			share.edit().remove(key).commit();
	}

	public static String get(Context context, String key, String defaultValue) {
		SharedPreferences share = context.getSharedPreferences(APPINFO, Context.MODE_PRIVATE);
		String flag = share.getString(key, defaultValue);
		return flag;
	}

	/**
	 * 缓存map集合
	 * 
	 * @param context
	 * @param key
	 * @param mData
	 */
	public static Map<String, String> get(Context context, String key) {
		Map<String, String> data = new HashMap<String, String>();
		SharedPreferences share = context.getSharedPreferences(key, Activity.MODE_PRIVATE);
		Map<String, ?> shareData = share.getAll();
		Iterator<String> keys = shareData.keySet().iterator();
		while (keys.hasNext()) {
			String string = (String) keys.next();
			data.put(string, shareData.get(string).toString());
		}
		return data;
	}

	/**
	 * 清空某个key值的缓存
	 * 
	 * @param context
	 * @param key
	 */
	public static void clear(Context context, String key) {
		SharedPreferences share = context.getSharedPreferences(key, Activity.MODE_PRIVATE);
		if (share != null) {
			share.edit().clear().commit();
		}
	}

	/**
	 * 缓存gson信息
	 * 
	 * @param <E>
	 */

	public static void saveJson(Context context, String key, String src) {
		Editor editor = context.getSharedPreferences(SHARED_NAME, Activity.MODE_PRIVATE).edit();
		editor.putString(key, src);
		editor.commit();
	}

	public static void saveGson(Context context, String key, Object object) {
		Gson gson = new Gson();
		String json = gson.toJson(object);
		Editor editor = context.getSharedPreferences(SHARED_NAME, Activity.MODE_PRIVATE).edit();
		editor.putString(key, json);
		editor.commit();
	}

	/**
	 * 取出gson信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getGson(Context context, String key, TypeToken<T> token) {
		SharedPreferences share = context.getSharedPreferences(SHARED_NAME, Activity.MODE_PRIVATE);
		if (share != null) {
			String json = share.getString(key, "");
			if (json != null && !"".equals(json)) {
				Gson gson = new Gson();
				return (T) gson.fromJson(json, token.getType());
			}
		}
		return null;
	}

	/**
	 * 获取JSON字符串
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static String getJsonStr(Context context, String key) {
		SharedPreferences share = context.getSharedPreferences(SHARED_NAME, Activity.MODE_PRIVATE);
		if (share != null) {
			return share.getString(key, "");
		}
		return null;
	}

	public static boolean saveArray(Context context, List<?> data) {
		SharedPreferences share = context.getSharedPreferences(SHARED_NAME, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = share.edit();
		editor.putInt("Status_size", data.size());

		for (int i = 0; i < data.size(); i++) {
			editor.remove("Status_" + i);
			editor.putString("Status_" + i, "" + data.get(i));
		}

		return editor.commit();
	}

	// public static List<?> getArray(Context context,MedicalFeeModel model) {
	// List<MedicalFeeModel> data;
	// SharedPreferences sharedPreference =
	// PreferenceManager.getDefaultSharedPreferences(context);
	// data.clear();
	// int size = sharedPreference.getInt("Status_size", 0);
	//
	// for (int i = 0; i < size; i++) {
	// data.add(sharedPreference.getString("Status_" + i, null));
	// }
	// }
}
