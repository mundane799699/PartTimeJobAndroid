package com.example.daliantear;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class lifu extends Activity{
	String[] names={"年代：先秦(大鱼)","年代：秦汉(参商)","年代：明代(清明上河图)"};
	
	int[] icons=new int[]{R.drawable.xuanduan,
			R.drawable.quju,
			R.drawable.aoqun
	};
	private ListView list;
	private Button button5;
	private TextView jieshaotext,jieshaotext2,jieshaotext3,nametext;
	private List<Map<String,Object>>data=null;
	private MediaPlayer mp;
	private SeekBar volumeBar,posBar;
	private AudioManager am;//音频管理类
	private static int volume=0;
	private TextView curVol;
	private static int times=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lifu);
		volumeBar=(SeekBar) findViewById(R.id.volumeBar);
		posBar=(SeekBar) findViewById(R.id.posBar);
		
		List<Map<String,Object>> lists=new ArrayList<Map<String,Object>>();
		
		for(int i=0;i<names.length;i++){
			Map<String,Object>map=new HashMap<String,Object>();
			map.put("name", names[i]);
			map.put("icon", icons[i]);
			lists.add(map);
		}
		
		SimpleAdapter adapter2=new SimpleAdapter(lifu.this, lists, R.layout.lifu,
				new String[]{"icon","name"},
				new int[]{R.id.icon2,R.id.nameedit});
	}
	private void setMusicParams(){
		//获取系统AudioManager的服务
		am=(AudioManager) getSystemService(AUDIO_SERVICE);
		//设置当前调整音量只针对媒体音量
		lifu.this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		//获取最大音量
		volumeBar.setMax(am.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
		//获取当前音量
		volume=am.getStreamVolume(AudioManager.STREAM_MUSIC);
		volumeBar.setProgress(volume);//设置当前音量条音量
		volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {			}
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {			}
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				am.setStreamVolume(AudioManager.STREAM_MUSIC,arg1,AudioManager.FLAG_PLAY_SOUND);//设置改变后的音量
			}
		});
		posBar.setMax(times);
		posBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				mp.pause();
				mp.seekTo(arg0.getProgress());
				mp.start();
			}
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
			}
		});
	}
	public void playfromraw(View v){
		mp=MediaPlayer.create(this, R.raw.dayu);//创建MediaPlayer对象
		if(times==0)
			times=mp.getDuration();
		setMusicParams();
		mp.start();//播放
	}
	public void pausemusic(View v){
		Button button=(Button) v;
		if(mp.isPlaying()){
			mp.pause();
			button.setText("继续");
		}else{
			mp.start();
			button.setText("暂停");
		}
	}
	public void resetmusic(View v){
		Button button=(Button) v;
		if(mp.isPlaying()){
			mp.seekTo(0);
		}else{
			playfromraw(v);
		}
	}
	public void stopmusic(View v){
		Button button=(Button) v;
		if(mp.isPlaying()){
			mp.stop();
		}
	}
	protected void onDestroy(){
		if (mp != null) {
			mp.release();
		}
		super.onDestroy();
		Log.i("MainActivity", "销毁");
	}
	
	//assets方法
   /* public void playfromasset(View v){
    	mp=new MediaPlayer();
    	try{
    		AssetFileDescriptor fileDescriptor=getAssets().openFd("friend.mp3");
    		mp.setDataSource(fileDescriptor.getFileDescriptor());
    		mp.prepare();
    		mp.start();
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    }*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void submit_li(View v){
		Intent intent =new Intent(lifu.this,dengru.class);
		
		startActivity(intent);
	}
}
