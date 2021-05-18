package com.example.kmtest;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kmtest.broadcast.BroadcastActivity;
import com.example.kmtest.fragmentTest.NewsActivity;
import com.example.kmtest.serviceTest.ServiceActivity;

import java.util.ArrayList;

public class BookShelfAdapter extends RecyclerView.Adapter<BookShelfAdapter.ViewHolder> {

    private static final String TAG = "BookShelfAdapter";
    private ArrayList<String> itemNames;

    public BookShelfAdapter(ArrayList<String> itemNames) {
        this.itemNames = itemNames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_shelf_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemNameText.setOnClickListener(v -> {
            TextView textView = view.findViewById(R.id.itemText);
            switch (textView.getText().toString().trim()) {
                case "安卓UI练习":
                    Log.d(TAG, "onCreateViewHolder: 安卓UI练习" );
                    break;
                case "安卓服务练习":
                    Intent intentService = new Intent(parent.getContext(), ServiceActivity.class);
                    parent.getContext().startActivity(intentService);
                    break;
                case "安卓广播练习":
                    Intent intentBroadcast = new Intent(parent.getContext(), BroadcastActivity.class);
                    parent.getContext().startActivity(intentBroadcast);
                    break;
                case "安卓Fragment练习":
                    Intent intentFragment = new Intent(parent.getContext(), NewsActivity.class);
                    parent.getContext().startActivity(intentFragment);
                default:
                    break;
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String string = itemNames.get(position);
        holder.itemNameText.setText(string);
    }

    @Override
    public int getItemCount() {
        return itemNames.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemNameText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameText = itemView.findViewById(R.id.itemText);
        }
    }
}
