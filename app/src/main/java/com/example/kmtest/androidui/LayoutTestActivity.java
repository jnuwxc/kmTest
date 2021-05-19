package com.example.kmtest.androidui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kmtest.MyToast;
import com.example.kmtest.R;
import com.example.kmtest.databinding.ActivityLayoutTestBinding;
import com.example.kmtest.databinding.ActivitySettingBinding;

import org.w3c.dom.Text;

public class LayoutTestActivity extends AppCompatActivity {

    ActivityLayoutTestBinding binding;
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;
    FrameLayout frameLayout;
    ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLayoutTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        linearLayout = findViewById(R.id.linearLayout);
        relativeLayout = findViewById(R.id.relativeLayout);
        frameLayout = findViewById(R.id.frameLayout);
        constraintLayout = findViewById(R.id.constraintLayout);

        String type = getIntent().getStringExtra("type");
        MyToast.toast(type);
        switch (type){
            case "linear":
                linearLayout.setVisibility(View.VISIBLE);
                break;
            case "relative":
                relativeLayout.setVisibility(View.VISIBLE);
                break;
            case "frame":
                frameLayout.setVisibility(View.VISIBLE);
                break;
            case "constraint":
                constraintLayout.setVisibility(View.VISIBLE);
                break;
            default:
        }

    }
}