package com.example.kmtest.androidui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kmtest.BookShelfAdapter;
import com.example.kmtest.R;
import com.example.kmtest.broadcast.BroadcastActivity;
import com.example.kmtest.fragmentTest.NewsActivity;
import com.example.kmtest.serviceTest.ServiceActivity;

import java.util.ArrayList;

public class UiAdapter extends RecyclerView.Adapter<UiAdapter.ViewHolder> {

    private ArrayList<String> itemNames;

    public UiAdapter(ArrayList<String> itemNames) {
        this.itemNames = itemNames;
    }

    @NonNull
    @Override
    public UiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_shelf_item, parent, false);
        UiAdapter.ViewHolder viewHolder= new UiAdapter.ViewHolder(view);
        viewHolder.itemNameText.setOnClickListener(v -> {
            TextView textView = view.findViewById(R.id.itemText);
            switch (textView.getText().toString().trim()) {
                case "线性布局练习":
                    intentLayout(parent.getContext(), "linear");
                    break;
                case "相对布局练习":
                    intentLayout(parent.getContext(), "relative");
                    break;
                case "帧布局练习":
                    intentLayout(parent.getContext(), "frame");
                    break;
                case "约束布局练习":
                    intentLayout(parent.getContext(), "constraint");
                    break;
                case "基础控件练习":
                    Intent intentBasicView = new Intent(parent.getContext(), BasicViewActivity.class);
                    parent.getContext().startActivity(intentBasicView);
                    break;
                case "ViewPager2":
                    Intent intentViewPager = new Intent(parent.getContext(), ViewPagerActivity.class);
                    parent.getContext().startActivity(intentViewPager);
                    break;
                case "补间动画练习":
                    Intent intentTween = new Intent(parent.getContext(), TweenActivity.class);
                    parent.getContext().startActivity(intentTween);
                    break;
                case "ListView":
                    Intent intentListView = new Intent(parent.getContext(), ListViewActivity.class);
                    parent.getContext().startActivity(intentListView);
                    break;
                default:
                    break;
            }
        });
        return new UiAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UiAdapter.ViewHolder holder, int position) {
        String string = itemNames.get(position);
        holder.itemNameText.setText(string);
    }

    @Override
    public int getItemCount() {
        return itemNames.size();
    }

    /**
     * @param context
     * @param type
     */
    private void intentLayout(Context context, String type){
        Intent intent = new Intent(context, LayoutTestActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemNameText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameText = itemView.findViewById(R.id.itemText);
        }
    }
}

