package com.example.kmtest.androidui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.kmtest.BookShelfAdapter;
import com.example.kmtest.R;

import java.util.ArrayList;

public class UiActivity extends AppCompatActivity {

    private ArrayList<String> itemNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        initItemNames();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = findViewById(R.id.rvUiMain);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new UiAdapter(itemNames));
    }

    private void initItemNames() {
        itemNames.add("线性布局练习");
        itemNames.add("相对布局练习");
        itemNames.add("帧布局练习");
        itemNames.add("约束布局练习");
        itemNames.add("基础控件练习");
        itemNames.add("ViewPager2");
        itemNames.add("补间动画练习");
        itemNames.add("ListView");
        itemNames.add("测试");
        itemNames.add("测试");
        itemNames.add("测试");
        itemNames.add("测试");
        itemNames.add("测试");
    }
}