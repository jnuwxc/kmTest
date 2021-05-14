package com.example.kmtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowInsetsController;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().hide();

        // 将状态栏字体颜色设置为黑色
        // 关于WindowInsetsController，查看下面的链接
        // https://developer.android.com/reference/android/view/WindowInsetsController?hl=en
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            WindowInsetsController insetsController = getWindow().getInsetsController();
            insetsController.setSystemBarsAppearance(WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
        }else{
            // API30以下
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        ImageView backup = findViewById(R.id.backUp);
        backup.setOnClickListener(v -> {
            finish();
        });

        TextView userAgent = findViewById(R.id.settingUserAgent);
        userAgent.setOnClickListener(v -> {
            Intent intent = new Intent(SettingActivity.this, WebViewActivity.class);
            intent.putExtra("title", "用户协议");
            intent.putExtra("url", "https://www.baidu.com");
            startActivity(intent);
        });
        TextView  privacyPolicy = findViewById(R.id.privacyPolicy);
        privacyPolicy.setOnClickListener(v -> {
            Intent intent = new Intent(SettingActivity.this, WebViewActivity.class);
            intent.putExtra("title", "隐私政策");
            intent.putExtra("url", "https://www.baidu.com");
            startActivity(intent);
        });
        TextView childrenP = findViewById(R.id.childrenPersonalInformationProtectionRules);
        childrenP.setOnClickListener(v -> {
            Intent intent = new Intent(SettingActivity.this, WebViewActivity.class);
            intent.putExtra("title", "儿童个人信息保护政策");
            intent.putExtra("url", "https://www.baidu.com");
            startActivity(intent);
        });
    }

}