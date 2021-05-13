package com.example.kmtest;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookSelfFragment extends Fragment {

    private MyService.DownloadBinder downloadBinder;
    private ArrayList<String> itemNames = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_book_shelf, container, false);
        Activity activity = getActivity();

        initItemNames();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        RecyclerView recyclerView = view.findViewById(R.id.bookShelfRecyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new BookShelfAdapter(itemNames));

//        Button startServiceBtn = view.findViewById(R.id.startService);
//        Button bindServiceBtn = view.findViewById(R.id.bindService);
//        Button unbindServiceBtn = view.findViewById(R.id.unBindService);
//        Button stopServiceBtn = view.findViewById(R.id.stopService);
//        Button foregroundBtn = view.findViewById(R.id.foregroundService);
//
//        Connection connection = new Connection();
//
//        startServiceBtn.setOnClickListener(v -> {
//            Intent intent = new Intent(activity, MyService.class);
//            activity.startService(intent);
//        });
//        bindServiceBtn.setOnClickListener(v -> {
//            Intent intent = new Intent(activity, MyService.class);
//            activity.bindService(intent, connection, Context.BIND_AUTO_CREATE);
//        });
//        unbindServiceBtn.setOnClickListener(v -> {
//            getContext().unbindService(connection);
//        });
//        stopServiceBtn.setOnClickListener(v -> {
//            Intent intent = new Intent(getContext(), MyService.class);
//            getContext().stopService(intent);
//        });
//        foregroundBtn.setOnClickListener(v -> {
//
//        });
        return view;
    }

//    class Connection implements ServiceConnection {
//
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            downloadBinder = (MyService.DownloadBinder) service;
//            downloadBinder.startDownload();
//            downloadBinder.getProgress();
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//
//        }
//    }

    private void initItemNames() {
        itemNames.add("安卓UI练习");
        itemNames.add("安卓activity练习");
        itemNames.add("安卓服务练习");
        itemNames.add("安卓广播练习");
        itemNames.add("测试");
        itemNames.add("测试");
        itemNames.add("测试");
        itemNames.add("测试");
        itemNames.add("测试");
        itemNames.add("测试");
        itemNames.add("测试");
        itemNames.add("测试");
        itemNames.add("测试");
        itemNames.add("测试");
        itemNames.add("测试");
    }
}
