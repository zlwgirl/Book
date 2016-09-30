package com.example.z_lw.aaaa;

import android.app.Activity;
import android.content.Context;
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

/**
 * Created by Z-LW on 2016/9/29.
 */
public class RegisterActivity extends Activity implements View.OnClickListener {
    private Button loginBtn, registerBtn;
    private EditText mEdittext_username, mEdittext_password;
    static final String PATH = "http://139.129.215.221:8080/service/UserServlet";
//    static StringBuilder url = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        loginBtn = (Button) findViewById(R.id.register_login_btn);
        registerBtn = (Button) findViewById(R.id.register_btn);
        mEdittext_username = (EditText) findViewById(R.id.register_et_username);
        mEdittext_password = (EditText) findViewById(R.id.register_et_password);
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_login_btn:
                String username = mEdittext_username.getText().toString();
                String password = mEdittext_password.getText().toString();
                FinalHttp finalHttp = new FinalHttp();
                AjaxParams params = new AjaxParams();
                params.put("type", "reg");
                params.put("account", username);
                params.put("password", password);
                params.put("name", "1");
                params.put("phone", "2");
                params.put("gender", "3");
                params.put("age", "4");
                params.put("address", "5");
                finalHttp.post(PATH, params, new AjaxCallBack<Object>() {
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
                SharedPreferences sp = getSharedPreferences("Register", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();

//                boolean result = UserService.check(username, password);
//                if (result) {
//                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_LONG).show();
//                }


                editor.putString("userName", username);
                editor.commit();
                Intent intentLogin = new Intent(this, MainActivity.class);
                startActivity(intentLogin);
                finish();
                break;


            case R.id.register_btn:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }


    private void decodeJSON(String json) {
        try {
            JSONObject object = new JSONObject(json);
            JSONObject data = object.getJSONObject("data");
            Boolean result = data.getBoolean("flag");
            String username = data.getString("account");
            String name = data.getString("name");
            String phone = data.getString("phone");
            String gender = data.getString("gender");
            String age = data.getString("age");
            String address = data.getString("address");
            SharedPreferences sp = getSharedPreferences("UserInformation",
                    Context.MODE_PRIVATE);
            sp.edit().putString("account", username)
                    .commit();
            if (result) {

                Toast.makeText(RegisterActivity.this, "注册成功" + "账号：" + username, Toast.LENGTH_LONG)
                        .show();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

//    public static class UserService {
//        public static boolean check(String username, String password) {
//            path = "http://139.129.215.221:8080/service/UserServlet";
//            //将用户名和密码放入HashMap中
//            Map<String, String> params = new HashMap<String, String>();
//            params.put("account", username);
//            params.put("password", password);
//            params.put("type", "reg");
//            params.put("name", "2");
//            params.put("phone", "3");
//            params.put("gender", "4");
//            params.put("age", "5");
//            params.put("address", "6");
//            try {
//                return sendGETRequest(path, params, "UTF-8");
//            } catch (MalformedURLException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return false;
//        }
//
//        private static boolean sendGETRequest(String path,
//                                              Map<String, String> params, String encode) throws MalformedURLException, IOException, JSONException {
//            url = new StringBuilder(path);
//            url.append("?");
//            for (Map.Entry<String, String> entry : params.entrySet()) {
//                url.append(entry.getKey()).append("=");
//                url.append(URLEncoder.encode(entry.getValue(), encode));
//                url.append("&");
//            }
//            //删掉最后一个&
//            url.deleteCharAt(url.length() - 1);
//            Log.d("TAGGG", "url  ____:" + url);
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    HttpURLConnection conn = null;
//                    try {
//                        conn = (HttpURLConnection) new URL(url.toString()).openConnection();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    conn.setConnectTimeout(5000);
//                    try {
//                        conn.setRequestMethod("POST");
//                    } catch (ProtocolException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        if (conn.getResponseCode() == 200) {
//                            InputStream is = conn.getInputStream();
//                            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//                            String str = "";
//                            StringBuilder builder = new StringBuilder();
//                            while ((str = br.readLine()) != null) {
//                                builder.append(str);
//                            }
//                            Log.d("hhk", "sendGETRequest: " + conn.getResponseCode());
//                            JSONObject jsonObject = new JSONObject(builder.toString());
//                            JSONObject object = jsonObject.getJSONObject("data");
//                            boolean flag = object.getBoolean("flag");
//                            if (flag) {
//
//                            } else {
////                                Toast.makeText(RegisterActivity.this, "", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
////
//                }
//            }).start();
//            return false;

//    public static void sendGetRequest(Map<String,String> infos){
//        String url = "http://139.129.215.221:8080/service/UserServlet?";
//        StringBuilder builder = new StringBuilder(url);
//        Set<String> keys = infos.keySet();
//        for (String key : keys) {
//            String value = infos.get(key);
//            builder.append(key);
//            builder.append("=");
//            builder.append(value);
//            builder.append("&");
//        }
//
//        builder.deleteCharAt(builder.length() - 1);
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }).start();


//    }
//        }
//    }
}
