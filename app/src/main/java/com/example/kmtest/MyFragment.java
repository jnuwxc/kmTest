package com.example.kmtest;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * 我的页面fragment
 * @author wxc
 * @date 2021.5.18
 */
public class MyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_my, container, false);
        Button settingBtn = view.findViewById(R.id.btnSetting);
        settingBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SettingActivity.class);
            startActivity(intent);
        });
        return view;
    }
}