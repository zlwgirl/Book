package com.example.z_lw.aaaa;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

/**
 * Created by Z-LW on 2016/10/13.
 * 上传界面
 */
public class UpLoadActivity extends Activity implements View.OnClickListener {

    private Button upLoadBtn, selectBtn;
    private TextView upLoadTv;
    private static String requestURL = "http://139.129.215.221:8080/upload/UploadServlet";
    private String filePath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        upLoadBtn = (Button) findViewById(R.id.uploadfile);
        selectBtn = (Button) findViewById(R.id.selectfile);

        upLoadTv = (TextView) findViewById(R.id.upload_tv);
        upLoadBtn.setOnClickListener(this);
        selectBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.selectfile:
                /***
                 * 这个是调用android内置的intent，来过滤图片文件   ，同时也可以过滤其他的
                 */
                Intent intent = new Intent();
                intent.setType("file/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
                break;
            case R.id.uploadfile:
                File file = new File(filePath);
                if (file != null) {
                    String request = UploadUtil.UploadUtil(file, requestURL);
                    upLoadBtn.setText(request);
                }

                break;

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            /**
             * 当选择的图片不为空的话，在获取到图片的途径
             */
            String FilePath = data.getDataString();
            String RealFilePath = FilePath.substring(7, FilePath.length());
            // System.out.println("path------->" + mRealFilePath);
            if (RealFilePath.endsWith("pdf") || RealFilePath.endsWith("txt")) {
                filePath = RealFilePath;
                upLoadTv.setText("文件路径:" + RealFilePath);
            } else {
                alert();
            }
        } else {
            alert();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void alert() {
        Dialog dialog = new AlertDialog.Builder(this).setTitle("提示")
                .setMessage("您选择的不是有效的文件")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        filePath = null;
                    }
                }).create();
        dialog.show();

    }
}
