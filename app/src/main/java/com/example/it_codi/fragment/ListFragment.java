package com.example.it_codi.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.it_codi.R;
import com.example.it_codi.adapter.ListRecyclerAdapter;
import com.example.it_codi.database.Clothes;
import com.example.it_codi.database.ClothesDao;
import com.example.it_codi.database.ClothesDatabase;
import com.example.it_codi.database.Converters;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ListFragment extends Fragment {

    RecyclerView recyclerView;
    ListRecyclerAdapter adapter;
    GridLayoutManager layoutManager;

    ArrayList<Clothes> list = new ArrayList<Clothes>();

    ClothesDatabase DB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DB = ClothesDatabase.getInstance(getContext().getApplicationContext());

        if(list.isEmpty())
            list.addAll(DB.clothesDao().findAll());
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

        return layout;
    }
}