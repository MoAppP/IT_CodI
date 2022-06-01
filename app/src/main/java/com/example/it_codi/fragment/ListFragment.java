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

    }

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

        resetList(layout);
        adapter.notifyDataSetChanged();

        cb1.setOnClickListener(view -> {
            resetList(layout);

            adapter.notifyDataSetChanged();
        });
        cb2.setOnClickListener(view ->{
            resetList(layout);

            adapter.notifyDataSetChanged();
        });
        cb3.setOnClickListener(view -> {
            resetList(layout);

            adapter.notifyDataSetChanged();
        });
        cb4.setOnClickListener(view -> {
            resetList(layout);

            adapter.notifyDataSetChanged();
        });
        cb5.setOnClickListener(view -> {
            resetList(layout);

            adapter.notifyDataSetChanged();
        });
        ss1.setOnClickListener(view -> {
            resetList(layout);

            adapter.notifyDataSetChanged();
        });
        ss2.setOnClickListener(view -> {
            resetList(layout);

            adapter.notifyDataSetChanged();
        });
        ss3.setOnClickListener(view -> {
            resetList(layout);

            adapter.notifyDataSetChanged();
        });
        ss4.setOnClickListener(view -> {
            resetList(layout);

            adapter.notifyDataSetChanged();
        });

        return layout;
    }


    private void resetList(View layout){
        boolean american = pref.getBoolean("american", false);
        boolean city = pref.getBoolean("city",false);
        boolean dandy = pref.getBoolean("dandy",false);
        boolean casual = pref.getBoolean("casual",false);

        CheckBox cb1 = layout.findViewById(R.id.case1);
        CheckBox cb2 = layout.findViewById(R.id.case2);
        CheckBox cb3 = layout.findViewById(R.id.case3);
        CheckBox cb4 = layout.findViewById(R.id.case4);
        CheckBox cb5 = layout.findViewById(R.id.case5);

        CheckBox ss1 = layout.findViewById(R.id.season1);
        CheckBox ss2 = layout.findViewById(R.id.season2);
        CheckBox ss3 = layout.findViewById(R.id.season3);
        CheckBox ss4 = layout.findViewById(R.id.season4);

        ArrayList<String> styles = new ArrayList<>();
        if (!(american || city || dandy || casual)){
            styles.add(getString(R.string.american));
            styles.add(getString(R.string.city));
            styles.add(getString(R.string.dandy));
            styles.add(getString(R.string.casual));
        }
        else {
            if (american)
                styles.add(getString(R.string.american));
            if (city)
                styles.add(getString(R.string.city));
            if (dandy)
                styles.add(getString(R.string.dandy));
            if (casual)
                styles.add(getString(R.string.casual));
        }

        ArrayList<String> types = new ArrayList<>();
        if (cb1.isChecked())
            types.add(cb1.getText().toString());
        if (cb2.isChecked())
            types.add(cb2.getText().toString());
        if (cb3.isChecked())
            types.add(cb3.getText().toString());
        if (cb4.isChecked())
            types.add(cb4.getText().toString());
        if (cb5.isChecked())
            types.add(cb5.getText().toString());

        ArrayList<String> seasons = new ArrayList<>();
        if (ss1.isChecked())
            seasons.add(ss1.getText().toString());
        if (ss2.isChecked())
            seasons.add(ss2.getText().toString());
        if (ss3.isChecked())
            seasons.add(ss3.getText().toString());
        if (ss4.isChecked())
            seasons.add(ss4.getText().toString());

        LinkedHashSet<Clothes> set = new LinkedHashSet<>();
        for (String style : styles){
            for (String type : types){
                for (String season : seasons){
                    if (season.equals(ss1.getText().toString()))
                        set.addAll(DB.clothesDao().findByTypeSpringStyle(type, season, style));
                    if (season.equals(ss2.getText().toString()))
                        set.addAll(DB.clothesDao().findByTypeSummerStyle(type, season, style));
                    if (season.equals(ss3.getText().toString()))
                        set.addAll(DB.clothesDao().findByTypeAutumnStyle(type, season, style));
                    if (season.equals(ss4.getText().toString()))
                        set.addAll(DB.clothesDao().findByTypeWinterStyle(type, season, style));
                }
            }
        }

        list.clear();
        list.addAll(set);
    }


}