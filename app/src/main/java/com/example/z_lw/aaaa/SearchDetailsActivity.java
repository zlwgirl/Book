package com.example.z_lw.aaaa;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/10/21.
 */
public class SearchDetailsActivity extends AppCompatActivity {
    private ImageView sv_imageView;
    private TextView sv_tv_bookname;
    private TextView sv_tv_publish;
    private String SERACH_URL ="http://139.129.215.221:8080/service/UserServlet";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchdetails);
        sv_imageView = (ImageView) findViewById(R.id.search_iamgeview);
        sv_tv_bookname = (TextView) findViewById(R.id.sv_tv_cname);
        sv_tv_publish = (TextView) findViewById(R.id.sv_tv_publish);

        SharedPreferences sharedPreferences = getSharedPreferences("Search",MODE_PRIVATE);
        String content = sharedPreferences.getString("content","");

        FinalHttp finalHttp = new FinalHttp();
        AjaxParams params = new AjaxParams();
        params.put("type","getBookInfo");
        params.put("cname",content);
        finalHttp.post(SERACH_URL, params, new AjaxCallBack<Object>() {
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                Toast.makeText(SearchDetailsActivity.this, "查无此书", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(Object o) {
                super.onSuccess(o);
                decodeJSON(o.toString());
            }
        });

    }
    private void decodeJSON(String json){
        try {
            JSONObject object = new JSONObject(json);
            JSONObject data = object.getJSONObject("data");
            String name = data.getString("name");  //书的英文名
            String category = data.getString("category");   //书的分类
            String publishing = data.getString("publishing");   //出版社
            String path = data.getString("path");  //书在服务器中存储位置
            String cname = data.getString("cname");  //书的中文名
            String coverPath = data.getString("coverPath");  //书的封面地址
            Picasso.with(this).load("http://139.129.215.221:8080"+coverPath).into(sv_imageView);
            sv_tv_bookname.setText("书名："+cname);
            sv_tv_publish.setText("出版社："+publishing);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
