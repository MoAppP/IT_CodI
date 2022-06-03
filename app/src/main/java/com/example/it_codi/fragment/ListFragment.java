package com.example.it_codi.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.it_codi.R;
import com.example.it_codi.adapter.ListRecyclerAdapter;
import com.example.it_codi.database.Clothes;
import com.example.it_codi.database.ClothesDatabase;

import java.util.ArrayList;
import java.util.HashSet;

public class ListFragment extends Fragment {

    RecyclerView recyclerView;
    ListRecyclerAdapter adapter;
    GridLayoutManager layoutManager;

    ArrayList<Clothes> list = new ArrayList<Clothes>();
    ArrayList<Clothes> temp = new ArrayList<Clothes>();
    ArrayList<CheckBox> list_ty = new ArrayList<CheckBox>();
    ArrayList<CheckBox> list_ss1 = new ArrayList<CheckBox>();
    ArrayList<CheckBox> list_ss2 = new ArrayList<CheckBox>();
    ArrayList<CheckBox> list_ss3 = new ArrayList<CheckBox>();
    ArrayList<CheckBox> list_ss4 = new ArrayList<CheckBox>();

    ClothesDatabase DB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DB = ClothesDatabase.getInstance(getContext().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
            if(cb1.isChecked()) {
                list_ty.add(cb1);
            }
            else {
                list_ty.remove(cb1);
            }
            list.clear();
            check_add();
            overlap_clear();
            adapter.notifyDataSetChanged();
        });
        cb2.setOnClickListener(view ->{
            if(cb2.isChecked()) {
                list_ty.add(cb2);
            }
            else {
                list_ty.remove(cb2);
            }
            list.clear();
            check_add();
            overlap_clear();
            adapter.notifyDataSetChanged();
        });
        cb3.setOnClickListener(view -> {
            if(cb3.isChecked()) {
                list_ty.add(cb3);
            }
            else {
                list_ty.remove(cb3);
            }
            list.clear();
            check_add();
            overlap_clear();
            adapter.notifyDataSetChanged();
        });
        cb4.setOnClickListener(view -> {
            if(cb4.isChecked()) {
                list_ty.add(cb4);
            }
            else {
                list_ty.remove(cb4);
            }
            list.clear();
            check_add();
            overlap_clear();
            adapter.notifyDataSetChanged();
        });
        cb5.setOnClickListener(view -> {
            if(cb5.isChecked()) {
                list_ty.add(cb5);
            }
            else {
                list_ty.remove(cb5);
            }
            list.clear();
            check_add();
            overlap_clear();
            adapter.notifyDataSetChanged();
        });
        ss1.setOnClickListener(view -> {
            if(ss1.isChecked()) {
                list_ss1.add(ss1);
            }
            else {
                list_ss1.remove(ss1);
            }
            list.clear();
            check_add();
            overlap_clear();
            adapter.notifyDataSetChanged();
        });
        ss2.setOnClickListener(view -> {
            if(ss2.isChecked()) {
                list_ss2.add(ss2);
            }
            else {
                list_ss2.remove(ss2);
            }
            list.clear();
            check_add();
            overlap_clear();
            adapter.notifyDataSetChanged();
        });
        ss3.setOnClickListener(view -> {
            if(ss3.isChecked()) {
                list_ss3.add(ss3);
            }
            else {
                list_ss3.remove(ss3);
            }
            list.clear();
            check_add();
            overlap_clear();
            adapter.notifyDataSetChanged();
        });
        ss4.setOnClickListener(view -> {
            if(ss4.isChecked()) {
                list_ss4.add(ss4);
            }
            else {
                list_ss4.remove(ss4);
            }
            list.clear();
            check_add();
            overlap_clear();
            adapter.notifyDataSetChanged();
        });

        return layout;
    }

    @Override
    public void onResume(){
        super.onResume();
        list.clear();
        check_add();
        overlap_clear();
        adapter.notifyDataSetChanged();
    }

    private void check_add() {
        SharedPreferences pref = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        boolean st1 = pref.getBoolean("american", false);
        boolean st2 = pref.getBoolean("city", false);
        boolean st3 = pref.getBoolean("dandy", false);
        boolean st4 = pref.getBoolean("casual", false);
        boolean typ = list_ty.isEmpty();
        boolean spr = list_ss1.isEmpty();
        boolean sum = list_ss2.isEmpty();
        boolean aut = list_ss3.isEmpty();
        boolean win = list_ss4.isEmpty();
        boolean sea = !spr || !sum || !aut || !win;
        if(!typ && sea){
            temp.clear();
            for(CheckBox it : list_ty) { list.addAll(DB.clothesDao().findByType(it.getText().toString())); }
            for(Clothes it : list) {
                boolean s1 = it.getSpring().equals("");
                boolean s2 = it.getSummer().equals("");
                boolean s3 = it.getAutumn().equals("");
                boolean s4 = it.getWinter().equals("");
                if((!spr && !s1) || (!sum && !s2) || (!aut && !s3) || (!win && !s4))
                    temp.add(it);
            }
            list.clear();
            list.addAll(temp);
        }
        else if(!typ) {
            for(CheckBox it : list_ty){ list.addAll(DB.clothesDao().findByType(it.getText().toString())); }
        }
        else if(sea) {
            if(!spr)
                for(CheckBox it : list_ss1){ list.addAll(DB.clothesDao().findBySpring(it.getText().toString())); }
            if(!sum)
                for(CheckBox it : list_ss2){ list.addAll(DB.clothesDao().findBySummer(it.getText().toString())); }
            if(!aut)
                for(CheckBox it : list_ss3){ list.addAll(DB.clothesDao().findByAutumn(it.getText().toString())); }
            if(!win)
                for(CheckBox it : list_ss4){ list.addAll(DB.clothesDao().findByWinter(it.getText().toString())); }
        }
        if(st1 || st2 || st3 || st4) {
            temp.clear();
            for (Clothes it : list) {
                String style = it.getStyle();
                boolean ame = style.equals(getString(R.string.american));
                boolean cit = style.equals(getString(R.string.city));
                boolean dan = style.equals(getString(R.string.dandy));
                boolean cas = style.equals(getString(R.string.casual));
                if ((ame && st1) || (cit && st2) || (dan && st3) || (cas && st4))
                    temp.add(it);
            }
            list.clear();
            list.addAll(temp);
        }
    }

    private void overlap_clear() {
        HashSet<Clothes> set = new HashSet<>();
        for(Clothes item : list){ set.add(item); }
        list.clear();
        list.addAll(set);
    }
}