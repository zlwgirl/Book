package com.example.z_lw.aaaa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Z-LW on 2016/9/29.
 */
public class RegisterActivity extends Activity implements View.OnClickListener {
    private Button loginBtn, registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        loginBtn = (Button) findViewById(R.id.register_login_btn);
        registerBtn = (Button) findViewById(R.id.register_btn);
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_login_btn:
                Intent intentLogin = new Intent(this, LoginActivity.class);
                startActivity(intentLogin);
                finish();
                break;
            case R.id.register_btn:
                Intent intentRegister = new Intent(this, MainActivity.class);
                startActivity(intentRegister);
                finish();
                break;
        }
    }
}
