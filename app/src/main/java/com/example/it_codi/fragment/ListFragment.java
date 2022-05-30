package com.example.it_codi.fragment;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.example.it_codi.R;
import com.example.it_codi.adapter.ListRecyclerAdapter;
import com.example.it_codi.database.Clothes;
import com.example.it_codi.database.ClothesDao;
import com.example.it_codi.database.ClothesDatabase;
import com.example.it_codi.database.Converters;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ListFragment extends Fragment {

    RecyclerView recyclerView;
    ListRecyclerAdapter adapter;
    GridLayoutManager layoutManager;

    ArrayList<Clothes> list = new ArrayList<Clothes>();

    ClothesDatabase DB;

    SharedPreferences pref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DB = ClothesDatabase.getInstance(getContext().getApplicationContext());

        pref = this.getActivity().getSharedPreferences("pref", Activity.MODE_PRIVATE);

        if(list.isEmpty()) {
            boolean american = pref.getBoolean("american", false);
            boolean city = pref.getBoolean("city",false);
            boolean dandy = pref.getBoolean("dandy",false);
            boolean casual = pref.getBoolean("casual",false);

            if (!(american || city || dandy || casual))
                list.addAll(DB.clothesDao().findAll());
            else{
                if (american)
                    list.addAll(DB.clothesDao().findByType(getString(R.string.american)));
                if (city)
                    list.addAll(DB.clothesDao().findByType(getString(R.string.city)));
                if (dandy)
                    list.addAll(DB.clothesDao().findByType(getString(R.string.dandy)));
                if (casual)
                    list.addAll(DB.clothesDao().findByType(getString(R.string.casual)));
            }

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view);
        adapter = new ListRecyclerAdapter(list);
        layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        CheckBox cb1 = layout.findViewById(R.id.case1);
        CheckBox cb2 = layout.findViewById(R.id.case2);
        CheckBox cb3 = layout.findViewById(R.id.case3);
        CheckBox cb4 = layout.findViewById(R.id.case4);
        CheckBox cb5 = layout.findViewById(R.id.case5);
        CheckBox ss1 = layout.findViewById(R.id.season1);
        CheckBox ss2 = layout.findViewById(R.id.season2);
        CheckBox ss3 = layout.findViewById(R.id.season3);
        CheckBox ss4 = layout.findViewById(R.id.season4);


        cb1.setOnClickListener(view -> {
            if(cb1.isChecked())
                list.addAll(DB.clothesDao().findByType(cb1.getText().toString()));
            else
                list.removeAll(DB.clothesDao().findByType(cb1.getText().toString()));

            adapter.notifyDataSetChanged();
        });
        cb2.setOnClickListener(view ->{
            if(cb2.isChecked())
                list.addAll(DB.clothesDao().findByType(cb2.getText().toString()));
            else
                list.removeAll(DB.clothesDao().findByType(cb2.getText().toString()));

            adapter.notifyDataSetChanged();
        });
        cb3.setOnClickListener(view -> {
            if(cb3.isChecked())
                list.addAll(DB.clothesDao().findByType(cb3.getText().toString()));
            else
                list.removeAll(DB.clothesDao().findByType(cb3.getText().toString()));

            adapter.notifyDataSetChanged();
        });
        cb4.setOnClickListener(view -> {
            if(cb4.isChecked())
                list.addAll(DB.clothesDao().findByType(cb4.getText().toString()));
            else
                list.removeAll(DB.clothesDao().findByType(cb4.getText().toString()));

            adapter.notifyDataSetChanged();
        });
        cb5.setOnClickListener(view -> {
            if(cb5.isChecked())
                list.addAll(DB.clothesDao().findByType(cb5.getText().toString()));
            else
                list.removeAll(DB.clothesDao().findByType(cb5.getText().toString()));

            adapter.notifyDataSetChanged();
        });
        ss1.setOnClickListener(view -> {
            if(ss1.isChecked())
                list.addAll(DB.clothesDao().findBySpring(ss1.getText().toString()));
            else
                list.removeAll(DB.clothesDao().findBySpring(ss1.getText().toString()));

            adapter.notifyDataSetChanged();
        });
        ss2.setOnClickListener(view -> {
            if(ss2.isChecked())
                list.addAll(DB.clothesDao().findBySummer(ss2.getText().toString()));
            else
                list.removeAll(DB.clothesDao().findBySummer(ss2.getText().toString()));

            adapter.notifyDataSetChanged();
        });
        ss3.setOnClickListener(view -> {
            if(ss3.isChecked())
                list.addAll(DB.clothesDao().findByAutumn(ss3.getText().toString()));
            else
                list.removeAll(DB.clothesDao().findByAutumn(ss3.getText().toString()));

            adapter.notifyDataSetChanged();
        });
        ss4.setOnClickListener(view -> {
            if(ss4.isChecked())
                list.addAll(DB.clothesDao().findByWinter(ss4.getText().toString()));
            else
                list.removeAll(DB.clothesDao().findByWinter(ss4.getText().toString()));

            adapter.notifyDataSetChanged();
        });

        return layout;
    }


}