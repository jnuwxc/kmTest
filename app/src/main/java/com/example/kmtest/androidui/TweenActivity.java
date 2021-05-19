package com.example.kmtest.androidui;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kmtest.R;

public class TweenActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_alpha;
    private Button btn_scale;
    private Button btn_tran;
    private Button btn_rotate;
    private Button btn_set;
    private ImageView img_show;
    private Animation animation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);

        btn_alpha = (Button) findViewById(R.id.alpha_tween);
        btn_scale = (Button) findViewById(R.id.scale_tween);
        btn_tran = (Button) findViewById(R.id.tran_tween);
        btn_rotate = (Button) findViewById(R.id.rotate_tween);
        btn_set = (Button) findViewById(R.id.set_tween);
        img_show = (ImageView) findViewById(R.id.imageView);

        btn_alpha.setOnClickListener(this);
        btn_scale.setOnClickListener(this);
        btn_tran.setOnClickListener(this);
        btn_rotate.setOnClickListener(this);
        btn_set.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.alpha_tween:
                animation = AnimationUtils.loadAnimation(this,
                        R.anim.anim_alpha);
                img_show.startAnimation(animation);
                break;
            case R.id.scale_tween:
                animation = AnimationUtils.loadAnimation(this,
                        R.anim.anim_sacle);
                img_show.startAnimation(animation);
                break;
            case R.id.tran_tween:
                animation = AnimationUtils.loadAnimation(this,
                        R.anim.anim_translate);
                img_show.startAnimation(animation);
                break;
            case R.id.rotate_tween:
                animation = AnimationUtils.loadAnimation(this,
                        R.anim.anim_rotate);
                img_show.startAnimation(animation);
                break;
            case R.id.set_tween:
                animation = AnimationUtils.loadAnimation(this,
                        R.anim.anim_set);

                img_show.startAnimation(animation);
                break;
            default:
        }
    }
}