package com.example.kmtest.serviceTest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;

import com.example.kmtest.IMyAidlInterface;
import com.example.kmtest.MyToast;

public class AIDLService extends Service {
    public AIDLService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        MyToast.toast("绑定成功");
        return new MyBinder();
    }

    class MyBinder extends IMyAidlInterface.Stub{

        @Override
        public int getPid() throws RemoteException {
            return Process.myPid();
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    }
}