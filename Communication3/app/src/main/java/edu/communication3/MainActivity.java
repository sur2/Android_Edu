package edu.communication3;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class MainActivity extends Activity {
    private EditText et1, et2;
    private Socket socket;
    private DataOutputStream writeSocket;
    private DataInputStream readSocket;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
    }


    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                (new Connect()).start();
                break;
            case R.id.button2:
                (new Disconnect()).start();
                break;
            case R.id.button3:
                (new sendMessage()).start();
                break;
        }
    }

    class Connect extends Thread {
        public void run() {
            Log.d("Connect", "Run Connect");
            String ip = null;
            int port = 0;

            try {
                ip = et1.getText().toString();
                port = Integer.parseInt(et2.getText().toString());
            } catch (Exception e) {
                final String recvInput = "정확히 입력하세요!";
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        setToast(recvInput);
                    }
                });
            }
            try {
                socket = new Socket(ip, port);
                writeSocket = new DataOutputStream(socket.getOutputStream());


                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        setToast("연결에 성공하였습니다.");
                    }

                });
            } catch (Exception e) {
                final String recvInput = "연결에 실패하였습니다.";
                Log.d("Connect", e.getMessage());
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        setToast(recvInput);
                    }

                });

            }

        }
    }

    class Disconnect extends Thread {
        public void run() {
            try {
                if (socket != null) {
                    socket.close();
                    mHandler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            setToast("연결이 종료되었습니다.");
                        }
                    });

                }

            } catch (Exception e) {
                final String recvInput = "연결을 끊는데 실패했습니다.";
                Log.d("Connect", e.getMessage());
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        setToast(recvInput);
                    }

                });

            }

        }
    }

    class sendMessage extends Thread {
        public void run() {
            try {
                byte[] b;
                b = "Hello, Dear~".getBytes();
                writeSocket.write(b);

            } catch (Exception e) {
                final String recvInput = "메시지 전송에 실패하였습니다.";
                Log.d("Message", e.getMessage());
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        setToast(recvInput);
                    }

                });

            }

        }
    }

    void setToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }}


