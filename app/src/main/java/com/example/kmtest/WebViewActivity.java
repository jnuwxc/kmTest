package com.example.kmtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 用于显示webView的activity
 * 通过Intent传入标题和链接
 * @author wxc
 * @date 2021.5.17
 */
public class WebViewActivity extends AppCompatActivity {

    private static final String TAG = "WebViewActivity";
    private final String DEFAULT_TITLE = "标题";
    private final String DEFAULT_URL = "https://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        TextView titleText = findViewById(R.id.webViewTitle);
        WebView webView = findViewById(R.id.webView);
        ImageView backup = findViewById(R.id.webViewBackup);

        // 设置标题
        String title = getIntent().getStringExtra("title");
        if(title != null){
            titleText.setText(title);
        }else {
            titleText.setText(DEFAULT_TITLE);
            Log.w(TAG, "onCreate: intent no title");
        }

        // 设置webView
        String url = getIntent().getStringExtra("url");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        if(url != null){
            webView.loadUrl(url);
        }else {
            webView.loadUrl(DEFAULT_URL);
            Log.w(TAG, "onCreate: intent no url");
        }

        // 返回键
        backup.setOnClickListener(v -> {
            finish();
        });
    }
}