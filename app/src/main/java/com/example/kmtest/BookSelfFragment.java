package com.example.kmtest;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.kmtest.serviceTest.MyService;

import java.util.ArrayList;


/**
 * 书架模块fragment
 * @author wxc
 * @date 2021.5.19
 */
public class BookSelfFragment extends Fragment {

    //下拉刷新计数
    private int refreshCount;
    private ArrayList<String> itemNames = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_book_shelf, container, false);
        Activity activity = getActivity();
        // TODO: 2021/5/19 toolbar及菜单栏 
        Toolbar toolbar = view.findViewById(R.id.bookShelfToolbar);
        toolbar.inflateMenu(R.menu.main_items);
        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()){
                case R.id.searchItem:
                    Toast.makeText(activity, "你点击了搜索按钮", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.historyItem:
                    Toast.makeText(activity, "你点击了阅读历史按钮", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.bookManagerItem:
                    Toast.makeText(activity, "你点击了书籍管理按钮", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.importBookItem:
                    Toast.makeText(activity, "你点击了导入书籍按钮", Toast.LENGTH_SHORT).show();
                    return true;
                default:
                    return false;
            }
        });
        // TODO: 2021/5/19 下拉刷新 
        refreshCount = 0;
        TextView refreshText = view.findViewById(R.id.refreshText);
        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.mainRefresh);
        swipeRefreshLayout.setOnRefreshListener(()->{
            refreshCount++;
            String string = "这是第" + refreshCount + "次刷新";
            refreshText.setText(string);
            swipeRefreshLayout.setRefreshing(false);
        });
        // TODO: 2021/5/19 recyclerView设置 
        initItemNames();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        RecyclerView recyclerView = view.findViewById(R.id.bookShelfRecyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new BookShelfAdapter(itemNames));
        return view;
    }

    /**
     * 填充列表
     */
    private void initItemNames() {
        itemNames.add("安卓UI练习");
        itemNames.add("安卓activity练习");
        itemNames.add("安卓服务练习");
        itemNames.add("安卓广播练习");
        itemNames.add("安卓Fragment练习");
        itemNames.add("network练习");
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
