package com.example.kmtest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class MyService extends Service {

    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(MyService.this, "service create", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(MyService.this, "service start", Toast.LENGTH_SHORT).show();

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("my_service", "前台service", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }
        Intent intent1 = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent1, 0);
        Notification notification = new NotificationCompat.Builder(this, "my_service")
                .setContentTitle("this is content title")
                .setContentText("this is content text")
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Toast.makeText(MyService.this, "service bind", Toast.LENGTH_SHORT).show();
        return new DownloadBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(MyService.this, "service stop", Toast.LENGTH_SHORT).show();
    }

    class DownloadBinder extends Binder{
        void startDownload(){
            Toast.makeText(MyService.this, "start download", Toast.LENGTH_SHORT).show();
        }

        int getProgress(){
            return 0;
        }

    }
}