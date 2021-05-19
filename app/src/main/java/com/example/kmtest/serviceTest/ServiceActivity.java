package com.example.kmtest.serviceTest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.os.Process;
import com.example.kmtest.IMyAidlInterface;
import com.example.kmtest.util.MyToast;
import com.example.kmtest.databinding.ActivityServiceBinding;

public class ServiceActivity extends AppCompatActivity {

    private static final String TAG = "ServiceActivity";
    private ActivityServiceBinding binding;
    private MyService.ForegroundBinder foregroundBinder;
    private AIDLService.MyBinder myBinder;
    private boolean isBindService;
    private boolean isBindAIDLService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this, MyService.class);
                startService(intent);
            }
        });

        binding.btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this, MyService.class);
                stopService(intent);
            }
        });

        Connection connection = new Connection();
        binding.btnBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this, MyService.class);
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
            }
        });
        binding.btnUnbindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(connection);
            }
        });

        binding.btnForgroundService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBindService) {
                    foregroundBinder.startForegroundService();
                }else {
                    MyToast.toast("必须先绑定服务！点击绑定服务按钮");
                }
            }
        });

        AIDLConnection aidlConnection = new AIDLConnection();
        binding.btnBindAIDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this, AIDLService.class);
                bindService(intent, aidlConnection, Context.BIND_AUTO_CREATE);

            }
        });
        binding.btnGetPid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBindAIDLService){
                    try {
                        MyToast.toast("当前进程ID" + Process.myPid() + " AIDL进程ID" + myBinder.getPid());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }else {
                    MyToast.toast("必须先绑定AIDL服务！点击绑定AIDL服务按钮");
                }
            }
        });
    }

    class Connection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            foregroundBinder = (MyService.ForegroundBinder) service;
            isBindService = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    }

    class AIDLConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (AIDLService.MyBinder) IMyAidlInterface.Stub.asInterface(service);
            isBindAIDLService = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}