package com.example.z_lw.aaaa;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Z-LW on 2016/9/28.
 * 设置界面
 */
public class SettingActivity extends Activity {
    private TextView cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_set);
        cache = (TextView) findViewById(R.id.cach);
        ImageView backIm = (ImageView) findViewById(R.id.back_im);
        backIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog();
            }
        });

    }

    public void alertDialog() {
        Dialog alertDialog = new AlertDialog.Builder(this).
                setTitle("提示").
                setMessage("您确定清除缓存吗？").
                setIcon(R.mipmap.ic_launcher).
                setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }
                }).
                setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }
                }).create();
        alertDialog.show();
    }
}
