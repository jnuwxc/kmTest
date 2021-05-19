package com.example.kmtest.serviceTest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.kmtest.MainActivity;
import com.example.kmtest.util.MyToast;

public class MyService extends Service {

    private static final String TAG = "MyService";
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "myService create");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "myService start");
        MyToast.toast("服务已开启");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        MyToast.toast("服务已绑定");
        return new ForegroundBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyToast.toast("服务关闭");
    }

    class ForegroundBinder extends Binder{
        public void startForegroundService() {
            MyToast.toast("开启前台服务");
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("my_service", "前台service", NotificationManager.IMPORTANCE_HIGH);
                manager.createNotificationChannel(channel);
            }
            Intent intent1 = new Intent(MyService.this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this, 0, intent1, 0);
            Notification notification = new NotificationCompat.Builder(MyService.this, "my_service")
                    .setContentTitle("this is content title")
                    .setContentText("this is content text")
                    .setContentIntent(pendingIntent)
                    .build();
            startForeground(1, notification);
        }
    }


}