package com.example.z_lw.aaaa;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
        pdfView = (PDFView) findViewById(R.id.pdfView);
        button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //显示pdf文件
            File file = new File(Environment.getExternalStorageDirectory(),"wyoos.pdf");
            pdfView.fromFile(file)
                    .defaultPage(1)
                    .onPageChange(new OnPageChangeListener() {
                @Override
                public void onPageChanged(int page, int pageCount) {
                }
            }).load();
    }
}
