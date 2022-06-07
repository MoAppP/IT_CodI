package com.example.it_codi.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.it_codi.R;
import com.example.it_codi.activity.ListLoadingActivity;
import com.example.it_codi.activity.MainActivity;
import com.example.it_codi.adapter.ListRecyclerAdapter;
import com.example.it_codi.database.Clothes;
import com.example.it_codi.database.ClothesDatabase;

import java.util.ArrayList;
import java.util.HashSet;

public class ListFragment extends Fragment {

    RecyclerView recyclerView;
    ListRecyclerAdapter adapter;
    GridLayoutManager layoutManager;

    ArrayList<Clothes> allList = new ArrayList<Clothes>();
    ArrayList<Clothes> list = new ArrayList<Clothes>();
    ArrayList<Clothes> temp = new ArrayList<Clothes>();
    ArrayList<CheckBox> list_ty = new ArrayList<CheckBox>();
    ArrayList<CheckBox> list_ss1 = new ArrayList<CheckBox>();
    ArrayList<CheckBox> list_ss2 = new ArrayList<CheckBox>();
    ArrayList<CheckBox> list_ss3 = new ArrayList<CheckBox>();
    ArrayList<CheckBox> list_ss4 = new ArrayList<CheckBox>();

    ClothesDatabase DB;

    boolean isLoading = false;

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
        adapter.setHasStableIds(true);
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
            new CheckAddBackGround().execute(0);
        });
        cb2.setOnClickListener(view ->{
            if(cb2.isChecked()) {
                list_ty.add(cb2);
            }
            else {
                list_ty.remove(cb2);
            }
            new CheckAddBackGround().execute(0);
        });
        cb3.setOnClickListener(view -> {
            if(cb3.isChecked()) {
                list_ty.add(cb3);
            }
            else {
                list_ty.remove(cb3);
            }
            new CheckAddBackGround().execute(0);
        });
        cb4.setOnClickListener(view -> {
            if(cb4.isChecked()) {
                list_ty.add(cb4);
            }
            else {
                list_ty.remove(cb4);
            }
            new CheckAddBackGround().execute(0);
        });
        cb5.setOnClickListener(view -> {
            if(cb5.isChecked()) {
                list_ty.add(cb5);
            }
            else {
                list_ty.remove(cb5);
            }
            new CheckAddBackGround().execute(0);
        });
        ss1.setOnClickListener(view -> {
            if(ss1.isChecked()) {
                list_ss1.add(ss1);
            }
            else {
                list_ss1.remove(ss1);
            }
            new CheckAddBackGround().execute(0);
        });
        ss2.setOnClickListener(view -> {
            if(ss2.isChecked()) {
                list_ss2.add(ss2);
            }
            else {
                list_ss2.remove(ss2);
            }
            new CheckAddBackGround().execute(0);
        });
        ss3.setOnClickListener(view -> {
            if(ss3.isChecked()) {
                list_ss3.add(ss3);
            }
            else {
                list_ss3.remove(ss3);
            }
            new CheckAddBackGround().execute(0);
        });
        ss4.setOnClickListener(view -> {
            if(ss4.isChecked()) {
                list_ss4.add(ss4);
            }
            else {
                list_ss4.remove(ss4);
            }
            new CheckAddBackGround().execute(0);
        });

        return layout;
    }

    @Override
    public void onResume(){
        super.onResume();
        check_add();
        adapter.notifyDataSetChanged();
    }

    private void check_add() {
        allList.clear();

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
            for(CheckBox it : list_ty) { allList.addAll(DB.clothesDao().findByType(it.getText().toString())); }
            for(Clothes it : allList) {
                boolean s1 = it.getSpring().equals("");
                boolean s2 = it.getSummer().equals("");
                boolean s3 = it.getAutumn().equals("");
                boolean s4 = it.getWinter().equals("");
                if((!spr && !s1) || (!sum && !s2) || (!aut && !s3) || (!win && !s4))
                    temp.add(it);
            }
            allList.clear();
            allList.addAll(temp);
        }
        else if(!typ) {
            for(CheckBox it : list_ty){ allList.addAll(DB.clothesDao().findByType(it.getText().toString())); }
        }
        else if(sea) {
            if(!spr)
                for(CheckBox it : list_ss1){ allList.addAll(DB.clothesDao().findBySpring(it.getText().toString())); }
            if(!sum)
                for(CheckBox it : list_ss2){ allList.addAll(DB.clothesDao().findBySummer(it.getText().toString())); }
            if(!aut)
                for(CheckBox it : list_ss3){ allList.addAll(DB.clothesDao().findByAutumn(it.getText().toString())); }
            if(!win)
                for(CheckBox it : list_ss4){ allList.addAll(DB.clothesDao().findByWinter(it.getText().toString())); }
        }
        if(st1 || st2 || st3 || st4) {
            temp.clear();
            for (Clothes it : allList) {
                String style = it.getStyle();
                boolean ame = style.equals(getString(R.string.american));
                boolean cit = style.equals(getString(R.string.city));
                boolean dan = style.equals(getString(R.string.dandy));
                boolean cas = style.equals(getString(R.string.casual));
                if ((ame && st1) || (cit && st2) || (dan && st3) || (cas && st4))
                    temp.add(it);
            }
            allList.clear();
            allList.addAll(temp);
        }

        overlap_clear();
    }

    private void overlap_clear() {
        HashSet<Clothes> set = new HashSet<>();
        for(Clothes item : allList){ set.add(item); }
        allList.clear();
        allList.addAll(set);
    }

    private void firstData() {
        // 총 아이템에서 6개를 받아옴
        list.clear();
        for (int i=0; i < 4 && i < allList.size(); i++) {
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

    //새로운 TASK정의 (AsyncTask)
// < >안에 들은 자료형은 순서대로 doInBackground, onProgressUpdate, onPostExecute의 매개변수 자료형을 뜻한다.(내가 사용할 매개변수타입을 설정하면된다)
    class CheckAddBackGround extends AsyncTask<Integer , Integer , Integer> {
        //초기화 단계에서 사용한다. 초기화관련 코드를 작성했다.
        protected void onPreExecute() {
            Intent it = new Intent(getActivity(), ListLoadingActivity.class);
            startActivity(it);
        }

        //스레드의 백그라운드 작업 구현
//여기서 매개변수 Intger ... values란 values란 이름의 Integer배열이라 생각하면된다.
//배열이라 여러개를 받을 수 도 있다. ex) excute(100, 10, 20, 30); 이런식으로 전달 받으면 된다.
        protected Integer doInBackground(Integer ... values) {
            check_add();
            firstData();
            Log.d("test", Integer.valueOf(allList.size()).toString()+"allList size");
            return values[0];
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
//            adapter.notifyDataSetChanged();
        }

        //이 Task에서(즉 이 스레드에서) 수행되던 작업이 종료되었을 때 호출됨
        protected void onPostExecute(Integer result) {
            adapter.notifyDataSetChanged();
            ListLoadingActivity.activity.finish();
        }
    }
}