package com.example.z_lw.aaaa;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2016/9/28.
 */
public class FreeDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button,button_dowm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freedetails);
        button = (Button) findViewById(R.id.button_back);
        button.setOnClickListener(this);
        button_dowm = (Button) findViewById(R.id.button_down);
        button_dowm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL("http://139.129.215.221:8080/books/test.txt");
                            URLConnection connection = url.openConnection();
                            InputStream inputStream = connection.getInputStream();
                            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) ;
                            FileOutputStream outputStream = new FileOutputStream(Environment.getExternalStorageDirectory() + File.separator + "test3.txt");
                            byte[] bytes = new byte[1024];
                            int len = inputStream.read(bytes);
                            while (len > 0) {
                                outputStream.write(bytes, 0, len);
                                len = inputStream.read(bytes);
                            }
                            outputStream.flush();
                            outputStream.close();
                            inputStream.close();


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

    @Override
    public void onClick(View view) {
        finish();
    }
}
