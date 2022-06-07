package com.example.it_codi.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
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

    ArrayList<Clothes> allList = new ArrayList<Clothes>();
    ArrayList<Clothes> list = new ArrayList<Clothes>();

    ClothesDatabase DB;

    boolean isLoading = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DB = ClothesDatabase.getInstance(getContext().getApplicationContext());

        if (allList.isEmpty()) {
            allList.addAll(DB.clothesDao().findByLiked(true));
            firstData();
        }
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

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    Log.d("test", "?");
                    Log.d("test", Boolean.toString(isLoading));
                    if (!isLoading){
//                        isLoading = true;
                        dataMore();
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        resetList();
        adapter.notifyDataSetChanged();
    }

    private void resetList(){
        allList.clear();
        allList.addAll(DB.clothesDao().findByLiked(true));
    }

    private void firstData() {
        // 총 아이템에서 6개를 받아옴
        list.clear();
        for (int i=0; i < 6 && i < allList.size(); i++) {
            list.add(allList.get(i));
        }
    }

    private void dataMore() {
        list.add(null);
        adapter.notifyItemInserted(list.size() -1 );

        isLoading = true;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.remove(list.size() -1 );
                int scrollPosition = list.size();
                adapter.notifyItemRemoved(scrollPosition);
                int currentSize = scrollPosition;
                int nextLimit = currentSize + 4;

                for (int i=currentSize; i<nextLimit; i++) {
                    if (i >= allList.size()) {
                        break;
                    }
                    list.add(allList.get(i));
                    Log.d("test", Integer.valueOf(i).toString()+"dataMore");
                }

                adapter.notifyDataSetChanged();
                isLoading = false;
                Log.d("test", Integer.valueOf(list.size()).toString()+"afterDataMore");
            }
        }, 2000);

    }

}