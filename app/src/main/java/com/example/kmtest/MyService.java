package com.example.kmtest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

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