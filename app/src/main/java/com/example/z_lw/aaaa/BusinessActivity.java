package com.example.z_lw.aaaa;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Z-LW on 2016/10/13.
 */
public class BusinessActivity extends Activity {
    private EditText busAccount, busPassword, busName, busPhone, busEmail, busAdress,busPersonal,busApliy;
    private TextView personal;
    private Button busRegister,busCanel;
    private static  final String BUSREGISTER_URL = "http://139.129.215.221:8080/service/UserServlet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        busAccount = (EditText) findViewById(R.id.bus_account);
        busAdress = (EditText) findViewById(R.id.bus_adress);
        busEmail = (EditText) findViewById(R.id.bus_email);
        busName = (EditText) findViewById(R.id.bus_name);
        busPassword = (EditText) findViewById(R.id.bus_password);
        busPhone = (EditText) findViewById(R.id.bus_phone);
        personal = (TextView) findViewById(R.id.reg_personal);
        busRegister = (Button) findViewById(R.id.bus_register);
        busCanel = (Button) findViewById(R.id.bus_cancel);
        busPersonal = (EditText) findViewById(R.id.bus_personalname);
        busApliy = (EditText) findViewById(R.id.bus_apliy);

        // 点击跳转到个人注册
        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BusinessActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        // click register
        busRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bAccount = busAccount.getText().toString();
                String bPassword = busPassword.getText().toString();
                String bName = busName.getText().toString();
                String bPhone = busPhone.getText().toString();
                String bEmail = busEmail.getText().toString();
                String bAddress = busAdress.getText().toString();
                String bPersonalName = busPersonal.getText().toString();
                String bApliy = busApliy.getText().toString();
                FinalHttp finalHttp = new FinalHttp();
                AjaxParams params = new AjaxParams();
                params.put("type","companyReg");
                params.put("account",bAccount);
                params.put("password",bPassword);
                params.put("companyName",bName);
                params.put("phone",bPhone);
                params.put("email",bEmail);
                params.put("address",bAddress);
                params.put("name",bPersonalName);
                params.put("alipay",bApliy);
                finalHttp.post(BUSREGISTER_URL, params, new AjaxCallBack<Object>() {
                    @Override
                    public void onSuccess(Object o) {
                        super.onSuccess(o);
                        decodeJSON(o.toString());
                    }

                    @Override
                    public void onFailure(Throwable t, int errorNo, String strMsg) {
                        super.onFailure(t, errorNo, strMsg);
                        Toast.makeText(BusinessActivity.this, strMsg,
                                Toast.LENGTH_LONG).show();
                    }
                });
                Intent intent = new Intent(BusinessActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        busCanel.setOnClickListener(new View.OnClickListener() {
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
            String nameBus = data.getString("companyName");
            String phone = data.getString("phone");
            String email = data.getString("email");
            String message = data.getString("message");
            String address = data.getString("address");
            SharedPreferences sp = getSharedPreferences("Register",MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("userName",nameBus);
            editor.commit();
            if (result) {
                Toast.makeText(BusinessActivity.this, "注册成功" + "昵称：" + nameBus + "电话:" + phone, Toast.LENGTH_LONG)
                        .show();
                Intent intent = new Intent(BusinessActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
