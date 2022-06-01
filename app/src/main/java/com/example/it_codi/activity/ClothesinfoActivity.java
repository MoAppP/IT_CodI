package com.example.it_codi.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MenuItem;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.it_codi.R;
import com.example.it_codi.database.Clothes;
import com.example.it_codi.database.ClothesDatabase;

import java.util.ArrayList;

public class ClothesinfoActivity extends Activity {
    private Bitmap img;
    private ArrayList<String> tags;
    private boolean like;

    private ImageView iv;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_clothesinfo);

        Intent intent = getIntent();
        byte [] arr = intent.getByteArrayExtra("byteArray");
        img = BitmapFactory.decodeByteArray(arr, 0, arr.length);
        tags = (ArrayList<String>) intent.getSerializableExtra("tags");
        like = intent.getBooleanExtra("like",false);

        iv = findViewById(R.id.clothes);
        tv = findViewById(R.id.clothesinfo);

        iv.setImageBitmap(img);

        String temp = "";
        for (String tag:tags) {
            temp += "#"+tag+"\n";
        }
        tv.setText(temp);
    }

}
