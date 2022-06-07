package com.example.it_codi.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.example.it_codi.R;
import com.example.it_codi.database.ClothesDatabase;

public class ListLoadingActivity extends Activity {
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listloading);
        activity = ListLoadingActivity.this;
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
