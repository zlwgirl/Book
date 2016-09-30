package com.example.z_lw.aaaa;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/9/29.
 */
public class SearchActivity extends Activity {
    private EditText editText;
    private Button button;
    private String content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editText = (EditText) findViewById(R.id.editText_search);
        button = (Button) findViewById(R.id.button_search);
        content  = editText.getText().toString();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(SearchActivity.this, "搜索的内容是"+ content, Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
