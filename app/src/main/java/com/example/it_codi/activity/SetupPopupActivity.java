package com.example.it_codi.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
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

    String height, weight, footSize;
    boolean american, city, dandy, casual;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_setuppopup);

        EditText heightInput = findViewById(R.id.height_et);
        EditText weightInput = findViewById(R.id.weight_et);
        EditText footSizeInput = findViewById(R.id.footSize_et);
        CheckBox cBtn_1 = findViewById(R.id.checkbtn_1);
        CheckBox cBtn_2 = findViewById(R.id.checkbtn_2);
        CheckBox cBtn_3 = findViewById(R.id.checkbtn_3);
        CheckBox cBtn_4 = findViewById(R.id.checkbtn_4);
        Button closeBtn = findViewById(R.id.close_btn);

        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        height = pref.getString("height", "");
        weight = pref.getString("weight", "");
        footSize = pref.getString("footSize", "");
        american = pref.getBoolean("american", false);
        city = pref.getBoolean("city", false);
        dandy = pref.getBoolean("dandy", false);
        casual = pref.getBoolean("casual", false);

        if(!height.equals(""))
            heightInput.setText(height);
        if(!weight.equals(""))
            weightInput.setText(weight);
        if(!footSize.equals(""))
            footSizeInput.setText(footSize);

        cBtn_1.setChecked(american);
        cBtn_2.setChecked(city);
        cBtn_3.setChecked(dandy);
        cBtn_4.setChecked(casual);

        closeBtn.setOnClickListener(view -> {
            String heights = heightInput.getText().toString();
            String weights = weightInput.getText().toString();
            String footSize = footSizeInput.getText().toString();
            try {
                Float.valueOf(heights);
            } catch (NumberFormatException e){
                return;
            }
            try {
                Float.valueOf(weights);
            } catch (NumberFormatException e){
                return;
            }
            try {
                Float.valueOf(footSize);
            } catch (NumberFormatException e){
                return;
            }

            editor.putString("height", heights);
            editor.putString("weight", weights);
            editor.putString("footSize", footSize);

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