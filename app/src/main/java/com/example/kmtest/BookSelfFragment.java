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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

public class BookSelfFragment extends Fragment {

    private MyService.DownloadBinder downloadBinder;
    private int refreshCount;
    private ArrayList<String> itemNames = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_book_shelf, container, false);
        Activity activity = getActivity();

        refreshCount = 0;
        TextView refreshText = view.findViewById(R.id.refreshText);
        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.mainRefresh);
        swipeRefreshLayout.setOnRefreshListener(()->{
            refreshCount++;
            String string = "这是第" + refreshCount + "次刷新";
            refreshText.setText(string);
            swipeRefreshLayout.setRefreshing(false);
        });

        initItemNames();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        RecyclerView recyclerView = view.findViewById(R.id.bookShelfRecyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new BookShelfAdapter(itemNames));
        return view;
    }

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
