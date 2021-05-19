package com.example.kmtest.androidui;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kmtest.R;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {


    private ArrayList<Fruit> fruitArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initFruits();
        FruitAdapter adapter = new FruitAdapter(ListViewActivity.this, R.layout.fruit_item, fruitArrayList);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Fruit fruit = fruitArrayList.get(position);
            Toast.makeText(this, fruit.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    private void initFruits(){
        for(int i = 0; i < 7; i++){
            fruitArrayList.add(new Fruit("apple", R.drawable.apple_pic));
            fruitArrayList.add(new Fruit("banana", R.drawable.banana_pic));
            fruitArrayList.add(new Fruit("orange", R.drawable.orange_pic));
        }
    }
}