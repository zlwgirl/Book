package com.example.z_lw.aaaa;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;


/**
 * Created by Administrator on 2016/9/28.
 */
public class FreeDetailsActivity extends AppCompatActivity {
    private Button button_back;
    private PDFView pdfView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freedetails);
        button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        pdfView = (PDFView) findViewById(R.id.pdfView);
        SharedPreferences sharedPreferences = getSharedPreferences("pdf",MODE_PRIVATE);
        String content = sharedPreferences.getString("Pdf","");
        pdfView.fromFile(new File(Environment.getExternalStorageDirectory(),content))
                .defaultPage(1)
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        Toast.makeText(FreeDetailsActivity.this, page+"/"+pageCount, Toast.LENGTH_SHORT).show();
                    }
                }).load();
    }
}
