package com.example.kmtest.components;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "开机成功", Toast.LENGTH_SHORT).show();
        Log.d("BroadcastReceiver", "onReceive: 收到开机广播");
    }
}
