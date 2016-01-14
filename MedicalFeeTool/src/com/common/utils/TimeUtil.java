package com.common.utils;

import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.common.interfaces.GetCurrentInternetTime;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

@SuppressLint("SimpleDateFormat")
public class TimeUtil {
	public static void ThreadForGetTime(final GetCurrentInternetTime getCurrentInternetTime) {
		final Handler handle = new Handler() {
			@SuppressLint("NewApi")
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch (msg.what) {
				case 0x10:
					getCurrentInternetTime.dealCurrentInternetTime(msg.getData().getString("currentInternetTime", "0"));
					break;

				default:
					break;
				}
				super.handleMessage(msg);
			}
		};
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Message msg = new Message();
					msg.what = 0x10;
					Bundle bundle = new Bundle();
					bundle.putString("currentInternetTime", getCurrentInternetTime());
					msg.setData(bundle);
					handle.sendMessage(msg);
				} catch (Exception e) {
				}
			}
		});
	}

	public static String getCurrentInternetTime() throws Exception {
		URL urlTime = new URL("http://open.baidu.com/special/time");// 取得资源对象
		URLConnection uc = urlTime.openConnection();// 生成连接对象
		uc.connect(); // 发出连接
		long ld = uc.getDate(); // 取得网站日期时间
		Date date = new Date(ld); // 转换为标准时间对象
		String internetCurrentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		return internetCurrentTime;
	}
}