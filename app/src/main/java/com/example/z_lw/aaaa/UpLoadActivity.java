package com.example.z_lw.aaaa;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Z-LW on 2016/10/13.
 * 上传界面
 */
public class UpLoadActivity extends Activity implements View.OnClickListener {

    private Button upLoadBtn;
    private ImageView selectBtn;
    private TextView upLoadTv;
    private File file;
    String request;
    private EditText mbookEnName, mbookCnName, mpublishing;
    private String filePath = null;
    private TextView mClassify;
    private Spinner mSpinner;// 分类
    private ArrayAdapter<String> adapter;
    String filename;
    private List<String> list = new ArrayList<>();
    private static final String UPLOAD_URL = "http://139.129.215.221:8080/service/UserServlet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        upLoadBtn = (Button) findViewById(R.id.uploadfile);
        selectBtn = (ImageView) findViewById(R.id.selectfile);
        upLoadTv = (TextView) findViewById(R.id.upload_tv);
        mbookEnName = (EditText) findViewById(R.id.my_edit_bookname);
        mbookCnName = (EditText) findViewById(R.id.my_et_cnname);
        mpublishing = (EditText) findViewById(R.id.my_et_publishing);
        upLoadBtn.setOnClickListener(this);
        selectBtn.setOnClickListener(this);
        mClassify = (TextView) findViewById(R.id.classify);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        // 添加一个下拉列表的list
        list.add("数学");
        list.add("语文");
        list.add("英语");
        //为下拉列表定义一个适配器
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        //为适配器设置下拉列表时的菜单样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 将适配器添加到下拉列表
        mSpinner.setAdapter(adapter);
        // 为下拉列表设置各种时间的响应
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // 将所选的值带入textview
                mClassify.setText("您选择的分类是：" + adapter.getItem(i));
                adapterView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                mClassify.setText("NONE");
                adapterView.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.selectfile:
                /***
                 * 调用android内置的intent过滤文件
                 */
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, 1);
                break;
            case R.id.uploadfile:
                file = new File(filePath);
                if (file != null) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            request = UploadUtil.uploadFile(file);
                        }
                    }).start();
                }
                String bookEnName = mbookEnName.getText().toString();
                String bookCnName = mbookCnName.getText().toString();
                String publishing = mpublishing.getText().toString();
                try {
                  filename = URLDecoder.decode(getFileName(filePath), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                String path = "/books/"+filename;
                String coverPath = "/images/"+getFileName(filePath);

                String category = mSpinner.getSelectedItem().toString();
                FinalHttp finalHttp = new FinalHttp();
                AjaxParams params = new AjaxParams();
                params.put("type","importBooksInfo");
                params.put("name", bookEnName);
                params.put("category", category);
                params.put("publishing", publishing);
                params.put("path", path);
                params.put("cname", bookCnName);
                params.put("coverPath", coverPath);
                finalHttp.post(UPLOAD_URL, params, new AjaxCallBack<Object>() {
                    @Override
                    public void onSuccess(Object o) {
                        super.onSuccess(o);
                        decodeJSON(o.toString());
                    }

                    @Override
                    public void onFailure(Throwable t, int errorNo, String strMsg) {
                        super.onFailure(t, errorNo, strMsg);
                    }
                });
                Intent intentUpLoad = new Intent(UpLoadActivity.this, MainActivity.class);
                startActivity(intentUpLoad);
                finish();
                break;

            default:
                break;
        }

    }

    public String getFileName(String pathandname) {
        int start = pathandname.lastIndexOf("/");
        int end = pathandname.lastIndexOf(".");
        if (start != -1 && end != -1) {
            return pathandname.substring(start + 1, end);
        } else {
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    /**
                     * 当选择的文件不为空，获取到文件的途径
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
                break;
        }
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

    private void decodeJSON(String json) {
        try {
            JSONObject object = new JSONObject(json);
            JSONObject data = object.getJSONObject("data");
            Boolean result = data.getBoolean("flag");
            String category = data.getString("category");
            String name = data.getString("name");
            String publishing = data.getString("publishing");
            String path = data.getString("path");
            String coverPath = data.getString("coverPath");
            String cname = data.getString("cname");
//            SharedPreferences sp = getSharedPreferences("Register",MODE_PRIVATE);
//            SharedPreferences.Editor editor = sp.edit();
//            editor.putString("userName",nameuser);
//            editor.commit();
            if (result) {
                Toast.makeText(UpLoadActivity.this, "成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
