package com.example.kmtest.network;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.kmtest.R;
import com.example.kmtest.databinding.ActivityNetworkBinding;
import com.example.kmtest.util.MyToast;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkActivity extends AppCompatActivity {

    private static final String TAG = "NetworkActivity";
    private ActivityNetworkBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNetworkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnStartRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder().url("http://121.4.20.8/test.php?userId=1").build();
                        Response response;
                        try {
                            response = client.newCall(request).execute();
                            String responseContent = response.body().string();
                            if (responseContent.equals("[]")) {
                                MyToast.toast("账号不存在");
                            }else {
                                Request request1 = new Request.Builder().url("http://121.4.20.8/password.php?userId=1&userPassword=123456").build();
                                Response response1 = client.newCall(request1).execute();
                                if(response1.body().toString().equals("[]")){
                                    MyToast.toast("密码错误");
                                }else {
                                    Log.d(TAG, "run: " + response1.body().string());
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

    }
}