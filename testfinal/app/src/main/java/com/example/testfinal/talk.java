package com.example.testfinal;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class talk extends Activity {
	static Socket socket;//声明socket变量
	Handler handler;	//声明handler变量
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.talk);
		final EditText name=(EditText) findViewById(R.id.name);
		final EditText sentence=(EditText) findViewById(R.id.sentence);
		final EditText show=(EditText) findViewById(R.id.show);
		final Button send=(Button) findViewById(R.id.send);
		this.new ChatThread().start();
		send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String s1=name.getText().toString();
				String s2=sentence.getText().toString();
				String s=s1+":"+s2+"\n";
				if(socket!=null){
					try{//获取输出流发送消息
						socket.getOutputStream().write(s.getBytes("utf-8"));
					}catch(Exception e){
						e.printStackTrace();
					}
					sentence.setText("");
				}
				
			}
		});
		handler=new Handler(){//使用handler处理消息
			public void handleMessage(Message msg){
				super.handleMessage(msg);
				String tmp=show.getText().toString();//获取show原来显示内容
				show.setText(tmp+"\n\n"+msg.getData().getString("chat"));
			}
		};
		
	}
	
	
	class ChatThread extends Thread{
		Socket s;
		public ChatThread(){
		
		}
		public void run(){
			try{
				s=new Socket("10.0.2.2",12345);//创建socket，传入IP地址和端口号
				talk.socket=s;
				
			}catch(Exception e){
				e.printStackTrace();
			}
			try{//从socket当中获取getInputStream()输入流
				//将InputStream转换成BufferedHeader
				BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream(),"utf-8"));
				while(true){
					String sentence=in.readLine();//读取数据
					if(sentence!=null){
						Bundle b=new Bundle();//创建bundle
						b.putString("chat", sentence);//把数据放入bundle里
						Message msg=Message.obtain();//获取Message
						msg.setData(b);//把bundle设置给Message
						handler.sendMessage(msg);//使用Handler发送消息
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}
	
}

