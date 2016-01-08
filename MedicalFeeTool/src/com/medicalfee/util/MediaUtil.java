package com.medicalfee.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;

public class MediaUtil {
	private static boolean canMediaPlay = true;

	// /**
	// * 创建本地MP3
	// *
	// * @return
	// */
	// private static MediaPlayer createLocalMp3(Context context, int raw) {
	// /**
	// * 创建音频文件的方法：
	// * 1、播放资源目录的文件：MediaPlayer.create(MainActivity.this,R.raw.beatit
	// * );//播放res/raw 资源目录下的MP3文件 2:播放sdcard卡的文件：mediaPlayer=new
	// * MediaPlayer(); mediaPlayer.setDataSource("/sdcard/beatit.mp3");//
	// * 前提是sdcard卡要先导入音频文件
	// */
	// MediaPlayer mp = MediaPlayer.create(context, raw);
	// mp.stop();
	// return mp;
	// }

	public static void startMP3(Context context, int raw) {
		if (!canMediaPlay) {
			return;
		}
		canMediaPlay = false;
		MediaPlayer mediaPlayer = MediaPlayer.create(context, raw);
		// 当播放完音频资源时，会触发onCompletion事件，可以在该事件中释放音频资源，
		// 以便其他应用程序可以使用该资源:
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				canMediaPlay = true;
				mp.stop();
				mp.release();// 释放音频资源
				Log.d("MediaPlayer", "资源已经被释放了");
			}
		});
		try {
			// 在播放音频资源之前，必须调用Prepare方法完成些准备工作
			// mediaPlayer.prepare();
			// 开始播放音频
			mediaPlayer.start();
		} catch (Exception e) {
			Log.e("声音出现问题", e.getMessage());
		}
	}
}
