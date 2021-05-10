package com.example.kmtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(it->{
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        });
    }
}