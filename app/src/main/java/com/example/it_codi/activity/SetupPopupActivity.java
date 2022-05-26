package com.example.it_codi.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

import com.example.it_codi.R;

public class SetupPopupActivity extends Activity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    int height, weight;
    String season;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_setuppopup);

        EditText heightInput = findViewById(R.id.height_et);
        EditText weightInput = findViewById(R.id.weight_et);
        RadioButton rBtn_1 = findViewById(R.id.radioBtn_1);
        RadioButton rBtn_2 = findViewById(R.id.radioBtn_2);
        RadioButton rBtn_3 = findViewById(R.id.radioBtn_3);
        RadioButton rBtn_4 = findViewById(R.id.radioBtn_4);
        Button closeBtn = findViewById(R.id.close_btn);

        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        height = pref.getInt("height", 0);
        weight = pref.getInt("weight", 0);
        season = pref.getString("season", "");

        if(height != 0)
            heightInput.setText(Integer.toString(height));
        if(weight != 0)
            weightInput.setText(Integer.toString(weight));

        if(season.equals("봄"))
            rBtn_1.setChecked(true);
        else if(season.equals("여름"))
            rBtn_2.setChecked(true);
        else if(season.equals("가을"))
            rBtn_3.setChecked(true);
        else if(season.equals("겨울"))
            rBtn_4.setChecked(true);


        closeBtn.setOnClickListener(view -> {
            if(!heightInput.getText().toString().equals(""))
                editor.putInt("height", Integer.parseInt(heightInput.getText().toString()));
            else
                return;
            if(!weightInput.getText().toString().equals(""))
                editor.putInt("weight", Integer.parseInt(weightInput.getText().toString()));
            else
                return;

            if(rBtn_1.isChecked())
                editor.putString("season", rBtn_1.getText().toString());
            else if(rBtn_2.isChecked())
                editor.putString("season", rBtn_2.getText().toString());
            else if(rBtn_3.isChecked())
                editor.putString("season", rBtn_3.getText().toString());
            else if(rBtn_4.isChecked())
                editor.putString("season", rBtn_4.getText().toString());
            else
                editor.putString("season", "");

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
