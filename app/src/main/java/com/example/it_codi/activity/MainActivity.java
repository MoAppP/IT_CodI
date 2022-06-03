package com.example.it_codi.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.it_codi.R;
import com.example.it_codi.database.Clothes;
import com.example.it_codi.database.ClothesDatabase;
import com.example.it_codi.fragment.HomeFragment;
import com.example.it_codi.fragment.LikeFragment;
import com.example.it_codi.fragment.ListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft;
    Fragment homeFragment = new HomeFragment();
    Fragment listFragment = new ListFragment();
    Fragment likeFragment = new LikeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        ft = fm.beginTransaction();
        ft.add(R.id.frame, homeFragment).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(item ->  {
            ft = fm.beginTransaction();
            switch (item.getItemId()){
                case R.id.home:
                    ft.replace(R.id.frame, homeFragment);
                    break;
                case R.id.list:
                    ft.replace(R.id.frame, listFragment);
                    break;
                case R.id.like:
                    ft.replace(R.id.frame, likeFragment);
                    break;
            }
            ft.commit();
            return true;
        });

    }

    @Override
    protected void onDestroy() {
        //DB.clothesDao().deleteAll();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "setup");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent it;

        switch (item.getItemId()) {
            case 1:
                it = new Intent(this, SetupPopupActivity.class);
                startActivity(it);
                return true;
        }
        return false;
    }

    class MakeRoom extends AsyncTask<Context, Integer, ClothesDatabase> {
        ClothesDatabase db;

        public void MakeRoom(ClothesDatabase db) {
            this.db = db;
        }

        // 백그라운드 작업 실행
        //  UI 작업 불가
        @Override
        protected ClothesDatabase doInBackground(Context... contexts) {
            db = ClothesDatabase.getInstance(getApplicationContext());
            return db;
        }

        // 초기화 코드
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        // Background 작업 중 UI 작업 진행
        // publishProgress() 통해 호출
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//
//        }

        // 결과를 반환.
        @Override
        protected void onPostExecute(ClothesDatabase db) {
            super.onPostExecute(db);
        }

        // 취소 시 발생할 이벤트
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

}