package com.example.kmtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        replaceFragment(new BookSelfFragment());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bookShelf:
                        replaceFragment(new BookSelfFragment());
                        Log.d(TAG, "onNavigationItemSelected: " + " bookShelf");
                        break;
                    case R.id.bookStore:
                        replaceFragment(new BookStoreFragment());
                        Log.d(TAG, "onNavigationItemSelected: " + " bookStore");
                        break;
                    case R.id.classification:
                        replaceFragment(new ClassificationFragment());
                        Log.d(TAG, "onNavigationItemSelected: " + " classification");
                        break;
                    case R.id.my:
                        replaceFragment(new MyFragment());
                        Log.d(TAG, "onNavigationItemSelected: " + " my");
                        break;
                    default:
                }
                return true;
            }
        });
    }

    private void replaceFragment(Fragment fragment){
        Log.d(TAG, "replaceFragment: ");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFragment, fragment);
        transaction.commit();
    }

}