package com.example.kmtest;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.mainToolbar);
        toolbar.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);
        replaceFragment(new BookSelfFragment());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bookShelf:
                        toolbar.setVisibility(View.VISIBLE);
                        replaceFragment(new BookSelfFragment());
                        Log.d(TAG, "onNavigationItemSelected: " + " bookShelf");
                        break;
                    case R.id.bookStore:
                        toolbar.setVisibility(View.GONE);
                        replaceFragment(new BookStoreFragment());
                        Log.d(TAG, "onNavigationItemSelected: " + " bookStore");
                        break;
                    case R.id.classification:
                        toolbar.setVisibility(View.GONE);
                        replaceFragment(new ClassificationFragment());
                        Log.d(TAG, "onNavigationItemSelected: " + " classification");
                        break;
                    case R.id.my:
                        toolbar.setVisibility(View.GONE);
                        replaceFragment(new MyFragment());
                        Log.d(TAG, "onNavigationItemSelected: " + " my");
                        break;
                    default:
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.main_items, menu);
         return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.searchItem:
                Toast.makeText(this, "你点击了搜索按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.historyItem:
                Toast.makeText(this, "你点击了阅读历史按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bookManagerItem:
                Toast.makeText(this, "你点击了书籍管理按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.importBookItem:
                Toast.makeText(this, "你点击了导入书籍按钮", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment){
        Log.d(TAG, "replaceFragment: ");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFragment, fragment);
        transaction.commit();
    }

}