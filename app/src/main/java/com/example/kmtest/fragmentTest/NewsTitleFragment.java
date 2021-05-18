package com.example.kmtest.fragmentTest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kmtest.R;

import java.util.ArrayList;
import java.util.List;

public class NewsTitleFragment extends Fragment {

    private static final String TAG = "NewsTitleFragment";
    private boolean isTwoPane;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.news_title_frag, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RecyclerView recyclerView = view.findViewById(R.id.newsTitleRecyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new NewsAdapter(getNews()));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isTwoPane = getActivity().findViewById(R.id.newsContentLayout) != null;

    }

    private List<News> getNews() {
        ArrayList<News> newsList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            News news = new News("This is news title" + i, getRandomLengthString("This is news content ") + i);
            newsList.add(news);
        }
        return newsList;
    }
    private String getRandomLengthString(String string){
        int n = (int) (Math.random() * 20);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append(string);
        }
        return builder.toString();
    }


    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

        private List<News> newsList;

        public NewsAdapter(List<News> newsList) {
            this.newsList = newsList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.itemView.setOnClickListener(v -> {
                News news = newsList.get(viewHolder.getAdapterPosition());
                if (isTwoPane) {
                    // 如果是双页模式，则刷新NewsContentFragment中的内容
                    NewsContentFragment newsContentFragment = (NewsContentFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.newsContentFrag);
                    newsContentFragment.refresh(news.getTitle(), news.getContent());
                }else {
                    // 如果是单页模式，则直接启动NewsContentActivity
                    Log.d(TAG, "onCreateViewHolder: 单页模式");
                    Intent intent = new Intent(getContext(), NewsContentActivity.class);
                    intent.putExtra("news_title", news.getTitle());
                    intent.putExtra("news_content", news.getContent());
                    startActivity(intent);
                }
            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull  NewsTitleFragment.NewsAdapter.ViewHolder holder, int position) {
            News news = newsList.get(position);
            holder.newsTitle.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return newsList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            private TextView newsTitle;
            public ViewHolder(View itemView) {
                super(itemView);
                newsTitle = itemView.findViewById(R.id.newsTitle);
            }
        }
    }
}
