package com.example.it_codi.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.it_codi.R;

public class SetupPopupActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_setuppopup);

        Button closeBtn = findViewById(R.id.close_btn);

        closeBtn.setOnClickListener(view -> {
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
