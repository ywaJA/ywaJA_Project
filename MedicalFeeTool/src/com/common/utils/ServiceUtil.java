/**
 * sunrise, Inc. All rights reserved. Copyright (C): 2015
 */
package com.common.utils;

import java.util.Iterator;
import java.util.Set;

import org.apache.http.Header;

import android.util.Log;

import com.common.config.Constants;
import com.common.model.BaseCoreBean;
import com.common.service.BaseService;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

public class ServiceUtil {

	/**
	 * 
	 * @param vo
	 *            请求参数对象
	 * @param type
	 *            返回数据json映射对象
	 * @param baseService
	 *            回调接口类
	 */
	public static <T> void getJsonData(BaseCoreBean vo, TypeToken<T> type, BaseService<T> baseService) {
		RequestParams params = getParamsByObject(vo);
		if (Constants.DATA_COMPRESS_NO == vo.getDataCompress()) {
			doNoCOMPRESS(vo, params, baseService, type);
		} else {
			doCOMPRESS(vo, params, baseService, type);
		}
	}

	/**
	 * 拼接参数
	 * 
	 * @param vo
	 * @return
	 */
	private static RequestParams getParamsByObject(BaseCoreBean vo) {
		RequestParams params = new RequestParams();
		if (vo.getPage() != null) {
			params.put("page", vo.getPage());
		}
		if (vo.getRows() != null) {
			params.put("rows", vo.getRows());
		}
		if (vo.getMap() != null && !vo.getMap().isEmpty()) {
			Set<String> key = vo.getMap().keySet();
			for (Iterator<String> it = key.iterator(); it.hasNext();) {
				String s = (String) it.next();
				params.put(s, vo.getMap().get(s));
			}
		}
		return params;
	}

	/**
	 * 返回非压缩数据
	 * 
	 * @param vo
	 * @param params
	 * @param baseService
	 * @param type
	 */
	private static <T> void doNoCOMPRESS(BaseCoreBean vo, RequestParams params, BaseService<T> baseService, TypeToken<T> type) {
		if (Constants.REQUEST_MODE_GET == vo.getRequestMode()) {
			doGetNoCOMPRESS(vo, params, baseService, type);
		} else {
			doPostNoCOMPRESS(vo, params, baseService, type);
		}
	}

	/**
	 * 
	 * @param vo
	 * @param params
	 * @param baseService
	 * @param type
	 */
	private static <T> void doCOMPRESS(BaseCoreBean vo, RequestParams params, BaseService<T> baseService, TypeToken<T> type) {
		switch (vo.getRequestMode()) {
		case Constants.REQUEST_MODE_GET:
			doGetCOMPRESS(vo, params, baseService, type);
			break;
		default:
			doPostCOMPRESS(vo, params, baseService, type);
			break;

		}
	}

	/**
	 * 执行非压缩get请求
	 * 
	 * @param vo
	 * @param params
	 * @param baseService
	 * @param type
	 */
	private static <T> void doGetNoCOMPRESS(final BaseCoreBean vo, RequestParams params, final BaseService<T> baseService, final TypeToken<T> type) {
		HttpUtils.doGetNoCompress(vo.getUrl(), params, new TextHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				Log.i("请求成功：", arg2);
				baseService.callBackJsonData(JsonUtils.json2Object(arg2, type));
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
				Log.e("Get请求服务器日志：", vo.getUrl() + "数据请求返回出错！" + arg2);
				baseService.callBackFail(arg2);
			}
		});
	}

	/**
	 * 执行非压缩post请求
	 * 
	 * @param vo
	 * @param params
	 * @param baseService
	 * @param type
	 */
	private static <T> void doPostNoCOMPRESS(final BaseCoreBean vo, RequestParams params, final BaseService<T> baseService, final TypeToken<T> type) {
		HttpUtils.doPostNoCompress(vo.getUrl(), params, new TextHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				Log.i("请求成功：", arg2);
				baseService.callBackJsonData(JsonUtils.json2Object(arg2, type));
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
				Log.e("Get请求服务器日志：", vo.getUrl() + "数据请求返回出错！" + arg2);
				baseService.callBackFail(arg2);
			}
		});
	}

	/**
	 * 执行压缩get请求
	 * 
	 * @param vo
	 * @param params
	 * @param baseService
	 * @param type
	 */
	private static <T> void doGetCOMPRESS(final BaseCoreBean vo, RequestParams params, final BaseService<T> baseService, final TypeToken<T> type) {
		HttpUtils.doGet(vo.getUrl(), params, new TextHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				Log.i("请求成功：", arg2);
				baseService.callBackJsonData(JsonUtils.json2Object(arg2, type));
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
				Log.e("Get请求服务器日志：", vo.getUrl() + "数据请求返回出错！" + arg2);
				baseService.callBackFail(arg2);
			}
		});
	}

	/**
	 * 执行压缩post
	 * 
	 * @param vo
	 * @param params
	 * @param baseService
	 * @param type
	 */
	private static <T> void doPostCOMPRESS(final BaseCoreBean vo, RequestParams params, final BaseService<T> baseService, final TypeToken<T> type) {
		HttpUtils.doPost(vo.getUrl(), params, new TextHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				Log.i("请求成功：", arg2);
				baseService.callBackJsonData(JsonUtils.json2Object(arg2, type));
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
				Log.e("Post请求服务器日志：", vo.getUrl() + "数据请求返回出错！" + arg2);
				baseService.callBackFail(arg2);
			}
		});
	}

}
