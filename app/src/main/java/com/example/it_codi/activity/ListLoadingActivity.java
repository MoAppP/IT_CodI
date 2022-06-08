package com.example.it_codi.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.example.it_codi.R;
import com.example.it_codi.database.ClothesDatabase;

public class ListLoadingActivity extends Activity {
//    private static ListLoadingActivity instance = null;
    public static Activity activity;

//    public static ListLoadingActivity getInstance(){
//        if (instance == null){
//            instance = new ListLoadingActivity();
//        }
//        return instance;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listloading);
        activity = ListLoadingActivity.this;
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        onDestroy();
//    }

//    @Override
//    public void onBackPressed() {
//        return;
//    }
}
