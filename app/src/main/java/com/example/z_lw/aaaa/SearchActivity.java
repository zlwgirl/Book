package com.example.z_lw.aaaa;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/9/29.
 * 搜索页面
 */
public class SearchActivity extends Activity implements SearchView.OnQueryTextListener {
    private SearchView sv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        sv = (SearchView) findViewById(R.id.searchView);
        sv.setIconifiedByDefault(false);
        sv.setOnQueryTextListener(this);
        sv.setQueryHint("搜索书籍名称");
        sv.setSubmitButtonEnabled(true);

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
//        Toast.makeText(SearchActivity.this, "你搜索的内容是"+s, Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor sp = getSharedPreferences("Search",MODE_PRIVATE).edit();
        sp.putString("content",s);
        sp.commit();
        Intent intent = new Intent(this,SearchDetailsActivity.class);
        startActivity(intent);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        Toast.makeText(SearchActivity.this, "你想要搜索的内容是"+s, Toast.LENGTH_SHORT).show();
        return false;
    }
}
