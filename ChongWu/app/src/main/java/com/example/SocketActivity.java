package com.example;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import com.example.base.BaseActivity;
import com.example.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketActivity extends BaseActivity {
    
    private static final String TAG = "MainActivity";
    private EditText mEtIP;
    private TextView mTvReceived;
    private EditText mEtContent;
    private View mBtnSend;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        mEtIP = (EditText) findViewById(R.id.et_ip);
        mTvReceived = (TextView) findViewById(R.id.tv_received);
        mEtContent = (EditText) findViewById(R.id.et_content);
        mBtnSend = findViewById(R.id.btn_send);
        findViewById(R.id.btn_confirm).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                connectServer();
            }
        });
    }
    
    private Socket mSocket;
    private BufferedWriter mWriter;
    private BufferedReader mReader;
    // 切记端口号一定要和服务端保持一致！
    private static int PORT = 2345;
    
    private void connectServer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String IP = mEtIP.getText().toString().trim();
                    mSocket = new Socket(IP, PORT);
                    mWriter = new BufferedWriter(new OutputStreamWriter(mSocket.getOutputStream(), "utf-8"));
                    mReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream(), "utf-8"));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showMessage("连接成功");
                            mEtIP.setEnabled(false);
                            mBtnSend.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String content = mEtContent.getText().toString();
                                    if (!TextUtils.isEmpty(content)) {
                                        sendMsg(content);
                                    }
                                }
                            });
                        }
                    });
                } catch (IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showMessage("连接失败");
                        }
                    });
                    e.printStackTrace();
                    return;
                }
                
                try {
                    String line;
                    while ((line = mReader.readLine()) != null) {
                        final String finalLine = line;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String text = mTvReceived.getText().toString();
                                mTvReceived.setText(text + finalLine);
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String text = mTvReceived.getText().toString();
                            mTvReceived.setText(text + "\n服务端已停止服务");
                        }
                    });
                }
            }
        }).start();
    }
    
    private void sendMsg(final String msg) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                // 如果mSocket为null有可能两种情况：
                // 1.还在尝试连接服务端
                // 2.连接失败
                if (mSocket == null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showMessage("连接未完成或连接失败，无法发送消息！");
                        }
                    });
                    return;
                }
                try {
                    //服务端是按行读取消息，所以每条消息最后必须加换行符 \n
                    mWriter.write(msg + "\n");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String text = mTvReceived.getText().toString();
                            mTvReceived.setText(text + "\n你:" + msg + "\n");
                            mEtContent.setText(null);
                        }
                    });
                    mWriter.flush();
                } catch (IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showMessage("发送失败：服务端已关闭服务！");
                        }
                    });
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
