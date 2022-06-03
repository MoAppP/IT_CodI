package com.example.it_codi.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.it_codi.R;
import com.example.it_codi.database.Clothes;
import com.example.it_codi.database.ClothesDatabase;

public class ClothesinfoActivity extends Activity {
    private Clothes clothes;

    private ImageView iv;
    private TextView tv;
    private ToggleButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_clothesinfo);

        //get clothes uid from intent
        Intent intent = getIntent();
        int clothes_uid = intent.getIntExtra("clothes_uid",0);

        //get clothes object from DB by uid
        ClothesDatabase DB = ClothesDatabase.getInstance(getApplicationContext());
        clothes = DB.clothesDao().findById(clothes_uid);

        iv = findViewById(R.id.clothes);
        tv = findViewById(R.id.clothesinfo);
        btn = findViewById(R.id.likebtn);

        //set image
        iv.setImageBitmap(clothes.getImg());


        //make tag list
        String temp = "";
        if(!clothes.getName().equals(""))
            temp += String.format("#%s\n",clothes.getName());
        if(!clothes.getType().equals(""))
            temp += String.format("#%s\n",clothes.getType());
        if(!clothes.getSpring().equals(""))
            temp += String.format("#%s\n",clothes.getSpring());
        if(!clothes.getSummer().equals(""))
            temp += String.format("#%s\n",clothes.getSummer());
        if(!clothes.getAutumn().equals(""))
            temp += String.format("#%s\n",clothes.getAutumn());
        if(!clothes.getWinter().equals(""))
            temp += String.format("#%s\n",clothes.getWinter());
        if(!clothes.getStyle().equals(""))
            temp += String.format("#%s\n",clothes.getStyle());

        tv.setText(temp);

        //likebtn
        btn.setChecked(clothes.getLiked());
        btn.setOnClickListener(view -> {
            clothes.setLiked(btn.isChecked());
            DB.clothesDao().updateClothes(clothes);
        });

    }

}
