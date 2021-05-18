package com.example.kmtest.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kmtest.R;

import java.util.Date;

public class BroadcastActivity extends AppCompatActivity {

    private TimeChangeReceiver  timeChangeReceiver;
    private TextView textView;
    private static final String TAG = "BroadcastActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Log.d(TAG, "onCreate: " + Application.getProcessName());
        }

        timeChangeReceiver = new TimeChangeReceiver();
        textView = findViewById(R.id.broadcastText);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_TICK");
        registerReceiver(timeChangeReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(timeChangeReceiver);
    }

    class TimeChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(BroadcastActivity.this, "Time changed", Toast.LENGTH_SHORT).show();
            String text = textView.getText() + "\n" + "收到时间改变的广播，当前时间为：" + new Date();
            textView.setText(text);
        }
    }
    class ConnectionChangeReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(intent.getAction() + "\n");
            String text = textView.getText() + "\n" + "收到蓝牙连接改变的广播  " + stringBuilder.toString();
            textView.setText(text);
        }
    }
}