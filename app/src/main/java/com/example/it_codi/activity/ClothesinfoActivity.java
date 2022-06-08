package com.example.it_codi.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
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
    private Button btn2;

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
        btn2=findViewById(R.id.close_btn);

        //set image
        iv.setImageBitmap(clothes.getImg());


        //make tag list
        String temp = "";

        temp += String.format("#추천 사이즈 : %s\n", calculateSize(clothes));

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

        //close_btn
        btn2.setOnClickListener(
                view -> {finish();}
        );
    }

    private String calculateSize(Clothes clothes){
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if (!clothes.getType().equals("악세서리") && !clothes.getType().equals("신발")) {
            String heights_str = pref.getString("height", "");
            Float height;
            try {
                height = Float.valueOf(heights_str);
            } catch (NumberFormatException e) {
                height = Integer.valueOf(0).floatValue();
            }

            if (height > 180)
                return "XL";
            else if (height > 175)
                return "L";
            else if (height > 165)
                return "M";
            else
                return "S";
        }
        else if (clothes.getType().equals("신발")){
            String footSize_str = pref.getString("footSize", "");
            Float footSize;
            try {
                footSize = Float.valueOf(footSize_str);
            } catch (NumberFormatException e) {
                footSize = Integer.valueOf(0).floatValue();
            }

            if (footSize > 280)
                return "XL";
            else if (footSize > 270)
                return "L";
            else if (footSize > 260)
                return "M";
            else
                return "S";
        }
        else
            return "Free";
    }
}
