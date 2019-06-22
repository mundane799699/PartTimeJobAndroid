package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import com.example.myapp.R;

public class MyService extends Service {

	MediaPlayer mediaPlayer;
	public MyService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mediaPlayer=MediaPlayer.create(this, R.raw.wjh);
		Log.e("TAG","create");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		paly();
		Log.e("TAG","start");
		return super.onStartCommand(intent, flags, startId);
	}
	private void paly() {
		mediaPlayer.start();
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		mediaPlayer.stop();
		Log.e("TAG","destoryed");
	}
	

}
