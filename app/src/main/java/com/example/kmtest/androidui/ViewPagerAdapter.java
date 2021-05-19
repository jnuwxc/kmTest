package com.example.kmtest.androidui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private String[] data;
    public ViewPagerAdapter(@NonNull Fragment fragment, String[] data) {
        super(fragment);
        this.data = data;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new ViewPagerItemFragment(data[position]);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}
