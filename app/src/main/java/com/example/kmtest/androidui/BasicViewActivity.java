package com.example.kmtest.androidui;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kmtest.R;

public class BasicViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_view);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(it->{
            showPopup(button);
//            new AlertDialog.Builder(this)
//                    .setTitle("标题")
//                    .setMessage("这是一个AlertDialog")
//                    .setCancelable(false)
//                    .setPositiveButton("ok", new DialogInterface.OnClickListener(){
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    })
//                    .setNegativeButton("cancel", (dialog ,which)->{
//
//                    }).show();
        });

        Button alertDialogBtn = findViewById(R.id.alertDialog);
        alertDialogBtn.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("标题")
                    .setSingleChoiceItems(new String[]{"选项1", "选项2", "选项3"}, 2, (dialog, which) -> {
                        Toast.makeText(BasicViewActivity.this, "you clicked " + which, Toast.LENGTH_SHORT).show();
                    })
                    .setCancelable(false)
                    .setPositiveButton("ok", (dialog, which) -> {
                        //
                    })
                    .setNegativeButton("cancel", (dialog ,which)->{
                        //
                    })
                    .show();
        });
        registerForContextMenu(alertDialogBtn);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.context_menu, popup.getMenu());
        popup.show();
    }
}