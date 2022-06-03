package com.example.it_codi.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.it_codi.R;
import com.example.it_codi.adapter.ListRecyclerAdapter;
import com.example.it_codi.database.Clothes;
import com.example.it_codi.database.ClothesDatabase;

import java.util.ArrayList;

public class LikeFragment extends Fragment {

    RecyclerView recyclerView;
    ListRecyclerAdapter adapter;
    GridLayoutManager layoutManager;

    ArrayList<Clothes> list = new ArrayList<Clothes>();

    ClothesDatabase DB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DB = ClothesDatabase.getInstance(getContext().getApplicationContext());

        if (list.isEmpty())
            list.addAll(DB.clothesDao().findByLiked(true));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_like, container, false);
        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) layout.findViewById(R.id.like_recycler_view);
        adapter = new ListRecyclerAdapter(list);
        layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        resetList();
        adapter.notifyDataSetChanged();
    }

    private void resetList(){
        list.clear();
        list.addAll(DB.clothesDao().findByLiked(true));
    }
}