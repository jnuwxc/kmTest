package com.example.kmtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);



        String title = getIntent().getStringExtra("title");
        String url = getIntent().getStringExtra("url");
        if (title != null) {
            TextView titleText = findViewById(R.id.webViewTitle);
            titleText.setText(title);
        }

        if (url != null) {
            WebView webView = findViewById(R.id.webView);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(url);
        }

        ImageView backup = findViewById(R.id.webViewBackup);
        backup.setOnClickListener(v -> {
            finish();
        });
    }
}