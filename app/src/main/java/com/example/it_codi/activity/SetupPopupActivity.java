package com.example.it_codi.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.it_codi.R;

public class SetupPopupActivity extends Activity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    float height, weight;
    boolean american, city, dandy, casual;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_setuppopup);

        EditText heightInput = findViewById(R.id.height_et);
        EditText weightInput = findViewById(R.id.weight_et);
        CheckBox cBtn_1 = findViewById(R.id.checkbtn_1);
        CheckBox cBtn_2 = findViewById(R.id.checkbtn_2);
        CheckBox cBtn_3 = findViewById(R.id.checkbtn_3);
        CheckBox cBtn_4 = findViewById(R.id.checkbtn_4);
        Button closeBtn = findViewById(R.id.close_btn);

        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        height = pref.getFloat("height", 0);
        weight = pref.getFloat("weight", 0);

        american = pref.getBoolean("american", false);
        city = pref.getBoolean("city", false);
        dandy = pref.getBoolean("dandy", false);
        casual = pref.getBoolean("casual", false);


        if(height != 0)
            heightInput.setText(Float.toString(height));
        else
            heightInput.setText("");
        if(weight != 0)
            weightInput.setText(Float.toString(weight));
        else
            weightInput.setText("");



        cBtn_1.setChecked(american);
        cBtn_1.setChecked(city);
        cBtn_1.setChecked(dandy);
        cBtn_1.setChecked(casual);


        closeBtn.setOnClickListener(view -> {
            if(!heightInput.getText().toString().equals(""))
                editor.putFloat("height", Float.parseFloat(heightInput.getText().toString()));
            else
                editor.putFloat("height", 0);
            if(!weightInput.getText().toString().equals(""))
                editor.putFloat("weight", Float.parseFloat(weightInput.getText().toString()));
            else
                editor.putFloat("weight", 0);

            editor.putBoolean("american", cBtn_1.isChecked());
            editor.putBoolean("city", cBtn_2.isChecked());
            editor.putBoolean("dandy", cBtn_3.isChecked());
            editor.putBoolean("casual", cBtn_4.isChecked());

            editor.apply();
            setupOnClose(view);
        });
    }

    public void setupOnClose(View v){
        finish();
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
