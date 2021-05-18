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
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.kmtest.databinding.ActivitySettingBinding;

/**
 * 设置模块的Activity
 * @author wxc
 * @date 2021.5.17
 */
public class SettingActivity extends AppCompatActivity{

    private ActivitySettingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        /*
         * 将状态栏字体颜色设置为黑色
         * 关于WindowInsetsController，查看下面的链接
         * https://developer.android.com/reference/android/view/WindowInsetsController?hl=en
         */
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            WindowInsetsController insetsController = getWindow().getInsetsController();
            insetsController.setSystemBarsAppearance(WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
        }else{
            // API30以下
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        // 返回键监听
        binding.ivBackUp.setOnClickListener(v -> {
            finish();
        });

        // 基本信息监听
        binding.clBasicInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyToast.toastCenter("你点击了基本信息");
            }
        });

        // 账号与安全监听
        binding.clAccountSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyToast.toast("你点击了账号与安全");
            }
        });

        // 夜间模式switch监听
        binding.swDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MyToast.toast("夜间模式打开");
                }else {
                    MyToast.toast("夜间模式关闭");
                }
            }
        });

        // 阅读设置监听
        binding.clReadSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyToast.toast("你点击了阅读设置");
            }
        });

        // 推送设置监听
        binding.clPushSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyToast.toast("你点击了推送设置");
            }
        });

        // 清理缓存监听
        binding.clClearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyToast.toast("你点击了清理缓存");
            }
        });

        // 隐私设置监听
        binding.clPrivacySetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyToast.toast("你点击了隐私设置");
            }
        });

        // 关于七猫免费小说监听
        binding.clAboutQimao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyToast.toast("你点击了关于七猫免费小说");
            }
        });

        // 用户协议、隐私政策、儿童个人信息保护政策监听点击事件，跳转到webViewActivity
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