package com.example.it_codi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.it_codi.R;
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

}