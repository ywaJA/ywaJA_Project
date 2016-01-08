package com.common.utils;

import org.apache.http.Header;

import android.util.Config;
import android.util.Log;

import com.common.config.MyApplication;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * @网络请求工具类
 */
public class HttpUtils {
	private static AsyncHttpClient client = new AsyncHttpClient();
	public static String loginSecretKey = "";

	/**
	 * 无压缩的网络请求,用于访问公开网络接口
	 * 
	 * @param url
	 * @param responseHandler
	 */
	public static void doGetNoCompress(String url, final AsyncHttpResponseHandler responseHandler) {
		url += loginSecretKey;
		client.get(url, responseHandler);
	}

	public static void doGetNoCompress(String url, RequestParams params, final AsyncHttpResponseHandler responseHandler) {
		client.get(url, params, responseHandler);
	}

	/**
	 * 
	 * @param url
	 * @param params
	 * @param responseHandler
	 */
	public static void doPostNoCompress(String url, RequestParams params, final AsyncHttpResponseHandler responseHandler) {
		client.post(url, params, responseHandler);
	}

	/**
	 * 带压缩的网络请求操作,用于访问app后台接口
	 * 
	 * @param url
	 * @param responseHandler
	 */
	public static void doGet(String url, final AsyncHttpResponseHandler responseHandler) {
		final String tempUrl = url;
		url += loginSecretKey;
		Log.d("url", url);
		client.get(url, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				byte[] temp = DataCompress.DecompressData(arg2);
				temp = temp == null ? "".getBytes() : temp;
				responseHandler.onSuccess(arg0, arg1, temp);
				saveData(tempUrl, temp);
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				responseHandler.onFailure(arg0, arg1, arg2, arg3);
			}
		});
	}

	public static void saveData(String url, byte[] value) {
		if (value == null)
			return;
	}

	public static void doPost(String url, RequestParams params, final AsyncHttpResponseHandler responseHandler) {
		final String tempUrl = url + params;
		url += loginSecretKey;
		Log.d("url", url + "&" + params);
		client.post(url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {

				byte[] temp = DataCompress.DecompressData(arg2);
				temp = temp == null ? "".getBytes() : temp;
				responseHandler.onSuccess(arg0, arg1, temp);
				saveData(tempUrl, temp);
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				responseHandler.onFailure(arg0, arg1, (arg2 == null ? "".getBytes() : arg2), arg3);

			}
		});
	}

	public static void doGet(String url, final AsyncHttpResponseHandler responseHandler, int timeout) {

		url += loginSecretKey;
		final String tempUrl = url;
		client.setTimeout(timeout);
		Log.d("url", url);
		client.get(url, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {

				byte[] temp = DataCompress.DecompressData(arg2);
				temp = temp == null ? "".getBytes() : temp;
				responseHandler.onSuccess(arg0, arg1, temp);
				saveData(tempUrl, temp);// 缓存数据
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				responseHandler.onFailure(arg0, arg1, arg2, arg3);
			}
		});

	}

	public static void doGet(String url, RequestParams params, final AsyncHttpResponseHandler responseHandler) {
		Log.d("url", url + "&" + params);
		client.get(url, params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				byte[] temp = DataCompress.DecompressData(arg2);
				temp = temp == null ? "".getBytes() : temp;
				responseHandler.onSuccess(arg0, arg1, temp);
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				responseHandler.onFailure(arg0, arg1, arg2, arg3);

			}
		});
	}
}
