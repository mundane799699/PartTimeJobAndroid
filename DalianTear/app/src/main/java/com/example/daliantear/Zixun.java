package com.example.daliantear;

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

public class Zixun extends Activity{

	static Socket socket;
	Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zixun);
        Button button1 = (Button)findViewById(R.id.button1);
        
        final EditText name=(EditText)findViewById(R.id.name);
        final EditText sentence=(EditText)findViewById(R.id.sentence);
        final Button send=(Button)findViewById(R.id.send);
        final EditText show=(EditText)findViewById(R.id.show);
        this.new ChatThread().start();
      
    	
        send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String s1=name.getText().toString();
				String s2=sentence.getText().toString();
				String s=s1+":"+s2+"\n";
				if(socket!=null){
					try{
						socket.getOutputStream().write(s.getBytes("utf-8"));
					}catch(Exception e){
						e.printStackTrace();
					}
					sentence.setText("");
				}
			}
		});
        handler=new Handler(){
        	public void handleMessage(Message msg){
        		super.handleMessage(msg);
        		String tmp=show.getText().toString();
        		show.setText(tmp+"\n\n"+msg.getData().getString("chat"));
        	}
        };
    }
    class ChatThread extends Thread{
		Socket s;
		public ChatThread(){}
		public void run(){
			try{
	        	s=new Socket("10.0.2.2",12345);
	        	Zixun.socket=s;
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
			try{
				BufferedReader in=new BufferedReader(
					new InputStreamReader(s.getInputStream(),"utf-8"));
					while(true){
						String sentence=in.readLine();
						if(sentence!=null){
							Bundle b=new Bundle();
							b.putString("chat", sentence);
							Message msg=Message.obtain();
							msg.setData(b);
							handler.sendMessage(msg);
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

