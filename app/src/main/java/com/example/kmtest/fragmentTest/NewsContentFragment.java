package com.example.kmtest.fragmentTest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.kmtest.R;
import com.example.kmtest.databinding.NewsContentFragBinding;

public class NewsContentFragment extends Fragment {

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_content_frag, container, false);
        return view;
    }

    void refresh(String title, String content) {
        LinearLayout linearLayout = view.findViewById(R.id.contentLayout);
        linearLayout.setVisibility(View.VISIBLE);
        TextView tvTitle = view.findViewById(R.id.newsTitle);
        tvTitle.setText(title);
        TextView tvContent = view.findViewById(R.id.newsContent);
        tvContent.setText(content);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
