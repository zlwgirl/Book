package com.example.z_lw.aaaa;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/9/28.
 */
public class FreeDetailsActivity extends AppCompatActivity {
    private Button button_back,button_dowm;
    private boolean flag  = false;
    private Handler handler;
    private TextView textView;
    private String sourceUrl = "http://139.129.215.221:8080/books/test.txt";
    private URL url;
    private HttpURLConnection connection;
    private InputStream is;
    private String imageurl = "http://139.129.215.221:8080/images/wyoos.png";
    private BufferedReader bufferedReader;
    private StringBuffer buffer = new StringBuffer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freedetails);
        textView = (TextView) findViewById(R.id.bookstore_textView);
        button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        button_dowm = (Button) findViewById(R.id.button_down);
        //点击下载文件到本地
        button_dowm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            url = new URL(sourceUrl);
                            connection = (HttpURLConnection) url.openConnection();
                            is = connection.getInputStream();
                            if (is != null) {
                                //获取文件扩建名
                                String expandName = sourceUrl.substring(sourceUrl.lastIndexOf(".") + 1, sourceUrl.length()).toLowerCase();
                                //获取文件名
                                String fileName = sourceUrl.substring(sourceUrl.lastIndexOf("/") + 1, sourceUrl.lastIndexOf("."));
                                File file = new File("sdcard/Download/" + fileName + "." + expandName);
                                FileOutputStream fos = new FileOutputStream(file);
                                byte buf[] = new byte[1024];
                                while (true) {
                                    int numread = is.read(buf);
                                    if (numread <= 0) {
                                        break;
                                    } else {
                                        fos.write(buf, 0, numread);
                                    }
                                }
                            }
                            is.close();
                            connection.disconnect();
                            flag = true;
                        } catch (MalformedURLException e1) {
                            e1.printStackTrace();
                            flag = false;
                        } catch (IOException e1) {
                            e1.printStackTrace();
                            flag = false;
                        }
                        Message m = handler.obtainMessage();
                        handler.sendMessage(m);
                    }
                }).start();
                //点击下载将书封面网址传到第一页
                SharedPreferences.Editor sp = getSharedPreferences("download",MODE_PRIVATE).edit();
                sp.putString("url",imageurl);
                sp.commit();
            }
        });
        handler  = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (flag) {
                    Toast.makeText(FreeDetailsActivity.this, "文件下载完成，保存到本地！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FreeDetailsActivity.this, "文件下载失败！", Toast.LENGTH_SHORT).show();
                }
                super.handleMessage(msg);
            }
        };
        //点击显示文本内容
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            url = new URL(sourceUrl);
                            connection = (HttpURLConnection) url.openConnection();
                            is = connection.getInputStream();
                            InputStreamReader reader = new InputStreamReader(is);
                            bufferedReader = new BufferedReader(reader);
                            String line = null;
                            while ((line = bufferedReader.readLine())!=null){
                                buffer.append(line+"\n");
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(buffer.toString());
                                }
                            });
                            bufferedReader.close();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}
