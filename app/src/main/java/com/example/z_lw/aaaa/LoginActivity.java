package com.example.z_lw.aaaa;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Z-LW on 2016/9/28.
 * 登录页面
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    private Button loginBtn, registerBtn;
    private EditText mAccount, mPassword;
    ArrayList<Map<String, String>> users;
    static final String LOGIN_URL = "http://139.129.215.221:8080/service/UserServlet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBtn = (Button) findViewById(R.id.login_btn);
        registerBtn = (Button) findViewById(R.id.login_register_btn);
        mAccount = (EditText) findViewById(R.id.login_et);
        mPassword = (EditText) findViewById(R.id.register_et);
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
                String account = mAccount.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                FinalHttp finalHttp = new FinalHttp();
                AjaxParams params = new AjaxParams();
                params.put("type", "login");
                params.put("account", account);
                params.put("password", password);
                SharedPreferences sp = getSharedPreferences("Register",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("userName", account);
                editor.commit();
                finalHttp.post(LOGIN_URL, params, new AjaxCallBack<Object>() {
                    @Override
                    public void onFailure(Throwable t, int errorNo,
                                          String strMsg) {
                        super.onFailure(t, errorNo, strMsg);
                        Toast.makeText(LoginActivity.this, "账号或者密码错误，请重新输入",
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccess(Object t) {
                        super.onSuccess(t);
                        decodeJSON(t.toString());
                    }
                });
                finish();
                break;
            case R.id.login_register_btn:
                Intent intentLR = new Intent(this, RegisterActivity.class);
                startActivity(intentLR);

                finish();
                break;
        }

    }
    private void decodeJSON(String json) {
        try {
            JSONObject object = new JSONObject(json);
            JSONObject data = object.getJSONObject("data");
            Boolean result = data.getBoolean("flag");
            String name = data.getString("name");
            String companyName = data.getString("companyName");

            if (result) {
                Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_LONG)
                        .show();
                Intent intent = new Intent(LoginActivity.this,
                        MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(LoginActivity.this, "账号或者密码错误",
                        Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
