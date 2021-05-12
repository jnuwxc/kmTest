package com.example.kmtest;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BookSelfFragment extends Fragment {

    private MyService.DownloadBinder downloadBinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_book_shelf, container, false);
        Button startServiceBtn = view.findViewById(R.id.startService);
        Button bindServiceBtn = view.findViewById(R.id.bindService);
        Button unbindServiceBtn = view.findViewById(R.id.unBindService);
        Button stopServiceBtn = view.findViewById(R.id.stopService);

        Connection connection = new Connection();

        startServiceBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MyService.class);
            getContext().startService(intent);
        });
        bindServiceBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MyService.class);
            getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        });
        unbindServiceBtn.setOnClickListener(v -> {
            getContext().unbindService(connection);
        });
        stopServiceBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MyService.class);
            getContext().stopService(intent);
        });
        return view;
    }

    class Connection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
