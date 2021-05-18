package com.example.kmtest.fragmentTest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.example.kmtest.R;

public class NewsContentActivity extends AppCompatActivity {

    private static final String TAG = "NewsContentActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        String title = getIntent().getStringExtra("news_title");
        String content = getIntent().getStringExtra("news_content");
        if (title != null && content != null) {
            NewsContentFragment fragment = (NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.newsContentFrag);
            Log.d(TAG, "onCreate: " + fragment);
            fragment.refresh(title, content);
        }
    }


}