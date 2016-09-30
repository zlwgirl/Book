package com.example.z_lw.aaaa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Z-LW on 2016/9/29.
 */
public class RegisterActivity extends Activity {
    EditText mAccount, mPassword, mName, mPhone, mAge, mAddress;
    RadioButton mMan, mWoman;
    RadioGroup mRg;
    Button mRegister, mCancel;
    static final String REG_URL = "http://139.129.215.221:8080/service/UserServlet";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAccount = (EditText) findViewById(R.id.account);
        mPassword = (EditText) findViewById(R.id.password);
        mRg = (RadioGroup) findViewById(R.id.rg);
        mName = (EditText) findViewById(R.id.name);
        mPhone = (EditText) findViewById(R.id.phone);
        mAge = (EditText) findViewById(R.id.age);
        mAddress = (EditText) findViewById(R.id.address);
        mMan = (RadioButton) findViewById(R.id.rb_man);
        mWoman = (RadioButton) findViewById(R.id.rb_woman);
        mRegister = (Button) findViewById(R.id.reg_register);
        mCancel = (Button) findViewById(R.id.cancel);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = mAccount.getText().toString();
                String password = mPassword.getText().toString();
                String name = mName.getText().toString();
                String phone = mPhone.getText().toString();
                String age = mAge.getText().toString();
                String address = mAddress.getText().toString();
                String gender = mRg.getCheckedRadioButtonId() == R.id.rb_man ? "man"
                        : "woman";
                SharedPreferences sp = getSharedPreferences("Register",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("userName",name);
                editor.commit();

                FinalHttp finalHttp = new FinalHttp();
                AjaxParams params = new AjaxParams();
                params.put("type", "reg");
                params.put("account", account);
                params.put("password", password);
                params.put("name", name);
                params.put("phone", phone);
                params.put("gender", gender);
                params.put("age", age);
                params.put("address", address);
                finalHttp.post(REG_URL, params, new AjaxCallBack<Object>() {
                    @Override
                    public void onFailure(Throwable t, int errorNo,
                                          String strMsg) {
                        super.onFailure(t, errorNo, strMsg);
                        Toast.makeText(RegisterActivity.this, strMsg,
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccess(Object t) {
                        super.onSuccess(t);
                        decodeJSON(t.toString());
                    }
                });
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void decodeJSON(String json) {
        try {
            JSONObject object = new JSONObject(json);
            JSONObject data = object.getJSONObject("data");
            Boolean result = data.getBoolean("flag");
            String account = data.getString("account");
            String name = data.getString("name");
            String phone = data.getString("phone");
            String gender = data.getString("gender");
            String age = data.getString("age");
            String address = data.getString("address");
            SharedPreferences sp = getSharedPreferences("UserInformation",
                    Context.MODE_PRIVATE);
            sp.edit().putString("account", account).putString("name", name)
                    .putString("phone", phone).putString("gender", gender)
                    .putString("age", age).putString("address", address)
                    .commit();
            if (result) {
                Toast.makeText(RegisterActivity.this, "注册成功" + "昵称：" + name + "电话:" + phone, Toast.LENGTH_LONG)
                        .show();
                finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

